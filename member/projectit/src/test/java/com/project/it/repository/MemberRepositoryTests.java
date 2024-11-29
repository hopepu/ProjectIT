package com.project.it.repository;

import com.project.it.domain.Member;
import com.project.it.domain.MemberRole;
import com.project.it.domain.MemberStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberStatusRepository memberStatusRepository;




    @Test
    public void testInsertMember() {
        Member member = Member.builder()
                .email("user2@test.com")
                .pw(passwordEncoder.encode("test2"))
                .build();

        member.addRole(MemberRole.STAFF);

        memberRepository.save(member);


    }





    @Test
    public void testRead(){
        String email = "user2@test.com";

        Member member = memberRepository.getWithRoles(email);


        log.info(member);


        log.info("------------------------------------");
        log.info(member);
    }

    @Test
    public void testModify(){
        List<Member> memberList = new ArrayList<>();
        Member member;

        memberList = memberRepository.findAll();

        for(int i = 0; i<=memberList.size()-1; i++){
            memberList.get(i).setPw(passwordEncoder.encode("test"));
            memberRepository.save(memberList.get(i));
        }
    }

    @Test
    public void testDelete(){
        String mno = "1";

        Optional<Member> result = memberRepository.findById(mno);

        Member member = result.orElseThrow();



        memberRepository.save(member);

    }

    @Test
    public void read(){

        Member member = memberRepository.searchMemberByMno(952L);
        log.info(member);
        log.info(member.getMemberRoleList());


    }

}




