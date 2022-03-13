package com.polozov.springDemo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public record FileNameProviderImpl(String fileName, String locale,
                                   String fileFormat) implements FileNameProvider {

    public FileNameProviderImpl(
            @Value("${filename}") String fileName,
            @Value("${main.locale}") String locale,
            @Value("${fileFormat}") String fileFormat) {
        this.fileName = fileName;
        this.locale = locale;
        this.fileFormat = fileFormat;
    }

    @Override
    public String getFilename() {
        return fileName + locale + fileFormat;
    }
}
