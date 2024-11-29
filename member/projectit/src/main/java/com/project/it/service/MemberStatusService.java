package com.project.it.service;

import com.project.it.dto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemberStatusService{

    String register(MemberStatusDTO R);

    String registerOne(MemberStatusDTO R);

    int read(MemberStatusDTO R);

    MemberStatusDTO readOne(Long mno);

    String modifyOne(MemberStatusDTO mdto);

    List<MemberStatusDTO> list();

    PageResponseDTO<MemberStatusDTO> getList(PageRequestDTO pageRequestDTO);

    void modifyMemberRole(MemberTeamDTO memberTeamDTO);



}
