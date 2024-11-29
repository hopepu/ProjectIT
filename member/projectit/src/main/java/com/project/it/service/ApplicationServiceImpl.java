package com.project.it.service;

import com.project.it.domain.Application;
import com.project.it.domain.ApplicationFile;
import com.project.it.domain.JoinStatus;
import com.project.it.domain.OrganizationTeam;
import com.project.it.dto.ApplicationDTO;
import com.project.it.dto.PageRequestDTO;
import com.project.it.dto.PageResponseDTO;
import com.project.it.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{

    private final ApplicationRepository APR;

    @Override
    public Long register(ApplicationDTO applicationDTO) { // C
        log.info("지원서 등록 : " + applicationDTO.toString());
        Application application = dtoToEntity(applicationDTO);
        application.changeJoinStatus(JoinStatus.WAITING);
        Application result = APR.save(application);

        log.info(result);

        return result.getNo();
    }

    @Override
    public Long modify(ApplicationDTO applicationDTO) { //U
        log.info("serviceImpl start");
        Optional<Application> application = APR.findById(applicationDTO.getNo().toString());
        log.info("searchApplicationByNo's data : " + application.toString());
        JoinStatus joinStatus = JoinStatus.valueOf(applicationDTO.getJoinStatus().get(0));
        application.get().changeJoinStatus(joinStatus);

        log.info("joinStatus 변경후의 application 값 : " + application.toString());

        Application result = APR.save(application.get());

        log.info(result);

        return result.getNo();

    }

    @Override
    public ApplicationDTO getOne(String no) { //R-O
        Optional<Application> application = APR.findById(no);
        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .no(application.get().getNo())
                .name(application.get().getName())
                .phoneNum(application.get().getPhoneNum())
                .mail(application.get().getMail())
                .start_date(application.get().getStart_date())
                .build();

        List<ApplicationFile> applicationFilesLists = application.get().getApplicationFileList();
        applicationDTO.getUploadFileNames().add(applicationFilesLists.get(0).getFileName());

        applicationDTO.getOrganizationTeam().add(application.get().getOrganizationTeamList().get(0).toString());
        applicationDTO.getJoinStatus().add(application.get().getJoinStatusList().get(0).toString());
        log.info(applicationDTO);
        return applicationDTO;
    }


    @Override
    public PageResponseDTO<ApplicationDTO> getList(PageRequestDTO pageRequestDTO) { // R-List
        log.info("getList..........");

        // 페이지 번호가 1 미만일 경우 기본값 1로 설정
        int page = pageRequestDTO.getPage() < 1 ? 1 : pageRequestDTO.getPage();

        Pageable pageable = PageRequest.of(
                page - 1,  // 페이지 번호는 0부터 시작하므로 -1 처리
                pageRequestDTO.getSize(),
                Sort.by("no").descending());

        Page<Application> result = null;

        String searchQuery = pageRequestDTO.getSearchQuery(); // 검색어
        log.info("Search Query: " + searchQuery);

        // 검색어 처리 로직
        if (searchQuery == null || searchQuery.isEmpty()) {
            // 검색어 없이 전체 리스트
            result = APR.selectList(pageable);
        } else {
            // 검색어에 따른 검색 분기 처리
            JoinStatus joinStatus = tryParseJoinStatus(searchQuery);
            if (joinStatus != null) {
                log.info("지원결과 검색");
                result = APR.findAllByJoinStatus(joinStatus, pageable);
            } else {
                OrganizationTeam organizationTeam = tryParseOrganizationTeam(searchQuery);
                if (organizationTeam != null) {
                    log.info("지원부서 검색");
                    result = APR.findAllByOrganizationTeam(organizationTeam, pageable);
                } else {
                    log.info("이름 검색");
                    result = APR.findAllByName(searchQuery, pageable);
                }
            }
        }

        // 비어있는 결과 처리
        if (result == null || result.getContent().isEmpty()) {
            log.warn("No results found for the search query: " + searchQuery);
            return new PageResponseDTO<>(Collections.emptyList(), pageRequestDTO, 0L);
        }

        List<ApplicationDTO> dtoList = new ArrayList<>();
        // result.getContent() 사용하여 안전하게 데이터 순회
        for (Application application : result.getContent()) {
            ApplicationDTO applicationDTO = ApplicationDTO.builder()
                    .no(application.getNo())
                    .name(application.getName())
                    .phoneNum(application.getPhoneNum())
                    .mail(application.getMail())
                    .start_date(application.getStart_date())
                    .build();

            // JoinStatusList 확인 및 DTO에 추가
            if (application.getJoinStatusList() != null && !application.getJoinStatusList().isEmpty()) {
                applicationDTO.getJoinStatus().add(application.getJoinStatusList().get(0).toString());
            }

            // OrganizationTeamList 확인 및 DTO에 추가
            if (application.getOrganizationTeamList() != null && !application.getOrganizationTeamList().isEmpty()) {
                applicationDTO.getOrganizationTeam().add(application.getOrganizationTeamList().get(0).toString());
            }

            // ApplicationFileList 확인 및 DTO에 추가
            if (application.getApplicationFileList() != null && !application.getApplicationFileList().isEmpty()) {
                applicationDTO.getUploadFileNames().add(application.getApplicationFileList().get(0).getFileName());
            }

            dtoList.add(applicationDTO);
        }

        Long totalCount = result.getTotalElements();
        log.info(dtoList);

        return PageResponseDTO.<ApplicationDTO>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .pageRequestDTO(pageRequestDTO)
                .build();
    }

    // JoinStatus 변환을 안전하게 처리하는 메서드
    private JoinStatus tryParseJoinStatus(String searchQuery) {
        try {
            return JoinStatus.fromString(searchQuery);
        } catch (IllegalArgumentException e) {
            return null; // 변환할 수 없으면 null 반환
        }
    }

    // OrganizationTeam 변환을 안전하게 처리하는 메서드
    private OrganizationTeam tryParseOrganizationTeam(String searchQuery) {
        try {
            return OrganizationTeam.fromKoreanName(searchQuery);
        } catch (IllegalArgumentException e) {
            return null; // 변환할 수 없으면 null 반환
        }
    }




    private Application dtoToEntity(ApplicationDTO applicationDTO){  // dto를 Entity로 변환

        Application application = Application.builder()
                .no(applicationDTO.getNo())
                .name(applicationDTO.getName())
                .phoneNum(applicationDTO.getPhoneNum())
                .mail(applicationDTO.getMail())
                .start_date(applicationDTO.getStart_date())
                .build();

        //업로드 처리가 끝난 파일들의 이름 리스트
        List<String> uploadFileNames = applicationDTO.getUploadFileNames();

        if(uploadFileNames != null){
            uploadFileNames.stream().forEach(fileName -> {
                application.addAppString(fileName);
            });}

        // 지원 부서
        List<String> organizationTeamName = applicationDTO.getOrganizationTeam();

        if(organizationTeamName != null){
            organizationTeamName.stream().forEach(teamName -> {
                application.addOTL(OrganizationTeam.fromKoreanName(teamName));
            });
        }

        // 입사 상태
        List<String> joinStatus = applicationDTO.getJoinStatus();
        if(joinStatus != null){
            joinStatus.stream().forEach(status -> {
                application.changeJoinStatus(JoinStatus.fromString(status));
            });
        }

        return application;
    } //dtoToEntity 메서드 종료

    private ApplicationDTO entityToDto(Application application) {  // Entity를 dto로 변환

        List<String> joinStatus = new ArrayList<>();
        joinStatus.add(application.getJoinStatusList().get(0).toString());

        List<String> organizationTeam = new ArrayList<>();
        organizationTeam.add(application.getOrganizationTeamList().get(0).toString());

        List<String> uploadFileNames = new ArrayList<>();
        uploadFileNames.add(application.getApplicationFileList().get(0).toString());

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .no(application.getNo())
                .name(application.getName())
                .phoneNum(application.getPhoneNum())
                .mail(application.getMail())
                .start_date(application.getStart_date())
                .uploadFileNames(uploadFileNames)
                .organizationTeam(organizationTeam)
                .joinStatus(joinStatus)
                .build();

        return applicationDTO;
    }

}
