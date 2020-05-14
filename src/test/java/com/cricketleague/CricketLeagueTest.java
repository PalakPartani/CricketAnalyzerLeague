package com.cricketleague;

import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.BattingCSVFile;
import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.service.CricketLeagueAnalyzer;
import com.cricketleague.service.SortField;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

public class CricketLeagueTest {
    private static final String IPL_MOST_RUNS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_MOST_BALLS_FILE_PATH = "src/test/resources/IPL2019FactsheetMostWkts.csv";

    //uc1
    @Test
    public void givenIPLData_WhenGivenBattingAverage_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc2
    @Test
    public void givenIPLData_WhenGivenStrikingRates_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.STRIKING_RATES);
            CricketDAO[] iplDto = new Gson().fromJson(sortedData, CricketDAO[].class);
            Assert.assertEquals("Ishant Sharma", iplDto[0].player);

        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc3
    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnCorrectRecord() {

        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.SIX_FOURS_STRIKE);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc4
    @Test
    public void givenIPLData_WhenGivenSixAndFours_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.SIX_FOURS_STRIKE);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc5
    @Test
    public void givenIPLData_WhenGivenAvg_StrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_SR);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc6
    @Test
    public void givenIPLData_WhenGivenAvgRuns_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_SR_RUNS);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("David Warner ", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Balling
    //uc7
    @Test
    public void givenIPLData_WhenGivenBowlingAverage_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.BALLING_AVG);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Anukul Roy", iplDto[iplDto.length-1].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc8
    @Test
    public void givenIPLData_WhenGivenBowlingStrikingRates_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.STRIKING_RATES);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Alzarri Joseph", iplDto[iplDto.length-1].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc9
    @Test
    public void givenIPLData_WhenGivenBestEconomyRates_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.ECONOMY);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Ben Cutting", iplDto[iplDto.length-1].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc10
    @Test
    public void givenIPLData_WhenGivenStrikingRatesWith4sand5s_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.WICKETS_AND_STRIKERATE);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Lasith Malinga", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc11
    @Test
    public void givenIPLData_WhenGivenStrikingRatesWithAvg_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_SR);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Umesh Yadav", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc12
    @Test
    public void givenIPLData_WhenGivenWicketsWithAvg_ShouldReturnCorrectRecord() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BALLING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.WICKET_AND_AVG);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("Imran Tahir", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc13
    @Test
    public void givenIPLData_WhenGivenBestAvgOfBatsmanAndBowler_ShouldReturnCorrectPlayer() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(CricketLeagueAnalyzer.BatsOrBall.BATTING, IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.BEST_BATTING_BOWLING_AVERAGE);
            BowlerCSVFile[] iplDto = new Gson().fromJson(sortedData, BowlerCSVFile[].class);
            Assert.assertEquals("MS Dhoni", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //uc14
    @Test
    public void givenIplData_WhenGivenRunsAndWickets_ShouldReturnCorrectPlayer() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH, IPL_MOST_BALLS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.ALL_ROUNDER);
            CricketDAO[] iplDto = new Gson().fromJson(sortedData, CricketDAO[].class);
            Assert.assertEquals("David Warner ", iplDto[0].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }

    //Test coverage
    @Test
    public void givenIplData_WhenGivenWrongType_ShouldReturnException() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.FEILDING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_BALLS_FILE_PATH);
        } catch (CricketAnalyzerException e) {
            Assert.assertEquals(CricketAnalyzerException.ExceptionType.INVALID_TYPE, e.type);
        }
    }

    //uc5 by comparing no of matches finding the best avg with strike rates
    //uc5
    @Test
    public void givenIPLData_WhenGivenAverage_StrikingRates_ShouldReturnPlayerWithHighestStrikingRates() {
        try {
            CricketLeagueAnalyzer cricketLeagueAnalyzer = new CricketLeagueAnalyzer(CricketLeagueAnalyzer.BatsOrBall.BATTING);
            cricketLeagueAnalyzer.loadIPLData(IPL_MOST_RUNS_FILE_PATH);
            String sortedData = cricketLeagueAnalyzer.getSortedCricketData(SortField.AVG_SR_RUNS);
            BattingCSVFile[] iplDto = new Gson().fromJson(sortedData, BattingCSVFile[].class);
            Assert.assertEquals("Andre Russell", iplDto[iplDto.length-1].player);
        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }
}