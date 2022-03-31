package com.polozov.springDemo.service;

import com.polozov.springDemo.annotation.MethodLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileNameProviderImpl implements FileNameProvider {
        private final String fileName;
        private final String locale;
        private final String fileFormat;

    public FileNameProviderImpl(
            @Value("${filename}") String fileName,
            @Value("${main.locale}") String locale,
            @Value("${fileFormat}") String fileFormat) {
        this.fileName = fileName;
        this.locale = locale;
        this.fileFormat = fileFormat;
    }

    @MethodLogger
    @Override
    public String getFilename() {
        return fileName + locale + fileFormat;
    }
}
