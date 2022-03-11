package com.polozov.springDemo.service;

import com.polozov.springDemo.entity.Student;
import com.polozov.springDemo.util.LocaleUtil;
import com.polozov.springDemo.view.DataPrinter;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final DataPrinter printer;
    private final DataInput dataInput;
    private final LocaleUtil localeUtil;

    public StudentServiceImpl(DataPrinter printer, DataInput dataInput, LocaleUtil localeUtil) {
        this.printer = printer;
        this.dataInput = dataInput;
        this.localeUtil = localeUtil;
    }

    @Override
    public Student readStudent() {
        askFirstName();
        String firstName = dataInput.getData();
        askLastName();
        String lastName = dataInput.getData();
        return new Student(firstName, lastName);
    }

    private void askFirstName() {
        printer.printShortLine(localeUtil.getLocaleMessage("ask.firstname") + ": ");
    }

    private void askLastName() {
        printer.printShortLine(localeUtil.getLocaleMessage("ask.lastname") + ": ");
    }
}
