package com.cricketleague;

import com.cricketleague.service.CricketLeagueAnalyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SpecificSort {

    List<CricketDAO> daoList;
    CricketLeagueAnalyzer.BatsOrBall batsOrBall;

    public static void initializePredicate(List<CricketLeagueAnalyzer> daoList) {
        daoList = daoList;
    }

    public ArrayList<CricketDAO> getSortedData(String type) {
        switch (type) {
            case "ballingAvg":
                return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.ballingAvg != 0);
            case "strikeRate":
                return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.strikeRate != 0);
            case "wicket":
                return (ArrayList) daoList.stream().filter(cricketDAO -> cricketDAO.wicket != 0);
        }
        return null;
    }
}