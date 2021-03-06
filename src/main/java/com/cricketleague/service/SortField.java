package com.cricketleague.service;

import com.cricketleague.CricketDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    AVG, STRIKING_RATES, SIX_FOURS_STRIKE, AVG_SR, ECONOMY, WICKETS_AND_STRIKERATE, WICKET_AND_AVG, BEST_BATTING_BOWLING_AVERAGE, ALL_ROUNDER, SIX_FOUR, AVG_SR_RUNS, BALLING_AVG, AVERAGE_SR;
    static Map<SortField, Comparator<CricketDAO>> sortMap;

    public static void sortField() {
        sortMap = new HashMap<>();

        sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.battingAverage));

        sortMap.put(SortField.BALLING_AVG, Comparator.comparing(cricketDAO -> cricketDAO.ballingAvg));

        sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        sortMap.put(SortField.SIX_FOUR, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours));

        sortMap.put(SortField.SIX_FOURS_STRIKE, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.battingAverage * cricketDAO.strikeRate / 100));

        sortMap.put(SortField.AVG_SR_RUNS, Comparator.comparing(cricketDAO -> cricketDAO.battingAverage+cricketDAO.strikeRate>100));
        sortMap.put(SortField.ECONOMY, Comparator.comparing(cricketDAO -> cricketDAO.economy));

        Comparator<CricketDAO> allRounder = Comparator.comparing(cricketDAO -> cricketDAO.battingAverage);
        sortMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE, allRounder.thenComparing(iplData -> iplData.ballingAvg));

        sortMap.put(SortField.ALL_ROUNDER, Comparator.comparing(cricketDAO -> cricketDAO.battingAverage * cricketDAO.strikeRate / 100));

       /* Comparator<CricketDAO> allRounderWkts = Comparator.comparing((cricketDAO -> cricketDAO.battingAverage * cricketDAO.strikeRate / 100));
        sortMap.put(SortField.ALL_ROUNDER, allRounderWkts.thenComparing(cricketDAO -> cricketDAO.runs));*/

        Comparator<CricketDAO> maxWicketsAndStrikeRate = Comparator.comparing(cricketDAO -> cricketDAO.battingAverage * cricketDAO.strikeRate / 100);
        sortMap.put(SortField.WICKETS_AND_STRIKERATE, maxWicketsAndStrikeRate.thenComparing(cricketDAO -> cricketDAO.runs));

        Comparator<CricketDAO> maxWicketsAndStrikeRates = Comparator.comparing(cricketDAO -> cricketDAO.wicket);
        sortMap.put(SortField.WICKET_AND_AVG, maxWicketsAndStrikeRates.thenComparing(cricketDAO -> cricketDAO.strikeRate));

        System.out.println(sortMap.size());
    }
}