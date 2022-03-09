package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final DataPrinter printer;
    private final DataInput dataInput;

    public StudentServiceImpl(DataPrinter printer, DataInput dataInput) {
        this.printer = printer;
        this.dataInput = dataInput;
    }

    @Override
    public Student readStudent() {
        askFirstName();
        String firstName = dataInput.getData();
        askLastName();
        String lastName = dataInput.getData();
        return Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    private void askFirstName() {
        printer.printShortLine("Your firstname: ");
    }

    private void askLastName() {
        printer.printShortLine("Your lastname: ");
    }
}
