package com.project.it.repository;

import com.project.it.domain.Application;
import com.project.it.domain.JoinStatus;
import com.project.it.domain.Member;
import com.project.it.domain.OrganizationTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {

    @Query("select App from Application App order by App.no asc")
    Page<Application> selectList(Pageable pageable);

    Page<Application> findAllByName(String searchQuery, Pageable pageable);

    @Query("select App from Application App join App.joinStatusList js where js= :joinStatus ")
    Page<Application> findAllByJoinStatus(JoinStatus joinStatus, Pageable pageable);

    @Query("select App from Application App join App.organizationTeamList oTl where oTl= :organizationTeam")
    Page<Application> findAllByOrganizationTeam(OrganizationTeam organizationTeam, Pageable pageable);

    @EntityGraph(attributePaths = {"joinStatus"})
    Application searchApplicationByNo(@Param("mno") Long mno);






}
