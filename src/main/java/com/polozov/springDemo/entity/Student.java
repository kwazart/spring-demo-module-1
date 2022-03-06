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
public class Student {
    private String firstName;
    private String lastName;
    private Set<StudentAnswer> answers;
}
