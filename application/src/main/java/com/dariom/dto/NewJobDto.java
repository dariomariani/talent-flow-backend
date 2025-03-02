package com.dariom.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewJobDto {

    private String title;
    private String description;
    private String location;
    private String publishDate;

}
