package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SuccessPanel extends FrameInitiation {
	JLabel text = new JLabel("", JLabel.CENTER);
	JButton backButton = new JButton("Back");
    /**
      * build successfully sent email page
      * @param 
      * @return
      * @throws  
      */
	public void choose() {
		initiateFrame();
		initiatePanel(2, 1, 20, 20);
		text.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(text);
		mainPanel.add(backButton);
		text.setText("Sent email successfully.");
		// send email
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				mainFrame.dispose();
				ManagerMain mm = new ManagerMain();
				mm.theMainPanel();
			}// send email

		});
	}
	/**
      * build successfully picked up or returned page
      * @param 
      * @return
      * @throws  
      */
	public void chooseWord(boolean type) {
		initiateFrame();
		initiatePanel(2, 1, 20, 20);
		text.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(text);
		mainPanel.add(backButton);
		if (!type)
			text.setText("You've picked up the scooter successfully.");
		// pick up
		else if (type)
			text.setText("You've returned the scooter successfully.");
		// return
	
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					mainFrame.dispose();
					MainFrame mf = new MainFrame();
					mf.theMainPanel();
			}
		});//back to the main frame
	}
}
