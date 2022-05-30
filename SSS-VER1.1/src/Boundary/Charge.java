package Boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.BackendLogic;

public class Charge extends FrameInitiation {

	JButton payButton, runButton;
	JLabel usedTimeText, payText;

	public Charge() {

	}
	/**
      * build the charge page 
      * @param type,min,i
      * @return
      * @throws 
      */
	public void theMainPanel(boolean type, String min, int i) {
		initiateFrame();
		initiatePanel(4, 1, 40, 30);
		payButton = new JButton("Pay");
		runButton = new JButton("Maybe later...");
		usedTimeText = new JLabel("!!OVERTIME!!", JLabel.CENTER);
		payText = new JLabel("You need to pay 100 pounds or you can't use scooter again.", JLabel.CENTER);
		payButton.setFont(fL);
		runButton.setFont(fL);
		usedTimeText.setFont(fL);
		payText.setFont(fL);
		mainPanel.add(usedTimeText);
		mainPanel.add(payText);
		mainPanel.add(payButton);
		mainPanel.add(runButton);
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				if (i == 0) {
					//after login to pay
					
					UserMain um = new UserMain();
					try {
						um.theMainPanel();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						BackendLogic.changeCreditToWhite();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				} else {
					//after ride
					SuccessPanel s = new SuccessPanel();
					s.chooseWord(type);// choose pick up or return
					try {
						BackendLogic.changeCreditToWhite();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					BackendLogic.changeCreditToBlack();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				mainFrame.dispose();
				MainFrame mf = new MainFrame();
				mf.theMainPanel();
			}// go back to the main page
		});
	}
}