package com.mizbancloud.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration for MizbanCloud client.
 */
public class MizbanCloudConfig {
    private String baseUrl = "https://auth.mizbancloud.com";
    private int timeout = 30;
    private String language = "en";
    private Map<String, String> headers = new HashMap<>();
    private String token;

    public MizbanCloudConfig() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public MizbanCloudConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public int getTimeout() {
        return timeout;
    }

    public MizbanCloudConfig setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public MizbanCloudConfig setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public MizbanCloudConfig setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public MizbanCloudConfig addHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public String getToken() {
        return token;
    }

    public MizbanCloudConfig setToken(String token) {
        this.token = token;
        return this;
    }
}
