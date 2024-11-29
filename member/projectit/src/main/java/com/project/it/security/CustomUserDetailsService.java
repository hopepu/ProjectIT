package com.project.it.security;

import com.project.it.domain.Member;
import com.project.it.dto.MemberDTO;
import com.project.it.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("------------------loadUserByUsername------------ ------");

        log.info("loadUserByUsername's email : " + email);

        Member member = memberRepository.getWithRoles(email);

        log.info("loadUserByUsername's member : " + member);

        if(member==null){
            throw new UsernameNotFoundException("Not Found");
        }

        MemberDTO memberDTO = new MemberDTO(
                member.getEmail(),
                member.getPw(),
                member.getMno(),
                member.getMemberRoleList()
                        .stream()   //스트림 생성 : 저장 요소를 하나씩 참조하여 람다식으로 처리할 수 있또록 해주는 반복자
                        .map(memberRole -> memberRole.name()).collect(Collectors.toList()));

        log.info(memberDTO);

        return memberDTO;

    }
}
