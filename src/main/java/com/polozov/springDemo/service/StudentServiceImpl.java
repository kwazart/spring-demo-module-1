package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.view.StudentPrinter;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentPrinter studentPrinter;
    private final DataInput dataInput;

    public StudentServiceImpl(StudentPrinter studentPrinter, DataInput dataInput) {
        this.studentPrinter = studentPrinter;
        this.dataInput = dataInput;
    }

    @Override
    public Student save() {
        studentPrinter.askFirstName();
        String firstName = dataInput.getData();
        studentPrinter.askLastName();
        String lastName = dataInput.getData();
        return Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
