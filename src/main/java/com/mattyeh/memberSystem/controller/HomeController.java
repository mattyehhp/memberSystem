package com.mattyeh.memberSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/signup")
    public String getSignUpPage() {
        return "sign_up";
    }

    @GetMapping("/login")
    public String getLogInPage() {
        return "log_in";
    }
}
