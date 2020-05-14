package com.cricketleague.model;

import com.opencsv.bean.CsvBindByName;

public class BowlerCSVFile {

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Avg", required = true)
    public double ballingAvg;

    @CsvBindByName(column = "Wkts", required = true)
    public double wicket;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "4w", required = true)
    public int fourWicket;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWicket;

    public BowlerCSVFile(String player, double ballingAvg, double strikeRate, double economy, int fiveWicket, int fourWicket, double wicket) {
        this.player=player;
        this.ballingAvg =ballingAvg;
        this.strikeRate=strikeRate;
        this.economy=economy;
        this.fiveWicket=fiveWicket;
        this.fourWicket=fourWicket;
        this.wicket=wicket;
    }

    public BowlerCSVFile() {
    }
}