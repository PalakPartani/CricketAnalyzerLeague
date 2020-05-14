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

    @Override
    public Map<String, CricketDAO> loadIPLData(String... csvFilePath) {
        Map<String, CricketDAO> map = super.loadIPLData(BattingCSVFile.class, csvFilePath[0]);
        if (csvFilePath.length > 1)
            this.loadIPLData(map, csvFilePath[1]);
        return map;
    }

    private Map<String, CricketDAO> loadIPLData(Map<String, CricketDAO> daoMap, String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<BowlerCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, BowlerCSVFile.class);
            Iterable<BowlerCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false).filter(csvFile -> daoMap.get(csvFile.player) != null)
                    .forEach(csvFile -> {
                        daoMap.get(csvFile.player).ballingAvg = csvFile.ballingAvg;
                        daoMap.get(csvFile.player).wicket = csvFile.wicket;
                    });

            return daoMap;
        } catch (IOException e) {
            throw new CricketAnalyzerException(e.getMessage(),
                    CricketAnalyzerException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}

