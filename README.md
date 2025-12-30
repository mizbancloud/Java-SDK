# MizbanCloud Java SDK

Official Java SDK for interacting with MizbanCloud CDN and Cloud APIs.

## Requirements

- Java 11 or higher
- Maven 3.6+

## Installation

### Maven

Add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.mizbancloud</groupId>
    <artifactId>mizbancloud-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'com.mizbancloud:mizbancloud-sdk:1.0.0'
```

## Quick Start

```java
import com.mizbancloud.sdk.MizbanCloudClient;
import com.mizbancloud.sdk.MizbanCloudConfig;

// Create client instance
MizbanCloudClient client = new MizbanCloudClient(
    new MizbanCloudConfig().setBaseUrl("https://auth.mizbancloud.com")
);

// Set API token
client.setToken("your-api-token");

// List CDN domains
var domains = client.cdn().listDomains();

// List cloud servers
var servers = client.cloud().listServers();

// Close client when done
client.close();
```

### Using try-with-resources

```java
try (MizbanCloudClient client = new MizbanCloudClient()) {
    client.setToken("your-api-token");
    var domains = client.cdn().listDomains();
}
```

## Configuration

```java
MizbanCloudConfig config = new MizbanCloudConfig()
    .setBaseUrl("https://auth.mizbancloud.com")  // API base URL
    .setTimeout(30)                               // Request timeout in seconds
    .setLanguage("en")                            // Response language: "en" or "fa"
    .addHeader("X-Custom-Header", "value");       // Additional headers

MizbanCloudClient client = new MizbanCloudClient(config);
```

## Modules

### Auth Module

Token management and wallet operations.

```java
// Set API token
client.auth().setApiToken("your-token");

// Get current token
String token = client.auth().getApiToken();

// Clear token
client.auth().clearApiToken();

// Get wallet balance
var wallet = client.auth().getWallet();
```

### CDN Module

Complete CDN management: domains, DNS, SSL, cache, security, WAF, page rules, and more.

#### Domains

```java
// List all domains
var domains = client.cdn().listDomains();

// Get domain details
var domain = client.cdn().getDomain(domainId);

// Add a new domain
Map<String, Object> data = new HashMap<>();
data.put("domain", "example.com");
var result = client.cdn().addDomain(data);

// Delete domain (requires SMS confirmation)
client.cdn().sendDeleteConfirmCode(domainId);
client.cdn().deleteDomain(domainId, confirmCode);

// Get domain usage/analytics
var usage = client.cdn().getUsage(domainId);
```

#### DNS Records

```java
// List DNS records
var records = client.cdn().listDnsRecords(domainId);

// Add DNS record
Map<String, Object> data = new HashMap<>();
data.put("type", "A");
data.put("name", "www");
data.put("value", "1.2.3.4");
data.put("ttl", 3600);
data.put("proxied", true);
var record = client.cdn().addDnsRecord(domainId, data);

// Update DNS record
Map<String, Object> updateData = new HashMap<>();
updateData.put("value", "5.6.7.8");
client.cdn().updateDnsRecord(domainId, recordId, updateData);

// Delete DNS record
client.cdn().deleteDnsRecord(domainId, recordId);

// Enable DNSSEC
client.cdn().setDnssec(domainId, true);
```

#### SSL/HTTPS

```java
// List SSL certificates
var certs = client.cdn().listSsl(domainId);

// Request free SSL (Let's Encrypt)
client.cdn().requestFreeSsl(domainId);

// Add custom SSL certificate
Map<String, Object> sslData = new HashMap<>();
sslData.put("certificate", "-----BEGIN CERTIFICATE-----...");
sslData.put("private_key", "-----BEGIN PRIVATE KEY-----...");
client.cdn().addCustomSsl(domainId, sslData);

// Configure HSTS
Map<String, Object> hstsData = new HashMap<>();
hstsData.put("enabled", true);
hstsData.put("max_age", 31536000);
hstsData.put("include_subdomains", true);
client.cdn().setHsts(domainId, hstsData);

// Enable HTTPS redirect
client.cdn().setHttpsRedirect(domainId, true);
```

#### Cache

```java
// Get cache settings
var settings = client.cdn().getCacheSettings(domainId);

// Set cache mode
client.cdn().setCacheMode(domainId, "aggressive");

// Set cache TTL
client.cdn().setCacheTtl(domainId, 86400);

// Enable developer mode (bypass cache)
client.cdn().setDeveloperMode(domainId, true);

// Purge all cache
Map<String, Object> purgeData = new HashMap<>();
purgeData.put("purge_all", true);
client.cdn().purgeCache(domainId, purgeData);
```

#### DDoS Protection

```java
// Get DDoS settings
var settings = client.cdn().getDdosSettings(domainId);

// Set DDoS settings
Map<String, Object> ddosData = new HashMap<>();
ddosData.put("mode", "high");
client.cdn().setDdosSettings(domainId, ddosData);
```

#### Firewall & WAF

```java
// Get firewall configs
var configs = client.cdn().getFirewallConfigs(domainId);

// Get WAF settings
var waf = client.cdn().getWafSettings(domainId);

// Set WAF settings
Map<String, Object> wafData = new HashMap<>();
wafData.put("enabled", true);
wafData.put("mode", "block");
client.cdn().setWafSettings(domainId, wafData);
```

### Cloud Module

Complete IaaS management: servers, firewall, networks, volumes, snapshots, SSH keys.

#### Servers

```java
// List all servers
var servers = client.cloud().listServers();

// Get server details
var server = client.cloud().getServer(serverId);

// Create a new server
Map<String, Object> serverData = new HashMap<>();
serverData.put("name", "my-server");
serverData.put("datacenter_id", 1);
serverData.put("os_id", 1);
serverData.put("cpu", 2);
serverData.put("ram", 4096);
serverData.put("disk", 50);
var newServer = client.cloud().createServer(serverData);

// Rename server
client.cloud().renameServer(serverId, "new-name");

// Delete server
client.cloud().deleteServer(serverId);
```

#### Power Management

```java
// Power on
client.cloud().powerOn(serverId);

// Power off (hard)
client.cloud().powerOff(serverId);

// Reboot (hard)
client.cloud().reboot(serverId);

// Restart (soft/graceful)
client.cloud().restart(serverId);
```

#### Security Groups (Firewall)

```java
// List security groups
var groups = client.cloud().listSecurityGroups();

// Create security group
Map<String, Object> groupData = new HashMap<>();
groupData.put("name", "web-servers");
var group = client.cloud().createSecurityGroup(groupData);

// Add security rule
Map<String, Object> ruleData = new HashMap<>();
ruleData.put("security_group_id", groupId);
ruleData.put("direction", "ingress");
ruleData.put("protocol", "tcp");
ruleData.put("port_range_min", 80);
ruleData.put("port_range_max", 80);
ruleData.put("remote_ip_prefix", "0.0.0.0/0");
client.cloud().addSecurityRule(ruleData);
```

#### Volumes

```java
// List volumes
var volumes = client.cloud().listVolumes();

// Create volume
Map<String, Object> volumeData = new HashMap<>();
volumeData.put("name", "data-volume");
volumeData.put("size", 100);
volumeData.put("datacenter_id", 1);
var volume = client.cloud().createVolume(volumeData);

// Attach volume to server
Map<String, Object> attachData = new HashMap<>();
attachData.put("volume_id", volumeId);
attachData.put("server_id", serverId);
client.cloud().attachVolume(attachData);
```

#### SSH Keys

```java
// List SSH keys
var keys = client.cloud().listSshKeys();

// Create SSH key
Map<String, Object> keyData = new HashMap<>();
keyData.put("name", "my-key");
keyData.put("public_key", "ssh-rsa AAAA...");
var key = client.cloud().createSshKey(keyData);

// Generate random SSH key pair
var keyPair = client.cloud().generateRandomSshKey();
```

### Statics Module

Static catalog data for server creation.

```java
// List available datacenters
var datacenters = client.statics().listDatacenters();

// List available operating systems
var osList = client.statics().listOperatingSystems();

// Get cache time options
var cacheTimes = client.statics().getCacheTimes();
```

## Error Handling

```java
import com.mizbancloud.sdk.exceptions.MizbanCloudException;

try {
    var domains = client.cdn().listDomains();
} catch (MizbanCloudException e) {
    System.out.println("Error: " + e.getMessage());
    System.out.println("Status Code: " + e.getStatusCode());

    // Get validation errors
    if (e.getFields() != null) {
        for (Map.Entry<String, String> entry : e.getFields().entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }
} catch (IOException e) {
    System.out.println("Network error: " + e.getMessage());
}
```

## Language Support

```java
// Set language for API responses
client.setLanguage("fa"); // Persian
client.setLanguage("en"); // English (default)

// Get current language
String lang = client.getLanguage();
```

## Authentication Status

```java
// Check if authenticated
if (client.isAuthenticated()) {
    System.out.println("Token is set");
}

// Get current token
String token = client.getToken();
```

## Building from Source

```bash
# Clone repository
git clone https://github.com/mizbancloud/java-sdk.git
cd java-sdk

# Build
mvn clean install

# Run tests
mvn test
```

## License

MIT License
