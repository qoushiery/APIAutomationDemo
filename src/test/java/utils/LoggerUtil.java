package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class LoggerUtil {
    private static final String LOG_FILE_PATH = "log.json";
    private static JSONArray logArray = new JSONArray();
    private static final Logger log= LoggerFactory.getLogger(LoggerUtil.class);
    public static void logRequestResponse(String request,String requestBody, String response) {
        JSONObject logEntry = new JSONObject();
        logEntry.put("request", request);
        logEntry.put("requestBody", requestBody);
        logEntry.put("response", response);
        logArray.put(logEntry);
        writeLogToFile();
    }

    private static void writeLogToFile() {
        try (FileWriter file = new FileWriter(LOG_FILE_PATH)) {
            file.write(logArray.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String message) {
        log.info(message);
    }

    public static void info(String message, Object... args) {
        log.info(message, args);
    }

    public static void error(String message) {
        log.error(message);
    }

    public static void error(String message, Object... args) {
        log.error(message, args);
    }

    public static void pass(String message) {
        log.info("PASS: " + message);
    }
}
