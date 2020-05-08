package com.cricketleague;

import com.cricketleague.exception.CricketAnalyzerException;
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
            CricketDAO[] iplDto = new Gson().fromJson(sortedData, CricketDAO[].class);
            Assert.assertEquals(83.2, iplDto[0].average, 0.0);

        } catch (CricketAnalyzerException e) {
            e.printStackTrace();
        }
    }
}
