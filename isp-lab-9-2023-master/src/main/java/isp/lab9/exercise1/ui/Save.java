package isp.lab9.exercise1.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import isp.lab9.exercise1.services.UserPortfolio;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Save {
    private static final String Directory = "Portfolio/";

    public static void SaveShares(String symbol, BigDecimal value, int quantity,UserPortfolio portfolio) throws Exception {
        String filename = Directory + "Stocks.txt";
        File file       = new File(filename);
        if(portfolio.getShares().containsKey(symbol)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(symbol + ": " + quantity + " ," + value + System.lineSeparator());
                writer.flush();
            } catch (IOException e) {
                System.out.println("Error saving shares: " + e.getMessage());
            }
        }
        else{
            System.out.println("Error when saving");
        }
    }

    public static Set<String> GetShares(UserPortfolio portfolio) throws Exception {
        String filename     = Directory + "Stocks.txt";
        File file           = new File(filename);

        Set<String> symbols = new HashSet<>();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    int colonIndex = line.indexOf(':');
                    if (colonIndex != -1) {
                        symbols.add(line.substring(0, colonIndex).trim());
                    }
                }
            } catch (Exception e) {
                System.out.println("Error when reading" + e.getMessage());
            }
        }
        return symbols;
    }
    public static void clearSymbol(String filename){
        File file = new File(filename);
        if(file.exists()){
            File[]files = file.listFiles();
            if(files != null){
                for(File file1:files){
                    file1.delete();
                }
            }
        }
    }
}
