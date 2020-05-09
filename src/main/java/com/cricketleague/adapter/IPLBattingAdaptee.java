package com.cricketleague.adapter;

import com.cricketleague.CricketDAO;
import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.BowlerCSVFile;
import com.cricketleague.model.BattingCSVFile;
import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IPLBattingAdaptee extends IPLAdapter {
 /*   @Override
    public Map<String, CricketDAO> loadIPLData(String csvFilePath) {
        return super.loadIPLData(IPLCSVFile.class, csvFilePath);
    }*/

    @Override
    public Map<String, CricketDAO> loadIPLData(String... csvFilePath) {
        Map<String, CricketDAO> iplMap = super.loadIPLData(BattingCSVFile.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadIPLData(iplMap, csvFilePath[1]);
        return iplMap;
    }

    private Map<String, CricketDAO> loadIPLData(Map<String, CricketDAO> ipldtoMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BowlerCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, BowlerCSVFile.class);
            Iterable<BowlerCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(stat -> ipldtoMap.get(stat.player) != null)
                    .forEach(stat -> {
                        ipldtoMap.get(stat.player).battingaverage = stat.average;
                        ipldtoMap.get(stat.player).battingaverage = stat.wicket;
                    });

            return ipldtoMap;
        } catch (IOException e) {
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}

