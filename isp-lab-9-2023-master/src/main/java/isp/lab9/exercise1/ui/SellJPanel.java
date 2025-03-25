package isp.lab9.exercise1.ui;

import isp.lab9.exercise1.services.UserPortfolio;
import isp.lab9.exercise1.utils.Utils;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * note: it should look similar to the 'Buy' panel
 */
public class SellJPanel extends JPanel {
    private StockMarketJFrame   mainFrame;
    private static final String Directory = "Portfolio/";
    private final String        filename  = Directory + "Stocks.txt";
    private JComboBox<String>   symbolComboBox;
    private JTextField          availableStocksTextField;
    public SellJPanel(StockMarketJFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
        changeView();
    }

    public void initComponents() {
        setLayout(new GridLayout(2, 2));
        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(10, 10));
    }
    public void changeView() {
        UserPortfolio userPortfolio  = mainFrame.getPortfolio();
        JLabel symbolLabel           = new JLabel("Symbol");
        symbolComboBox               = new JComboBox<>();

        JLabel availableStocksLabel  = new JLabel("Available Stocks");
        availableStocksTextField     = new JTextField();
        availableStocksTextField.setEditable(false);

        updateAvailableShares(userPortfolio);
        JLabel quantityLabel         = new JLabel("Quantity: ");
        JTextField quantityTextField = new JTextField();

        JLabel valueLabel            = new JLabel("Total Value: ");
        JTextField valueTextField    = new JTextField();
        valueTextField.setEditable(false);

        symbolComboBox.addActionListener(e -> {
            quantityTextField.setText("");
            valueTextField.setText("");
        });


        JButton sellButton = new JButton("Sell");
        sellButton.setEnabled(false);
        sellButton.addActionListener(e -> {
            String symbol           = (String) symbolComboBox.getSelectedItem();
            Integer quantity        = Integer.parseInt(quantityTextField.getText());
            BigDecimal totalVal     = BigDecimal.valueOf(Double.parseDouble(valueTextField.getText()));
            Integer availableShares = userPortfolio.getShares().getOrDefault(symbol, 0);

            if (availableShares >= quantity) {
                userPortfolio.setCash(userPortfolio.getCash().add(totalVal));
                userPortfolio.getShares().compute(symbol, (k, v) -> v != null ? v - quantity : quantity);
                JOptionPane.showMessageDialog(this, "Transaction is successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateAvailableShares(userPortfolio);
                try {
                    Save.clearSymbol("Stocks.txt");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this, "This is not available" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "There are not enough shares to sell", "Error", JOptionPane.ERROR_MESSAGE);
            }
            quantityTextField.setText("");
            valueTextField.setText("");
            sellButton.setEnabled(false);
        });

        JButton costButton = new JButton("Get value");
        costButton.addActionListener(e ->
                calculateTotalCostActionPerformed(symbolComboBox, quantityTextField, valueTextField, sellButton));

        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(10, 2));
        sellPanel.add(availableStocksLabel);
        sellPanel.add(availableStocksTextField);
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(symbolLabel);
        sellPanel.add(symbolComboBox);
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(quantityLabel);
        sellPanel.add(quantityTextField);
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(valueLabel);
        sellPanel.add(valueTextField);
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(new JPanel()); // empty cell in the grid
        sellPanel.add(costButton);
        sellPanel.add(sellButton);
        add(sellPanel);
        add(new JPanel()); // empty cell in the grid
        add(new JPanel()); // empty cell in the grid
        add(new JPanel()); // empty cell in the grid

        try{
            clearSymbol(filename);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this,"Error Occurred " +e.getMessage());
        }
    }

    private void calculateTotalCostActionPerformed(JComboBox<String> symbolComboBox, JTextField quantityTextField, JTextField totalValueTextField, JButton sellButton) {
        try {
            String symbol = (String) symbolComboBox.getSelectedItem();
            BigDecimal stockPrice = mainFrame.getMarketService().getStockPrice(symbol);

            try {
                int quantity = Integer.parseInt(quantityTextField.getText());
                totalValueTextField.setText(
                        Utils.formatBigDecimal(stockPrice.multiply(new BigDecimal(quantity))));
                sellButton.setEnabled(true);
            } catch (NumberFormatException e) {
                totalValueTextField.setText("Invalid quantity value");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(StockMarketJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateAvailableShares(UserPortfolio portfolio) {
        if (portfolio != null) {
            Set<String> symbolsOwned = new HashSet<>();
            try {
                symbolsOwned = getShares(portfolio,filename);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error loading panel", "Error", JOptionPane.ERROR_MESSAGE);
            }
            for(String symbol:symbolsOwned){
                System.out.println(symbol);
            }
            DefaultComboBoxModel<String> symbolComboBoxModel = new DefaultComboBoxModel<>();
            for (String symbol : symbolsOwned) {
                symbolComboBoxModel.addElement(symbol);
            }
            symbolComboBox.setModel(symbolComboBoxModel);
            String selectedSymbol = (String) symbolComboBox.getSelectedItem();
            if (selectedSymbol != null) {
                Integer shares = portfolio.getShares().getOrDefault(selectedSymbol,0);
                availableStocksTextField.setText(shares.toString());
            } else {
                availableStocksTextField.setText("N/A");
            }
        }
    }
    public static Set<String> getShares(UserPortfolio portfolio, String filename) throws Exception {
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
            }
            catch(Exception e){
                System.out.println("Error in reading " + e.getMessage());
            }
        }
        return symbols;
    }
    public static void clearSymbol(String filename){
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("");
            } catch (IOException e) {
                System.err.println("Error occurred while deleting file content: " + e.getMessage());
            }
        }
    }
}


