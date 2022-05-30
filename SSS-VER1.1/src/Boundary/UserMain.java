package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Control.BackendLogic;

public class UserMain extends FrameInitiation {
	JButton sAButton, sBButton, sCButton, checkUsageButton;
	static JFrame user;
	public UserMain() {
		user = mainFrame;
	}

	/**
	 * build the user page
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public void theMainPanel() throws Exception {
		initiateFrame();
		boolean borrow = BackendLogic.checkBorrow();// 检查该用户的借还情况，用这个Boolean判断后面显示的界面

		initiatePanel(5, 1, 20, 10);
		sAButton = new StationA("Station A",borrow);
		sBButton = new StationB("Station B",borrow);
		sCButton = new StationC("Station C",borrow);
		
		checkUsageButton = new JButton("Check Usage");

		checkUsageButton.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(sAButton);
		mainPanel.add(sBButton);
		mainPanel.add(sCButton);
	
		mainPanel.add(checkUsageButton);
		mainPanel.add(backButton);
		
		checkUsageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				UsagePanel up = new UsagePanel(BackendLogic.getID(), 2);
			}
		});// jump to the page that user can check the his/her own usage

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