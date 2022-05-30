package Boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Operation.BackendLogic;
import Operation.FileOperation;
import Operation.Report;
import Operation.TimeControl;

public class UsagePanel extends FrameInitiation {
	JPanel leftPanel = null;
	JPanel show = null;
	ButtonGroup chooseGroup = null;
	JRadioButton day = null;
	JRadioButton week = null;
	JRadioButton month = null;
	JRadioButton year = null;

	TitledBorder ts;
	JButton back = null;
	JTable friends = null;
	private DefaultTableModel model = null;
	private JTable table = null;
	/**
      * build the user usage situation page
      * @param userID,type
      * @return
      * @throws  
      */
	public UsagePanel(String userID, int type) {
		initiateFrame();
		leftPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chooseGroup = new ButtonGroup();

		day = new JRadioButton("Daily   ");
		day.setFont(fL);
		week = new JRadioButton("Weekly   ");
		week.setFont(fL);
		month = new JRadioButton("Monthly   ");
		month.setFont(fL);
		year = new JRadioButton("Yearly   ");
		year.setFont(fL);
		day.isSelected();
		chooseGroup.add(day);
		chooseGroup.add(week);
		chooseGroup.add(month);
		chooseGroup.add(year);
		leftPanel.add(day);
		leftPanel.add(week);
		leftPanel.add(month);
		leftPanel.add(year);
		show = new JPanel();

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 450));
		ts = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Usage Information");

		ts.setTitleJustification(TitledBorder.LEFT);
		ts.setTitleFont(fS);

		DefaultTableModel d = new DefaultTableModel();
		d.addColumn("");

		String[][] datas = {};
		String[] titles = { "Time", "Usage" };
		model = new DefaultTableModel(datas, titles);

		table = new JTable(model);
		table.setRowHeight(50);
		table.setGridColor(Color.GRAY);
		JTableHeader head = table.getTableHeader();
		head.setPreferredSize(new Dimension(head.getWidth(), 30));
		head.setFont(fL);

		table.setEnabled(false);
		table.setDragEnabled(false);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);

		panel.add(new JScrollPane(table));

		show.add(panel);
		show.setBorder(ts);

		mainPanel.setLayout(new BorderLayout(15, 5));
		JPanel down = new JPanel();
		mainPanel.add(BorderLayout.CENTER, show);
		mainPanel.add(BorderLayout.WEST, leftPanel);

		mainPanel.setSize(600, 450);
		down.add(backButton);
		mainPanel.add(BorderLayout.SOUTH, down);
		backButton.setFont(fS);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type == 1) {
					mainFrame.dispose();
					UsageSearchPanel us = new UsageSearchPanel();
					us.createDiscriptionPanel();
				} else if (type == 2) {
					mainFrame.dispose();
					UserMain um = new UserMain();
					try {
						um.theMainPanel();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});// back to the usage search page

		day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				try {
					String usageFile = ".\\file\\usage\\" + userID + ".txt";
					ArrayList<String> list = FileOperation.getFileContent(usageFile, 8);
					for (String temp : list) {
						long aa = Report.calDaily(usageFile, temp);
						String value = TimeControl.transTime(aa);
						// System.out.println(temp +": "+ value);
						model.addRow(new Object[] { temp, value });
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		week.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String date = df.format(new Date());// new Date()
				String usageFile = ".\\file\\usage\\" + userID + ".txt";
				for (int i = 0; i < 7; i++) {
					String temp;
					try {
						temp = TimeControl.getDayOfWeek(date, i);
						// System.out.println(temp);
						long aa = Report.calDaily(usageFile, temp);
						// System.out.println("i:"+ i +aa);
						if (aa > 0) {
							String value = TimeControl.transTime(aa);
							model.addRow(new Object[] { temp, value });
						}
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					} catch (Exception e2) {
						
						e2.printStackTrace();
					}

				}
			}
		});

		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				try {
					String usageFile = ".\\file\\usage\\" + userID + ".txt";
					ArrayList<String> list = FileOperation.getFileContent(usageFile, 12);
					for (String temp : list) {
						long aa = Report.calDaily(usageFile, temp);
						String value = TimeControl.transTime(aa);
						// System.out.println(temp +": "+ value);
						model.addRow(new Object[] { temp, value });
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				try {
					String usageFile = ".\\file\\usage\\" + userID + ".txt";
					ArrayList<String> list = FileOperation.getFileContent(usageFile, 15);
					for (String temp : list) {
						long aa = Report.calDaily(usageFile, temp);
						String value = TimeControl.transTime(aa);
						// System.out.println(temp +": "+ value);
						model.addRow(new Object[] { temp, value });
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
