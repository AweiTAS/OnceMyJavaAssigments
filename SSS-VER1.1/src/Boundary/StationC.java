package Boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Control.BackendLogic;

public class StationC extends StationButton {
	public StationC(String name, Boolean borrow) {
		super(name, borrow);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Init();
			}
		});
	}
	/**
     * initiate the stationc 
     * @param 
     * @return
     * @throws  
     */
	public void Init() {//
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
					UserMain.user.dispose();
					SlotPanel sp = new SlotPanel();
					sp.createSlotPanel(3, pos, borrow);// default
				}
			} // jump to station slot page
			else {
				UserMain.user.dispose();
				Charge c = new Charge();
				c.theMainPanel(borrow, "", 0);// first & second can change

			} // jump to payment page
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
