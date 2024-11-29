package com.project.it.domain;


import jakarta.persistence.Id;

import java.util.Arrays;


public enum MemberRole {
    CONTRACT_WORKER,            // 계약직
    INTERN,                     // 인턴
    STAFF,                      // 사원
    ASSOCIATE,                  // 주임
    ASSISTANT_MANAGER,          // 대리
    MANAGER,                    // 과장
    DEPUTY_MANAGER,             // 차장
    GENERAL_MANAGER,            // 부장
    DIRECTOR,                   // 이사
    SENIOR_DIRECTOR,            // 상무
    EXECUTIVE_VICE_PRESIDENT,   // 전무
    PRESIDENT,                  // 사장
    VICE_CHAIRMAN,              // 부회장
    CHAIRMAN,                   // 회장
    CEO;                         // 대표이사

    private Long id;

    // 문자열을 enum으로 변환하는 메서드
    public static MemberRole fromString(String role) {
        for (MemberRole r : MemberRole.values()) {
            if (r.name().equalsIgnoreCase(role)) {  // 대소문자 구분 없이 비교
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + role);  // 예외 처리
    }


}