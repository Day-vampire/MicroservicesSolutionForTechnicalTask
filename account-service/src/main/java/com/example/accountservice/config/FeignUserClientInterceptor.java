package com.example.accountservice.config;

import com.example.accountservice.service.JwtService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignUserClientInterceptor implements RequestInterceptor {

    private final JwtService jwtService;

    @Override
    public void apply(RequestTemplate template) {
        String clientName = template.feignTarget().name();
        if ("user-service".equals(clientName)) {
            System.out.println(clientName);
            template.header("Authorization", "Bearer " + getJwt());
        }
    }
    private String getJwt(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {

           String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());
           System.out.println(token);
           return token;
        }
        System.out.println("***************************************************************");
        return null;
    }

}