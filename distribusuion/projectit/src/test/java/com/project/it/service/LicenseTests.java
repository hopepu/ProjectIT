package com.project.it.service;

import com.project.it.constant.ContractStatus;
import com.project.it.constant.PriceUnit;
import com.project.it.constant.RightType;
import com.project.it.constant.UsePurpose;
import com.project.it.dto.*;
import com.project.it.util.CustomFileUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class LicenseTests {
    @Autowired
    private InfoPartnersService partnersService;
    @Autowired
    private InfoLicenseService serviceInfo;
    @Autowired
    private AssetLicenseService serviceAsset;
    @Autowired
    private CustomFileUtil fileUtil;
    @Autowired
    private ModelMapper modelMapper;



    //고객사 등록(Dummy)
    @Test
    public void insertPartnerTest(){
        DecimalFormat front = new DecimalFormat("000");
        DecimalFormat middle = new DecimalFormat("00");
        DecimalFormat last = new DecimalFormat("00000");
        DecimalFormat four = new DecimalFormat("0000");
        IntStream.rangeClosed(1, 20).forEach(i->{
            InfoPartnersDTO infoPartnersDTO = InfoPartnersDTO.builder()
                    .comName("회사명"+i).coNum(front.format(i)+"-"+middle.format(i)+"-"+last.format(i))
                    .phone(front.format(i)+"-"+four.format(i)+"-"+four.format(i)).site("www.homepage"+i+".com").address("주소"+i)
                    .bizType("업종"+(i/10))
                    .build();
            Long cno = partnersService.register(infoPartnersDTO);
            log.info("등록된 회사 : "+cno);
        });

    }

    //라이선스 -info등록
    @Test
    public void insertInfoLicenseTest(){
        InfoLicenseIPDTO dto1 = InfoLicenseIPDTO.builder()
                .rightName("IntelliJ IDEA Ultimate")
                .version("IntelliJ IDEA")
                .purpose("programming")
                .copyrightHolder("JetBrains s.r.o.")
                .price(826680)
                .priceUnit(PriceUnit.PERSON)
                .maxUserCount(1)
                .contact("https://www.jetbrains.com/ko-kr/idea/buy/?section=commercial&billing=yearly")
                .build();

        InfoLicenseIPDTO dto2 = InfoLicenseIPDTO.builder()
                .rightName("Adobe Creative Cloud")
                .version("Creative Cloud")
                .purpose("design")
                .copyrightHolder("Adobe")
                .price(104000)
                .priceUnit(PriceUnit.MONTHLY)
                .maxUserCount(1)
                .contact("https://www.adobe.com/kr/creativecloud/plans.html")
                .build();
        Long ano1 = serviceInfo.register(dto1);
        Long ano2 = serviceInfo.register(dto2);
        log.info("등록된 ano :"+ano1 + "/"+ano2);
    }


    //info dummy
    @Test
    public void infoDummyInsertTest(){
        DecimalFormat fourNum = new DecimalFormat("0000");
        List<InfoLicenseIPDTO> list = new ArrayList<>();
        LongStream.rangeClosed(1, 10).forEach(i->{
            InfoLicenseIPDTO dto = InfoLicenseIPDTO.builder()
                    .rightName("라이선스"+fourNum.format(i))
                    .version("version"+i)
                    .purpose("dummy")
                    .copyrightHolder("dummy")
                    .price((int) i*10000)
                    .priceUnit(PriceUnit.MONTHLY)
                    .maxUserCount(1)
                    .contact("DummyData")
                    .build();
            serviceInfo.register(dto);
            list.add(dto);
        });
        list.forEach(infoLicenseIPDTO -> log.info(infoLicenseIPDTO));
    }

    //info 수정
    @Test
    public void modInfoLicense(){
        InfoLicenseOPDTO findInfo = serviceInfo.getOne(2L);
        InfoLicenseIPDTO modinfo = modelMapper.map(findInfo, InfoLicenseIPDTO.class);
        modinfo.setPriceUnit(PriceUnit.MONTHLY);
        serviceInfo.update(modinfo);
        log.info(modinfo);
    }

    //Asset 등록
    @Test
    public void insertAssetTest(){
       InfoLicenseOPDTO insertInfo = serviceInfo.getOne(1L);
        LocalDate today = LocalDate.now();
        AssetLicenseIPDTO dto = AssetLicenseIPDTO.builder()
                .rightType(RightType.LICENSE)
                .usePurpose(UsePurpose.DEVELOPMENT)
                .contractStatus(ContractStatus.NEW)
                .contractCount(5)
                .contractDate(today)
                .expireDate(today.plusYears(1L))
                .totalPrice(insertInfo.getPrice()*5)
                .licenseId(1L)
                .build();
        serviceAsset.register(dto);
    }


    //Asset dummy
    @Test
    public void assetDummyInsertTest(){
        //DecimalFormat fourNum = new DecimalFormat("0000");
        LocalDate today = LocalDate.now();
        List<AssetLicenseIPDTO> list = new ArrayList<>();
        LongStream.rangeClosed(1L, 5L).forEach(i->{

            AssetLicenseIPDTO dto = AssetLicenseIPDTO.builder()
                    .rightType(RightType.LICENSE)
                    .usePurpose(UsePurpose.ETC)
                    .contractStatus(ContractStatus.NEW)
                    .contractCount(5+(int)i)
                    .totalPrice((5+(int)i)*10000)
                    .contractDate(today)
                    .expireDate(today.plusMonths(1L))
                    .licenseId(i)
                    .build();
            serviceAsset.register(dto);
            list.add(dto);
        });
        list.forEach(infoLicenseDTO -> log.info(infoLicenseDTO));
    }


    //리스트
    @Test
    public void assetListTest(){
        PageRequestDTO requestDTO = new PageRequestDTO();
        requestDTO.setPage(1);
        requestDTO.setSize(5);
        serviceAsset.getList(requestDTO);
        PageResponseDTO<AssetLicenseListOPDTO> assetList = serviceAsset.getList(requestDTO);
        List<AssetLicenseListOPDTO> list = assetList.getDtoList();
        log.info("리스트 개수 : "+list.size());
        list.forEach(asset->{
            log.info(asset);
        });
    }

    //enum test
    @Test
    public void eunmInsertTest(){
        String desc = "인";


        log.info("=======출력결과 : ");
    }


}
