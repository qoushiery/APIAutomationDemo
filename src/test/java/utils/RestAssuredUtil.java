package utils;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class RestAssuredUtil {
    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given().baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .filter((requestSpec, responseSpec, ctx) -> {
                    String request = requestSpec.getMethod() + " " + requestSpec.getURI();
                    String requestBody = requestSpec.getBody() != null ? requestSpec.getBody().toString() : "";
                    Response response = ctx.next(requestSpec, responseSpec);
                    LoggerUtil.logRequestResponse(request, requestBody, response.asString());
                    return response;
                });
    }
}