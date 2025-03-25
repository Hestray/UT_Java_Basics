/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isp.lab10.raceapp;

import javax.swing.*;
import java.awt.*;

public class Semaphore {
    // idk
    private static JFrame frame = new JFrame("Semaphore");
    private SemaphoreThread semaphoreThread;
    public Semaphore() {
        this.window();
    }
    public void window() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SemaphorePanel semaphorePanel = new SemaphorePanel();

        frame.getContentPane().add(semaphorePanel);
        frame.pack();

        this.semaphoreThread = new SemaphoreThread(semaphorePanel);
    }

    public void closeWindow() { frame.setVisible(false); }
    public void start() {
        frame.setVisible(true); // set the frame to be visible
        // start the thread
        this.semaphoreThread.start();
        try {
            this.semaphoreThread.join();
        } catch (InterruptedException e) {
            System.out.println("Semaphore was interrupted by Godzilla.");
        }
    }
}

class SemaphoreThread extends Thread {
    private SemaphorePanel semaphorePanel;
    
    public SemaphoreThread(SemaphorePanel semaphorePanel) {
        this.semaphorePanel = semaphorePanel;
    }
    
    public void run() {
        try {
            semaphorePanel.setGray();
            Thread.sleep((int) (Math.random() * 5000) + 2000);
            
            semaphorePanel.setYellow();
            Thread.sleep((int) (Math.random() * 5000) + 2000);
            
            semaphorePanel.setGreen();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SemaphorePanel extends JPanel {
    private Color color;
    
    public SemaphorePanel() {
        setPreferredSize(new Dimension(100, 300));
        color = Color.GRAY;
    }
    
    public void setGray() {
        color = Color.GRAY;
        repaint();
    }
    
    public void setYellow() {
        color = Color.YELLOW;
        repaint();
    }
    
    public void setGreen() {
        color = Color.GREEN;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int diameter = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        
        g.setColor(color);
        g.fillOval(x, y, diameter, diameter);
        
        g.setColor(Color.BLACK);
        g.drawOval(x, y, diameter, diameter);
    }
}
