package com.mizbancloud.sdk.modules;

import com.google.gson.JsonObject;
import com.mizbancloud.sdk.HttpClient;
import com.mizbancloud.sdk.exceptions.MizbanCloudException;

import java.io.IOException;

/**
 * Static catalog data for server creation.
 */
public class Statics {
    private final HttpClient http;

    public Statics(HttpClient http) {
        this.http = http;
    }

    /**
     * List available datacenters.
     */
    public JsonObject listDatacenters() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/catalog/datacenters");
    }

    /**
     * List available operating systems.
     */
    public JsonObject listOperatingSystems() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/catalog/images");
    }

    /**
     * Get cache time options.
     */
    public JsonObject getCacheTimes() throws MizbanCloudException, IOException {
        return http.get("/api/v1/statics/cache-times");
    }

    /**
     * Get slider configurations.
     */
    public JsonObject getSliders() throws MizbanCloudException, IOException {
        return http.get("/api/v1/statics/sliders");
    }
}
