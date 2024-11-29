package com.project.it.service;

import com.project.it.domain.*;
import com.project.it.dto.*;
import com.project.it.repository.MemberRepository;
import com.project.it.repository.MemberStatusRepository;
import com.project.it.repository.OrganizationRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Log4j2
public class MemberServiceTests {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberStatusRepository memberStatusRepository;
    @Autowired
    OrganizationRepository orgRepo;

    @Autowired
    MemberStatusService memberStatusService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Test
    @DisplayName("패스워드 암호화 테스트")
    void passwordEncode() {
        String rawPassword = "12345678";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        boolean result = passwordEncoder.matches(rawPassword, encodedPassword);
        log.info("===========================================================");
        log.info(result);
    }

    @Test
    public void testRegi(){
        Member member = memberRepository.searchMemberByMno(102L);
        member.setPw(passwordEncoder.encode("0000"));

        memberRepository.save(member);

        member = memberRepository.searchMemberByMno(99L);
        member.setPw(passwordEncoder.encode("0000"));
        memberRepository.save(member);


    }



    @Test
    public void testRegister(){
        for(int i = 0; i < 100; i++){
        Member member = Member.builder()
                .email("user" + i + "@test.com")
                .pw(passwordEncoder.encode("test"))
                .build();

        memberRepository.save(member);

        member = memberRepository.getWithRoles("user" + i + "@test.com");
        MemberStatus memberS =
                MemberStatus.builder()
                        .mno(member.getMno())
                        .name("user"+i)
                        .birth("100000")
                        .tel("00000000000")
                        .sex("male")
                        .marital_status("미혼")
                        .children_count(0)
                        .education("대졸")
                        .member(member)
                        .build();

        memberStatusRepository.save(memberS);
    }}

    @Test
    public void changeBirth() {
        for (int i = 0; i < 100; i++) {
//            Member member = memberRepository.getStatus(952L + i);
//            MemberStatus memberS = memberStatusRepository.getOne(member);
//            member = memberRepository.getStatus(952L + i);
//            member.addRole(MemberRole.INTERN);
//            memberRepository.save(member);
//
//            log.info(memberS);
//
//            memberStatusRepository.save(memberS);
        }
    }

    @Test
    public void read(){
     //Member member = memberRepository.getStatus(952L);
     //MemberStatus memberStatus = memberStatusRepository.getOne(member);
     //log.info(memberStatus);
        }

    @Test
    public void readone(){
        MemberStatus memberStatus = memberStatusRepository.findByMemberMno(952L);
        log.info(memberStatus);
        }

    @Test
    public void list(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        log.info(memberStatusService.getList(pageRequestDTO));

    }

    @Test
    public void insertRole(){
        List<Member> member = memberRepository.findAll();
        for(int i = 0; i <= member.size()-1; i++){
//            member.get(i).addRole(MemberRole.INTERN);
//            memberRepository.save(member.get(i));

            Organization organization = Organization.builder()
                    .member(member.get(i))
                    .teamName("")
                    .build();

            log.info(organization);

            organization.addOrganizationTeam(OrganizationTeam.AWAIT);

            log.info(organization);

            orgRepo.save(organization);
        }

    }

    @Test
    public void getOneTest() {
        Long mno = 1L;
        MemberStatus memberStatus = memberStatusRepository.findByMemberMno(mno);
        Member member = memberRepository.searchMemberByMno(mno);

        log.info(memberRepository.searchMemberByMno(mno).getMemberRoleList().get(member.getMemberRoleList().size()-1));
        log.info(memberStatus);

    }

    @Test
    public void addRole() {
        List<Member> member = memberRepository.findAll();
        for (int i = 0; i <= member.size() - 1; i++) {
            Long Mno = member.get(i).getMno();
            Organization organization = orgRepo.findByMemberMno(Mno);
            organization.addOrganizationTeam(OrganizationTeam.AWAIT);
            orgRepo.save(organization);


        }

    }

    @Test
    public void memberRoleAddTest(){
        Member member = memberRepository.searchMemberByMno(1L);
        log.info(member.getMemberRoleList());
        member.addRole(MemberRole.fromString("STAFF"));
        log.info(member.getMemberRoleList());
        memberRepository.save(member);

    }

    @Test
    public void listTest() {
        List<MemberStatusDTO> listDTO = new ArrayList<>();

        MemberStatusDTO memberStatusDTO;
        List<MemberStatus> list = new ArrayList<>();
        list = memberStatusRepository.findAll();
        int count = (int)memberStatusRepository.count();
        MemberStatus memberStatus;

        for(int i = 0; i <= count-1; i++ ){
            memberStatus = list.get(i);
            Member member = memberRepository.searchMemberByMno(memberStatus.getMno());
            String memberRole = member.getMemberRoleList().get(member.getMemberRoleList().size()-1).toString();
            Organization ot = orgRepo.findByMemberMno(memberStatus.getMno());
            String team = ot.getOrganizationTeamList().get(ot.getOrganizationTeamList().size()-1).toString();

            memberStatusDTO = MemberStatusDTO.builder()
                    .email(memberStatus.getMember().getEmail())
                    .mno(memberStatus.getMember().getMno())
                    .start_date(memberStatus.getMember().getStart_date())
                    .memberRole(memberRole)
                    .name(memberStatus.getName())
                    .tel(memberStatus.getTel())
                    .birth(memberStatus.getBirth())
                    .education(memberStatus.getEducation())
                    .teamName(ot.getTeamName())
                    .team(team)
                    .build();

            listDTO.add(memberStatusDTO);
        }
        log.info("serviceImpl 완료");
        log.info(listDTO);

    }
}
