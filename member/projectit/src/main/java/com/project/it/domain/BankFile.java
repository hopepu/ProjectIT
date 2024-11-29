package com.project.it.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankFile {

    private String fileName;
    private int ord;

    public void setOrd(int size) {
    }
}

