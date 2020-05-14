package com.cricketleague.service;

import com.cricketleague.CricketDAO;
import com.cricketleague.adapter.IPLAdapterFactory;
import com.cricketleague.exception.CricketAnalyzerException;
import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class CricketLeagueAnalyzer {
    public enum BatsOrBall {BATTING, BALLING, FEILDING;}

    Map<String, CricketDAO> daoMap = null;
    List<CricketDAO> daoList = null;
    public BatsOrBall batsOrBall;

    public CricketLeagueAnalyzer(BatsOrBall batsOrBall) {
        SortField.sortField();
        this.batsOrBall = batsOrBall;
    }

    //for finding best comparison between batsman and bowler
    public int loadIPLData(BatsOrBall batsOrBall, String... csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    //loading batsman data through adapter pattern
    public int loadIPLData(String... csvFilePath) {
        daoMap = IPLAdapterFactory.getIPLAdapter(batsOrBall, csvFilePath);
        return daoMap.size();
    }

    //for calling sort method
    public String getSortedCricketData(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());

        if (daoList == null || daoList.size() == 0)
            throw new CricketAnalyzerException("No Cricket data available", CricketAnalyzerException.ExceptionType.NO_CRICKET_DATA_AVAILABLE);
        List list = this.sort(sortField.sortMap.get(sortField).reversed());

        //.stream
        //.filter(ipl->ipl.ballingavg!=0)
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    private ArrayList sort(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.ballingAvg != 0)
                .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());

    }
}
//  return (ArrayList) daoList.stream().sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
