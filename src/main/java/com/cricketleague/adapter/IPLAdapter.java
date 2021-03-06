package com.cricketleague.adapter;

import com.cricketleague.CricketDAO;
import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.BattingCSVFile;
import com.cricketleague.model.BowlerCSVFile;
import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public abstract class IPLAdapter {
    static Map<String, CricketDAO> daoMap;


    public abstract Map<String, CricketDAO> loadIPLData(String... csvFilePath);

    public static <E> Map<String, CricketDAO> loadIPLData(Class<E> csvClass, String csvFilePath) {
        daoMap = new HashMap<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = icsvBuilder.getCSVFileIterator(reader, csvClass);
            Iterable<E> csvIterable = () -> csvIterator;

            if (csvClass.getName() == "com.cricketleague.model.BattingCSVFile") {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(BattingCSVFile.class::cast).
                        forEach(csvFile -> daoMap.put(csvFile.player, new CricketDAO(csvFile)));
            } else if (csvClass.getName() == "com.cricketleague.model.BowlerCSVFile") {
                StreamSupport.stream(csvIterable.spliterator(), false).
                        map(BowlerCSVFile.class::cast).
                        forEach(bowlerCSVFile -> daoMap.put(bowlerCSVFile.player, new CricketDAO(bowlerCSVFile)));
            }
            return daoMap;
        } catch (IOException e) {
            e.printStackTrace();
            throw new CricketAnalyzerException(e.getMessage(), CricketAnalyzerException.ExceptionType.CSV_FILE_PROBLEM);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new CricketAnalyzerException(e.getMessage(), CricketAnalyzerException.ExceptionType.CSV_FILE_PROBLEM);
        }
    }
}