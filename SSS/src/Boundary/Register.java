package Boundary;

import javax.swing.*;

import Operation.BackendLogic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Register extends FrameInitiation{
	JLabel qmNoText,nameText,emailText;
	JTextField text1,text2,text3;
	JButton registerButton,resetButton;
	String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	Pattern pattern = Pattern.compile("\\d{9}");
	public Register(){
		
	}
	/**
      * build the register page
      * @param 
      * @return
      * @throws  
      */
	public void theMainPanel(){
		initiateFrame();
		initiatePanel(5,2,20,20);
		qmNoText = new JLabel("QM Numbers:");
		nameText = new JLabel("Full Name:");
		emailText = new JLabel("Email Address:");
		text1 = new JTextField(9);
		text2 = new JTextField(10);
		text3 = new JTextField(10);
		registerButton = new JButton("Register");
		resetButton = new JButton("Reset");
		qmNoText.setFont(fL);
		nameText.setFont(fL);
		emailText.setFont(fL);
		text1.setFont(fL);
		text2.setFont(fL);
		text3.setFont(fL);
		registerButton.setFont(fL);
		resetButton.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(qmNoText);
		mainPanel.add(text1);
		mainPanel.add(nameText);
		mainPanel.add(text2);
		mainPanel.add(emailText);
		mainPanel.add(text3);
		mainPanel.add(registerButton);
		mainPanel.add(resetButton);
		mainPanel.add(backButton);
		registerButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(pattern.matcher(text1.getText()).matches()){
					if(text2.getText().length() !=0){
						if(text3.getText().matches(reg)){
							if (!BackendLogic.checkID(text1.getText(), false)) {
								BackendLogic.register(text1.getText(), text2.getText(), text3.getText());
								mainFrame.dispose();
								ManagerMain mm = new ManagerMain();
								mm.theMainPanel();
								JOptionPane.showMessageDialog(null, "Register successful", "Information!!",
										JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null, "ID exist", "Information!!",
										JOptionPane.WARNING_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Input correct email format", "WARNING!!",
									JOptionPane.ERROR_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Input your name!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Wrong Id Format, it needs nine numbers", "WARNING!!", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});//set judgement conditions
		resetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				text1.setText("");
				text2.setText("");
				text3.setText("");
			}
		});//reset the text field
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				ManagerMain mm =  new ManagerMain();
				mm.theMainPanel();	
			}//back to the manager page
			
		});
	}
}
