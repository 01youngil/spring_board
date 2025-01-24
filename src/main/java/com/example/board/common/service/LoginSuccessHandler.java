package com.example.board.common.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        SecurityContextHolder -> SecurityContext -> Authentication
        HttpSession httpSession = request.getSession();
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        String role = null;
        for(GrantedAuthority g : authentication1.getAuthorities()){
            role = g.getAuthority();
        }
        httpSession.setAttribute("role", role);
        httpSession.setAttribute("email", authentication1.getName());
        response.sendRedirect("/");
    }
}
