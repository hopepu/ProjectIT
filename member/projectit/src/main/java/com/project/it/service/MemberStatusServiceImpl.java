package com.project.it.service;

import com.project.it.domain.*;
import com.project.it.dto.*;
import com.project.it.repository.BankRepository;
import com.project.it.repository.MemberRepository;
import com.project.it.repository.MemberStatusRepository;
import com.project.it.repository.OrganizationRepository;
import com.project.it.util.RoleNameMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberStatusServiceImpl implements MemberStatusService{

    private final MemberStatusRepository memberSR;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrganizationRepository orgRepo;
    private final BankRepository bankRepo;



    @Override
    public String register(MemberStatusDTO R) {
        // 회원 생성
        Member member = Member.builder()
                .email(R.getEmail())
                .pw(passwordEncoder.encode(R.getPassword()))
                .build();

        member.addRole(MemberRole.INTERN);

        memberRepository.save(member);
        member = memberRepository.getWithRoles(R.getEmail());

        // qualifications와 antecedents를 쉼표로 구분된 문자열로 변환하여 List<String>으로 처리
        List<String> qualificationsList = R.getQualifications() != null ? List.of(R.getQualifications().split(",")) : new ArrayList<>();
        List<String> antecedentsList = R.getAntecedents() != null ? List.of(R.getAntecedents().split(",")) : new ArrayList<>();

        // MemberStatus 생성
        MemberStatus memberS = MemberStatus.builder()
                .mno(member.getMno())
                .name(R.getName())
                .birth(R.getBirth())
                .tel(R.getTel())
                .sex(R.getSex())
                .marital_status(R.getMarital_status())
                .children_count(R.getChildren_count())
                .qualifications(qualificationsList) // List<String>으로 전달
                .education(R.getEducation())
                .antecedents(antecedentsList)        // List<String>으로 전달
                .member(member)
                .build();

        // 기본 팀 정보 설정
        Organization organization = Organization.builder()
                .teamName("")
                .member(member)
                .build();

        organization.addOrganizationTeam(OrganizationTeam.AWAIT);

        orgRepo.save(organization);

        memberSR.save(memberS);

        return memberS.getName();
    }



    @Override
    public int read(MemberStatusDTO R){
        log.info(R.toString());
        int result1 = memberRepository.countByEmail(R.getEmail());
        int result2 = memberSR.countByTel(R.getTel());
        return result1 + result2;
    }

    @Override
    public String registerOne(MemberStatusDTO R) {

        Member member = Member.builder()
                .email(R.getEmail())
                .pw(passwordEncoder.encode("0000"))  // Default password (0000)
                .build();


        member.addRole(MemberRole.INTERN);


        log.info(member);


        memberRepository.save(member);


        member = memberRepository.getWithRoles(R.getEmail());


        log.info(member);


        List<String> qualificationsList = R.getQualifications() != null ? List.of(R.getQualifications().split(",")) : new ArrayList<>();
        List<String> antecedentsList = R.getAntecedents() != null ? List.of(R.getAntecedents().split(",")) : new ArrayList<>();



        MemberStatus memberS = MemberStatus.builder()
                .name(R.getName())
                .birth(R.getBirth())
                .tel(R.getTel())
                .sex(R.getSex())
                .marital_status(R.getMarital_status())
                .children_count(R.getChildren_count())
                .qualifications(qualificationsList)
                .education(R.getEducation())
                .antecedents(antecedentsList)
                .member(member)
                .build();


        log.info(memberS);


        Organization organization = Organization.builder()
                .teamName("")
                .member(member)
                .build();


        organization.addOrganizationTeam(OrganizationTeam.AWAIT);

        Bank bank = Bank.builder()
                        .member(member)
                                .account("")
                                        .build();


        bankRepo.save(bank);
        orgRepo.save(organization);

        memberSR.save(memberS);


        return memberS.getName();
    }



    @Override
    public MemberStatusDTO readOne(Long mno) {
        log.info(mno);
        Member member = memberRepository.searchMemberByMno(mno);
        log.info(member);
        MemberStatus memberStatus = memberSR.findByMemberMno(member.getMno());
        log.info(memberStatus);
        log.info(memberStatus.getStatusFileList().toString());

        Organization ot = orgRepo.findByMemberMno(mno);
        String team = ot.getOrganizationTeamList().get(ot.getOrganizationTeamList().size() - 1).toString();
        Bank bank = bankRepo.findByMemberMno(member.getMno());

        String bankcode = "";
        if(bank.getBankCodeList().size()>0){
            bankcode = bank.getBankCodeList().get(0).toString();
        }


        MemberStatusDTO result = MemberStatusDTO.builder()
                .email(member.getEmail())
                .password(member.getPw())
                .start_date(member.getStart_date())
                .memberRole(member.getMemberRoleList().get(0).toString())
                .name(memberStatus.getName())
                .birth(memberStatus.getBirth())
                .tel(memberStatus.getTel())
                .sex(memberStatus.getSex())
                .marital_status(memberStatus.getMarital_status())
                .children_count(memberStatus.getChildren_count())
                .qualifications(String.join(",", memberStatus.getQualifications()))  // List<String>을 ,로 구분된 문자열로 변환
                .antecedents(String.join(",", memberStatus.getAntecedents()))// List<String>을 ,로 구분된 문자열로 변환
                .education(memberStatus.getEducation())
                .mno(memberStatus.getMno())
                .team(team)
                .teamName(ot.getTeamName())
                .account(bank.getAccount())
                .bankCode(bankcode)
                .build();

        if(memberStatus.getStatusFileList().size()!=0){
            List<StatusFile> StatusFilesLists = memberStatus.getStatusFileList();
            result.getUploadFileNames().add(memberStatus.getStatusFileList().get(0).getFileName());
        }

        if(bank.getBankFileList().size()!=0){
            List<BankFile> bankFileList = bank.getBankFileList();
            result.getUploadFileNames2().add(bank.getBankFileList().get(0).getFileName());
        }


        return result;
    }




    @Override
    public String modifyOne(MemberStatusDTO R) {

        Member member = memberRepository.searchMemberByMno(R.getMno());
        log.info(R.getPassword());
        member.setPw(passwordEncoder.encode(R.getPassword()));


        MemberStatus memberSB = memberSR.findByMemberMno(member.getMno());


        List<String> qualificationsList = R.getQualifications() != null ? List.of(R.getQualifications().split(",")) : new ArrayList<>();
        List<String> antecedentsList = R.getAntecedents() != null ? List.of(R.getAntecedents().split(",")) : new ArrayList<>();
        List<String> uploadFileNames = R.getUploadFileNames();
        List<String> uploadFileNames2 = R.getUploadFileNames2();

        Bank bank = bankRepo.findByMemberMno(R.getMno());
        bank.setAccount(R.getAccount());
        bank.addBankCode(BankCode.fromCode(R.getBankCode()));

        if(!uploadFileNames2.isEmpty()){
            uploadFileNames2.forEach(bank::addAppString);}


        MemberStatus memberS = MemberStatus.builder()
                .mno(memberSB.getMno())
                .name(R.getName())
                .birth(R.getBirth())
                .tel(R.getTel())
                .sex(R.getSex())
                .marital_status(R.getMarital_status())
                .children_count(R.getChildren_count())
                .qualifications(qualificationsList)
                .education(R.getEducation())
                .antecedents(antecedentsList)
                .member(member)
                .build();

        log.info(uploadFileNames.toString());

        if(!uploadFileNames.isEmpty()){
            uploadFileNames.forEach(memberS::addAppString);}

        memberSR.save(memberS);
        bankRepo.save(bank);
        memberRepository.save(member);
        log.info(memberS.getStatusFileList().toString());

        return memberS.getName();
    }



    @Override
    public List<MemberStatusDTO> list() {
        List<MemberStatusDTO> listDTO = new ArrayList<>();
        List<MemberStatus> list = memberSR.findAll();

        for (MemberStatus memberStatus : list) {
            Member member = memberRepository.searchMemberByMno(memberStatus.getMember().getMno());
            Organization ot = orgRepo.findByMemberMno(memberStatus.getMember().getMno());
            String team = ot.getOrganizationTeamList().get(ot.getOrganizationTeamList().size() - 1).toString();

            MemberStatusDTO memberStatusDTO = MemberStatusDTO.builder()
                    .email(memberStatus.getMember().getEmail())
                    .mno(memberStatus.getMember().getMno())
                    .start_date(memberStatus.getMember().getStart_date())
                    .name(memberStatus.getName())
                    .tel(memberStatus.getTel())
                    .birth(memberStatus.getBirth())
                    .education(memberStatus.getEducation())
                    .teamName(ot.getTeamName())
                    .team(team)
                    .qualifications(String.join(", ", memberStatus.getQualifications()))  // List<String>을 ,로 구분된 문자열로 변환
                    .antecedents(String.join(", ", memberStatus.getAntecedents()))  // List<String>을 ,로 구분된 문자열로 변환
                    .build();

            listDTO.add(memberStatusDTO);
        }

        return listDTO;
    }




    @Override
    public PageResponseDTO<MemberStatusDTO> getList(PageRequestDTO pageRequestDTO) {
        log.info("getList..............");

        // 페이지 번호가 1 미만일 경우 기본값 1로 처리
        int page = pageRequestDTO.getPage() < 1 ? 1 : pageRequestDTO.getPage();

        // 페이지 번호가 0 이상이어야 하므로, page - 1이 0 미만이 되지 않도록 조건 처리
        Pageable pageable = PageRequest.of(
                page - 1,  // 페이지 번호가 0부터 시작하므로 -1 처리
                pageRequestDTO.getSize(),
                Sort.by("mno").descending());

        String searchQuery = pageRequestDTO.getSearchQuery();
        Page<MemberStatus> result = null;

        // 검색어에 따라 Query 변경
        if (searchQuery != null && !searchQuery.isEmpty()) {
            List<MemberStatus> memberStatusList = new ArrayList<>();

            try {
                // 직위 검색
                MemberRole searchQueryMemberRole = RoleNameMapping.getRoleFromKoreanName(searchQuery);
                List<Member> memberList = memberRepository.searchMembersByRole(searchQueryMemberRole);
                for (Member member : memberList) {
                    MemberStatus ms = memberSR.searchByMno(member.getMno());
                    memberStatusList.add(ms);
                }
            } catch (IllegalArgumentException e) {
                log.warn("Invalid Role search query: " + searchQuery);
            }

            // 직위로 검색이 없으면 부서로 검색
            if (memberStatusList.isEmpty()) {
                try {
                    OrganizationTeam searchQueryOT = OrganizationTeam.fromKoreanName(searchQuery);
                    List<Organization> org = orgRepo.searchOrganizationsByTeam(searchQueryOT);
                    for (Organization organization : org) {
                        MemberStatus ms = memberSR.searchByMno(organization.getMember().getMno());
                        memberStatusList.add(ms);
                    }
                } catch (IllegalArgumentException e) {
                    log.warn("Invalid Team search query: " + searchQuery);
                }
            }

            // 부서로도 검색이 없으면 이름으로 검색
            if (memberStatusList.isEmpty()) {
                result = memberSR.searchByQuery(searchQuery, pageable);
            } else {
                // 검색 결과가 있으면 PageImpl로 처리
                result = new PageImpl<>(memberStatusList, pageable, memberStatusList.size());
            }
        } else {
            // 검색어가 없으면 전체 리스트 조회
            result = memberSR.selectList(pageable);
        }

        List<MemberStatusDTO> list = new ArrayList<>();
        // 페이지 데이터가 있을 때만 처리
        List<MemberStatus> memberStatusList = result.getContent();  // getContent() 사용
        if (!memberStatusList.isEmpty()) {
            log.info("Page result : " + memberStatusList.get(0));

            for (MemberStatus memberStatus : memberStatusList) {
                log.info(memberStatus.getMember().getMno());
                Member member = memberRepository.searchMemberByMno(memberStatus.getMember().getMno());
                String memberRole = member.getMemberRoleList().get(member.getMemberRoleList().size() - 1).toString();
                Organization ot = orgRepo.findByMemberMno(memberStatus.getMember().getMno());
                String team = ot.getOrganizationTeamList().get(ot.getOrganizationTeamList().size() - 1).toString();

                MemberStatusDTO memberStatusDTO = MemberStatusDTO.builder()
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
                list.add(memberStatusDTO);
            }
        } else {
            log.info("No member status data found.");
        }

        long totalCount = result.getTotalElements();

        return PageResponseDTO.<MemberStatusDTO>withAll()
                .dtoList(list)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }




    @Override
    public void modifyMemberRole(MemberTeamDTO mtDTO) {
        Member member = memberRepository.searchMemberByMno(mtDTO.getMno());
        member.addRole(MemberRole.fromString(mtDTO.getMemberRole()));
        memberRepository.save(member);


    }


}
