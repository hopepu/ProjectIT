package com.project.it.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Log4j2
public class JWTCheckFilter extends OncePerRequestFilter {
    //Access Token 체크 필터 : HTTP 헤더 중 Authorization -> <type>Bearer<token>
    //OncePerRequestFilter : 모든 요청에 대해 체크할 때 사용
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //필터로 체크하지 않을 경로, 메서드 지정
        //Preflight 요청 체크 제외
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        String path = request.getRequestURI();
        log.info("check uri----------------------"+path);

        //api/member/경로 호출 체크 제외
        if(path.startsWith("/api/member/")){
            return true;
        }
        //이미지 조회 경로 체크 제외
        if(path.startsWith("/api/products/view/")){
            return true;
        }
        return false;}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("----------------------doFilterInternal------------------");
        String authHeaderStr = request.getHeader("Authorization");

      /*   try {
            String accessToken = authHeaderStr.substring(7); //Bearer accesstoken...
            Map<String, Object> claims = JWTUtil.validationToken(accessToken);
            log.info("JWT claims : "+claims);
            //filterChain.doFilter(request, response); //통과
            //인증정보를 활용하여 객체구성
            String email = (String) claims.get("email");
            String pw = (String) claims.get("pw");
            String nickname = (String) claims.get("nickname");
            Boolean social = (Boolean) claims.get("social");
            List<String> roleNames = (List<String>) claims.get("roleNames");
           MemberDTO memberDTO = new MemberDTO(email, pw, nickname, social, roleNames);
            log.info("memberDTO/Authorities : "+memberDTO+" / "+memberDTO.getAuthorities());
            //getAuthorities() : spring security - user

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberDTO, pw, memberDTO.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }catch (Exception e){
            log.error("JWT Check Error----------------------");
            log.error(e.getMessage());
            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESS_TOKEN"));
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
        }*/

    }


}
