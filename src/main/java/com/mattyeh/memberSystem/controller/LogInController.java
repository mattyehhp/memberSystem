package com.mattyeh.memberSystem.controller;

import com.google.code.kaptcha.Constants;
import com.mattyeh.memberSystem.entity.MemberEntity;
import com.mattyeh.memberSystem.service.MemberService;

import com.mattyeh.memberSystem.utils.EncryptUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.NoSuchAlgorithmException;


@Controller
@Slf4j
public class LogInController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public String checkLogInMemberInfo(MemberEntity member, @RequestParam String kaptcha, Model model, HttpServletRequest httpServletRequest) throws NoSuchAlgorithmException {

        HttpSession httpSession = httpServletRequest.getSession();
        String verifyCode = (String)httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        httpSession.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (verifyCode == null) {
            model.addAttribute("error", "log in failed!");
            log.info("model: {}", model);
            return "result";
        } else if (!verifyCode.equals(kaptcha)) {
            model.addAttribute("error", "verifyCode is not correct!");
            log.info("model: {}", model);
            return "result";
        }

        log.info("This is login post request");
        MemberEntity memberFromRepository = memberService.getMemberByMemberName(member.getMemberName());
        if (memberFromRepository == null) {
            model.addAttribute("error", "Member name is not found!");
            log.info("model: {}", model);
            return "result";
        }

        String encryptedPassword = EncryptUtils.encryptPassword(member.getPassword());

        if (!encryptedPassword.equals(memberFromRepository.getPassword())) {
            model.addAttribute("error", "password is wrong!");
            log.info("model: {}", model);
            return "result";
        } else {
            model.addAttribute("member", memberFromRepository);
            httpSession.setAttribute("loginMember", memberFromRepository);
            log.info("model: {}", model);
            return "redirect:/service/home";
        }
    }

    @GetMapping("/getUrl")
    public @ResponseBody String getUrl() {
        return ServletUriComponentsBuilder.fromCurrentRequest().build().toString();
    }

}
