package com.polozov.springDemo.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ConsoleDataInput implements DataInput {
    @Override
    public String getData() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
