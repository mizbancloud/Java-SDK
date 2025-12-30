package com.mizbancloud.sdk.modules;

import com.google.gson.JsonObject;
import com.mizbancloud.sdk.HttpClient;
import com.mizbancloud.sdk.exceptions.MizbanCloudException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cloud IaaS management: servers, firewall, networks, volumes, snapshots, SSH keys.
 */
public class Cloud {
    private final HttpClient http;

    public Cloud(HttpClient http) {
        this.http = http;
    }

    // ==================== Server Methods ====================

    public JsonObject listServers() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers");
    }

    public JsonObject getServer(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId);
    }

    public JsonObject pollServer(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/poll");
    }

    public JsonObject createServer(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers", data);
    }

    public JsonObject deleteServer(int serverId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/servers/" + serverId);
    }

    public JsonObject renameServer(int serverId, String name) throws MizbanCloudException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        return http.post("/api/v1/cloud/servers/" + serverId + "/rename", data);
    }

    public JsonObject resizeServer(int serverId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/resize", data);
    }

    public JsonObject reloadOs(int serverId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/reload-os", data);
    }

    // ==================== Power Management Methods ====================

    public JsonObject powerOn(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/power-on");
    }

    public JsonObject powerOff(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/power-off");
    }

    public JsonObject reboot(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/reboot");
    }

    public JsonObject restart(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/restart");
    }

    // ==================== Access Methods ====================

    public JsonObject getVnc(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/vnc");
    }

    public JsonObject resetPassword(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/reset-password");
    }

    public JsonObject getInitialPassword(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/initial-password");
    }

    // ==================== Rescue Mode Methods ====================

    public JsonObject rescue(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/rescue");
    }

    public JsonObject rescue(int serverId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/rescue", data);
    }

    public JsonObject unrescue(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/unrescue");
    }

    // ==================== Autopilot Methods ====================

    public JsonObject enableAutopilot(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/autopilot/enable");
    }

    public JsonObject disableAutopilot(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/autopilot/disable");
    }

    // ==================== Monitoring Methods ====================

    public JsonObject getLogs(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/logs");
    }

    public JsonObject getCharts(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/charts");
    }

    public JsonObject getTrafficUsage(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/traffic-usage");
    }

    public JsonObject getTraffics(int serverId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/servers/" + serverId + "/traffics");
    }

    // ==================== Test Server Methods ====================

    public JsonObject convertToPermanent(int serverId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/" + serverId + "/convert-to-permanent");
    }

    // ==================== Security Group Methods ====================

    public JsonObject listSecurityGroups() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/security-groups");
    }

    public JsonObject createSecurityGroup(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/security-groups", data);
    }

    public JsonObject deleteSecurityGroup(int groupId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/security-groups/" + groupId);
    }

    public JsonObject addSecurityRule(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/security-rules", data);
    }

    public JsonObject removeSecurityRule(int ruleId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/security-rules/" + ruleId);
    }

    public JsonObject attachFirewall(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/attach-firewall", data);
    }

    public JsonObject detachFirewall(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/detach-firewall", data);
    }

    // ==================== Private Network Methods ====================

    public JsonObject listPrivateNetworks() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/private-networks");
    }

    public JsonObject createPrivateNetwork(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/private-networks", data);
    }

    public JsonObject updatePrivateNetwork(int networkId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.put("/api/v1/cloud/private-networks/" + networkId, data);
    }

    public JsonObject deletePrivateNetwork(int networkId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/private-networks/" + networkId);
    }

    public JsonObject attachToPrivateNetwork(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/private-networks/attach", data);
    }

    public JsonObject detachFromPrivateNetwork(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/private-networks/detach", data);
    }

    public JsonObject purgeNetworkAttachments(int networkId) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/private-networks/" + networkId + "/purge");
    }

    // ==================== Public Network Methods ====================

    public JsonObject attachPublicNetwork(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/attach-public-network", data);
    }

    public JsonObject detachPublicNetwork(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/servers/detach-public-network", data);
    }

    // ==================== Volume Methods ====================

    public JsonObject listVolumes() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/volumes");
    }

    public JsonObject getVolume(int volumeId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/volumes/" + volumeId);
    }

    public JsonObject createVolume(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/volumes", data);
    }

    public JsonObject updateVolume(int volumeId, Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.put("/api/v1/cloud/volumes/" + volumeId, data);
    }

    public JsonObject deleteVolume(int volumeId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/volumes/" + volumeId);
    }

    public JsonObject attachVolume(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/volumes/attach", data);
    }

    public JsonObject detachVolume(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/volumes/detach", data);
    }

    public JsonObject syncVolumes() throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/volumes/sync");
    }

    // ==================== Snapshot Methods ====================

    public JsonObject listSnapshots() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/snapshots");
    }

    public JsonObject getSnapshot(int snapshotId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/snapshots/" + snapshotId);
    }

    public JsonObject createSnapshot(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/snapshots", data);
    }

    public JsonObject deleteSnapshot(int snapshotId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/snapshots/" + snapshotId);
    }

    public JsonObject syncSnapshots() throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/snapshots/sync");
    }

    // ==================== SSH Key Methods ====================

    public JsonObject listSshKeys() throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/ssh-keys");
    }

    public JsonObject getSshKey(int keyId) throws MizbanCloudException, IOException {
        return http.get("/api/v1/cloud/ssh-keys/" + keyId);
    }

    public JsonObject createSshKey(Map<String, Object> data) throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/ssh-keys", data);
    }

    public JsonObject deleteSshKey(int keyId) throws MizbanCloudException, IOException {
        return http.delete("/api/v1/cloud/ssh-keys/" + keyId);
    }

    public JsonObject generateRandomSshKey() throws MizbanCloudException, IOException {
        return http.post("/api/v1/cloud/ssh-keys/generate");
    }
}
