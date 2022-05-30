package Boundary;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class FrameInitiation {
	JFrame mainFrame = new JFrame();
	JPanel mainPanel= new JPanel();
	Font fL = new Font("Times",Font.PLAIN,30);
	Font fM = new Font("Times",Font.PLAIN,20);
	Font fS = new Font("Times",Font.PLAIN,15);
	JButton backButton = new JButton("Back");
	/**
      * initiate the basic frame
      * @param 
      * @return
      * @throws  
      */
	public void initiateFrame(){
		mainFrame.setTitle("Scooter Sharing System");
		mainFrame.setVisible(true);
		mainFrame.setBounds(0,0,900,700);
		mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 80, 70));
		mainFrame.add(mainPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
      * initiate the basic panel
      * @param 
      * @return
      * @throws  
      */
	public void initiatePanel(int x,int y,int l,int h){
		mainPanel.setLayout(new GridLayout(x,y,l,h));
		
	}
	
}
