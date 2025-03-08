package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.UserServices;
import utils.ApiActions;
import config.Config;
import utils.LoggerUtil;

public class TestUserCRUD {

    String userId;
    @Test
    public void testCreateUser() {
        LoggerUtil.info("Starting Creating a new user");
        // Send POST request to /users endpoint using createUser method
        Response response = UserServices.createUser(Config.TEST_USER_NAME, Config.TEST_USER_JOB);

        // Log the response
        LoggerUtil.info("Response: " + response.asString());

        // Assert the status code is 201 (Created)
        ApiActions.assertStatusCode(201);

        // Validate the response body
        Assert.assertEquals(ApiActions.getResponseValue("name"), Config.TEST_USER_NAME);
        LoggerUtil.pass("User name is validated successfully");
        Assert.assertEquals(ApiActions.getResponseValue("job"), Config.TEST_USER_JOB);
        LoggerUtil.pass("User job is validated successfully");
        userId = ApiActions.getResponseValue("id");
        LoggerUtil.info("User ID: " + userId);
    }
    @Test
    public void testGetUserInfoByID() {
        LoggerUtil.info("Starting Getting user info by ID");
        // Send GET request to /users/{userId} endpoint using getUserInfoByID method
        Response response = UserServices.getUserInfoByID(userId);

        // Log the response
        LoggerUtil.info("Response: " + response.asString());

        // Assert the status code is 200 (OK)
        ApiActions.assertStatusCode(200);

        // Validate the response body
        Assert.assertEquals(ApiActions.getResponseValue("name"), Config.TEST_USER_NAME);
        LoggerUtil.pass("User name is validated successfully");
        Assert.assertEquals(ApiActions.getResponseValue("job"), Config.TEST_USER_JOB);
        LoggerUtil.pass("User job is validated successfully");
        Assert.assertEquals(ApiActions.getResponseValue("id"), userId);
        LoggerUtil.pass("user ID is validated successfully");
    }

    @Test
    public void testUpdateUserInfo() {
        LoggerUtil.info("Starting Updating user info By ID");
        String updatedUsername = Config.TEST_USER_NAME + "Updated";
        String updatedJob = Config.TEST_USER_JOB + "Updated";

        // Send PUT request to /users/{userId} endpoint using updateUserInfo method
        Response response = UserServices.updateUserInfo(updatedUsername, updatedJob, userId);

        // Log the response
        LoggerUtil.info("Response: " + response.asString());

        // Assert the status code is 200 (OK)
        ApiActions.assertStatusCode(200);

        // Validate the response body
        Assert.assertEquals(ApiActions.getResponseValue("name"), updatedUsername);
        LoggerUtil.pass("User name is Updated successfully");
        Assert.assertEquals(ApiActions.getResponseValue("job"), updatedJob);
        LoggerUtil.pass("User job is Updated successfully");
    }


}