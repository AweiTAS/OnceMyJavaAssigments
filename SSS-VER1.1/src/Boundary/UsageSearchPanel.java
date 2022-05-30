package Boundary;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.BackendLogic;

public class UsageSearchPanel extends FrameInitiation {
	JPanel subPanel;
	JLabel titleText, text;
	JTextField tf;
	JButton searchButton;

	/**
	 * build the usage searching page 
	 * @param 
	 * @return
	 */
	public void createDiscriptionPanel() {
		initiateFrame();
		initiatePanel(3, 1, 30, 20);
		subPanel = new JPanel();
		titleText = new JLabel("", JLabel.CENTER);
		text = new JLabel("QM numbers:", JLabel.CENTER);
		tf = new JTextField();
		searchButton = new JButton("Search");

		titleText.setFont(fL);
		text.setFont(fL);
		tf.setFont(fL);
		searchButton.setFont(fL);
		backButton.setFont(fL);
		subPanel.setLayout(new GridLayout(1, 3, 10, 10));
		mainPanel.add(titleText);
		subPanel.add(text);
		subPanel.add(tf);
		subPanel.add(searchButton);
		mainPanel.add(subPanel);
		mainPanel.add(backButton);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (BackendLogic.checkID(tf.getText(), false)) {
					mainFrame.dispose();
					UsagePanel up = new UsagePanel(tf.getText(), 1);
				} else {
					JOptionPane.showMessageDialog(null, "ID not exist", "Wrong!!", JOptionPane.WARNING_MESSAGE);
				}
			}

		});// jump to the usage page that present the usage situation of the specific user
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				mainFrame.dispose();
				ManagerMain mm = new ManagerMain();
				mm.theMainPanel();
			}

		});// back to the manager page

	}
}
