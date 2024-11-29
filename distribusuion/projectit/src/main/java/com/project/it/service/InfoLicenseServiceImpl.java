package com.project.it.service;

import com.project.it.domain.InfoLicense;
import com.project.it.dto.InfoLicenseIPDTO;
import com.project.it.dto.InfoLicenseOPDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.repository.InfoLicenseRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class InfoLicenseServiceImpl implements InfoLicenseService{
    private final InfoLicenseRepository infoLicenseRepository;


    @Override  //C : 등록
    public Long register(InfoLicenseIPDTO infoLicenseIPDTO) {
        InfoLicense result = InfoLicense.builder()
                .rightName(infoLicenseIPDTO.getRightName())
                .version(infoLicenseIPDTO.getVersion())
                .purpose(infoLicenseIPDTO.getPurpose())
                .copyrightHolder(infoLicenseIPDTO.getCopyrightHolder())
                .price(infoLicenseIPDTO.getPrice())
                .priceUnit(infoLicenseIPDTO.getPriceUnit())
                .maxUserCount(infoLicenseIPDTO.getMaxUserCount())
                .contact(infoLicenseIPDTO.getContact())
                .build();
        InfoLicense saveInfo = infoLicenseRepository.save(result);
        return saveInfo.getLno();
    }

    @Override //R_one : 라이선스 정보 하나만 가져오기
    public InfoLicenseOPDTO getOne(Long lno) {
        InfoLicense findResult = infoLicenseRepository.findById(lno).orElseThrow(EntityExistsException::new);
        InfoLicenseOPDTO findDto = InfoLicenseOPDTO.builder()
                .lno(findResult.getLno())
                .rightName(findResult.getRightName())
                .version(findResult.getVersion())
                .purpose(findResult.getPurpose())
                .copyrightHolder(findResult.getCopyrightHolder())
                .price(findResult.getPrice())
                .priceUnit(findResult.getPriceUnit().getDesc())
                .maxUserCount(findResult.getMaxUserCount())
                .contact(findResult.getContact())
                .build();
        return findDto;
    }

    @Override //R_all : 라이선스 리스트(정보 객체만 담은 리스트-> asset file 과 합쳐져야 함)
    public List<InfoLicenseOPDTO> getList() {
        List<InfoLicense> entityList = infoLicenseRepository.findAll(Sort.by("purpose").ascending());
        //entity->dto
        List<InfoLicenseOPDTO> dtoList = new ArrayList<>();
        entityList.forEach(infoLicense -> {
            InfoLicenseOPDTO dto =  InfoLicenseOPDTO.builder()
                    .lno(infoLicense.getLno())
                    .rightName(infoLicense.getRightName())
                    .version(infoLicense.getVersion())
                    .purpose(infoLicense.getPurpose())
                    .copyrightHolder(infoLicense.getCopyrightHolder())
                    .price(infoLicense.getPrice())
                    .priceUnit(infoLicense.getPriceUnit().getDesc())
                    .maxUserCount(infoLicense.getMaxUserCount())
                    .contact(infoLicense.getContact())
                    .build();
            dtoList.add(dto);
        });
        return dtoList;
    }

    @Override //리스트(+paging)
    public PageResponseDTO<InfoLicenseOPDTO> getListWithPage(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("lno").descending());
        Page<InfoLicense> result = infoLicenseRepository.searchAllByPaging(pageable);
        List<InfoLicenseOPDTO> dtoList = result.get().map(info->{
            InfoLicenseOPDTO dto = InfoLicenseOPDTO.builder()
                    .lno(info.getLno())
                    .rightName(info.getRightName())
                    .version(info.getVersion())
                    .purpose(info.getPurpose())
                    .copyrightHolder(info.getCopyrightHolder())
                    .price(info.getPrice())
                    .priceUnit(info.getPriceUnit().getDesc())
                    .maxUserCount(info.getMaxUserCount())
                    .contact(info.getContact())
                    .build();
            return dto;
        }).collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        return PageResponseDTO.<InfoLicenseOPDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
    }

    @Override //U : 라이선스 정보 변경
    public void update(InfoLicenseIPDTO infoLicenseIPDTO) {
        InfoLicense findResult = infoLicenseRepository.findById(infoLicenseIPDTO.getLno()).orElseThrow(EntityExistsException::new);
        findResult.changePrice(infoLicenseIPDTO.getPrice());
        findResult.changeUnit(infoLicenseIPDTO.getPriceUnit());
        findResult.changeUserCount(infoLicenseIPDTO.getMaxUserCount());
        findResult.changeContact(infoLicenseIPDTO.getContact());
        infoLicenseRepository.save(findResult);
    }

    @Override  //D: 라이선스 삭제_삭제처리(리스트에서 보이지 않게)
    public void remove(Long lno) {
        infoLicenseRepository.updateDelState(lno, true);
    }

    @Override  //D_forever : 라이선스 영구삭제_db에서 삭제
    public void removeForever(Long lno) {
    infoLicenseRepository.deleteById(lno);
    }
}
