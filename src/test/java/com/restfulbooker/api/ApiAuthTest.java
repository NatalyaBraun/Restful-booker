package com.restfulbooker.api;

import com.restfulbooker.api.api.AuthApi;
import com.restfulbooker.api.config.Config;
import com.restfulbooker.api.payloads.Auth;
import io.restassured.response.Response;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiAuthTest {

    @Test
    public void authApiShouldReturnToken() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        Auth auth = new Auth.Builder()
                .setUsername(username)
                .setPassword(password)
                .build();
        Response response = AuthApi.postAuth(auth);
        Approvals.verify(response.getStatusCode());
        String actualToken = response.getBody().jsonPath().get("token").toString();
        Assertions.assertFalse(actualToken.isEmpty());
    }

    @Test
    public void authApiShouldReturn400ForInvalidPassword() {
        String username = Config.getProperty("username");
        String password = "invalid-password";

        Auth auth = new Auth.Builder()
                .setUsername(username)
                .setPassword(password)
                .build();
        Response response = AuthApi.postAuth(auth);
        String reasonInResponse = response.getBody().jsonPath().get("reason").toString();
        Assertions.assertEquals("Bad credentials", reasonInResponse);
    }
}
