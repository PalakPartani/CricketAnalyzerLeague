package com.cricketleague.service;

import com.cricketleague.CricketDAO;

public class GetAverage implements java.util.Comparator<com.cricketleague.CricketDAO> {
    @Override
    public int compare(CricketDAO p1, CricketDAO p2) {
        int bestAverage = (int) ((p1.battingaverage + p1.ballingAvg) - (p2.battingaverage + p2.ballingAvg));
        return bestAverage;
    }
}
