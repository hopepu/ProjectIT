package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Application")
@EntityListeners(AuditingEntityListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long no;
    // 입사 번호
    private String name;
    // 입사 이름
    private String phoneNum;
    // 전화번호
    private String mail;
    // 이메일
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime start_date;
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<JoinStatus> joinStatusList = new ArrayList<>();
    // 입사상태
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<OrganizationTeam> organizationTeamList = new ArrayList<>();
    // 희망부서
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<ApplicationFile> applicationFileList = new ArrayList<>();
    // 입사지원서 파일 관련

    //희망부서 내용 추가
    public void addOTL(OrganizationTeam organizationTeam){
        organizationTeamList.add(organizationTeam);
    }

    //입사상태 내용 변경
    public void changeJoinStatus(JoinStatus joinStatus){
        if(joinStatusList.size()>=1){
            joinStatusList.remove(0);
        }
        joinStatusList.add(joinStatus);
    }

    public void addAppFile(ApplicationFile file) {

        file.setOrd(this.applicationFileList.size());
        applicationFileList.add(file);
    }

    public void addAppString(String fileName){

        ApplicationFile applicationFile = ApplicationFile.builder()
                .fileName(fileName)
                .build();
        addAppFile(applicationFile);

    }

    // 첨부파일 데이터를 비우고 다시 이미지 추가하는 방식
    public void clearList() {
        this.applicationFileList.clear();
    }



}
