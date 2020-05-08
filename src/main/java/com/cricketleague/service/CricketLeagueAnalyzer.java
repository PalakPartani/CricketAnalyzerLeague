package com.cricketleague.service;

import com.cricketleague.CricketDAO;
import com.cricketleague.exception.CricketAnalyzerException;
import com.cricketleague.model.IPLCSVFile;
import com.csvparser.CSVBuilderFactory;
import com.csvparser.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CricketLeagueAnalyzer {
    Map<SortField, Comparator<CricketDAO>> sortMap;
    Map<String, CricketDAO> daoMap = new HashMap<>();
    List<CricketDAO> daoList;

    public CricketLeagueAnalyzer() {
        this.sortMap = new HashMap<>();
        this.sortMap.put(SortField.AVG, Comparator.comparing(cricketDAO -> cricketDAO.average));

    }

    public int loadIPLData(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLCSVFile> csvIterator = csvBuilder.getCSVFileIterator(reader, IPLCSVFile.class);
            Iterable<IPLCSVFile> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(cricketDAO -> daoMap.put(cricketDAO.player, new CricketDAO(cricketDAO)));
            return daoMap.size();
        } catch (IOException e) {
            throw new CricketAnalyzerException("No such file", CricketAnalyzerException.ExceptionType.NO_CENSUS_DATA);
        } catch (RuntimeException e) {
            throw new CricketAnalyzerException("No Census data available", CricketAnalyzerException.ExceptionType.NO_CENSUS_DATA);
        }
    }

    public String getSortedCricketData(SortField sortField) {
        if (daoMap == null || daoMap.size() == 0) {
            throw new CricketAnalyzerException("No Census data available", CricketAnalyzerException.ExceptionType.NO_CENSUS_DATA);
        }
        daoList = daoMap.values().stream().collect(Collectors.toList());
        daoList.stream().sorted(this.sortMap.get(sortField).reversed()).collect(Collectors.toList());
        String sortedStateCensusJson = new Gson().toJson(daoList);
        return sortedStateCensusJson;
    }
}