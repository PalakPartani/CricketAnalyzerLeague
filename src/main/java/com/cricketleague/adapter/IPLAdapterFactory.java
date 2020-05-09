package com.cricketleague.adapter;

import com.cricketleague.CricketDAO;
import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.service.CricketLeagueAnalyzer;

import java.util.Map;

public class IPLAdapterFactory {
    public static <E> Map<String, CricketDAO> getIPLAdapter(CricketLeagueAnalyzer.BatsOrBall batsOrBall, String csvFilePath) {
        if (batsOrBall.equals(CricketLeagueAnalyzer.BatsOrBall.BATTING))
            return new IPLBattingAdaptee().loadIPLData(csvFilePath);
        if (batsOrBall.equals(CricketLeagueAnalyzer.BatsOrBall.BALLING))
            return new IPLBowlerAdaptee().loadIPLData(csvFilePath);
        else
            throw new CricketAnalyzerException("Invalid type!", CricketAnalyzerException.ExceptionType.INVALID_TYPE);
    }
}
