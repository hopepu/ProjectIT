package com.project.it.dto;

import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class MemberDTO extends User {

    private String email;
    private String pw;
    private List<String> roleNames = new ArrayList<>();

    private Long mno;

    public MemberDTO(String email, String pw,  Long mno, List<String> roleNames){
        super( email, pw,
                roleNames.stream().map(str -> new SimpleGrantedAuthority("ROLE_"+str)).collect(Collectors.toList()));

        this.email=email;
        this.pw=pw;
        this.roleNames=roleNames;
        this.mno=mno;

    }



    public Map<String, Object> getClaims(){
        // JWT 문자열 생성시에 사용하기 위한 Map
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("email", email);
        dataMap.put("pw", pw);
        dataMap.put("roleNames", roleNames);
        dataMap.put("mno", mno);

        return  dataMap;
    }


}
