package com.polozov.springDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class StudentAnswer {
    private String answer;
    private Question question;
}
