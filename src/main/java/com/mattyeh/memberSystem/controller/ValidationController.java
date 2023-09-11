package com.mattyeh.memberSystem.controller;

import com.google.code.kaptcha.Constants;
import com.mattyeh.memberSystem.service.ValidationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ValidationController {
    @Autowired
    ValidationService validationService;
    @PostMapping("/checkValidation/memberName")
    public @ResponseBody String checkMemberName(@RequestParam String memberName) {
        Integer memberNameCheckState = validationService.isMemberNameValid(memberName);
        if (memberNameCheckState == 0) {
            return "member name has invalid character";
        } else if (memberNameCheckState == 1) {
            return "valid";
        }
        return null;
    }

    @PostMapping("/checkValidation/email")
    public @ResponseBody String checkEmail(@RequestParam String email) {
        Integer emailCheckState = validationService.isEmailValid(email);
        if (emailCheckState == 1) {
            return "Email is valid";
        } else return "Pleas enter a valid email";
    }

    @PostMapping("/checkValidation/password")
    public @ResponseBody String checkPassword(@RequestParam String password) {
        Integer passwordCheckState = validationService.isPasswordValid(password);
        if (passwordCheckState == 1) {
            return "password is valid";
        } else return "請輸入以字母開頭，長度在6-18之間，只能包含字符、數字和下劃線的密碼";
    }


    @PostMapping("/checkValidation")
    public @ResponseBody Map<String, Integer> checkValidation(@RequestParam String password, @RequestParam String email, @RequestParam String memberName, HttpSession httpSession, @RequestParam String kaptcha) {
        Integer isPasswordValid = validationService.isPasswordValid(password);
        Integer isMemberNameValid = validationService.isMemberNameValid(memberName);
        Integer isEmailValid = validationService.isEmailValid(email);
        Map<String, Integer> result = new HashMap<>();

        //verify code
        String verifyCode = (String)httpSession.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!verifyCode.equals(kaptcha)) {
            result.put("isVerifyCodeCorrect", 0);
        } else {
            result.put("isVerifyCodeCorrect", 1);
        }


        Integer isMemberValid = 0;
        Integer checkState = isMemberNameValid + isPasswordValid + isEmailValid;
        if (checkState == 3) {
            isMemberValid = 1;
        }

        result.put("isPasswordValid", isPasswordValid);
        result.put("isMemberNameValid", isMemberNameValid);
        result.put("isEmailValid", isEmailValid);
        result.put("isMemberValid", isMemberValid);
        return result;
    }





}
