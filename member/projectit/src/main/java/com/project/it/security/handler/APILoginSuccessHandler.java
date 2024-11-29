package com.project.it.security.handler;

import com.google.gson.Gson;
import com.project.it.dto.MemberDTO;
import com.project.it.util.JWTUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler {
    //로그인 성공 처리 시에 대한 설정
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("------------------------------------------");
        log.info(authentication);
        log.info("------------------------------------------");

        MemberDTO memberDTO = (MemberDTO)authentication.getPrincipal();

        Map<String, Object> claims = memberDTO.getClaims();

        String accessToken = JWTUtil.generateToken(claims, 10); //10분간 유효한 accessToken
        String refreshToken = JWTUtil.generateToken(claims, 60*24); // 24시간 유효한 refreshToken

        claims.put("accessToken", accessToken);
        claims.put("refreshToken", refreshToken);

        claims.remove("pw"); //패스워드 전송하지 않도록 추가

        Gson gson = new Gson();

        String jsonStr = gson.toJson(claims);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonStr);
        printWriter.close();

    }
}
