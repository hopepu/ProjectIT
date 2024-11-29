package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member_status")
public class MemberStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String birth;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = true)
    private String sex;

    private String marital_status;

    private Integer children_count;

    private String education;

    // List로 변경: 자격증
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_qualifications", joinColumns = @JoinColumn(name = "mno"))
    @Column(name = "qualification")
    private List<String> qualifications = new ArrayList<>();

    // List로 변경: 경력
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "member_antecedents", joinColumns = @JoinColumn(name = "mno"))
    @Column(name = "antecedent")
    private List<String> antecedents = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_mno")
    private Member member;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<StatusFile> StatusFileList = new ArrayList<>();
    // 사진 파일 관련

    public void addAppFile(StatusFile file) {

        file.setOrd(this.StatusFileList.size());
        StatusFileList.add(file);
    }

    public void addAppString(String fileName){

        StatusFile statusFile = StatusFile.builder()
                .fileName(fileName)
                .build();
        addAppFile(statusFile);

    }

    // 첨부파일 데이터를 비우고 다시 이미지 추가하는 방식
    public void clearList() {
        this.StatusFileList.clear();
    }


    // method to add member relationship
    public void addMember(Member member){
        this.member = member;
    }

    // 자격증 추가 메소드
    public void addQualification(String qualification) {
        if (qualification != null && !qualification.trim().isEmpty()) {
            this.qualifications.add(qualification);
        }
    }

    // 경력 추가 메소드
    public void addAntecedent(String antecedent) {
        if (antecedent != null && !antecedent.trim().isEmpty()) {
            this.antecedents.add(antecedent);
        }
    }
}
