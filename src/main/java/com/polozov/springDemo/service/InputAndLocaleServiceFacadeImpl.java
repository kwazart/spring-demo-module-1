package com.polozov.springDemo.service;

import org.springframework.stereotype.Service;

@Service
public class InputAndLocaleServiceFacadeImpl implements InputAndLocaleServiceFacade {
    private final LocaleService localeService;
    private final DataInput dataInput;

    public InputAndLocaleServiceFacadeImpl(LocaleService localeService, DataInput dataInput) {
        this.localeService = localeService;
        this.dataInput = dataInput;
    }

    @Override
    public String getLocaleMessage(String code, String... values) {
        return localeService.getLocaleMessage(code, values);
    }

    @Override
    public String getData() {
        return dataInput.getData();
    }
}
