package com.project.it.repository;

import com.project.it.domain.Member;
import com.project.it.domain.MemberStatus;
import com.project.it.dto.MemberStatusDTO;
import com.project.it.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Log4j2
public class MemberStatusRepositoryTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberStatusRepository statusRepository;

    @Test
    public void testInsertMemberStatus() {
        log.info("test start======================");

        //Member member = memberRepository.getWithRoles("user@test.com");

        //log.info("Member내용 : " + member);

//        //MemberStatus memberStatus = MemberStatus.builder()
//                .mno(1L)
//                .tel(111111111L)
//                .marital_status("기혼")
//                .education("대졸")
//                .qualifications("JAVA")
//                .children_count(1)
//                .name("TESTER")
//                .sex("남")
//                .birth(900106L)
//                .build();
//
//        statusRepository.save(memberStatus);

        //statusRepository.insertStatus("경력", 111111l, 1,"대졸", "기혼", "TESTER", "JAVA", "MAN", 10000000L, 1L);
    }

    @Test
    public void testSearch(){

        MemberStatus memberStatus = statusRepository.findByMemberMno(965L);
        log.info(memberStatus);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1,  //페이지 시작 번호가 0부터 시작하므로
                pageRequestDTO.getSize(),
                Sort.by("mno").descending());

        Page<MemberStatus> result = statusRepository.selectList(pageable);
        log.info(result);


//        result.forEach(data -> {
//            System.out.println(data[0]);
//            Member member = (Member) data[1];
//            System.out.println(data[1]);
//        });

    }
}
