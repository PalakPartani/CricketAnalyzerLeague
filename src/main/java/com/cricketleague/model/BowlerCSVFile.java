package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    public BowlerCSVFile(String player, double average) {
        this.player = player;
        this.average = average;
    }

    public BowlerCSVFile() {
    }
}
