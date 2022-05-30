package Boundary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Operation.BackendLogic;
import Operation.FileOperation;

public class SlotPanel extends FrameInitiation {
	JLabel titleText, stationText;
	JPanel subPanel, slotPanel, onClickPanel, secondPanel,titlePanel;
	JLabel hintText, timeText;
	JButton yesButton, noButton;
	int a, count, flag;
	String stationName;
	/**
      * build the page for user to choose scooter from each station
      * @param station,row,type
      * @return
      * @throws  
      */
	public void createSlotPanel(int station, int row, boolean type) {
		initiateFrame();
		mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
		titlePanel = new JPanel();
		subPanel = new JPanel();
		slotPanel = new JPanel();
		titleText = new JLabel();
		if (type)
			titleText.setText("Return scooter:");
		else
			titleText.setText("Pick up a scooter:");
		// choose return or pick up according to the value of type
		titleText.setFont(fL);
		backButton.setFont(fM);
		Dimension preferredSize = new Dimension(80,40);
		backButton.setPreferredSize(preferredSize);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT,200,0));
		titlePanel.add(titleText);
		titlePanel.add(backButton);
		mainPanel.add(titlePanel);
		subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		//if (station == 1)
		stationName = "Station " + (char)(64 + station);
		/*else if (station == 2)
			stationName = "Station B";
		else if (station == 3)
			stationName = "Station C";*/
		// choose station
		stationText = new JLabel(stationName);
		stationText.setFont(fL);

		slotPanel.setLayout(new GridLayout(1, RunningProgress.slotNum.get(station - 1), 2, 2));
		subPanel.add(stationText);
		subPanel.add(slotPanel);
		mainPanel.add(subPanel);
		/*
		 * Use for loop to create slot button and set the time to count time and imitate
		 * the light by change the button's background from red to white.
		 */
		for (int j = 0; j < RunningProgress.slotNum.get(station - 1); j++) {
			JButton b = new JButton("" + (j + 1));
			if (j == row) {
				b.setBackground(Color.GREEN);
				b.setOpaque(true);
				b.setBorderPainted(false);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						onClickPanel = new JPanel();
						secondPanel = new JPanel();
						mainPanel.add(onClickPanel);
						onClickPanel.setLayout(new GridLayout(3, 1, 0, 0));
						hintText = new JLabel("", JLabel.CENTER);
						timeText = new JLabel("", JLabel.RIGHT);
						yesButton = new JButton("Yes");
						noButton = new JButton("No");

						if (type)
							hintText.setText("Do you want to return this scooter?");
						else
							hintText.setText("Do you want to pick up this scooter?");
						// choose return or pick up according to the value of type
						Timer t = new Timer();// timer for counting time in 1 minute
						a = 59;// default
						t.schedule(new TimerTask() {
							@Override
							public void run() {
								if (a >= 0) {
									timeText.setText("Time:" + a + "s");
									a--;
								} else
									t.cancel();
							}
						}, 500, 1000);

						secondPanel.add(yesButton);
						secondPanel.add(noButton);
						onClickPanel.add(hintText);
						onClickPanel.add(secondPanel);
						onClickPanel.add(timeText);
						onClickPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						// default

						Timer timer = new Timer();// timer for change color
						count = 1;// default
						flag = 0;// default
						timer.scheduleAtFixedRate(new TimerTask() {
							@Override
							public void run() {
								if (count < 60) {
									if (flag == 0) {
										b.setBackground(Color.RED);
										flag++;
									} else {
										b.setBackground(Color.WHITE);
										flag--;
									}
									count++;
								} else {
									timer.cancel();
									mainFrame.dispose();
									UserMain um = new UserMain();
									try {
										um.theMainPanel();
									} catch (Exception e) {
										
										e.printStackTrace();
									}
								}
							}
						}, 500, 1000);// flash in 1 minute
						
						yesButton.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = df.format(new Date());// new Date()
								timer.cancel();
								mainFrame.dispose();
								if (!type) {
									try {
										BackendLogic.borrowS(date);
										BackendLogic.changeBorrowToOne();
										System.out.println("Borrow a scooter");
										RunningProgress.a.getStation(station-1).setPosition(row, "0");
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									SuccessPanel sp = new SuccessPanel();
									sp.chooseWord(type);
								}
								else {
									try {
										BackendLogic.returnS(date);
										BackendLogic.changeBorrowToZero();
										System.out.println("Return a scooter");
										RunningProgress.a.getStation(station-1).setPosition(row, "1");
									} catch (Exception e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
									try {
										if (!BackendLogic.checkCredit()) {
											SuccessPanel sp = new SuccessPanel();
											sp.chooseWord(type);
										} else {
											Charge c = new Charge();
											boolean borrow;
											try {
												borrow = BackendLogic.checkBorrow();
												c.theMainPanel(!borrow, "", 1);// first&second can change
											} catch (Exception e1) {
												
												e1.printStackTrace();
											}
										}
									} catch (Exception e1) {
										
										e1.printStackTrace();
									}
								}
							}
						});
						noButton.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								onClickPanel.setVisible(false);
								mainPanel.remove(onClickPanel);
								timer.cancel();
								b.setBackground(Color.GREEN);
								t.cancel();
							}// turn off the light when user click No

						});
					}
				});
			} else {
				b.setBackground(Color.WHITE);
				b.setOpaque(true);
				b.setBorderPainted(false);
			} // set other button into white color
			slotPanel.add(b);

		}
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				UserMain um = new UserMain();
				try {
					um.theMainPanel();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}// back to the user page
		});
	}

}
