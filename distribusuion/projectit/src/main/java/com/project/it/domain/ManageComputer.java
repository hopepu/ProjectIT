package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "manage_computer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "assetComputer")
public class ManageComputer { //컴퓨터 사용리스트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pcno;
    
    private String pcName; //컴퓨터 이름
    private String memberName; //사용자
    private String pcIp; //ip
    private boolean useYN; //사용중 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assetComputer_id")
    private AssetComputer assetComputer; //컴퓨터 계약정보(info정보 포함)
    
    
}
