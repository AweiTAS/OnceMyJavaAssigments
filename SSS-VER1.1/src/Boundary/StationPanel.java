package Boundary;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StationPanel extends FrameInitiation{
	JLabel titleText,sA,sB,sC,label1,label2,label3,label4;
	JPanel subPanel,labelPanel,slotPanel,secondPanel,descriptionPanel;
	String state;
	/**
      * build the station page 
      * for presenting the usage situation of scooters 
      * in the three stations
      * @param 
      * @return
      * @throws  Exception
      */
	public void createStationPanel() throws Exception{
		initiateFrame();
		descriptionPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5,1,0,0));
		subPanel = new JPanel();
		labelPanel = new JPanel();
		slotPanel = new JPanel();
		titleText = new JLabel();
		titleText.setText("Stations situation:");
		//choose return or pick up according to the value of type
		titleText.setFont(fL);
		mainPanel.add(titleText);
		subPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelPanel.setLayout(new GridLayout(3,1,17,17));
		sA = new JLabel("Station A");
		sB = new JLabel("Station B");
		sC = new JLabel("Station C");
		label1 = new JLabel("Empty Slot:"+String.valueOf(RunningProgress.a.getStatus("0")));
		label2= new JLabel("      Free Scooter"+String.valueOf(RunningProgress.a.getStatus("1")));
		label3= new JLabel("Free Scooter - Yellow");
		label4= new JLabel("Empty Slot - Green");

		backButton.setFont(fL);
		sA.setFont(fS);
		sB.setFont(fS);
		sC.setFont(fS);
		label1.setFont(fL);
		label2.setFont(fL);
		label3.setFont(fM);
		label4.setFont(fM);
		
		labelPanel.add(sA);
		labelPanel.add(sB);
		labelPanel.add(sC);
		slotPanel.setLayout(new GridLayout(3,8,2,2));
		descriptionPanel.setLayout(new GridLayout(2,5,20,20));
		descriptionPanel.add(label1);
		descriptionPanel.add(label2);
		descriptionPanel.add(label3);
		descriptionPanel.add(label4);

		subPanel.add(labelPanel);
		subPanel.add(slotPanel);
		mainPanel.add(subPanel);
		mainPanel.add(descriptionPanel);
		mainPanel.add(backButton);
		
		/*
		 * Use for loop to create slot button 
		 * and empty slot is green
		 * full slot is yellow.
		 * */
		state="";
		int stationSize = RunningProgress.a.getStationNum();
		int slotSize =  RunningProgress.a.getSlotNum();
		for(int i=0;i<stationSize;i++){
			for(int j=0;j<slotSize;j++){
				JButton b = new JButton(""+(j+1));
				state = RunningProgress.a.getStation(i).checkPosition(j); 
				if(state.equals("0")){
					b.setBackground(Color.GREEN);
					b.setOpaque(true); 
					b.setBorderPainted(false); 
				}					
				else if(state.equals("1")){
					b.setBackground(Color.YELLOW);
					b.setOpaque(true); 
					b.setBorderPainted(false); 
				}
				slotPanel.add(b);	
			}
		}
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
