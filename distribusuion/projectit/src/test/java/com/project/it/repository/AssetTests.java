package com.project.it.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Log4j2
@Transactional
public class AssetTests {
  @Autowired
    AssetLicenseRepository assetLicenseRepository;

    //리스트(+file)
    @Test
    public void listTest(){

    }

}
