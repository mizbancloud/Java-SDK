package com.mizbancloud.sdk.modules;

import com.google.gson.JsonObject;
import com.mizbancloud.sdk.HttpClient;
import com.mizbancloud.sdk.exceptions.MizbanCloudException;

import java.io.IOException;

/**
 * Authentication and wallet operations.
 */
public class Auth {
    private final HttpClient http;

    public Auth(HttpClient http) {
        this.http = http;
    }

    /**
     * Set the API token.
     */
    public void setApiToken(String token) {
        http.getConfig().setToken(token);
    }

    /**
     * Get the current API token.
     */
    public String getApiToken() {
        return http.getConfig().getToken();
    }

    /**
     * Clear the API token.
     */
    public void clearApiToken() {
        http.getConfig().setToken(null);
    }

    /**
     * Get wallet balance and information.
     */
    public JsonObject getWallet() throws MizbanCloudException, IOException {
        return http.get("/api/v1/wallet");
    }
}
