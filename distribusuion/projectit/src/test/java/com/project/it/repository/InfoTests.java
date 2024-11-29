package com.project.it.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
@Log4j2
public class InfoTests {
   @Autowired
    private InfoLicenseRepository infoLicenseRepository;


    //삭제
    @Test
    public void delInfoTest(){
            infoLicenseRepository.deleteById(17L);


    }


}
