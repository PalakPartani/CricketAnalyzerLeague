package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class IPLCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "6s", required = true)
    public double sixs;

    @CsvBindByName(column = "4s", required = true)
    public double fours;

    @Override
    public String toString() {
        return "IPLCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                '}';
    }

    public IPLCSVFile(String player, double average, double strikeRate, double sixs, double fours) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.sixs = sixs;
        this.fours = fours;
    }
}
