package ua.ilya.s.statistics_api.service.statistics;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.ilya.s.statistics_api.model.statistics.ReportSpecification;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByAsin;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByDate;
import ua.ilya.s.statistics_api.repository.statistics.ReportSpecificationRepository;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByAsinRepository;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByDateRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataLoaderService {

    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @PostConstruct
    public void loadData() {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Инициализация загрузки данных из test_report.json");

        try (InputStream inputStream = getClass().getResourceAsStream("/test_report.json")) {
            if (inputStream == null) {
                log.error("Файл test_report.json не найден. Проверьте его наличие в resources.");
                throw new IllegalArgumentException("Файл test_report.json не найден.");
            }

            log.info("Файл test_report.json успешно найден, начинается загрузка данных...");

            JsonNode rootNode = objectMapper.readTree(inputStream);

            JsonNode reportSpecNode = rootNode.get("reportSpecification");
            if (reportSpecNode != null) {
                ReportSpecification reportSpecification = objectMapper.convertValue(
                        reportSpecNode,
                        ReportSpecification.class);
                reportSpecificationRepository.save(reportSpecification);
            }

            JsonNode salesByDateNode = rootNode.get("salesAndTrafficByDate");
            if (salesByDateNode != null) {
                List<SalesAndTrafficByDate> salesByDateList = objectMapper.convertValue(
                        salesByDateNode,
                        new TypeReference<>() {
                        });
                salesAndTrafficByDateRepository.saveAll(salesByDateList);
            }

            JsonNode salesByAsinNode = rootNode.get("salesAndTrafficByAsin");
            if (salesByAsinNode != null) {
                List<SalesAndTrafficByAsin> salesByAsinList = objectMapper.convertValue(
                        salesByAsinNode,
                        new TypeReference<>() {
                        });
                salesAndTrafficByAsinRepository.saveAll(salesByAsinList);
            }

            System.out.println("Данные из test_report.json успешно загружены в БД.");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки данных из test_report.json", e);
        }
    }
}

