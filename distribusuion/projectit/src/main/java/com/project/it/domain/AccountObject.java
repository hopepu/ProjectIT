package com.project.it.domain;

import com.project.it.constant.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountObject {
    @Column(nullable = false)
    private String ac_id;
    @Column(nullable = false)
    private String ac_pw;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType; //계정유형
}
