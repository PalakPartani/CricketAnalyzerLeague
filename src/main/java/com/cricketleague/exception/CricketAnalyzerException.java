package com.cricketleague.exception;

public class CricketAnalyzerException extends RuntimeException {
    public CricketAnalyzerException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        CSV_FILE_PROBLEM, NO_CENSUS_DATA, INVALID_TYPE;


    }

    public ExceptionType type;
}
