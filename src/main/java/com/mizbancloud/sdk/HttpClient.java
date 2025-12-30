package com.mizbancloud.sdk;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mizbancloud.sdk.exceptions.MizbanCloudException;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HTTP client for MizbanCloud API requests.
 */
public class HttpClient {
    private final MizbanCloudConfig config;
    private final OkHttpClient client;
    private final Gson gson;

    public HttpClient(MizbanCloudConfig config) {
        this.config = config;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getTimeout(), TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
    }

    public MizbanCloudConfig getConfig() {
        return config;
    }

    private Headers buildHeaders() {
        Headers.Builder builder = new Headers.Builder()
                .add("Accept", "application/json")
                .add("Accept-Language", config.getLanguage());

        if (config.getToken() != null && !config.getToken().isEmpty()) {
            builder.add("Authorization", "Bearer " + config.getToken());
        }

        for (Map.Entry<String, String> entry : config.getHeaders().entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        return builder.build();
    }

    private JsonObject handleResponse(Response response) throws MizbanCloudException, IOException {
        String body = response.body() != null ? response.body().string() : "";
        JsonObject data = null;

        if (!body.isEmpty()) {
            try {
                data = gson.fromJson(body, JsonObject.class);
            } catch (Exception e) {
                // Not JSON response
            }
        }

        if (!response.isSuccessful()) {
            String message = "API request failed";
            Map<String, String> fields = new HashMap<>();
            List<String> invalidFields = new ArrayList<>();
            List<String> missingFields = new ArrayList<>();

            if (data != null) {
                if (data.has("message")) {
                    message = data.get("message").getAsString();
                }
                if (data.has("errors")) {
                    JsonElement errors = data.get("errors");
                    if (errors.isJsonObject()) {
                        for (Map.Entry<String, JsonElement> entry : errors.getAsJsonObject().entrySet()) {
                            fields.put(entry.getKey(), entry.getValue().getAsString());
                        }
                    } else if (errors.isJsonArray()) {
                        for (JsonElement err : errors.getAsJsonArray()) {
                            if (err.isJsonObject()) {
                                JsonObject errObj = err.getAsJsonObject();
                                String type = errObj.has("type") ? errObj.get("type").getAsString() : "";
                                String field = errObj.has("field") ? errObj.get("field").getAsString() : "";
                                if ("invalid".equals(type)) {
                                    invalidFields.add(field);
                                } else if ("missing".equals(type)) {
                                    missingFields.add(field);
                                }
                            }
                        }
                    }
                }
            }

            throw new MizbanCloudException(message, response.code(), fields, invalidFields, missingFields, data);
        }

        return data;
    }

    public JsonObject get(String endpoint) throws MizbanCloudException, IOException {
        return get(endpoint, null);
    }

    public JsonObject get(String endpoint, Map<String, String> params) throws MizbanCloudException, IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(config.getBaseUrl() + endpoint).newBuilder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .headers(buildHeaders())
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response);
        }
    }

    public JsonObject post(String endpoint) throws MizbanCloudException, IOException {
        return post(endpoint, null);
    }

    public JsonObject post(String endpoint, Map<String, Object> data) throws MizbanCloudException, IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder()
                .url(config.getBaseUrl() + endpoint)
                .headers(buildHeaders())
                .post(formBuilder.build())
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response);
        }
    }

    public JsonObject put(String endpoint, Map<String, Object> data) throws MizbanCloudException, IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();

        if (data != null) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }

        Request request = new Request.Builder()
                .url(config.getBaseUrl() + endpoint)
                .headers(buildHeaders())
                .put(formBuilder.build())
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response);
        }
    }

    public JsonObject delete(String endpoint) throws MizbanCloudException, IOException {
        return delete(endpoint, null);
    }

    public JsonObject delete(String endpoint, Map<String, Object> data) throws MizbanCloudException, IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(config.getBaseUrl() + endpoint)
                .headers(buildHeaders());

        if (data != null) {
            FormBody.Builder formBuilder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
            requestBuilder.delete(formBuilder.build());
        } else {
            requestBuilder.delete();
        }

        try (Response response = client.newCall(requestBuilder.build()).execute()) {
            return handleResponse(response);
        }
    }

    public void close() {
        client.dispatcher().executorService().shutdown();
        client.connectionPool().evictAll();
    }
}
