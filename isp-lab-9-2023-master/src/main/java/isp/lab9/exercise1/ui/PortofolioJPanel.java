package isp.lab9.exercise1.ui;

import isp.lab9.exercise1.services.StockItem;
import isp.lab9.exercise1.services.UserPortfolio;
import isp.lab9.exercise1.services.YahooWebClient;
import isp.lab9.exercise1.utils.Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * note: implement portfolio panel; for each owned stock add symbol, quantity, price per unit, total price of the position
 * note: it should look similar to the 'Market' panel
 */
public class PortofolioJPanel extends JPanel {
    private JTextField  portfolioValueTf;
    private JTable      portfolioTable;
    private JTextField  availableCashTf;
    private JComboBox<String> symbolComboBox;
    public PortofolioJPanel() {
        setLayout(null);
        JLabel portfolioValueL   = new JLabel("Portfolio value: ");
        portfolioValueL.setBounds(10, 10, 150, 20);

        portfolioValueTf         = new JTextField();
        portfolioValueTf.setBounds(180, 10, 150, 20);
        portfolioValueTf.setEditable(false);

        Object[]    columnNames  = {"Name", "Symbol", "Shares", "Price", "Currency", "Total Value"};
        Object[][]  data         = {};

        DefaultTableModel model  = new DefaultTableModel(data, columnNames);
        portfolioTable           = new JTable(model);

        JScrollPane scrollPane   = new JScrollPane(portfolioTable);
        scrollPane.setBounds(10, 50, 680, 250);

        JLabel availableCashL    = new JLabel("Available funds: ");
        availableCashL.setBounds(10, 310, 150, 20);

        availableCashTf          = new JTextField();
        availableCashTf.setBounds(180, 310, 150, 20);
        availableCashTf.setEditable(false);

        symbolComboBox           = new JComboBox<>();

        add(portfolioValueL);
        add(portfolioValueTf);
        add(scrollPane);
        add(availableCashL);
        add(availableCashTf);
    }

    public void refreshView(UserPortfolio portfolio) {
        Map<String, Integer> shares = portfolio.getShares();

        if (!shares.isEmpty()) {
            try {
                DefaultTableModel model = (DefaultTableModel) portfolioTable.getModel();
                model.setRowCount(0);

                BigDecimal[] portfolioValue = {portfolio.getCash()};

                Map<String, StockItem> stockItemMap = YahooWebClient.get(
                        shares.keySet().toArray(new String[0]))
                        .stream()
                        .collect(Collectors.toMap(StockItem::getSymbol, Function.identity()));

                shares.forEach((symbol, quantity) -> {
                    StockItem stockItem  = stockItemMap.get(symbol);
                    BigDecimal totalCost = stockItem.getPrice().multiply(BigDecimal.valueOf(quantity));
                    portfolioValue[0]    = portfolioValue[0].add(totalCost);
                    model.addRow(new Object[]{
                            stockItem.getName(),
                            stockItem.getSymbol(),
                            quantity.toString(),
                            Utils.formatBigDecimal(stockItem.getPrice()),
                            stockItem.getCurrency(),
                            Utils.formatBigDecimal(totalCost)
                    });
                });

                portfolioValueTf.setText(Utils.formatBigDecimal(portfolioValue[0])  + " $");
                availableCashTf.setText(Utils.formatBigDecimal(portfolio.getCash()) + " $");

                symbolComboBox.setModel(new DefaultComboBoxModel<>(portfolio.getShares().keySet().toArray(new String[0])));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        this,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            portfolioValueTf.setText(Utils.formatBigDecimal(portfolio.getCash()) + " $");
            availableCashTf.setText (Utils.formatBigDecimal(portfolio.getCash()) + " $");
            symbolComboBox.setModel (new DefaultComboBoxModel<>());
        }
    }

}
