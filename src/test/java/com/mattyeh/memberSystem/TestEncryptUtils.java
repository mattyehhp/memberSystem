package com.mattyeh.memberSystem;

import com.mattyeh.memberSystem.utils.EncryptUtils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class TestEncryptUtils {
    @Test
    void TestEncryptPassword() throws NoSuchAlgorithmException {

        System.out.println(EncryptUtils.encryptPassword("12345"));
    }

}
