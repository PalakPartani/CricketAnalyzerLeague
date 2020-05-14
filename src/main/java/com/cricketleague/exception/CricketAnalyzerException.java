package com.cricketleague.exception;

public class CricketAnalyzerException extends RuntimeException {
    public CricketAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        CSV_FILE_PROBLEM, NO_CRICKET_DATA_AVAILABLE, INVALID_TYPE;


    }

    public ExceptionType type;
}
