package com.mizbancloud.sdk;

import com.mizbancloud.sdk.modules.Cdn;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CdnTest {

    @Test
    void testCdnModuleExists() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertNotNull(client.cdn());
            assertTrue(client.cdn() instanceof Cdn);
        }
    }

    @Test
    void testCdnHasAtLeast90Methods() {
        long count = Arrays.stream(Cdn.class.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .count();
        assertTrue(count >= 90, "Expected at least 90 methods, found " + count);
    }

    // Domain Methods
    @Test void testListDomainsExists() { assertMethodExists("listDomains"); }
    @Test void testGetDomainExists() { assertMethodExists("getDomain"); }
    @Test void testAddDomainExists() { assertMethodExists("addDomain"); }
    @Test void testDeleteDomainExists() { assertMethodExists("deleteDomain"); }
    @Test void testSendDeleteConfirmCodeExists() { assertMethodExists("sendDeleteConfirmCode"); }
    @Test void testGetUsageExists() { assertMethodExists("getUsage"); }
    @Test void testGetWhoisExists() { assertMethodExists("getWhois"); }
    @Test void testGetReportsExists() { assertMethodExists("getReports"); }
    @Test void testSetRedirectModeExists() { assertMethodExists("setRedirectMode"); }

    // DNS Methods
    @Test void testListDnsRecordsExists() { assertMethodExists("listDnsRecords"); }
    @Test void testGetDnsRecordExists() { assertMethodExists("getDnsRecord"); }
    @Test void testAddDnsRecordExists() { assertMethodExists("addDnsRecord"); }
    @Test void testUpdateDnsRecordExists() { assertMethodExists("updateDnsRecord"); }
    @Test void testDeleteDnsRecordExists() { assertMethodExists("deleteDnsRecord"); }
    @Test void testFetchRecordsExists() { assertMethodExists("fetchRecords"); }
    @Test void testExportDnsRecordsExists() { assertMethodExists("exportDnsRecords"); }
    @Test void testImportDnsRecordsExists() { assertMethodExists("importDnsRecords"); }
    @Test void testGetProxiableRecordsExists() { assertMethodExists("getProxiableRecords"); }
    @Test void testSetCustomNameserversExists() { assertMethodExists("setCustomNameservers"); }
    @Test void testSetDnssecExists() { assertMethodExists("setDnssec"); }

    // SSL Methods
    @Test void testListSslExists() { assertMethodExists("listSsl"); }
    @Test void testGetSslInfoExists() { assertMethodExists("getSslInfo"); }
    @Test void testGetSslConfigsExists() { assertMethodExists("getSslConfigs"); }
    @Test void testAddCustomSslExists() { assertMethodExists("addCustomSsl"); }
    @Test void testRequestFreeSslExists() { assertMethodExists("requestFreeSsl"); }
    @Test void testRemoveSslExists() { assertMethodExists("removeSsl"); }
    @Test void testAttachSslExists() { assertMethodExists("attachSsl"); }
    @Test void testDetachSslExists() { assertMethodExists("detachSsl"); }
    @Test void testAttachDefaultSslExists() { assertMethodExists("attachDefaultSsl"); }
    @Test void testDetachDefaultSslExists() { assertMethodExists("detachDefaultSsl"); }
    @Test void testSetTlsVersionExists() { assertMethodExists("setTlsVersion"); }
    @Test void testSetHstsExists() { assertMethodExists("setHsts"); }
    @Test void testSetHttpsRedirectExists() { assertMethodExists("setHttpsRedirect"); }
    @Test void testSetCspOverrideExists() { assertMethodExists("setCspOverride"); }
    @Test void testSetBackendProtocolExists() { assertMethodExists("setBackendProtocol"); }
    @Test void testSetHttp3Exists() { assertMethodExists("setHttp3"); }

    // Cache Methods
    @Test void testGetCacheSettingsExists() { assertMethodExists("getCacheSettings"); }
    @Test void testSetCacheModeExists() { assertMethodExists("setCacheMode"); }
    @Test void testSetCacheTtlExists() { assertMethodExists("setCacheTtl"); }
    @Test void testSetDeveloperModeExists() { assertMethodExists("setDeveloperMode"); }
    @Test void testSetAlwaysOnlineExists() { assertMethodExists("setAlwaysOnline"); }
    @Test void testSetCacheCookiesExists() { assertMethodExists("setCacheCookies"); }
    @Test void testSetBrowserCacheModeExists() { assertMethodExists("setBrowserCacheMode"); }
    @Test void testSetBrowserCacheTtlExists() { assertMethodExists("setBrowserCacheTtl"); }
    @Test void testSetErrorCacheTtlExists() { assertMethodExists("setErrorCacheTtl"); }
    @Test void testPurgeCacheExists() { assertMethodExists("purgeCache"); }

    // Acceleration Methods
    @Test void testSetMinifyExists() { assertMethodExists("setMinify"); }
    @Test void testSetImageOptimizationExists() { assertMethodExists("setImageOptimization"); }
    @Test void testSetImageResizeExists() { assertMethodExists("setImageResize"); }

    // DDoS Methods
    @Test void testGetDdosSettingsExists() { assertMethodExists("getDdosSettings"); }
    @Test void testSetDdosSettingsExists() { assertMethodExists("setDdosSettings"); }
    @Test void testSetCaptchaModuleExists() { assertMethodExists("setCaptchaModule"); }
    @Test void testSetCaptchaTtlExists() { assertMethodExists("setCaptchaTtl"); }
    @Test void testSetCookieChallengeTtlExists() { assertMethodExists("setCookieChallengeTtl"); }
    @Test void testSetJsChallengeTtlExists() { assertMethodExists("setJsChallengeTtl"); }

    // Firewall Methods
    @Test void testGetFirewallConfigsExists() { assertMethodExists("getFirewallConfigs"); }
    @Test void testSetFirewallConfigsExists() { assertMethodExists("setFirewallConfigs"); }

    // WAF Methods
    @Test void testGetWafSettingsExists() { assertMethodExists("getWafSettings"); }
    @Test void testSetWafSettingsExists() { assertMethodExists("setWafSettings"); }
    @Test void testGetWafLayersExists() { assertMethodExists("getWafLayers"); }
    @Test void testGetWafRulesExists() { assertMethodExists("getWafRules"); }
    @Test void testGetDisabledWafRulesExists() { assertMethodExists("getDisabledWafRules"); }
    @Test void testSwitchWafGroupExists() { assertMethodExists("switchWafGroup"); }
    @Test void testSwitchWafRuleExists() { assertMethodExists("switchWafRule"); }

    // Page Rules Methods
    @Test void testGetPageRulesExists() { assertMethodExists("getPageRules"); }
    @Test void testGetPageRulesWafExists() { assertMethodExists("getPageRulesWaf"); }
    @Test void testGetPageRulesRatelimitExists() { assertMethodExists("getPageRulesRatelimit"); }
    @Test void testGetPageRulesDdosExists() { assertMethodExists("getPageRulesDdos"); }
    @Test void testGetPageRulesFirewallExists() { assertMethodExists("getPageRulesFirewall"); }
    @Test void testCreatePageRulePathExists() { assertMethodExists("createPageRulePath"); }
    @Test void testSetPageRulePriorityExists() { assertMethodExists("setPageRulePriority"); }
    @Test void testDeletePageRulePathExists() { assertMethodExists("deletePageRulePath"); }
    @Test void testCreateRuleExists() { assertMethodExists("createRule"); }
    @Test void testDeleteRuleExists() { assertMethodExists("deleteRule"); }
    @Test void testSetDirectRuleExists() { assertMethodExists("setDirectRule"); }

    // Cluster Methods
    @Test void testGetClustersExists() { assertMethodExists("getClusters"); }
    @Test void testGetClusterAssignmentsExists() { assertMethodExists("getClusterAssignments"); }
    @Test void testAddClusterExists() { assertMethodExists("addCluster"); }
    @Test void testUpdateClusterExists() { assertMethodExists("updateCluster"); }
    @Test void testDeleteClusterExists() { assertMethodExists("deleteCluster"); }
    @Test void testAddServerToClusterExists() { assertMethodExists("addServerToCluster"); }
    @Test void testRemoveServerFromClusterExists() { assertMethodExists("removeServerFromCluster"); }

    // Log Forwarder Methods
    @Test void testGetLogForwardersExists() { assertMethodExists("getLogForwarders"); }
    @Test void testAddLogForwarderExists() { assertMethodExists("addLogForwarder"); }
    @Test void testUpdateLogForwarderExists() { assertMethodExists("updateLogForwarder"); }
    @Test void testDeleteLogForwarderExists() { assertMethodExists("deleteLogForwarder"); }

    // Custom Pages Methods
    @Test void testGetCustomPagesExists() { assertMethodExists("getCustomPages"); }
    @Test void testSetCustomPagesExists() { assertMethodExists("setCustomPages"); }
    @Test void testDeleteCustomPagesExists() { assertMethodExists("deleteCustomPages"); }

    // Plan Methods
    @Test void testGetPlansExists() { assertMethodExists("getPlans"); }
    @Test void testChangePlanExists() { assertMethodExists("changePlan"); }

    private void assertMethodExists(String methodName) {
        boolean found = Arrays.stream(Cdn.class.getDeclaredMethods())
                .anyMatch(m -> m.getName().equals(methodName));
        assertTrue(found, "Method " + methodName + " not found");
    }
}
