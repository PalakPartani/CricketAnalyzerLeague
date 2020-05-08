package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class IPLCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @Override
    public String toString() {
        return "IPLCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                '}';
    }

    public IPLCSVFile(String player, double average, double strikeRate) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
    }
}
