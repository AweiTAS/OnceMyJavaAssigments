package Boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import Operation.SendMail;
import Operation.StationControl;

public class RunningProgress {
static StationControl a =null;
static int stationNum = 6;
static ArrayList<Integer> slotNum = new ArrayList<Integer>();
static int slotNumber[] = {7,8,6,15,7,9};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		for(int i = 0; i < stationNum; i++) {
			slotNum.add(slotNumber[i]);
		}
		a = new StationControl();
		MainFrame mainFrame = new MainFrame();
		mainFrame.theMainPanel();
		SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Timer timerRefresh = new Timer();
		timerRefresh.schedule(new TimerTask() {
			public void run() {
				try {
					SendMail.deterSend(yearMonthDay.format(new Date()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 0, 60000);
	}
}
