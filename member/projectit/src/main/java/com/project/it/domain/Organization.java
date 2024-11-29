
package com.project.it.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member_organization")
@Getter
@Setter
@ToString(exclude = "organizationTeamList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oNo;

    @Column
    private String teamName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<OrganizationTeam> organizationTeamList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_mno")
    private Member member;

    //method
    public void addMember(Member member){
        this.member = member;
    }

    public void addOrganizationTeam(OrganizationTeam organizationTeam){
        if (organizationTeamList.size() > 0) {
            organizationTeamList.remove(0);
        }
        organizationTeamList.add(organizationTeam);
    }

}

