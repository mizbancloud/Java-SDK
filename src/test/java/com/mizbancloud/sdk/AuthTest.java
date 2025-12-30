package com.mizbancloud.sdk;

import com.mizbancloud.sdk.modules.Auth;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class AuthTest {

    @Test
    void testAuthModuleExists() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertNotNull(client.auth());
            assertTrue(client.auth() instanceof Auth);
        }
    }

    @Test
    void testAuthHas4Methods() {
        Method[] methods = Auth.class.getDeclaredMethods();
        assertTrue(methods.length >= 4, "Expected at least 4 methods, found " + methods.length);
    }

    @Test
    void testSetApiTokenExists() throws NoSuchMethodException {
        assertNotNull(Auth.class.getMethod("setApiToken", String.class));
    }

    @Test
    void testGetApiTokenExists() throws NoSuchMethodException {
        assertNotNull(Auth.class.getMethod("getApiToken"));
    }

    @Test
    void testClearApiTokenExists() throws NoSuchMethodException {
        assertNotNull(Auth.class.getMethod("clearApiToken"));
    }

    @Test
    void testGetWalletExists() throws NoSuchMethodException {
        assertNotNull(Auth.class.getMethod("getWallet"));
    }

    @Test
    void testSetAndGetToken() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            client.auth().setApiToken("my-token");
            assertEquals("my-token", client.auth().getApiToken());
        }
    }

    @Test
    void testClearToken() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            client.auth().setApiToken("my-token");
            client.auth().clearApiToken();
            assertNull(client.auth().getApiToken());
        }
    }
}
