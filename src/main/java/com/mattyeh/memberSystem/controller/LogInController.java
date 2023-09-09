package com.mattyeh.memberSystem.controller;

import com.mattyeh.memberSystem.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogInController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public @ResponseBody String checkLogInMemberInfo(@RequestParam String email, @RequestParam String password) {
        String result = new String();

        return result;
    }
}
