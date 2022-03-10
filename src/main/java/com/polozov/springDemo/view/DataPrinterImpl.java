package com.polozov.springDemo.view;

import org.springframework.stereotype.Component;

@Component
public class DataPrinterImpl implements DataPrinter {

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void printShortLine(String line) {
        System.out.print(line);
    }
}
