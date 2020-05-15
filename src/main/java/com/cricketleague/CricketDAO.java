package com.cricketleague;

import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.model.BattingCSVFile;
import com.cricketleague.service.CricketLeagueAnalyzer;

public class CricketDAO {

    public String player;
    public double battingAverage;
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
    public double matches;

    public CricketDAO(BattingCSVFile cricketDAO) {
        player = cricketDAO.player;
        battingAverage = cricketDAO.average;
        strikeRate = cricketDAO.strikeRate;
        six = cricketDAO.sixs;
        fours = cricketDAO.fours;
        ballsFaced = cricketDAO.ballsFaced;
        runs = Math.max(this.runs,cricketDAO.runs);
        matches=cricketDAO.matches;
    }

    public CricketDAO(BowlerCSVFile cricketDao) {
        player = cricketDao.player;
        ballingAvg = Math.max(this.ballingAvg,cricketDao.ballingAvg);
        strikeRate = cricketDao.strikeRate;
        economy = cricketDao.economy;
        fiveWicket = cricketDao.fiveWicket;
        fourWicket = cricketDao.fourWicket;
        wicket = cricketDao.wicket;
    }

    public Object getCricketDTO(CricketLeagueAnalyzer.BatsOrBall batsOrBall) {
        if (batsOrBall.equals(CricketLeagueAnalyzer.BatsOrBall.BATTING)) {
            return new BattingCSVFile(player, battingAverage, strikeRate, fours, runs, six);
        } else {
            return new BowlerCSVFile(player, ballingAvg, strikeRate, economy, (int) fiveWicket, (int) fourWicket, wicket);
        }
    }
}