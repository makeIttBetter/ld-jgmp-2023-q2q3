package com.develop.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SecurityController class is a controller for security testing.
 * It provides endpoints for different roles.
 * @see com.develop.springboot.config.WebSecurityConfig
 */
@Slf4j
@Controller
@RequestMapping("/secured")
public class SecurityController {

    /**
     * Endpoint for all authenticated roles.
     * @return String
     */
    @GetMapping("/all")
    @ResponseBody
    public String openForAllRolesEndpoint() {
        return "Hello from localhost to All roles, whoever you are!";
    }

    /**
     * Endpoint for ADMIN role.
     * @return String
     */
    @GetMapping("/admin")
    @ResponseBody
    public String openForAdminRoleEndpoint() {
        return "Hello from localhost to ADMIN!";
    }

    /**
     * Endpoint for USER role.
     * @return String
     */
    @GetMapping("/user")
    @ResponseBody
    public String openForUserRoleEndpoint() {
        return "Hello from localhost to USER!";
    }

}
