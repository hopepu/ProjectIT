package com.project.it.domain;

public enum OrganizationTeam {
    AWAIT("부서발령대기"),
    TECHNIC("기술부"),
    PERSONNEL("인사부"),
    ACCOUNTING("회계부"),
    FINANCIAL_MANAGEMENT("재무관리팀");

    private final String koreanName;

    // 생성자
    OrganizationTeam(String koreanName) {
        this.koreanName = koreanName;
    }

    // 한글 부서명을 반환하는 메서드
    public String getKoreanName() {
        return koreanName;
    }

    // 한글 부서명으로 OrganizationTeam을 반환하는 역변환 메서드
    public static OrganizationTeam fromKoreanName(String koreanName) {
        for (OrganizationTeam team : OrganizationTeam.values()) {
            if (team.koreanName.equalsIgnoreCase(koreanName)) {
                return team;
            }
        }
        throw new IllegalArgumentException("Invalid Korean name: " + koreanName);
    }
}
