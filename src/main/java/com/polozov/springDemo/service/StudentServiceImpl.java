package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final DataPrinter printer;
    private final InputAndLocaleServiceFacade inputAndLocaleServiceFacade;

    public StudentServiceImpl(DataPrinter printer, InputAndLocaleServiceFacade inputAndLocaleServiceFacade) {
        this.printer = printer;
        this.inputAndLocaleServiceFacade = inputAndLocaleServiceFacade;
    }

    @Override
    public Student readStudent() {
        askFirstName();
        String firstName = inputAndLocaleServiceFacade.getData();
        askLastName();
        String lastName = inputAndLocaleServiceFacade.getData();
        return new Student(firstName, lastName);
    }

    private void askFirstName() {
        printer.printShortLine(inputAndLocaleServiceFacade.getLocaleMessage("ask.firstname") + ": ");
    }

    private void askLastName() {
        printer.printShortLine(inputAndLocaleServiceFacade.getLocaleMessage("ask.lastname") + ": ");
    }
}
