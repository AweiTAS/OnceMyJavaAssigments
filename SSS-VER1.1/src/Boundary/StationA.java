package Boundary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Control.BackendLogic;

public class StationA extends StationButton {

	public StationA(String name, Boolean borrow) {
		super(name, borrow);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Init();
			}
		});
	}

	/**
     * initiate the station A 
     * @param 
     * @return
     * @throws  
     */
	public void Init() {
		boolean flag;
		try {
			// check credit
			flag = BackendLogic.checkCredit();
			if (!flag) {
				// 0--return 1--borrow
				int pos = 0;
				if (this.borrow) {// 是否借车，依据借还车改变
					pos = RunningProgress.a.getStation(0).getPosition("0");
				} else {
					pos = RunningProgress.a.getStation(0).getPosition("1");
				}
				if (pos == -1) {
					System.out.println("No place ");
					JOptionPane.showMessageDialog(null, "No Place!", "WARNING!!", JOptionPane.ERROR_MESSAGE);
				} else {
					UserMain.user.dispose();
					SlotPanel sp = new SlotPanel();
					sp.createSlotPanel(1, pos, borrow);// default
				}
			} // jump to station slot page
			else {
				UserMain.user.dispose();
				Charge c = new Charge();
				c.theMainPanel(borrow, "", 0);
				// first & second can change
			} // jump to payment page
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
