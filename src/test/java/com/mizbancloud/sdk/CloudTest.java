package com.mizbancloud.sdk;

import com.mizbancloud.sdk.modules.Cloud;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CloudTest {

    @Test
    void testCloudModuleExists() {
        try (MizbanCloudClient client = new MizbanCloudClient()) {
            assertNotNull(client.cloud());
            assertTrue(client.cloud() instanceof Cloud);
        }
    }

    @Test
    void testCloudHasAtLeast55Methods() {
        long count = Arrays.stream(Cloud.class.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .count();
        assertTrue(count >= 55, "Expected at least 55 methods, found " + count);
    }

    // Server Methods
    @Test void testListServersExists() { assertMethodExists("listServers"); }
    @Test void testGetServerExists() { assertMethodExists("getServer"); }
    @Test void testPollServerExists() { assertMethodExists("pollServer"); }
    @Test void testCreateServerExists() { assertMethodExists("createServer"); }
    @Test void testDeleteServerExists() { assertMethodExists("deleteServer"); }
    @Test void testRenameServerExists() { assertMethodExists("renameServer"); }
    @Test void testResizeServerExists() { assertMethodExists("resizeServer"); }
    @Test void testReloadOsExists() { assertMethodExists("reloadOs"); }

    // Power Management Methods
    @Test void testPowerOnExists() { assertMethodExists("powerOn"); }
    @Test void testPowerOffExists() { assertMethodExists("powerOff"); }
    @Test void testRebootExists() { assertMethodExists("reboot"); }
    @Test void testRestartExists() { assertMethodExists("restart"); }

    // Access Methods
    @Test void testGetVncExists() { assertMethodExists("getVnc"); }
    @Test void testResetPasswordExists() { assertMethodExists("resetPassword"); }
    @Test void testGetInitialPasswordExists() { assertMethodExists("getInitialPassword"); }

    // Rescue Mode Methods
    @Test void testRescueExists() { assertMethodExists("rescue"); }
    @Test void testUnrescueExists() { assertMethodExists("unrescue"); }

    // Autopilot Methods
    @Test void testEnableAutopilotExists() { assertMethodExists("enableAutopilot"); }
    @Test void testDisableAutopilotExists() { assertMethodExists("disableAutopilot"); }

    // Monitoring Methods
    @Test void testGetLogsExists() { assertMethodExists("getLogs"); }
    @Test void testGetChartsExists() { assertMethodExists("getCharts"); }
    @Test void testGetTrafficUsageExists() { assertMethodExists("getTrafficUsage"); }
    @Test void testGetTrafficsExists() { assertMethodExists("getTraffics"); }

    // Test Server Methods
    @Test void testConvertToPermanentExists() { assertMethodExists("convertToPermanent"); }

    // Security Group Methods
    @Test void testListSecurityGroupsExists() { assertMethodExists("listSecurityGroups"); }
    @Test void testCreateSecurityGroupExists() { assertMethodExists("createSecurityGroup"); }
    @Test void testDeleteSecurityGroupExists() { assertMethodExists("deleteSecurityGroup"); }
    @Test void testAddSecurityRuleExists() { assertMethodExists("addSecurityRule"); }
    @Test void testRemoveSecurityRuleExists() { assertMethodExists("removeSecurityRule"); }
    @Test void testAttachFirewallExists() { assertMethodExists("attachFirewall"); }
    @Test void testDetachFirewallExists() { assertMethodExists("detachFirewall"); }

    // Private Network Methods
    @Test void testListPrivateNetworksExists() { assertMethodExists("listPrivateNetworks"); }
    @Test void testCreatePrivateNetworkExists() { assertMethodExists("createPrivateNetwork"); }
    @Test void testUpdatePrivateNetworkExists() { assertMethodExists("updatePrivateNetwork"); }
    @Test void testDeletePrivateNetworkExists() { assertMethodExists("deletePrivateNetwork"); }
    @Test void testAttachToPrivateNetworkExists() { assertMethodExists("attachToPrivateNetwork"); }
    @Test void testDetachFromPrivateNetworkExists() { assertMethodExists("detachFromPrivateNetwork"); }
    @Test void testPurgeNetworkAttachmentsExists() { assertMethodExists("purgeNetworkAttachments"); }

    // Public Network Methods
    @Test void testAttachPublicNetworkExists() { assertMethodExists("attachPublicNetwork"); }
    @Test void testDetachPublicNetworkExists() { assertMethodExists("detachPublicNetwork"); }

    // Volume Methods
    @Test void testListVolumesExists() { assertMethodExists("listVolumes"); }
    @Test void testGetVolumeExists() { assertMethodExists("getVolume"); }
    @Test void testCreateVolumeExists() { assertMethodExists("createVolume"); }
    @Test void testUpdateVolumeExists() { assertMethodExists("updateVolume"); }
    @Test void testDeleteVolumeExists() { assertMethodExists("deleteVolume"); }
    @Test void testAttachVolumeExists() { assertMethodExists("attachVolume"); }
    @Test void testDetachVolumeExists() { assertMethodExists("detachVolume"); }
    @Test void testSyncVolumesExists() { assertMethodExists("syncVolumes"); }

    // Snapshot Methods
    @Test void testListSnapshotsExists() { assertMethodExists("listSnapshots"); }
    @Test void testGetSnapshotExists() { assertMethodExists("getSnapshot"); }
    @Test void testCreateSnapshotExists() { assertMethodExists("createSnapshot"); }
    @Test void testDeleteSnapshotExists() { assertMethodExists("deleteSnapshot"); }
    @Test void testSyncSnapshotsExists() { assertMethodExists("syncSnapshots"); }

    // SSH Key Methods
    @Test void testListSshKeysExists() { assertMethodExists("listSshKeys"); }
    @Test void testGetSshKeyExists() { assertMethodExists("getSshKey"); }
    @Test void testCreateSshKeyExists() { assertMethodExists("createSshKey"); }
    @Test void testDeleteSshKeyExists() { assertMethodExists("deleteSshKey"); }
    @Test void testGenerateRandomSshKeyExists() { assertMethodExists("generateRandomSshKey"); }

    private void assertMethodExists(String methodName) {
        boolean found = Arrays.stream(Cloud.class.getDeclaredMethods())
                .anyMatch(m -> m.getName().equals(methodName));
        assertTrue(found, "Method " + methodName + " not found");
    }
}
