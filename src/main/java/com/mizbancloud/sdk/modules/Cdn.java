package com.mizbancloud.sdk.modules;

import com.google.gson.JsonObject;
import com.mizbancloud.sdk.HttpClient;
import com.mizbancloud.sdk.exceptions.MizbanCloudException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * CDN management: domains, DNS, SSL, cache, security, WAF, page rules, and more.
 */
public class Cdn {
    private final HttpClient http;

    public Cdn(HttpClient http) {
        this.http = http;
    }

    // ==================== Domain Methods ====================

    public JsonObject listDomains() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains");
    }

    public JsonObject getDomain(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId);
    }

    public JsonObject addDomain(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains", data);
    }

    public JsonObject deleteDomain(int domainId, String confirmCode) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("code", confirmCode);
        return http.delete("/api/v1/cdn/domains/" + domainId, data);
    }

    public JsonObject sendDeleteConfirmCode(int domainId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/delete/confirm");
    }

    public JsonObject getUsage(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/usage");
    }

    public JsonObject getWhois(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/whois");
    }

    public JsonObject getReports(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/reports");
    }

    public JsonObject setRedirectMode(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/redirect-mode", data);
    }

    // ==================== DNS Methods ====================

    public JsonObject listDnsRecords(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/dns");
    }

    public JsonObject getDnsRecord(int domainId, int recordId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/dns/" + recordId);
    }

    public JsonObject addDnsRecord(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/dns", data);
    }

    public JsonObject updateDnsRecord(int domainId, int recordId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.put("/api/v1/cdn/domains/" + domainId + "/dns/" + recordId, data);
    }

    public JsonObject deleteDnsRecord(int domainId, int recordId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/dns/" + recordId);
    }

    public JsonObject fetchRecords(int domainId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/dns/fetch");
    }

    public JsonObject exportDnsRecords(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/dns/export");
    }

    public JsonObject importDnsRecords(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/dns/import", data);
    }

    public JsonObject getProxiableRecords(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/dns/proxiable");
    }

    public JsonObject setCustomNameservers(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/dns/nameservers", data);
    }

    public JsonObject setDnssec(int domainId, boolean enabled) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        return http.post("/api/v1/cdn/domains/" + domainId + "/dns/dnssec", data);
    }

    // ==================== SSL Methods ====================

    public JsonObject listSsl(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/ssl");
    }

    public JsonObject getSslInfo(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/ssl/info");
    }

    public JsonObject getSslConfigs(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/ssl/configs");
    }

    public JsonObject addCustomSsl(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/custom", data);
    }

    public JsonObject requestFreeSsl(int domainId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/free");
    }

    public JsonObject removeSsl(int domainId, int sslId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/ssl/" + sslId);
    }

    public JsonObject attachSsl(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/attach", data);
    }

    public JsonObject detachSsl(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/detach", data);
    }

    public JsonObject attachDefaultSsl(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/default/attach", data);
    }

    public JsonObject detachDefaultSsl(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/default/detach", data);
    }

    public JsonObject setTlsVersion(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/tls-version", data);
    }

    public JsonObject setHsts(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/hsts", data);
    }

    public JsonObject setHttpsRedirect(int domainId, boolean enabled) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/https-redirect", data);
    }

    public JsonObject setCspOverride(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/csp-override", data);
    }

    public JsonObject setBackendProtocol(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/backend-protocol", data);
    }

    public JsonObject setHttp3(int domainId, boolean enabled) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        return http.post("/api/v1/cdn/domains/" + domainId + "/ssl/http3", data);
    }

    // ==================== Cache Methods ====================

    public JsonObject getCacheSettings(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/cache");
    }

    public JsonObject setCacheMode(int domainId, String mode) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("mode", mode);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/mode", data);
    }

    public JsonObject setCacheTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/ttl", data);
    }

    public JsonObject setDeveloperMode(int domainId, boolean enabled) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/developer-mode", data);
    }

    public JsonObject setAlwaysOnline(int domainId, boolean enabled) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("enabled", enabled);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/always-online", data);
    }

    public JsonObject setCacheCookies(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/cookies", data);
    }

    public JsonObject setBrowserCacheMode(int domainId, String mode) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("mode", mode);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/browser-mode", data);
    }

    public JsonObject setBrowserCacheTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/browser-ttl", data);
    }

    public JsonObject setErrorCacheTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/error-ttl", data);
    }

    public JsonObject purgeCache(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/cache/purge", data);
    }

    // ==================== Acceleration Methods ====================

    public JsonObject setMinify(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/acceleration/minify", data);
    }

    public JsonObject setImageOptimization(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/acceleration/image-optimization", data);
    }

    public JsonObject setImageResize(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/acceleration/image-resize", data);
    }

    // ==================== DDoS Methods ====================

    public JsonObject getDdosSettings(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/ddos");
    }

    public JsonObject setDdosSettings(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ddos", data);
    }

    public JsonObject setCaptchaModule(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/ddos/captcha", data);
    }

    public JsonObject setCaptchaTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/ddos/captcha-ttl", data);
    }

    public JsonObject setCookieChallengeTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/ddos/cookie-challenge-ttl", data);
    }

    public JsonObject setJsChallengeTtl(int domainId, int ttl) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("ttl", ttl);
        return http.post("/api/v1/cdn/domains/" + domainId + "/ddos/js-challenge-ttl", data);
    }

    // ==================== Firewall Methods ====================

    public JsonObject getFirewallConfigs(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/firewall");
    }

    public JsonObject setFirewallConfigs(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/firewall", data);
    }

    // ==================== WAF Methods ====================

    public JsonObject getWafSettings(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/waf");
    }

    public JsonObject setWafSettings(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/waf", data);
    }

    public JsonObject getWafLayers(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/waf/layers");
    }

    public JsonObject getWafRules(int domainId, int layerId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/waf/layers/" + layerId + "/rules");
    }

    public JsonObject getDisabledWafRules(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/waf/disabled-rules");
    }

    public JsonObject switchWafGroup(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/waf/switch-group", data);
    }

    public JsonObject switchWafRule(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/waf/switch-rule", data);
    }

    // ==================== Page Rules Methods ====================

    public JsonObject getPageRules(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/page-rules");
    }

    public JsonObject getPageRulesWaf(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/page-rules/waf");
    }

    public JsonObject getPageRulesRatelimit(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/page-rules/ratelimit");
    }

    public JsonObject getPageRulesDdos(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/page-rules/ddos");
    }

    public JsonObject getPageRulesFirewall(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/page-rules/firewall");
    }

    public JsonObject createPageRulePath(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/page-rules/path", data);
    }

    public JsonObject setPageRulePriority(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/page-rules/priority", data);
    }

    public JsonObject deletePageRulePath(int domainId, int pathId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/page-rules/path/" + pathId);
    }

    public JsonObject createRule(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/page-rules/rule", data);
    }

    public JsonObject deleteRule(int domainId, int ruleId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/page-rules/rule/" + ruleId);
    }

    public JsonObject setDirectRule(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/page-rules/direct", data);
    }

    // ==================== Cluster Methods ====================

    public JsonObject getClusters(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/clusters");
    }

    public JsonObject getClusterAssignments(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/clusters/assignments");
    }

    public JsonObject addCluster(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/clusters", data);
    }

    public JsonObject updateCluster(int domainId, int clusterId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.put("/api/v1/cdn/domains/" + domainId + "/clusters/" + clusterId, data);
    }

    public JsonObject deleteCluster(int domainId, int clusterId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/clusters/" + clusterId);
    }

    public JsonObject addServerToCluster(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/clusters/server", data);
    }

    public JsonObject removeServerFromCluster(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/clusters/server", data);
    }

    // ==================== Log Forwarder Methods ====================

    public JsonObject getLogForwarders(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/log-forwarders");
    }

    public JsonObject addLogForwarder(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/log-forwarders", data);
    }

    public JsonObject updateLogForwarder(int domainId, int forwarderId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.put("/api/v1/cdn/domains/" + domainId + "/log-forwarders/" + forwarderId, data);
    }

    public JsonObject deleteLogForwarder(int domainId, int forwarderId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/log-forwarders/" + forwarderId);
    }

    // ==================== Custom Pages Methods ====================

    public JsonObject getCustomPages(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/custom-pages");
    }

    public JsonObject setCustomPages(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/custom-pages", data);
    }

    public JsonObject deleteCustomPages(int domainId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cdn/domains/" + domainId + "/custom-pages");
    }

    // ==================== Plan Methods ====================

    public JsonObject getPlans(int domainId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cdn/domains/" + domainId + "/plans");
    }

    public JsonObject changePlan(int domainId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cdn/domains/" + domainId + "/plans/change", data);
    }
}
