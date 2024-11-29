package com.project.it.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusFile {
    private String fileName;

    @Setter
    private int ord;

}
