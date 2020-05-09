package com.cricketleague.adapter;

import com.cricketleague.CricketDAO;
import com.cricketleague.model.IPLCSVFile;

import java.util.Map;

public class IPLBattingAdaptee extends IPLAdapter {
    @Override
    public Map<String, CricketDAO> loadIPLData(String csvFilePath) {
        return super.loadIPLData(IPLCSVFile.class, csvFilePath);
    }
}

