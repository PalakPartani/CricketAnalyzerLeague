package com.cricketleague.service;

import com.cricketleague.CricketDAO;
import com.cricketleague.adapter.IPLAdapter;
import com.cricketleague.adapter.IPLAdapterFactory;
import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.BowlerCSVFile;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyzer {
    public enum BatsOrBall {BATTING, BALLING}

    Map<SortField, Comparator<CricketDAO>> sortMap;
    Map<String, CricketDAO> daoMap;
    List<CricketDAO> daoList = null;

    public CricketLeagueAnalyzer() {
        this.daoMap = new HashMap<>();
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.battingaverage));
        this.sortMap.put(SortField.STRIKING_RATES, Comparator.comparing(cricketDAO -> cricketDAO.strikeRate));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours));
        this.sortMap.put(SortField.SIX_FOURS, Comparator.comparing(cricketDAO -> cricketDAO.six + cricketDAO.fours / cricketDAO.ballsFaced * 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.battingaverage * cricketDAO.strikeRate / 100));
        this.sortMap.put(SortField.AVG_SR, Comparator.comparing(cricketDAO -> cricketDAO.runs));
        this.sortMap.put(SortField.ECONOMY, Comparator.comparing(cricketDAO -> cricketDAO.economy));
        Comparator<CricketDAO> maxWicketsAndStrikeRate = Comparator.comparing(cricketDAO -> cricketDAO.fourWicket + cricketDAO.fiveWicket);
        this.sortMap.put(SortField.WICKETS_AND_STRIKERATE, maxWicketsAndStrikeRate.thenComparing(iplData -> iplData.strikeRate));
        Comparator<CricketDAO> maxWicketsAndStrikeRates = Comparator.comparing(cricketDAO -> cricketDAO.wicket);
        this.sortMap.put(SortField.WICKET_AND_AVG, maxWicketsAndStrikeRates.thenComparing(cricketDAO -> cricketDAO.strikeRate));
        sortMap.put(SortField.BEST_BATTING_BOWLING_AVERAGE, new GetAverage());

        // formula for striking rate  (runs/balls faced)*100
    }

    public int loadIPLData(BatsOrBall batsOrBall, String... csvFilePath) {
        daoMap = new IPLAdapterFactory().getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    public int loadIPLBatsmenData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    public int loadIPLBowlerData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapter.loadIPLData(BowlerCSVFile.class, csvFilePath);
        return daoMap.size();
    }

    public String getSortedCricketData(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        if (daoMap == null || daoMap.size() == 0)
            throw new CricketAnalyzerException("No Census data available", CricketAnalyzerException.ExceptionType.NO_CENSUS_DATA);
        daoList.stream().sorted(this.sortMap.get(sortField)).collect(Collectors.toList());
        String sortedStateCensusJson = new Gson().toJson(daoList);
        return sortedStateCensusJson;

    }
}