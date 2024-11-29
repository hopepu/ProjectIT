package com.project.it.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
@Setter
@ToString
public class MemberBankDTO{

    private int mno;
    private String account_name;
    private String account;
    private List<String> bank_code = new ArrayList<>();

}
