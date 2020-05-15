package com.cricketleague.service;

import com.cricketleague.CricketDAO;
import com.cricketleague.SpecificSort;
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
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    public ArrayList sort(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream()
                .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
    }

    public ArrayList sortAvg(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.ballingAvg!= 0)
                .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
    }

    public ArrayList sortSR(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.strikeRate != 0)
        .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
    }

    public ArrayList sortWkts(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.strikeRate != 0)
                .filter(cricketDAO -> cricketDAO.fourWicket + cricketDAO.fiveWicket != 0)
                .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
    }

    public ArrayList sortRuns(Comparator<CricketDAO> comparator) {
        return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.wicket != 0)
                .filter(cricketDAO -> cricketDAO.runs != 0)
                .sorted(comparator).map(cricketDAO -> cricketDAO.getCricketDTO(batsOrBall)).collect(Collectors.toList());
    }

    public String returnFilterOnAvgSR(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        daoList.stream().filter(cricketDAO -> cricketDAO.battingAverage != 0).filter(cricketDAO ->cricketDAO.strikeRate!=0 );
        List list = this.sort(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    public String returnFilterOnAllRounder(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        List list = this.sortRuns(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    public String returnFilterOnAvg(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        List list = this.sortAvg(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
   /* public String returnFilterOnAvg(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        List list = this.sortAvg(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;*/

    public String returnFilterOnSR(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        List list = this.sortSR(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }

    public String returnFilterOnWkcts(SortField sortField) {
        daoList = daoMap.values().stream().collect(Collectors.toList());
        List list = this.sortWkts(sortField.sortMap.get(sortField).reversed());
        String sortedStateCensusJson = new Gson().toJson(list);
        return sortedStateCensusJson;
    }
}