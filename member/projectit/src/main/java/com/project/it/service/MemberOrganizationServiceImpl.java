package com.project.it.service;

import com.project.it.domain.Member;
import com.project.it.domain.MemberStatus;
import com.project.it.domain.Organization;
import com.project.it.domain.OrganizationTeam;
import com.project.it.dto.MemberTeamDTO;
import com.project.it.repository.MemberRepository;
import com.project.it.repository.MemberStatusRepository;
import com.project.it.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberOrganizationServiceImpl implements MemberOrganizationService{

    private final OrganizationRepository orgRepo;
    private final MemberStatusRepository msRepo;

    private final MemberRepository mRepo;

    @Override
    public MemberTeamDTO getOne(Long mno) {
        Organization organization = orgRepo.findByMemberMno(mno);
        log.info(organization);
        Member member = mRepo.searchMemberByMno(mno);
        log.info(member);
        MemberStatus memberStatus = msRepo.findByMemberMno(mno);
        log.info(memberStatus);
        MemberTeamDTO memberTeamDTO = MemberTeamDTO.builder()
                .memberRole(member.getMemberRoleList().get(member.getMemberRoleList().size()-1).toString())
                .name(memberStatus.getName())
                .teamName(organization.getTeamName())
                .mno(member.getMno())
                .team(organization.getOrganizationTeamList().get(organization.getOrganizationTeamList().size()-1).toString())
                .build();
        log.info("서비스 성공 : " + memberTeamDTO);

        return memberTeamDTO;
    }

    @Override
    public void modifyTeam(MemberTeamDTO T) {
        Organization organization = orgRepo.findByMemberMno(T.getMno());
        organization.setTeamName(T.getTeamName());
        organization.addOrganizationTeam(OrganizationTeam.valueOf(T.getTeam()));
        orgRepo.save(organization);

    }
}
