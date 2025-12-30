package com.mizbancloud.sdk;

import com.mizbancloud.sdk.modules.Statics;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class StaticsTest {

    @Test
    void testStaticsModuleExists() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertNotNull(client.statics());
            assertTrue(client.statics() instanceof Statics);
        }
    }

    @Test
    void testStaticsHas4Methods() {
        Method[] methods = Statics.class.getDeclaredMethods();
        assertTrue(methods.length >= 4, "Expected at least 4 methods, found " + methods.length);
    }

    @Test
    void testListDatacentersExists() throws NoSuchMethodException {
        assertNotNull(Statics.class.getMethod("listDatacenters"));
    }

    @Test
    void testListOperatingSystemsExists() throws NoSuchMethodException {
        assertNotNull(Statics.class.getMethod("listOperatingSystems"));
    }

    @Test
    void testGetCacheTimesExists() throws NoSuchMethodException {
        assertNotNull(Statics.class.getMethod("getCacheTimes"));
    }

    @Test
    void testGetSlidersExists() throws NoSuchMethodException {
        assertNotNull(Statics.class.getMethod("getSliders"));
    }
}
