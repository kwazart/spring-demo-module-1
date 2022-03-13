package com.polozov.springDemo.service;

import org.springframework.stereotype.Service;

@Service
public record InputAndLocaleServiceFacadeImpl(LocaleService localeService,
                                              DataInput dataInput) implements InputAndLocaleServiceFacade {

    @Override
    public String getLocaleMessage(String code, String... values) {
        return localeService.getLocaleMessage(code, values);
    }

    @Override
    public String getData() {
        return dataInput.getData();
    }
}
