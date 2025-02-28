package com.dariom.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    private Long id;
    private Job job;
    private User user;
    private ApplicationStatus status;
}
