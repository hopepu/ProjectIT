package com.project.it.service;

import com.project.it.util.CustomFileUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class FileTests {
    @Autowired
    private CustomFileUtil customFileUtil;


}
