package com.project.it.domain;


import lombok.Getter;

@Getter
public enum BankCode {
    BOKRKRSE("01", "한국은행"),       // 한국은행
    KODBKRSE("02", "KDB산업은행"),     // KDB산업은행
    IBKOKRSE("03", "기업은행"),       // 기업은행
    CZNBKRSE("04", "국민은행"),       // 국민은행
    NFFCKRSE("05", "수협은행"),       // 수협은행
    EXIKKRSE("06", "한국수출입은행"), // 한국수출입은행
    NACFKRSEXXX("07", "농협은행"),    // 농협은행
    HVBKKRSEXXX("08", "우리은행"),   // 우리은행
    SCBLKRSE("09", "SC제일은행"),    // SC제일은행
    CITIKRSX("10", "씨티은행"),      // 씨티은행
    DAEBKR22("11", "대구은행"),      // 대구은행
    PUSBKR2P("12", "부산은행"),      // 부산은행
    KWABKRSE("13", "광주은행"),      // 광주은행
    JJBKKR22("14", "제주은행"),      // 제주은행
    JEONKRSE("15", "전북은행"),      // 전북은행
    KYNAKR22XXX("16", "경남은행"),   // 경남은행
    SHBKKRSEPO("17", "우체국 은행"), // 우체국 은행
    KOEXKRSE("18", "하나은행"),      // 하나은행
    SHBKKRSE("19", "신한은행"),      // 신한은행
    CITIKRSXKAK("20", "카카오뱅크"); // 카카오뱅크



    // 은행 코드 값을 반환하는 메서드
    private final String code;  // 은행 코드 (숫자)
    // 은행 이름을 반환하는 메서드
    private final String bankName; // 은행 이름

    // 생성자
    BankCode(String code, String bankName) {
        this.code = code;
        this.bankName = bankName;
    }

    // 문자열을 BankCode로 변환하는 메서드
    public static BankCode fromString(String role) {
        for (BankCode r : BankCode.values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown BankCode: " + role);
    }

    // 숫자 코드로 BankCode 찾기
    public static BankCode fromCode(String code) {
        for (BankCode r : BankCode.values()) {
            if (r.getCode().equals(code)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Unknown BankCode for code: " + code);
    }

    // 모든 BankCode 출력
    public static void printAllBankCodes() {
        for (BankCode bankCode : BankCode.values()) {
            System.out.println("Code: " + bankCode.getCode() + ", Bank: " + bankCode.getBankName());
        }
    }
}
