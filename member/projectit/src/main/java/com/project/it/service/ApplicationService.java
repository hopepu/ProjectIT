package com.project.it.service;

import com.project.it.dto.ApplicationDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ApplicationService {
    PageResponseDTO<ApplicationDTO> getList(PageRequestDTO pageRequestDTO);
    ApplicationDTO getOne(String no);
    Long register(ApplicationDTO applicationDTO);
    Long modify(ApplicationDTO applicationDTO);



}
