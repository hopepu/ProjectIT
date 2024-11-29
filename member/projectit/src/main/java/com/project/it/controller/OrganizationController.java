package com.project.it.controller;

import com.project.it.dto.*;
import com.project.it.service.MemberOrganizationService;
import com.project.it.service.MemberStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/it/organization")
public class OrganizationController {

    private final MemberStatusService memberSS;
    private final MemberOrganizationService memberOS;


    @GetMapping("")
    public List listRead(){      
        return memberSS.list();
    }

    @GetMapping("/page")
    public PageResponseDTO<MemberStatusDTO> pagingList(PageRequestDTO pageRequestDTO){
        return memberSS.getList(pageRequestDTO);
    }

    @GetMapping("/{mno}")
    public MemberTeamDTO oneRead(@PathVariable(name = "mno") Long mno){
        return memberOS.getOne(mno);
    }

    @PostMapping("/modify")
    public Map<String, String> oneModify(MemberTeamDTO T){
        log.info("modify 내용 " + T);

        memberSS.modifyMemberRole(T);
        memberOS.modifyTeam(T);


        String msg = "수정이 완료되었습니다.";
        log.info(msg);

        return Map.of("RESULT", msg);
    }


}
