package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "Wkts", required = true)
    public double wicket;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "4W", required = true)
    public double fourWicket;

    @CsvBindByName(column = "5W", required = true)
    public double fiveWicket;
}


