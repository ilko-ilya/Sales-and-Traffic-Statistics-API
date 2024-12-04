package ua.ilya.s.statistics_api.exception;

public class StatisticsNotFoundException extends RuntimeException {
    public StatisticsNotFoundException(String message) {
        super(message);
    }
}
