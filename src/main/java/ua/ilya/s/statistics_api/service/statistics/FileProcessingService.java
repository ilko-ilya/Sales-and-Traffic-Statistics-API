package ua.ilya.s.statistics_api.service.statistics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.ilya.s.statistics_api.exception.FileProcessingException;
import org.springframework.beans.factory.annotation.Value;
import ua.ilya.s.statistics_api.model.statistics.ReportSpecification;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByAsin;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByDate;
import ua.ilya.s.statistics_api.repository.statistics.ReportSpecificationRepository;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByAsinRepository;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByDateRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileProcessingService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper;
    private final ReportSpecificationRepository reportSpecificationRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;

    @Value("${file.path}")
    private String jsonFilePath;

    public String readJsonFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        } catch (IOException e) {
            LOGGER.error("Error reading JSON file", e);
            throw new FileProcessingException("Could not read JSON file");
        }
    }

    public void processJsonData(String jsonData) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonData);

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

            LOGGER.info("JSON data processed and saved successfully");

        } catch (JsonProcessingException e) {
            LOGGER.error("Error parsing JSON data", e);
            throw new FileProcessingException("Could not parse JSON data");
        }
    }
}
