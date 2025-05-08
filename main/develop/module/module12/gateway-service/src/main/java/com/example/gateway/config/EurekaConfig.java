package com.example.gateway.config;

import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EurekaConfig {

    @Bean
    @Primary
    public EurekaInstanceConfigBean eurekaInstanceConfigBean(InetUtils inetUtils) {
        EurekaInstanceConfigBean config = new EurekaInstanceConfigBean(inetUtils);
        config.setHostname("HP-ProBook-450"); // Your desired hostname without .mshome.net
        config.setPreferIpAddress(false);      // Set to true if you prefer using IP addresses
        config.setNonSecurePort(8080);         // Ensure this matches your service's port
        // Optional: Set IP address if preferIpAddress is true
        // config.setIpAddress("192.168.1.100");
        return config;
    }
}
