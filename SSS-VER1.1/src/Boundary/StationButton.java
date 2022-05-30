package Boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Control.BackendLogic;

public class StationButton extends JButton {
	Boolean borrow= false;
	public StationButton(String name, Boolean borrow) {
		this.borrow = borrow;
		this.setText(name);
		Font fL = new Font("Times", Font.PLAIN, 30);
		this.setFont(fL);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("aaa");
			}
		});
	
	}

	
		
}
