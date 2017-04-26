package nl.ru.ai.hci.paint;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		/*
		 * JFrame mainFrame = new JFrame("Paint"); DrawPanel drawPanel = new
		 * DrawPanel(); mainFrame.setVisible(true);
		 * mainFrame.setDefaultCloseOperation(3);
		 * 
		 * JPanel contentPanel = new JPanel(); contentPanel.setLayout(new
		 * BorderLayout());
		 * 
		 * JPanel buttonPanel = new ButtonPanel(drawPanel);
		 * contentPanel.add(buttonPanel, "East");
		 * 
		 * mainFrame.getContentPane().add(buttonPanel); mainFrame.pack();
		 */

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Window();
			}
		});

	}

}
