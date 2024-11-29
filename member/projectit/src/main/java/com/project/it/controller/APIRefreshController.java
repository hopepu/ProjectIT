package com.project.it.controller;

import com.project.it.util.CustomJWTException;
import com.project.it.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
public class APIRefreshController {
    // Access Token과 Refresh Token의 검증 및 재발급
    // api/member/refresh 경로 사용
    @RequestMapping("/it/members/refresh")
    public Map<String, Object> refresh(@RequestHeader("Authorization") String authHeader, String refreshToken){
        // REFRESH TOKEN 만료
        if(refreshToken==null){
            throw new CustomJWTException("NULL_REFRESH");
        }

        // Access Token 없거나 잘못된 JWT인 경우
        if (authHeader == null || authHeader.length() < 7){
            throw new CustomJWTException("INVALID_STRING");
        }

        String accessToken = authHeader.substring(7);

        // Access Token이 만료되지 않았다면
        if(checkExpiredToken(accessToken) == false){
            return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
        }

        //Refresh token 검증
        Map<String, Object> claims = JWTUtil.validateToken(refreshToken);

        log.info("refresh ... claims :" + claims);

        String newAccessToken = JWTUtil.generateToken(claims, 10); //10분짜리 새로운 액세스 토큰 만들기

        String newRefreshToken = checkTime((Integer) claims.get("exp")) == true ?
                JWTUtil.generateToken(claims, 60*24) : refreshToken;

        return Map.of("accessToken", newAccessToken, "refreshToken", newRefreshToken);
    }

    // 시간이 1시간 미만으로 남았을 경우 판명 메서드
    private boolean checkTime(Integer exp){
        java.util.Date expDate = new java.util.Date((long)exp*(1000));  // JWT exp 날짜 변환
        long gap = expDate.getTime() - System.currentTimeMillis();      // 현재 시간과의 차이 계산 : 밀리세컨즈로 결과 도출
        long leftMin = gap / (1000 *60);                                // 분단위로 환산
        return leftMin < 60;                                            // 60분 미만인지 논리값으로 return
    }

    private boolean checkExpiredToken(String token){
        try{
            JWTUtil.validateToken(token);
        } catch(CustomJWTException ex){
            if(ex.getMessage().equals("Expired")){
                return true;
            }
        }
        return false;
    }

}
