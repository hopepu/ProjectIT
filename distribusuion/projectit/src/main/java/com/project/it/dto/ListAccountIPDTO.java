package com.project.it.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListAccountIPDTO {
    private Long siNum;
    private String siteName;
    private String siteUrl;
    private boolean useState;
    private boolean userAuthentication;
    private Long assetId; //AssetLicense ano
    private int totalUseCount; // AssetLicense totalUseCount

    @Builder.Default
    List<AccountObjectIPDTO> accountObjectList = new ArrayList<>();



}
