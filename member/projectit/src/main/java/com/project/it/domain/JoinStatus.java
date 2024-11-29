package com.project.it.domain;

public enum JoinStatus {
    WAITING("신규지원"),  // 지원 대기 상태
    HOLD("보류"),         // 보류 상태
    DISMISSED("불합격"),   // 불합격 상태
    PASSED("합격");       // 합격 상태

    private final String statusName;  // 상태명을 저장할 변수

    // 생성자 추가 (상태명을 저장)
    JoinStatus(String statusName) {
        this.statusName = statusName; // 상태명 초기화
    }

    // 문자열을 enum 값으로 변환하는 메소드
    public static JoinStatus fromString(String status) {
        for (JoinStatus s : JoinStatus.values()) {
            if (s.statusName.equalsIgnoreCase(status)) { // statusName을 사용해서 비교
                return s;  // 일치하는 값이 있으면 반환
            }
        }
        // 일치하는 값이 없으면 예외 던짐
        throw new IllegalArgumentException("Unknown JoinStatus: " + status);
    }

    // toString()을 오버라이드하여 상태명을 반환
    @Override
    public String toString() {
        return this.statusName; // 상태명 반환
    }
}
