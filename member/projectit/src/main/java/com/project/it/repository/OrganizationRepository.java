package com.project.it.repository;

import com.project.it.domain.Member;
import com.project.it.domain.MemberRole;
import com.project.it.domain.Organization;
import com.project.it.domain.OrganizationTeam;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    @EntityGraph(attributePaths = {"organizationTeamList"})
    Organization findByMemberMno (@Param("mno") Long mno);

    @Query("Select o from Organization o join o.organizationTeamList ot where ot = :searchQuery")
    List<Organization> searchOrganizationsByTeam(OrganizationTeam searchQuery);


}
