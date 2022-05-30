package Operation;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import Entity.User;

public class SendMail {

	/**
	 * send the email
	 * 
	 * @param
	 * @return
	 */
	public static void send() throws Exception {
		// TODO Auto-generated method stub
		getFileList(1);
		System.out.println("Successful Send Mail ");
	}

	/**
	 * send the email
	 * 
	 * @param type,this
	 *            week or last week
	 * @return
	 */
	public static void getFileList(int type) throws Exception {
		File file = new File(".\\file\\usage\\");
		File[] fileList = file.listFiles();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date());// new Date()
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				String fileName = "";
				if (type == 1) {
					fileName = ".\\email\\simulate-" + date + "-" + fileList[i].getName();
				} else {
					fileName = ".\\email\\auto-" + date + "-" + fileList[i].getName();
				}
				File file1 = new File(fileName);
				if (file1.exists()) {
					FileOperation.clearFile(fileName);
				} else {
					FileOperation.checkExistAndCreate(fileName);
				}
				String usageFileName = ".\\file\\usage\\" + fileList[i].getName();
				String userName = fileList[i].getName().substring(0, 9);
				// System.out.println( fileList[i].getName());
				// System.out.println(userName);
				String infoFileName = ".\\file\\info.txt";
				String[] info = FileOperation.checkIDExist(infoFileName, userName);
				// String id = info[0].trim();
				// String name = info[1].trim();
				// String email = info[2].trim();
				// String creditStatus = info[3].trim();
				// String borrowStatus = info[4].trim();
				if (info.length != 1) {
				//	System.out.println("Aaa");
					User customer = new User(info[0].trim(), info[1].trim(), info[2].trim(), info[3].trim(),
							info[4].trim());
					FileOperation.addToFile(fileName, "User ID: " + customer.getName());
					if (customer.getCredit().equals("0")) {
						FileOperation.addToFile(fileName, "User Credit: False");
					} else {
						FileOperation.addToFile(fileName, "User Credit: True");
					}
				}

				for (int j = 0; j < 7; j++) {
					String temp = "";
					if (type == 1) {
						temp = TimeControl.getDayOfWeek(date, j);
					} else {
						temp = TimeControl.getLastTimeInterval(date, j + 1);
					}
					long aa = GenerateReport.calDaily(usageFileName, temp);
					if (aa > 0) {
						String value = TimeControl.transTime(aa);
						FileOperation.addToFile(fileName, temp + ": " + value);
						// System.out.println(temp +" "+value);
					}
				}
			}
		}
	}

	/**
	 * send the email, the first minute of this week
	 * 
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public static void deterSend(String time) throws Exception {
		// TODO Auto-generated method stub
		// "yyyy-MM-dd-HH-mm"
		// 0-year 1-month 2-day -hour -mm
		String timeList[] = time.split("-");
		String sendTime = TimeControl.getFirstDayOfWeek(time);//
		String sendTimeList[] = time.split("-");
		if (timeList[0].equals(sendTimeList[0])) {
			if (timeList[1].equals(sendTimeList[1])) {
				if (timeList[2].equals(sendTimeList[2])) {
					if (timeList[3].equals("00")) {
						if (timeList[4].equals("00")) {
							System.out.println("Send Consumption Automatically");
							getFileList(2);
						}
					}
				}
			}
		}
	}
}
