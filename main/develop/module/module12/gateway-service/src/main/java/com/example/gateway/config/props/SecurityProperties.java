// src/main/java/com/example/gateway/config/SecurityProperties.java

package com.example.gateway.config.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Configuration properties for security settings.
 */
@Data
@Component
@ConfigurationProperties(prefix = "custom.security")
public class SecurityProperties {

    /**
     * List of public endpoints that do not require authentication.
     * property name: ${custom.security.public-paths}
     */
    private List<String> publicPaths;

    /**
     * List of secured endpoints that require authentication.
     * property name: ${custom.security.secured-paths}
     */
    private List<String> securedPaths;
}
