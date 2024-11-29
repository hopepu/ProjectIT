package com.project.it.controller;

import com.project.it.dto.InfoPartnersDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.service.InfoLicenseService;
import com.project.it.service.InfoPartnersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/info")
public class InfoController {
    private final InfoLicenseService serviceLicense;
    private final InfoPartnersService servicePartners;

    //List-고객사
    @GetMapping("/partner_list")
    public PageResponseDTO<InfoPartnersDTO> getPartnerList(PageRequestDTO pageRequestDTO){
        return servicePartners.getList(pageRequestDTO);
    }
}
