package com.cricketleague;

import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.model.BattingCSVFile;

public class CricketDAO {

    public String player;
    public double battingaverage;
    public double ballingAvg;
    public double strikeRate;
    public double six;
    public double fours;
    public double ballsFaced;
    public int runs;
    public double economy;
    public double fiveWicket;
    public double fourWicket;
    public double wicket;


    public CricketDAO(BattingCSVFile cricketDAO) {
        player = cricketDAO.player;
        battingaverage = cricketDAO.average;
        strikeRate = cricketDAO.strikeRate;
        six = cricketDAO.sixs;
        fours = cricketDAO.fours;
        ballsFaced = cricketDAO.ballsFaced;
        runs = cricketDAO.runs;
    }

    public CricketDAO(BowlerCSVFile bowlerCSVFile) {
        player = bowlerCSVFile.player;
        ballingAvg = bowlerCSVFile.average;
        strikeRate = bowlerCSVFile.strikeRate;
        economy = bowlerCSVFile.economy;
        fiveWicket = bowlerCSVFile.fiveWicket;
        fourWicket = bowlerCSVFile.fourWicket;
        wicket = bowlerCSVFile.wicket;


    }
}