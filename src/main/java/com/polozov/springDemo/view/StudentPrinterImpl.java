package com.polozov.springDemo.view;

import org.springframework.stereotype.Component;

@Component
public class StudentPrinterImpl implements StudentPrinter {

    @Override
    public void askFirstName() {
        System.out.print("Your firstname: ");
    }

    @Override
    public void askLastName() {
        System.out.print("Your lastname: ");
    }
}
