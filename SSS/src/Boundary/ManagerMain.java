package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.Operation;

import javax.swing.*;

import Operation.SendMail;

public class ManagerMain extends FrameInitiation {
	JButton registerButton, sendEmailButton, checkUsageButton, checkStationButton;

	public ManagerMain() {
	}
    /**
      * build the manager page
      * @param 
      * @return
      * @throws  
      */
	public void theMainPanel() {
		initiateFrame();
		initiatePanel(5, 1, 40, 30);
		registerButton = new JButton("Register");
		sendEmailButton = new JButton("Send Email");
		checkUsageButton = new JButton("Check Usage");
		checkStationButton = new JButton("Check Station");
		registerButton.setFont(fL);
		sendEmailButton.setFont(fL);
		checkUsageButton.setFont(fL);
		checkStationButton.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(registerButton);
		mainPanel.add(sendEmailButton);
		mainPanel.add(checkUsageButton);
		mainPanel.add(checkStationButton);
		mainPanel.add(backButton);
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				mainFrame.dispose();
				Register r = new Register();
				r.theMainPanel();
			}
		});// jump to register page
		sendEmailButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SendMail.send();
					mainFrame.dispose();
					SuccessPanel sp = new SuccessPanel();
					sp.choose();// default,don't change
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});// jump to send email page
		checkUsageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				UsageSearchPanel up = new UsageSearchPanel();
				up.createDiscriptionPanel();
			}

		});// jump to check usage page
		checkStationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				StationPanel sp = new StationPanel();
				try {
					sp.createStationPanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});// jump to check station page
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				MainFrame mf = new MainFrame();
				mf.theMainPanel();
			}// back to the main page

		});
	}

}