package com.mattyeh.memberSystem.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Controller
public class VerifyUtilController {
    @Autowired
    private Producer producer;

    @GetMapping("/getVerifyImage")
    public void getVerifyImage(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/jpeg"); // 設定為回傳一個 jpg 檔案
        String capText = producer.createText(); // 建立驗證碼文字
        BufferedImage bi = producer.createImage(capText) ;// 使用驗證碼文字建立驗證碼圖片

        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream(); // 取得 ServletOutputStream 實例
            ImageIO.write(bi, "jpg", out); // 輸出圖片
            out.flush();  // 強制請求清空緩存區
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }




}
