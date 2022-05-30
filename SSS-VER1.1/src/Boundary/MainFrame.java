package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainFrame extends FrameInitiation{
	JButton userButton,managerButton;
	JLabel titleText;
	public MainFrame(){
	}
	/**
      * build the beginning page
      * @param 
      * @return
      * @throws  
      */
	public void theMainPanel(){
		initiateFrame();
		initiatePanel(3,1,40,30);
		userButton = new JButton("User");
		managerButton = new JButton("Manager");
		titleText = new JLabel("Scooter Sharing System");
		userButton.setFont(fL);
		managerButton.setFont(fL);
		titleText.setFont(fL);
		mainPanel.add(titleText);
		mainPanel.add(userButton);
		mainPanel.add(managerButton);
		userButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				Login l = new Login();
				l.theMainPanel();
			}
		});//jump to login page
		
		managerButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				ManagerMain mm = new ManagerMain();
				mm.theMainPanel();
			}
		});//jump to manager page
	}
	
}
