package com.project.it.controller;

import com.project.it.dto.*;
import com.project.it.service.AssetComputerService;
import com.project.it.service.InfoComputerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/dist/computer")
public class ComputerController {
    private final AssetComputerService assetService;
    private final InfoComputerService infoService;
    /* info ----------------------------------------------------------------*/
    //등록
    @PostMapping("/info")
    public Map<String, Long> registerInfo(InfoComputerDTO infoComputerDTO){
        log.info("(Controller)computer info register 실행====등록할 파일 : " + infoComputerDTO);
        Long cino = infoService.register(infoComputerDTO);

        return Map.of("result", cino);
    }


    //조회 one : info license
    @GetMapping("/info/{cino}")
    public InfoComputerDTO readInfo(@PathVariable(name = "cino") Long cino){
        return infoService.getOne(cino);
    }

    //조회 all : info license
    @GetMapping("/info")
    public PageResponseDTO<InfoComputerDTO> listInfo(PageRequestDTO pageRequestDTO){

        return infoService.getList(pageRequestDTO);
    }



    //영구삭제
    @DeleteMapping("/info_del/{cino}")
    public Map<String, String> removeForeverInfo(@PathVariable("cino")Long cino){
        infoService.remove(cino);
        return Map.of("RESULT", "SUCCESS");
    }

    /* asset license ----------------------------------------------------------------*/
    //등록 : asset license
    @PostMapping("/asset")
    public Map<String, Long> registerAsset(AssetComputerDTO assetComputerDTO){
        log.info("(Controller)computer asset register 실행====등록할 파일 : " + assetComputerDTO);
        Long cno = assetService.register(assetComputerDTO);
        return Map.of("result", cno);
    }
    //조회 one : asset license(with file list)
    @GetMapping("/asset/{cno}")
    public AssetComputerOneDTO readAsset(@PathVariable(name = "cno") Long cno){
        return assetService.getOne(cno);
    }
    //조회 all : asset license(with paging+file count)
    @GetMapping("/asset")
    public PageResponseDTO<AssetComputerOneDTO> getListAsset(PageRequestDTO pageRequestDTO){
        return assetService.getList(pageRequestDTO);
    }
    @GetMapping("/asset_list")
    public List<AssetComputerOneDTO> getOnlyListAsset(){
        return assetService.getOnlyList();
    }


    //변경 :asset license(with file list)
    @PutMapping("/asset/{cno}")
    public Map<String, String> modifyAsset(@PathVariable("cno")Long cno, AssetComputerDTO assetComputerDTO){
        assetComputerDTO.setCno(cno);
        assetService.modify(assetComputerDTO);
        return Map.of("RESULT", "SUCCESS");
    }
    //삭제처리(상태변경) : asset license(with file list)
    @DeleteMapping("/asset/{cno}")
    public Map<String, String> removeAsset(@PathVariable("cno")Long cno){
        assetService.remove(cno);
        return Map.of("RESULT", "SUCCESS");
    }
    //영구삭제 : asset license(with file list)
    @DeleteMapping("/asset_del/{cno}")
    public Map<String, String> removeForeverAsset(@PathVariable("cno")Long cno){
        assetService.removeForever(cno);
        return Map.of("RESULT", "SUCCESS");
    }

}
