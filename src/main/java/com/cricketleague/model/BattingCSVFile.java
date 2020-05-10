package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class BattingCSVFile {
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

    @CsvBindByName(column = "BF", required = true)
    public double ballsFaced;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    public BattingCSVFile(String player, double battingaverage, double strikeRate, double fours, int runs, double six) {
        this.player = player;
        this.average = battingaverage;
        this.strikeRate = strikeRate;
        this.fours = fours;
        this.runs = runs;
        this.sixs = six;

    }

    @Override
    public String toString() {
        return "IPLCSVFile{" +
                "player='" + player + '\'' +
                ", average=" + average +
                ", strikeRate=" + strikeRate +
                ", sixs=" + sixs +
                ", fours=" + fours +
                '}';
    }

    public BattingCSVFile(String player, double average, double strikeRate, double sixs, double fours, double ballsFaced, int runs) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.sixs = sixs;
        this.fours = fours;
        this.ballsFaced = ballsFaced;
        this.runs = runs;
    }

    public BattingCSVFile() {
    }
}