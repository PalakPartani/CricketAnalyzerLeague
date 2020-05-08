package com.cricketleague;

import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.IPLCSVFile;
import com.cricketleague.service.CricketLeagueAnalyzer;
import com.cricketleague.service.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CricketLeagueTest {


    private static final String IPL_MOST_RUNS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostRuns.csv";

    CricketLeagueAnalyzer cricketLeagueAnalyzer;

    @Before
    public void setUp() throws Exception {
        cricketLeagueAnalyzer = new CricketLeagueAnalyzer();
    }

    @Test
    public void givenBattingAverage_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG);
            IPLCSVFile[] iplDto = new Gson().fromJson(sortedData, IPLCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplDto[0].player);

        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenStrikingRates_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.STRIKING_RATES);
            IPLCSVFile[] iplDto = new Gson().fromJson(sortedData, IPLCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplDto[0].player);

        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

}