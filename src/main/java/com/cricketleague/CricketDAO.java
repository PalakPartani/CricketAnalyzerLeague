package com.cricketleague;

import com.cricketleague.model.IPLCSVFile;

public class CricketDAO {

    public String player;
    public double average;
    public double strikeRate;
    public double six;
    public double fours;

    public CricketDAO(String player, double average, double strikeRate, double six, double fours) {
        this.player = player;
        this.average = average;
        this.strikeRate = strikeRate;
        this.six = six;
        this.fours = fours;
    }

    public CricketDAO(IPLCSVFile cricketDAO) {

    }
}
