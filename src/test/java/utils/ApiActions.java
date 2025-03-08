package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class ApiActions {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static String jsonResponse;
    private static int actualStatusCode;

    public static Response sendRequest(Method httpMethod, String endpoint, Object requestBody) {
        LoggerUtil.info("Sending " + httpMethod + " request to " + endpoint);
        RequestSpecification requestSpecification = RestAssuredUtil.getRequestSpecification();

        if (requestBody != null) {
            requestSpecification.body(requestBody).log().all();
        }

        Response response = switch (httpMethod) {
            case POST -> requestSpecification.when().post(endpoint);
            case GET -> requestSpecification.when().get(endpoint);
            case PUT -> requestSpecification.when().put(endpoint);
            case DELETE -> requestSpecification.when().delete(endpoint);
            case PATCH -> requestSpecification.when().patch(endpoint);
            default -> throw new IllegalArgumentException("HTTP method not supported");
        };

        jsonResponse = response.asString();
        actualStatusCode = response.getStatusCode();
        return response;
    }

    public static String getResponseValue(String key) {
        LoggerUtil.info("Trying To Get THE Value of Key: " + key);
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse).get(key);
            if (jsonNode != null) {
                LoggerUtil.info("Value of Key: " + key + " is: " + jsonNode.asText());
                return jsonNode.asText();
            } else {
                throw new RuntimeException("Key '" + key + "' not found in the JSON response");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    public static void assertStatusCode(int expectedStatusCode) {
        LoggerUtil.info("Trying to assert the status code");
        LoggerUtil.info("Actual Status Code: " + actualStatusCode);
        Assert.assertEquals(actualStatusCode, expectedStatusCode, "Status code does not match!");
    }
}