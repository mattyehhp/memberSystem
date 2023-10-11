package com.mattyeh.memberSystem.interceptor;

import com.mattyeh.memberSystem.entity.MemberEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        MemberEntity member = (MemberEntity) httpSession.getAttribute("loginMember");
        if (member == null) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}
