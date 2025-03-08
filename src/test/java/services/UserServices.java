package services;

import config.Endpoints;
import models.User;
import io.restassured.http.Method;
import io.restassured.response.Response;
import utils.ApiActions;
import utils.LoggerUtil;

public class UserServices {
        public static Response createUser(String name, String job) {
            User user = new User(name, job);
            LoggerUtil.info("Creating user with name: " + name + " and job: " + job);
            return ApiActions.sendRequest(Method.POST, Endpoints.USERS.getEndpoint(), user);
        }

    public static Response getUserInfoByID(String userId) {
            LoggerUtil.info("Getting user info by ID: " + userId);
        return ApiActions.sendRequest(Method.GET, Endpoints.USERS.getEndpoint() + userId, null);
    }

    public static Response updateUserInfo(String updatedUsername, String updatedJob, String userId) {
            LoggerUtil.info("Updating user info with ID: " + userId);
            User user = new User(updatedUsername, updatedJob);
            LoggerUtil.info("Updating user with name: " + updatedUsername + " and job: " + updatedJob);
            return ApiActions.sendRequest(Method.PUT, Endpoints.USERS.getEndpoint() + userId, user);
    }
}

