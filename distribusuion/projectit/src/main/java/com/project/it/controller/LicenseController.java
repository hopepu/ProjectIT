package com.project.it.controller;

import com.project.it.dto.*;
import com.project.it.service.AssetLicenseService;
import com.project.it.service.InfoLicenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/dist/license")
public class LicenseController {
    private final AssetLicenseService assetService;
    private final InfoLicenseService infoService;

    /* info license ----------------------------------------------------------------*/
    //등록 : info license
    @PostMapping("/info")
    public Map<String, Long> registerInfo(InfoLicenseIPDTO infoLicenseIPDTO){
        log.info("(Controller)license info register 실행====등록할 파일 : " + infoLicenseIPDTO);

        Long lno = infoService.register(infoLicenseIPDTO);

        return Map.of("result", lno);
    }
    //license asset register 실행==== AssetLicenseIPDTO(ano=null, rightType=INSTALLATION, contractStatus=NEW, contractDate=2024-11-21, expireDate=9999-12-31, contractCount=30, totalPrice=3000000, comment=, usePurpose=OFFICE, expireYN=false, deleteOrNot=false, totalUseCount=30, licenseId=13, files=[org.springframework.web.multipart.support.StandardMultipartHttpServletRequest$StandardMultipartFile@5d49cefa], fileCount=1)


    //조회 one : info license
    @GetMapping("/info/{lno}")
    public InfoLicenseOPDTO readInfo(@PathVariable(name = "lno") Long lno){
        return infoService.getOne(lno);
    }

    //조회 all : info license
    @GetMapping("/info")
    public PageResponseDTO<InfoLicenseOPDTO> listInfo(PageRequestDTO pageRequestDTO){

        return infoService.getListWithPage(pageRequestDTO);
    }

    //변경 :info license
    @PutMapping("/info/{lno}")
    public Map<String, String> modifyInfo(@PathVariable("lno") Long lno, InfoLicenseIPDTO infoLicenseIPDTO){
        infoLicenseIPDTO.setLno(lno);
        infoService.update(infoLicenseIPDTO);
        return Map.of("RESULT", "SUCCESS");
    }
    //삭제처리(상태변경) : info license
    @DeleteMapping("/info/{lno}")
    public Map<String, String> remove(@PathVariable("lno")Long lno){
        infoService.remove(lno);
        return Map.of("RESULT", "SUCCESS");
    }
    //영구삭제 : info license
    @DeleteMapping("/info_del/{lno}")
    public Map<String, String> removeForeverInfo(@PathVariable("lno")Long lno){
        infoService.removeForever(lno);
        return Map.of("RESULT", "SUCCESS");
    }

  /* asset license ----------------------------------------------------------------*/
    //등록 : asset license
    @PostMapping("/asset")
    public Map<String, Long> registerAsset(AssetLicenseIPDTO assetLicenseIPDTO){
        log.info("(Controller)license asset register 실행====등록할 파일 : " + assetLicenseIPDTO);
        Long ano = assetService.register(assetLicenseIPDTO);
        return Map.of("result", ano);
    }
    //조회 one : asset license(with file list)
    @GetMapping("/asset/{ano}")
    public AssetLicenseOneDTO readAsset(@PathVariable(name = "ano") Long ano){
        return assetService.getOne(ano);
    }
    //조회 all : asset license(with paging+file count)
    @GetMapping("/asset")
    public PageResponseDTO<AssetLicenseListOPDTO> getListAsset(PageRequestDTO pageRequestDTO){
        return assetService.getList(pageRequestDTO);
    }

    @GetMapping("/asset_list") //noPaging
    public List<AssetLicenseListOPDTO> getOnlyListAsset(){
        return assetService.getOnlyList();
    }

    //변경 :asset license(with file list)
    @PutMapping("/asset/{ano}")
    public Map<String, String> modifyAsset(@PathVariable("ano")Long ano, AssetLicenseIPDTO assetLicenseIPDTO){
        assetLicenseIPDTO.setAno(ano);
        assetService.update(assetLicenseIPDTO);
        return Map.of("RESULT", "SUCCESS");
    }
    //삭제처리(상태변경) : asset license(with file list)
    @DeleteMapping("/asset/{ano}")
    public Map<String, String> removeAsset(@PathVariable("ano")Long ano){
        assetService.remove(ano);
        return Map.of("result", "SUCCESS");
    }
    //영구삭제 : asset license(with file list)
    @DeleteMapping("/asset_del/{ano}")
    public Map<String, String> removeForeverAsset(@PathVariable("ano")Long ano){
        assetService.removeForever(ano);
        return Map.of("RESULT", "SUCCESS");
    }

}
