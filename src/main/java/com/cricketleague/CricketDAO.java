package com.cricketleague;

import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.model.BattingCSVFile;
import com.cricketleague.service.CricketLeagueAnalyzer;

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
    public double average;


    public CricketDAO(BattingCSVFile cricketDAO) {
        player = cricketDAO.player;
        average = cricketDAO.average;
        battingaverage = cricketDAO.average;
        strikeRate = cricketDAO.strikeRate;
        six = cricketDAO.sixs;
        fours = cricketDAO.fours;
        ballsFaced = cricketDAO.ballsFaced;
        runs = cricketDAO.runs;
    }

    public CricketDAO(BowlerCSVFile bowlerCSVFile) {
        player = bowlerCSVFile.player;
        average = bowlerCSVFile.average;
        ballingAvg = bowlerCSVFile.average;
        strikeRate = bowlerCSVFile.strikeRate;
        economy = bowlerCSVFile.economy;
        fiveWicket = bowlerCSVFile.fiveWicket;
        fourWicket = bowlerCSVFile.fourWicket;
        wicket = bowlerCSVFile.wicket;


    }

    public Object getCricketDTO(CricketLeagueAnalyzer.BatsOrBall batsOrBall) {
        if (batsOrBall.equals(CricketLeagueAnalyzer.BatsOrBall.BATTING)) {
            return new BattingCSVFile(player, battingaverage, strikeRate, fours, runs, six);
        } else {
            return new BowlerCSVFile(player, ballingAvg, strikeRate, economy, fiveWicket, fourWicket, wicket);

        }
    }
}
