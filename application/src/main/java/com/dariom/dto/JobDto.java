package com.dariom.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime publishDate;
    private List<ApplicationDto> applications;
}
