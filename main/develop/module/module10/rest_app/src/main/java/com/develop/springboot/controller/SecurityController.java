package com.develop.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/secured")
public class SecurityController {

    @GetMapping("/all")
    @ResponseBody
    public String openForAllRolesEndpoint() {
        return "Hello from localhost to All roles, whoever you are!";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String openForAdminRoleEndpoint() {
        return "Hello from localhost to ADMIN!";
    }

    @GetMapping("/user")
    @ResponseBody
    public String openForUserRoleEndpoint() {
        return "Hello from localhost to USER!";
    }

}
