package com.cricketleague.service;

import com.cricketleague.CricketDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    AVG, STRIKING_RATES, SIX_FOURS_STRIKE, AVG_SR, ECONOMY, WICKETS_AND_STRIKERATE, WICKET_AND_AVG, BEST_BATTING_BOWLING_AVERAGE, ALL_ROUNDER, SIX_FOUR, AVG_SR_RUNS;
    static Map<SortField, Comparator<CricketDAO>> sortMap;

    public static void sortField() {
        sortMap = new HashMap<>();
        sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.battingaverage));
        sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        sortMap.put(SortField.SIX_FOUR, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours));
        sortMap.put(SortField.SIX_FOURS_STRIKE, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.battingaverage * cricketDAO.strikeRate / 100));
        sortMap.put(SortField.AVG_SR_RUNS, Comparator.comparing(cricketDAO -> cricketDAO.runs));
        sortMap.put(SortField.ECONOMY, Comparator.comparing(cricketDAO -> cricketDAO.economy));
        sortMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE, new GetAverage());
        sortMap.put(SortField.ALL_ROUNDER, new GetAllRounder());

        Comparator<CricketDAO> maxWicketsAndStrikeRate = Comparator.comparing(cricketDAO -> cricketDAO.fourWicket + cricketDAO.fiveWicket);
        sortMap.put(SortField.WICKETS_AND_STRIKERATE, maxWicketsAndStrikeRate.thenComparing(iplData -> iplData.strikeRate));
        Comparator<CricketDAO> maxWicketsAndStrikeRates = Comparator.comparing(cricketDAO -> cricketDAO.wicket);
        sortMap.put(SortField.WICKET_AND_AVG, maxWicketsAndStrikeRates.thenComparing(cricketDAO -> cricketDAO.strikeRate));

        System.out.println(sortMap.size());

    }
}