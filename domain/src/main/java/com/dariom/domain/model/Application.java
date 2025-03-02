package com.dariom.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    private Long id;
    private Job job;
    private User user;
    private ApplicationStatus status;
}
