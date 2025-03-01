package com.dariom.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    private Long id;
    private String title;
    private String description;
    private String location;
    private List<Application> applications;
}
