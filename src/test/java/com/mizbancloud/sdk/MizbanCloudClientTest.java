package com.mizbancloud.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MizbanCloudClientTest {

    @Test
    void testClientCreationDefault() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertNotNull(client);
            assertNotNull(client.auth());
            assertNotNull(client.cdn());
            assertNotNull(client.cloud());
            assertNotNull(client.statics());
        }
    }

    @Test
    void testClientCreationWithConfig() {
        MizbanCloudConfig config = new MizbanCloudConfig()
                .setBaseUrl("https://custom.api.com")
                .setTimeout(60)
                .setLanguage("fa");

        try (MizbanCloudClient client = new MizbanCloudClient(config)) {
            assertEquals("fa", client.getLanguage());
        }
    }

    @Test
    void testSetToken() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            client.setToken("test-token");
            assertEquals("test-token", client.getToken());
            assertTrue(client.isAuthenticated());
        }
    }

    @Test
    void testIsAuthenticatedFalse() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertFalse(client.isAuthenticated());
        }
    }

    @Test
    void testSetLanguage() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            client.setLanguage("fa");
            assertEquals("fa", client.getLanguage());
            client.setLanguage("en");
            assertEquals("en", client.getLanguage());
        }
    }
}
