package com.project.it.dto;

import com.project.it.domain.MemberRole;
import com.project.it.domain.OrganizationTeam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberTeamDTO{
    private String team;
    private String memberRole;
    private String teamName;
    private Long mno;
    private String name;

}
