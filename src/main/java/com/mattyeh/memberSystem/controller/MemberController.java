package com.mattyeh.memberSystem.controller;

import com.google.code.kaptcha.Constants;
import com.mattyeh.memberSystem.entity.MemberEntity;
import com.mattyeh.memberSystem.service.MemberService;
import com.mattyeh.memberSystem.utils.EncryptUtils;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/member")
    public String signUpMember(MemberEntity member, @RequestParam String kaptcha, HttpSession httpSession, Model model) throws NoSuchAlgorithmException {

        log.info("signup member: {}", member.toString());
        log.info("kaptcha number: {}", kaptcha);

        String verifyCode = (String)httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        httpSession.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (verifyCode == null) {

            model.addAttribute("error", "sign up failed!");
            log.info("model: {}", model);
            return "result";
        } else if (!verifyCode.equals(kaptcha)) {
            model.addAttribute("error", "verifyCode is not correct!");
            log.info("model: {}", model);
            return "result";
        }
        if (!memberService.isEmailUsed(member.getEmail())) {
            //hash the password
            member.setPassword(EncryptUtils.encryptPassword(member.getPassword()));
            memberService.createMember(member);
            model.addAttribute("member", member);
            httpSession.setAttribute("loginMember", member);
            log.info("model: {}", model);
            return "redirect:service/home";
        } else {
            model.addAttribute("error", "Email is used!");
            log.info("model: {}", model);
            return "result";
        }
    }

    @PostMapping("/member/isUsed")
    public @ResponseBody Map<String, Integer> checkEmailIsUsed(@RequestParam String email, @RequestParam String memberName) {
        //檢查用戶名稱,信箱有無被使用
        Integer isEmailUsed = memberService.isEmailUsed(email) ? 1 : 0;
        Integer isMemberNameUsed = memberService.isMemberNameUsed(memberName) ? 1 : 0;

        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("isEmailUsed", isEmailUsed);
        result.put("isMemberNameUsed", isMemberNameUsed);
        return result;
    }

}
