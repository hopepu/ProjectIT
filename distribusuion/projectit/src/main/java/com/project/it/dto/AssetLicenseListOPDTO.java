package com.project.it.dto;

import com.project.it.constant.RightType;
import lombok.*;

import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AssetLicenseListOPDTO { //리스트 전송용 dto
    private Long ano;  //AssetLicenseDTO
    private String rightType; //AssetLicenseDTO --enum RightType
    private String rightName; //InfoLicenseDTO
    private String usePurpose; //사용목적 AssetLicenseDTO
    private int currentUseCount ; //AssetLicenseDTO
    private int totalUseCount; //AssetLicenseDTO
    private LocalDate expireDate; //AssetLicenseDTO

    private int fileCount;
}
