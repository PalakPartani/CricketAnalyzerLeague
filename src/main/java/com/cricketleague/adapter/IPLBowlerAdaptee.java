package com.cricketleague.adapter;

import com.cricketleague.CricketDAO;
import com.cricketleague.model.BowlerCSVFile;

import java.util.Map;

public class IPLBowlerAdaptee extends IPLAdapter {
    @Override
    public Map<String, CricketDAO> loadIPLData(String csvFilePath) {
        return super.loadIPLData(BowlerCSVFile.class, csvFilePath);
    }
}
