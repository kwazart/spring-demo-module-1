package com.polozov.springDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    private String question;
    private Set<String> answers;
    private boolean hasFreeAnswer;
    private String studentAnswer;
}
