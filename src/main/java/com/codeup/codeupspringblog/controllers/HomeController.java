package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "home and other words";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
