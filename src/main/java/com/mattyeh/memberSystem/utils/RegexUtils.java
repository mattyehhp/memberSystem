package com.mattyeh.memberSystem.utils;

import java.util.regex.Pattern;

public class RegexUtils {
    public static boolean checkEmail(String email) {
        String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        return Pattern.matches(regex, email);
    }
    public static boolean checkMemberName(String memberName) {
        String regex = "^[a-zA-Z]\\w{3,}+$";
        return Pattern.matches(regex, memberName);
    }

    public static boolean checkPassword(String password) {

        String regex = "^[a-zA-Z]\\w{5,}$";
        return Pattern.matches(regex, password);
    }

}
