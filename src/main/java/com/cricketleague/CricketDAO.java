package com.cricketleague;

import com.cricketleague.model.IPLCSVFile;

public class CricketDAO {

    public String player;
    public double average;

    public CricketDAO(IPLCSVFile next) {
        player = next.player;
        average = next.average;
    }
}
