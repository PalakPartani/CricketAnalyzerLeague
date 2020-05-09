package com.cricketleague;

import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.model.BattingCSVFile;
import com.cricketleague.service.CricketLeagueAnalyzer;
import com.cricketleague.service.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CricketLeagueTest {


    private static final String IPL_MOST_RUNS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BALLS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";


    CricketLeagueAnalyzer cricketLeagueAnalyzer;

    @Before
    public void setUp() throws Exception {
        cricketLeagueAnalyzer = new CricketLeagueAnalyzer();
    }

    @Test
    public void givenIPLData_WhenGivenBattingAverage_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenStrikingRates_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.STRIKING_RATES);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Ishant Sharma", iplDto[0].player);

        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnCorrectRecord() {

        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.SIX_FOURS);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.SIX_FOURS);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("David Warner", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenAvg_StrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_SR);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("MS Dhoni ", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenAvgRuns_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            cricketLeagueAnalyzer.loadIPLBatsmenData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_RUNS);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("David Warner", iplDto[0].average);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenBowlingAverage_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLBowlerData(CricketLeagueAnalyzer.BatsOrBall.BALLING, IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Suresh Raina", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIPLData_WhenGivenBowlingStrikingRates_ShouldReturnCorrectRecord() {
        try {
            cricketLeagueAnalyzer.loadIPLBowlerData(CricketLeagueAnalyzer.BatsOrBall.BALLING, IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.STRIKING_RATES);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Imran Tahir", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }
}