/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package isp.lab9.exercise1.ui;

import isp.lab9.exercise1.services.StockMarketQueryService;
import isp.lab9.exercise1.services.UserPortfolio;

import javax.swing.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mihai.hulea
 * @author radu.miron
 */
public class StockMarketJFrame extends JFrame {
    private StockMarketQueryService marketService;
    private UserPortfolio portfolio;

    /**
     * Creates new form StockMarketJFrame
     */
    public StockMarketJFrame() {
        try {
            marketService = new StockMarketQueryService();
            marketService.refreshMarketData();

            if(portfolio == null) {
                portfolio = new UserPortfolio(new BigDecimal(1000), new TreeMap<>());
            }
        } catch (IOException ex) {
            Logger.getLogger(StockMarketJFrame.class.getName()).log(Level.SEVERE, null, ex);
            portfolio = new UserPortfolio(new BigDecimal(1000),new TreeMap<>());
        }
        initComponents();
        setVisible(true);
    }
    public UserPortfolio initializePortfolio(){
        BigDecimal initialCash = new BigDecimal(1000);
        TreeMap<String,Integer> shares = new TreeMap<>();
        return new UserPortfolio(initialCash,shares);
    }
    /**
     * Initializes the window with the tabs and main panels. Each panel is defined in its own class.
     */
    private void initComponents() {
        this.setSize(700, 400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // configure windows the tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Market",        new MarketJPanel(this));
        tabs.addTab("UserPortfolio", new PortofolioJPanel());
        tabs.addTab("Buy",           new BuyJPanel(this));
        tabs.addTab("Sell",          new SellJPanel(this));

        tabs.addChangeListener(e -> {
            if (tabs.getSelectedComponent() instanceof PortofolioJPanel portofolioJPanel) {
                portofolioJPanel.refreshView(portfolio);
            } else if(tabs.getSelectedComponent() instanceof SellJPanel sellJPanel) {
                sellJPanel.updateAvailableShares(portfolio);

            }
        });
        this.add(tabs);
    }

    public StockMarketQueryService getMarketService() {
        return marketService;
    }

    public UserPortfolio getPortfolio() {
        return portfolio;
    }
}
