package com.project.it.util;

import com.project.it.domain.MemberRole;

public class RoleNameMapping {

    public static MemberRole getRoleFromKoreanName(String koreanName) {
        switch (koreanName) {
            case "계약직":
                return MemberRole.CONTRACT_WORKER;
            case "인턴":
                return MemberRole.INTERN;
            case "사원":
                return MemberRole.STAFF;
            case "주임":
                return MemberRole.ASSOCIATE;
            case "대리":
                return MemberRole.ASSISTANT_MANAGER;
            case "과장":
                return MemberRole.MANAGER;
            case "차장":
                return MemberRole.DEPUTY_MANAGER;
            case "부장":
                return MemberRole.GENERAL_MANAGER;
            case "사장":
                return MemberRole.PRESIDENT;
            default:
                return null; // 일치하는 값이 없으면 null 반환
        }
    }
}
