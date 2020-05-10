package com.cricketleague.service;

import com.cricketleague.CricketDAO;

public class GetAllRounder implements java.util.Comparator<com.cricketleague.CricketDAO> {
    @Override
    public int compare(CricketDAO p1, CricketDAO p2) {
        int total = (int) ((p1.runs * p1.wicket) - (p2.runs * p2.wicket));
        return total;
    }
}
