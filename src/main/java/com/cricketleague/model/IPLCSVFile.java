package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class IPLCSVFile {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double average;

    public IPLCSVFile(String player, double average) {
        this.player = player;
        this.average = average;
    }

    @Override
    public String toString() {
        return "IPLCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                '}';
    }
}
