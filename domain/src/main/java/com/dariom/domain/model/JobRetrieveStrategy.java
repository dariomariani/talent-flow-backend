package com.dariom.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class JobRetrieveStrategy {
    private boolean open;
    private String orderByField;
}
