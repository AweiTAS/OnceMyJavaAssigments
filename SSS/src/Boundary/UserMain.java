package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

import Operation.BackendLogic;

public class UserMain extends FrameInitiation {
	ArrayList<JButton> stationButtons;
	//JButton sAButton, sBButton, sCButton;
	JButton checkUsageButton;

	public UserMain() {
	}
	/**
      * build the user page
      * @param 
      * @return
      * @throws Exception
      */
	public void theMainPanel() throws Exception {
		stationButtons = new ArrayList<JButton>();
		initiateFrame();
		boolean borrow = BackendLogic.checkBorrow();

		initiatePanel(RunningProgress.stationNum + 2, 1, 20, 10);
		for(int i = 0; i < RunningProgress.stationNum; i++)
			stationButtons.add(new JButton("Station " + (char)(65 + i)));
		//sAButton = new JButton("Station A");
		//sBButton = new JButton("Station B");
		//sCButton = new JButton("Station C");
		checkUsageButton = new JButton("Check Usage");
		//new added start.......................................................
		for(int i = 0; i < RunningProgress.stationNum; i++)
			stationButtons.get(i).setFont(fL);
		//new added end.......................................................
		/*
		sAButton.setFont(fL);
		sBButton.setFont(fL);
		sCButton.setFont(fL);*/
		checkUsageButton.setFont(fL);
		backButton.setFont(fL);
		//new added start.......................................................
		for(int i = 0; i < RunningProgress.stationNum; i++)
			mainPanel.add(stationButtons.get(i));
		//new added end.......................................................
		/*
		mainPanel.add(sAButton);
		mainPanel.add(sBButton);
		mainPanel.add(sCButton);*/
		mainPanel.add(checkUsageButton);
		mainPanel.add(backButton);
		//new added start.......................................................
		for(int i = 0; i < RunningProgress.stationNum; i++) {
			stationButtons.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < RunningProgress.stationNum; i++) {
						if(stationButtons.get(i) == e.getSource()) {
							boolean flag;
							try {
								// check credit
								flag = BackendLogic.checkCredit();
								if (!flag) {
									// 0--return 1--borrow
									int pos = 0;
									if (borrow) {
										pos = RunningProgress.a.getStation(i).getPosition("0");
									} else {
										pos = RunningProgress.a.getStation(i).getPosition("1");
									}
									if (pos == -1) {
										System.out.println("No place ");
										JOptionPane.showMessageDialog(null, "No Place!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
									} else {
										mainFrame.dispose();
										SlotPanel sp = new SlotPanel();
										sp.createSlotPanel(i+1, pos, borrow);// default
									}
								} // jump to station slot page
								else {
									mainFrame.dispose();
									Charge c = new Charge();
									c.theMainPanel(borrow, "", 0);
									// first & second can change
								} // jump to payment page
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			});
		}
		//new added end.......................................................
		/*
		sAButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				try {
					// check credit
					flag = BackendLogic.checkCredit();
					if (!flag) {
						// 0--return 1--borrow
						int pos = 0;
						if (borrow) {
							pos = RunningProgress.a.getStation(0).getPosition("0");
						} else {
							pos = RunningProgress.a.getStation(0).getPosition("1");
						}
						if (pos == -1) {
							System.out.println("No place ");
							JOptionPane.showMessageDialog(null, "No Place!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
						} else {
							mainFrame.dispose();
							SlotPanel sp = new SlotPanel();
							sp.createSlotPanel(1, pos, borrow);// default
						}
					} // jump to station slot page
					else {
						mainFrame.dispose();
						Charge c = new Charge();
						c.theMainPanel(borrow, "", 0);
						// first & second can change
					} // jump to payment page
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
		});//jump to station A

		sBButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {boolean flag;
				try {
					flag = BackendLogic.checkCredit();
					if (!flag) {
						int pos = 0;
						if (borrow) {
							pos = RunningProgress.a.getStation(1).getPosition("0");
						} else {
							pos = RunningProgress.a.getStation(1).getPosition("1");
						}
						if (pos == -1) {
							System.out.println("No place ");
							JOptionPane.showMessageDialog(null, "No Place!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
						} else {
							mainFrame.dispose();
							SlotPanel sp = new SlotPanel();
							sp.createSlotPanel(2, pos, borrow);// default
						}
					} // jump to station slot page
					else {
						mainFrame.dispose();
						Charge c = new Charge();
						c.theMainPanel(borrow, "", 0);// first & second can change
						
					} // jump to payment page
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});//jump to station B
		sCButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean flag;
				try {
					flag = BackendLogic.checkCredit();
					if (!flag) {
						int pos = 0;
						if (borrow) {
							pos = RunningProgress.a.getStation(2).getPosition("0");
						} else {
							pos = RunningProgress.a.getStation(2).getPosition("1");
						}
						// System.out.print(pos);
						if (pos == -1) {
							System.out.println("No place ");
							JOptionPane.showMessageDialog(null, "No Place!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
						} else {
							mainFrame.dispose();
							SlotPanel sp = new SlotPanel();
							sp.createSlotPanel(3, pos, borrow);// default
						}
					} // jump to station slot page
					else {
						mainFrame.dispose();
						Charge c = new Charge();
						c.theMainPanel(borrow, "", 0);// first & second can change
						
					} // jump to payment page
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});//jump to station C
		 */
		checkUsageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				UsagePanel up = new UsagePanel(BackendLogic.getID(), 2);
			}
		});//jump to the page that user can check the his/her own usage 

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