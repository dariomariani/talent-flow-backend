package com.dariom.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private int id;
    private String title;
    private String description;
    private String location;
    private String status;
    private String publishDate;
    private List<ApplicationDto> applications;
}
