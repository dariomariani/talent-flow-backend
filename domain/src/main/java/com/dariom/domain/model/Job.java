package com.dariom.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Job {
    private int id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime publishDate;
    private JobStatus status;
    private List<Application> applications;

    public boolean isOpen(){
        return status == JobStatus.OPEN && applications.size() < 10;
    }
}
