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

    Map<String, CricketDAO> daoMap = null;
    List<CricketDAO> daoList = null;
    public BatsOrBall batsOrBall;

    public CricketLeagueAnalyzer(BatsOrBall batsOrBall) {
        SortField.sortField();
        this.batsOrBall = batsOrBall;
    }

    //for finding best comparison between batsman and bowler
    public int loadIPLData(BatsOrBall batsOrBall, String... csvFilePath) {
        daoMap = new IPLAdapterFactory().getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    //loading batsman data through adapter pattern
    public int loadIPLBatsmenData(String csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    //loading bowler data through adapter pattern
    public int loadIPLBowlerData(BatsOrBall batsOrBall, String csvFilePath) {
        daoMap = IPLAdapter.loadIPLData(BowlerCSVFile.class, csvFilePath);
        return daoMap.size();
    }

    //for calling sort method
    public String getSortedCricketData(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());

        if (daoList == null || daoList.size() == 0)
            throw new CricketAnalyzerException("No Census data available", CricketAnalyzerException.ExceptionType.NO_CENSUS_DATA);
        List list = this.sort(sortField.sortMap.get(sortField).reversed());

        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;

    }

    private ArrayList sort(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());

    }
}