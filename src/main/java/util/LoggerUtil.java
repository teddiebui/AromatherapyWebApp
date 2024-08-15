package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    // Private constructor to prevent instantiation
    private LoggerUtil() {
    }

    // Method to get a logger instance for a given class
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void info(Logger logger, String message) {
        logger.info(message);
    }

    public static void warn(Logger logger, String message) {
        logger.warn(message);
    }

    public static void error(Logger logger, String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
