package com.develop.springboot.controller;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.annotation.TimedSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ViewsController class is a controller for views testing.
 * It provides endpoints for different views.
 */
@Slf4j
@Controller
@RequestMapping("/views")
public class ViewsController {

    @Timed
    @GetMapping("/test")
    public String showTestPage(Model model) {
        model.addAttribute("message", "This is a test page!");
        return "test";
    }


    @Timed
    @GetMapping("/testError")
    public String throwError() {
        throw new RuntimeException("This is a test error!");
    }

}
