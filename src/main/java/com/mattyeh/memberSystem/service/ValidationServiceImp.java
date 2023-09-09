package com.mattyeh.memberSystem.service;

import com.mattyeh.memberSystem.utils.RegexUtils;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImp implements ValidationService {


    @Override
    public Integer isEmailValid(String email) {
        Integer result;
        boolean isEmailValid = RegexUtils.checkEmail(email);
        result = isEmailValid ? 1 : 0;
        return result;
    }

    @Override
    public Integer isMemberNameValid(String memberName) {
        /*
        result
        0: having invalid character.
        1: not having invalid character.
        2: is too short or too long.
         */
        Integer result;
        Integer memberNameLength = memberName.length();
        if (RegexUtils.checkMemberName(memberName)) {
            return result = 1;
        } else return result = 0;
    }

    @Override
    public Integer isPasswordValid(String password) {
        Integer result;
        result = RegexUtils.checkPassword(password) ? 1 : 0;
        return result;
    }


}
