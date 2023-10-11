package com.mattyeh.memberSystem.controller;

import com.mattyeh.memberSystem.entity.MemberEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/signup")
    public String getSignUpPage(HttpServletRequest httpServletRequest) {
        MemberEntity member = (MemberEntity) httpServletRequest.getSession().getAttribute("loginMember");
        if (member != null) {
            return "redirect:service/home";
        }
        return "sign_up";
    }

    @GetMapping("/service/home")
    public String getHomePage(HttpServletRequest httpServletRequest, Model model) {
        MemberEntity member = (MemberEntity) httpServletRequest.getSession().getAttribute("loginMember");
        model.addAttribute("member", member);

        return "success";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.removeAttribute("loginMember");
        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLogInPage(HttpServletRequest httpServletRequest) {
        MemberEntity member = (MemberEntity) httpServletRequest.getSession().getAttribute("loginMember");
        if (member != null) {
            return "redirect:service/home";
        }
        return "log_in";
    }
}
