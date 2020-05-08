package com.cricketleague.service;

import com.cricketleague.CricketDAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public enum SortField {
    AVG, STRIKING_RATES,SIX_FOURS, AVG_SR;
    static Map<SortField, Comparator<CricketDAO>> sortMap;

    public static void sortField() {
        sortMap = new HashMap<>();
        Comparator<CricketDAO> avgComparator =
                Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate, Comparator.reverseOrder());
        sortMap.put(AVG, avgComparator);

        Comparator<CricketDAO> strikeRateComparator =
                Comparator.comparing(iplBatsmanDAO -> iplBatsmanDAO.strikeRate, Comparator.reverseOrder());
        sortMap.put(SortField.STRIKING_RATES, strikeRateComparator);
    }
}