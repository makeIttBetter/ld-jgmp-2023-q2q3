package com.develop.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/views")
public class ViewsController {

    @GetMapping("/test")
    public String showTestPage(Model model) {
        model.addAttribute("message", "This is a test page!");
        return "test";
    }

    @GetMapping("/testError")
    public String throwError() {
        throw new RuntimeException("This is a test error!");
    }

}
