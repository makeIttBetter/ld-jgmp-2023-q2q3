package com.develop.springboot.controller;

import io.micrometer.core.annotation.Counted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SecurityController class is a controller for security testing.
 * It provides endpoints for different roles.
 *
 * @see com.develop.springboot.config.WebSecurityConfig
 */
@Slf4j
@Controller
@RequestMapping("/secured")
public class SecurityController {

    /**
     * Endpoint for all authenticated roles.
     *
     * @return String
     */
    @Counted(value = "secured.all.count", description = "Counting how many times the all method has been invoked")
    @GetMapping("/all")
    @ResponseBody
    public String openForAllRolesEndpoint() {
        return "Hello from localhost to All roles, whoever you are!";
    }

    /**
     * Endpoint for ADMIN role.
     *
     * @return String
     */
    @Counted(value = "secured.admin.count", description = "Counting how many times the admin method has been invoked")
    @GetMapping("/admin")
    @ResponseBody
    public String openForAdminRoleEndpoint() {
        return "Hello from localhost to ADMIN!";
    }

    /**
     * Endpoint for USER role.
     *
     * @return String
     */
    @Counted(value = "secured.user.count", description = "Counting how many times the user method has been invoked")
    @GetMapping("/user")
    @ResponseBody
    public String openForUserRoleEndpoint() {
        return "Hello from localhost to USER!";
    }

}
