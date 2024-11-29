package com.project.it.service;

import com.project.it.constant.ComputerType;

import com.project.it.domain.InfoComputer;
import com.project.it.dto.AssetComputerDTO;
import com.project.it.dto.InfoComputerDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class ComputerServiceTests {
    @Autowired
    private AssetComputerService serviceAsset;
    @Autowired
    private InfoComputerService serviceInfo;

    //입력-info
    @Test
    public void insertTest(){
        InfoComputerDTO dto = InfoComputerDTO.builder()
                .productName("델 컴퓨터 인스피론 3030 SFF 슬림형 14세대 i9")
                .maker("DELL")
                .type(ComputerType.DESKTOP)
                .cpu("코어i9-14900")
                .gpu("NVMeM.2")
                .ram("32GB")
                .osType("Windows11 Pro")
                .capacity("1TB")
                .price(2040000)
                .build();
        serviceInfo.register(dto);
    }

    //dummy
    @Test
    public void insertInfoDummy(){
        IntStream.rangeClosed(1, 5).forEach(i->{
            InfoComputerDTO dto = InfoComputerDTO.builder()
                    .productName("DeskTop"+i)
                    .maker("제조사"+i)
                    .type(ComputerType.DESKTOP)
                    .cpu("CPU"+i)
                    .gpu("GPU"+i)
                    .ram("64GB")
                    .osType("Windows11")
                    .capacity("1TB")
                    .price(1000000+ i)
                    .build();
            serviceInfo.register(dto);
        });
    }

    //삭제
    @Test
    public void deleteInfoTest(){
        LongStream.rangeClosed(1L, 8L).forEach(i->{
            serviceInfo.remove(i);
        });
    }

    @Test
    public void insertAssetTest(){
        InfoComputerDTO infoComputerDTO = serviceInfo.getOne(5L);
        log.info(infoComputerDTO);

      AssetComputerDTO assetComputerDTO = AssetComputerDTO.builder()
                .cino(infoComputerDTO.getCino())
                .productName(infoComputerDTO.getProductName())
                .price(infoComputerDTO.getPrice())
                .purpose("디자인용")
                .contractCount(2)
                .totalPrice(infoComputerDTO.getPrice()*2)
                .contractDate(LocalDate.now())
                .build();
        serviceAsset.register(assetComputerDTO);
        Long newCno = assetComputerDTO.getCno();
        log.info("--------------등록 cno"+newCno);
    }

    @Test
    public void deleteAllAssetTest(){
        serviceAsset.removeForever(1L);
    }

}
