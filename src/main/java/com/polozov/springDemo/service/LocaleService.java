package com.polozov.springDemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleService {
    private final String locale;
    private final MessageSource messageSource;

    public LocaleService(@Value("${main.locale}") String locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public String getLocaleMessage(String code, String... values) {
        return messageSource.getMessage(code, values, Locale.forLanguageTag(locale));
    }
}
