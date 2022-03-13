package com.polozov.springDemo.service;

public interface InputAndLocaleServiceFacade {
    String getLocaleMessage(String code, String... values);
    String getData();
}
