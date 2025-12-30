package com.mizbancloud.sdk;

import com.mizbancloud.sdk.modules.Auth;
import com.mizbancloud.sdk.modules.Cdn;
import com.mizbancloud.sdk.modules.Cloud;
import com.mizbancloud.sdk.modules.Statics;

import java.io.Closeable;

/**
 * Main client for interacting with MizbanCloud APIs.
 */
public class MizbanCloudClient implements Closeable {
    private final MizbanCloudConfig config;
    private final HttpClient http;
    private final Auth auth;
    private final Cdn cdn;
    private final Cloud cloud;
    private final Statics statics;

    /**
     * Create client with default configuration.
     */
    public MizbanCloudClient() {
        this(new MizbanCloudConfig());
    }

    /**
     * Create client with custom configuration.
     */
    public MizbanCloudClient(MizbanCloudConfig config) {
        this.config = config;
        this.http = new HttpClient(config);
        this.auth = new Auth(http);
        this.cdn = new Cdn(http);
        this.cloud = new Cloud(http);
        this.statics = new Statics(http);
    }

    /**
     * Get Auth module.
     */
    public Auth auth() {
        return auth;
    }

    /**
     * Get CDN module.
     */
    public Cdn cdn() {
        return cdn;
    }

    /**
     * Get Cloud module.
     */
    public Cloud cloud() {
        return cloud;
    }

    /**
     * Get Statics module.
     */
    public Statics statics() {
        return statics;
    }

    /**
     * Set API token.
     */
    public void setToken(String token) {
        config.setToken(token);
    }

    /**
     * Get current API token.
     */
    public String getToken() {
        return config.getToken();
    }

    /**
     * Set response language ('en' or 'fa').
     */
    public void setLanguage(String language) {
        config.setLanguage(language);
    }

    /**
     * Get current language.
     */
    public String getLanguage() {
        return config.getLanguage();
    }

    /**
     * Check if client has a token set.
     */
    public boolean isAuthenticated() {
        return config.getToken() != null && !config.getToken().isEmpty();
    }

    /**
     * Close the client and release resources.
     */
    @Override
    public void close() {
        http.close();
    }
}
