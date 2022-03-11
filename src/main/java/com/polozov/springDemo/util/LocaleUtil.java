package com.polozov.springDemo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public record LocaleUtil(String locale, MessageSource messageSource) {

    public LocaleUtil(@Value("${main.locale}") String locale, MessageSource messageSource) {
        this.locale = locale;
        this.messageSource = messageSource;
    }

    public String getLocaleMessage(String code, String[] values) {
        return messageSource.getMessage(code, values, Locale.forLanguageTag(locale));
    }

    public String getLocaleMessage(String code) {
        return messageSource.getMessage(code, new String[]{}, Locale.forLanguageTag(locale));
    }
}
