package isp.lab9.exercise1.ui;

import isp.lab9.exercise1.services.UserPortfolio;
import isp.lab9.exercise1.utils.Utils;
import isp.lab9.exercise1.ui.Save;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuyJPanel extends JPanel {
    private StockMarketJFrame   mainFrame;
    private static final String Directory = "Portfolio/";
    public BuyJPanel(StockMarketJFrame mainFrame) {
        this.mainFrame = mainFrame;
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridLayout(2, 2));

        JPanel buyPanel      = new JPanel();
        buyPanel.setLayout(new GridLayout(10, 2));

        JLabel availableFundsLabel = new JLabel("Available funds:");
        JTextField availableFundsTextField = new JTextField(mainFrame.getPortfolio().getCash().toPlainString() + " $");
        availableFundsTextField.setEditable(false);

        JLabel symbolLabel   = new JLabel("Symbol:");
        JComboBox<String> symbolComboBox = new JComboBox<>();
        symbolComboBox.setModel(new DefaultComboBoxModel<>(mainFrame.getMarketService().getSymbols()));

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityTextField = new JTextField();

        JLabel costLabel     = new JLabel("Total cost:");
        JTextField costTextField = new JTextField();
        costTextField.setEditable(false);

        JButton buyButton    = new JButton("Buy");
        buyButton.setEnabled(false);

        buyButton.addActionListener(e -> {
            UserPortfolio portfolio = mainFrame.getPortfolio();
            String symbol           = (String) symbolComboBox.getSelectedItem();
            Integer quantity        = Integer.parseInt(quantityTextField.getText());
            BigDecimal totalCost    = BigDecimal.valueOf(Double.parseDouble(costTextField.getText()));

            if (portfolio.getCash().compareTo(totalCost) > 0) {
                portfolio.setCash(portfolio.getCash().subtract(totalCost));
                availableFundsTextField.setText(Utils.formatBigDecimal(portfolio.getCash()));
                portfolio.getShares().compute(symbol, (k, v) -> v != null ? v + quantity : quantity);

                try{
                    String filename = Directory + "Stocks.txt";
                    File file = new File(filename);
                    if(portfolio.getShares().containsKey(symbol)) {
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                            writer.write(symbol + ": " + quantity + " ," + totalCost + System.lineSeparator());
                            writer.flush();
                        } catch (IOException ex) {
                            System.out.println("Error saving shares: " + ex.getMessage());
                        }
                    }
                    else{
                        System.out.println("Error when saving");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(this,"Error saving portfolio: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Not enough funds",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            quantityTextField.setText("");
            costTextField.setText("");
            buyButton.setEnabled(false);
        });

        JButton costButton = new JButton("Get cost");
        costButton.addActionListener(
            e -> calculateTotalCostActionPerformed(symbolComboBox, quantityTextField, costTextField, buyButton));

        buyPanel.add(availableFundsLabel);
        buyPanel.add(availableFundsTextField);
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(symbolLabel);
        buyPanel.add(symbolComboBox);
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(quantityLabel);
        buyPanel.add(quantityTextField);
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(costLabel);
        buyPanel.add(costTextField);
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(new JPanel()); // empty cell in the grid
        buyPanel.add(costButton);
        buyPanel.add(buyButton);
        add(buyPanel);
        add(new JPanel()); // empty cell in the grid
        add(new JPanel()); // empty cell in the grid
        add(new JPanel()); // empty cell in the grid

    }

    /**
     * Calculates the total transaction cost
     */
    private void calculateTotalCostActionPerformed(JComboBox<String> symbolComboBox,
                                                   JTextField quantityTextField,
                                                   JTextField totalCostTextField, JButton buyButton) {
        try {
            String symbol = (String) symbolComboBox.getSelectedItem();
            BigDecimal stockPrice = mainFrame.getMarketService().getStockPrice(symbol);

            try {
                int quantity = Integer.parseInt(quantityTextField.getText());
                totalCostTextField.setText(Utils.formatBigDecimal(stockPrice.multiply(new BigDecimal(quantity))));
                buyButton.setEnabled(true);
            } catch (NumberFormatException e) {
                totalCostTextField.setText("Invalid quantity value");
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
}
