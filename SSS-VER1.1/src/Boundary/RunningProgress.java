package Boundary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import Control.SendMail;
import Control.StationControl;

public class RunningProgress {
static StationControl a =null;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		a = new StationControl();
		MainFrame mainFrame = new MainFrame();
		mainFrame.theMainPanel();
		SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy-MM-dd-HH-mm");// 时间格式 为了存入文件
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
