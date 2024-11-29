package com.project.it.service;

import com.project.it.dto.MemberTeamDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberOrganizationService {
    //직위 및 팀 정보 가져오기
    MemberTeamDTO getOne(Long mno);

    void modifyTeam(MemberTeamDTO T);

}
