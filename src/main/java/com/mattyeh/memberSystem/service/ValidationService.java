package com.mattyeh.memberSystem.service;

public interface ValidationService {
    Integer isEmailValid(String email);
    Integer isMemberNameValid(String memberName);
    Integer isPasswordValid(String password);


}
