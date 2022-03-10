package com.polozov.springDemo.exception;

public class QuestionsLoadingException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Cannot load question from source";
    }
}
