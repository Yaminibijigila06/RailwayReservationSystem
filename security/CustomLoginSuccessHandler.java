package com.railway.RailwayReservationSystem.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        System.out.println("=======================================");
        System.out.println("LOGIN SUCCESS");
        System.out.println("User : " + authentication.getName());

        authentication.getAuthorities().forEach(authority ->
                System.out.println("Role : " + authority.getAuthority()));

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {

            System.out.println("Redirecting to ADMIN Dashboard");

            response.sendRedirect(request.getContextPath() + "/admin/dashboard");

        } else {

            System.out.println("Redirecting to USER Dashboard");

            response.sendRedirect(request.getContextPath() + "/user/dashboard");
        }

        System.out.println("=======================================");
    }
}