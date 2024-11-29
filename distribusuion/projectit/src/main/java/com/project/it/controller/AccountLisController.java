package com.project.it.controller;

import com.project.it.dto.*;
import com.project.it.service.ListAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/dist/account")
public class AccountLisController {
    private final ListAccountService serviceAccount;

    //등록 - no asset
    @PostMapping("/register")
    public Map<String, Long> registerAccount(@RequestBody ListAccountIPDTO listAccountIPDTO){
        log.info("controller=========받은 info : "+listAccountIPDTO);
        Long newSiNum = serviceAccount.register(listAccountIPDTO);
        return Map.of("result", newSiNum);
    }

    //등록 - asset 연결
    @PostMapping("/register_asset")
    public Map<String, Long> registerAssetAccount(ListAccountIPDTO listAccountIPDTO){
        Long newSiNum = serviceAccount.registerAsset(listAccountIPDTO);
        return Map.of("result", newSiNum);
    }

    //전체 파일 리스트
    @GetMapping("/list")
    public PageResponseDTO<ListAccountOPDTO> getAllList(PageRequestDTO pageRequestDTO){
        return serviceAccount.getList(pageRequestDTO);
    }

    //하나 조회
   @GetMapping("/{siNum}")
    public ListAccountOPDTO getOneAccount(@PathVariable("siNum")Long siNum){
        return serviceAccount.getOne(siNum);
    }

    //리스트 정보변경(주소, 사용여부, 인증여부)
    @PutMapping("/modify_list")
    public Map<String, String> modifyList(ListAccountIPDTO listAccountIPDTO){
        serviceAccount.modify(listAccountIPDTO);
        return Map.of("result","SUCCESS");
    }

    //리스트-계정만 변경
    @PutMapping("/modify_account/{siNum}")
    public Map<String, String> modifyAccount(List<AccountObjectIPDTO> accounts, @PathVariable("siNum") Long siNum){
        serviceAccount.modifyAccount(accounts, siNum);
        return Map.of("result","SUCCESS");
    }

    //삭제처리
    @DeleteMapping("/usenot/{siNum}")
    public Map<String, String> useNotAccount(@PathVariable("siNum")Long siNum){
        serviceAccount.remove(siNum);
        return Map.of("result","SUCCESS");
    }

    //영구삭제
    @DeleteMapping("/{siNum}")
    public Map<String, String> removeAccount(@PathVariable("siNum")Long siNum){
        serviceAccount.removeForever(siNum);
        return Map.of("result","SUCCESS");
    }
}
