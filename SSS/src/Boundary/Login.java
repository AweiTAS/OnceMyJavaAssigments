package Boundary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Operation.BackendLogic;

public class Login extends FrameInitiation {
	JLabel loginText, qmNoText;
	JTextField text;
	JButton enterButton;
	int n = 0;
	
	public Login() {
	}
    /**
      * build the login page
      * @param 
      * @return
      * @throws  
      */
	public void theMainPanel() {
		initiateFrame();
		initiatePanel(5, 1, 20, 20);
		loginText = new JLabel("Login", JLabel.CENTER);
		qmNoText = new JLabel("QM Numbers:", JLabel.CENTER);
		text = new JTextField(9);
		enterButton = new JButton("Enter");
		loginText.setFont(fL);
		qmNoText.setFont(fL);
		enterButton.setFont(fL);
		backButton.setFont(fL);
		mainPanel.add(loginText);
		mainPanel.add(qmNoText);
		mainPanel.add(text);
		mainPanel.add(enterButton);
		mainPanel.add(backButton);

		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = text.getText();
				if (id.length() != 9) {
					JOptionPane.showMessageDialog(null, "Wrong id length", "WARNING!!", JOptionPane.ERROR_MESSAGE);
				}
				if (BackendLogic.checkID(id, true)) {
					JOptionPane.showMessageDialog(null, "Login successful", "Information!!",
							JOptionPane.INFORMATION_MESSAGE);
					mainFrame.dispose();
					UserMain um = new UserMain();
					try {
						um.theMainPanel();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}// whether payed or not, default
				}
				else {
				JOptionPane.showMessageDialog(null, "ID Not Exist,\n Please register to manager", "WARNING!!", JOptionPane.ERROR_MESSAGE);
				}
			}// jump to the user page

		});
		
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
