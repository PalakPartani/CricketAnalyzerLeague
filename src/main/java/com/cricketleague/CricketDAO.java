package com.cricketleague;

import com.cricketleague.model.IPLCSVFile;

public class CricketDAO {

    public String player;
    public double average;
    public double strikeRate;
    public double six;
    public double fours;
    public double ballsFaced;
    public int runs;

    public CricketDAO(IPLCSVFile cricketDAO) {
        player = cricketDAO.player;
        average = cricketDAO.average;
        strikeRate = cricketDAO.strikeRate;
        six = cricketDAO.sixs;
        fours = cricketDAO.fours;
        ballsFaced = cricketDAO.ballsFaced;
        runs = cricketDAO.runs;
    }
}
