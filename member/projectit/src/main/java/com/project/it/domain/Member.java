package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@ToString(exclude = "memberRoleList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;
    //사원 번호
    private String email;
    //로그인 계정
    @Column(nullable = false)
    private String pw;
    //비밀번호
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime start_date;
    //입사일
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<MemberRole> memberRoleList = new ArrayList<>();


    // method
    public void changePw(String password){
        this.pw = password;
    }

    public void addRole(MemberRole memberRole){
        if(memberRoleList.size()>0) {
            memberRoleList.remove(0);
        }
        memberRoleList.add(memberRole);
    }

    public void clearRole(){
        memberRoleList.clear();
    }









}
