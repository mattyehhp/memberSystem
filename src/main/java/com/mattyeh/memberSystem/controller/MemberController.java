package com.mattyeh.memberSystem.controller;

import com.google.code.kaptcha.Constants;
import com.mattyeh.memberSystem.entity.MemberEntity;
import com.mattyeh.memberSystem.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;


    @PostMapping("/member")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String signUpMember(@RequestParam String email, @RequestParam String memberName, @RequestParam String password, @RequestParam String kaptcha, HttpSession httpSession) {
        String verifyCode = (String)httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!verifyCode.equals(kaptcha)) {
            return "failed";
        }
        if (!memberService.isEmailUsed(email)) {
            MemberEntity member = new MemberEntity();
            member.setMemberName(memberName);
            member.setEmail(email);
            //密碼需Hash後再寫入資料庫，將來登入時也將使用者輸入的密碼Hash後與資料庫比對
            member.setPassword(password);
            memberService.createMember(member);
            return "Success";
        } else {
            return "Email is used";
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
