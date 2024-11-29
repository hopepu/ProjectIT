package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "files", indexes = {@Index(name = "idx_file_category", columnList = "category"),@Index(name = "idx_file_asset", columnList = "assetNum")})
@ToString
@Getter
@DynamicInsert
@DynamicUpdate
@Setter
public class FileUpload { //라이선스 관련 증빙 파일, 설치파일 등 파일 리스트

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long fno;
    @Column(nullable = false, updatable = false)
    private String category; //구분(폴더)
    @Column(nullable = false, updatable = false)
    private Long assetNum; //카테고리_asset id

    @Column(nullable = false)
    private String originFileName; //원본파일이름
    @Column(nullable = false)
    private String saveFileName; //저장 파일 이름(uuid)
    @Column(nullable = false)
    private String folderPath; //저장경로
    @Column(nullable = false)
    private Long size; //파일크기
    @ColumnDefault("false")
    private boolean deleteOrNot; //삭제여부

}

