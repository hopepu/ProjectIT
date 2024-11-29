package com.project.it.service;

import com.project.it.domain.AccountObject;
import com.project.it.domain.AssetLicense;
import com.project.it.domain.ListAccount;
import com.project.it.dto.*;
import com.project.it.repository.AssetLicenseRepository;
import com.project.it.repository.ListAccountRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ListAccountServiceImpl implements ListAccountService{
    private final ListAccountRepository repoListAccount;
    private final AssetLicenseRepository repoAssetLicense;

    private final AssetLicense assetInit = AssetLicense.builder()
            .ano(0L)
            .totalUseCount(9999)
            .build();
    @Override //등록 (no asset)
    public Long register(ListAccountIPDTO listAccountIPDTO) {
        log.info("받은 account : "+listAccountIPDTO.getAssetId());
        //License 찾기
       // AssetLicense findAsset = assetInit; //기본값(0번)

        //entity 준비
        ListAccount entity = ListAccount.builder()
                .siteUrl(listAccountIPDTO.getSiteUrl())
                .siteName(listAccountIPDTO.getSiteName())
                .useState(listAccountIPDTO.isUseState())
                .userAuthentication(listAccountIPDTO.isUserAuthentication())
                .build();
        //계정리스트 변환, 추가
        List<AccountObjectIPDTO> dtoList = listAccountIPDTO.getAccountObjectList();
        dtoList.stream().forEach(dto->{
            entity.addAccountList(dtoToEntity(dto));
        });
        repoListAccount.save(entity);
        return entity.getSiNum();
    }

    @Override //등록 (asset)
    public Long registerAsset(ListAccountIPDTO listAccountIPDTO) {
        log.info("받은 account : "+listAccountIPDTO.getAssetId());
        //License 찾기
         Optional<AssetLicense> license  = repoAssetLicense.findById(listAccountIPDTO.getAssetId());
            AssetLicense findAsset=license.get();

        //entity 준비
        ListAccount entity = ListAccount.builder()
                .siteUrl(listAccountIPDTO.getSiteUrl())
                .siteName(listAccountIPDTO.getSiteName())
                .userAuthentication(listAccountIPDTO.isUserAuthentication())
                .assetLicense(findAsset)
                .build();
        //계정리스트 변환, 추가
        List<AccountObjectIPDTO> dtoList = listAccountIPDTO.getAccountObjectList();
        dtoList.stream().forEach(dto->{
            entity.addAccountList(dtoToEntity(dto));
        });
        repoListAccount.save(entity);
        return entity.getSiNum();
    }

    @Override //조회 All
    public PageResponseDTO<ListAccountOPDTO> getList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("siNum").descending() );

        Page<ListAccount> result = repoListAccount.accountList(pageable);


        List<ListAccountOPDTO> dtoList = result.get().map(entity->{

            ListAccountOPDTO listAccountOPDTO = ListAccountOPDTO.builder()
                    .siNum(entity.getSiNum())
                    .siteName(entity.getSiteName())
                    .useState(entity.isUseState())
                    .siteUrl(entity.getSiteUrl())
                    .userAuthentication(entity.isUserAuthentication())
                    .accountObjectList(changeObjectDto(entity.getAccountObjectList()))
                    .build();
            return listAccountOPDTO;
        }).collect(Collectors.toList());

        log.info("service 페이지 출력 : ++++++++++++++++"+dtoList);
        long totalCount = result.getTotalElements();
        return PageResponseDTO.<ListAccountOPDTO>withAll()
                .dtoList(dtoList).totalCount(totalCount)
                .pageRequestDTO(pageRequestDTO).build();
    }

    @Override //조회 One
    public ListAccountOPDTO getOne(Long siNum) {
        Optional<ListAccount> result = repoListAccount.selectOne(siNum);
        ListAccount listEntity = result.orElseThrow();
        ListAccountOPDTO dto = ListAccountOPDTO.builder()
                .siNum(listEntity.getSiNum())
                .siteName(listEntity.getSiteName())
                .siteUrl(listEntity.getSiteUrl())
                .useState(listEntity.isUseState())
                .userAuthentication(listEntity.isUserAuthentication())
                .accountObjectList(changeObjectDto(listEntity.getAccountObjectList()))
                .build();
        return dto;
    }

    @Override //변경(사이트 주소, 인증여부, 계정)
    public void modify(ListAccountIPDTO listAccountIPDTO) {
        Optional<ListAccount> result = repoListAccount.selectOne(listAccountIPDTO.getSiNum());
        ListAccount listEntity = result.orElseThrow();
        listEntity.changeSiteUrl(listAccountIPDTO.getSiteUrl());
        listEntity.changeUserAuthentication(listAccountIPDTO.isUserAuthentication());
        repoListAccount.save(listEntity);
    }

    @Override //변경(계정변경)
    public void modifyAccount(List<AccountObjectIPDTO> accounts, Long siNum) {
        Optional<ListAccount> result = repoListAccount.selectOne(siNum);
        ListAccount listEntity = result.orElseThrow();
        listEntity.clearList();
        accounts.stream().forEach(dto->{
            listEntity.addAccountList(dtoToEntity(dto));
        });
        repoListAccount.save(listEntity);
    }

    @Override  //삭제(사용안함)
    public void remove(Long siNum) {
    repoListAccount.updateDelState(siNum, false);

    }

    @Override //삭제(데이터 삭제)
    public void removeForever(Long siNum) {
    repoListAccount.deleteByAccountList(siNum);
    }

    //enum 처리(entity ->output Dto)
    private List<AccountObjectOPDTO> changeObjectDto(List<AccountObject> inputDtoList){
        List<AccountObjectOPDTO> opList = new ArrayList<>();
        for (AccountObject entity: inputDtoList){
            AccountObjectOPDTO opDto = new AccountObjectOPDTO(
                    entity.getAc_id(), entity.getAc_pw(), entity.getAccountType()
            );
            opList.add(opDto);
        }
        return opList;
    }

    //input Dto -> entity
    private AccountObject dtoToEntity(AccountObjectIPDTO inputDto){
         AccountObject entity = AccountObject.builder()
                    .ac_id(inputDto.getAc_id())
                    .ac_pw(inputDto.getAc_pw())
                    .accountType(inputDto.getAccountType())
                    .build();
        return entity;
    }
}
