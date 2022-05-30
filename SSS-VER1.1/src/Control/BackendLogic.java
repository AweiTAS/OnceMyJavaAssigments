package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Entity.User;

public class BackendLogic {
	static User a;
	static DecimalFormat deFormat = new DecimalFormat("0.0000");

	/**
	 * When a user wants to use this system, check whether the user in this system
	 * When the user login to the system, check whether ID is repeated true : repeat false : new id
	 * get the last line of the text
	 * 
	 * @param idI
	 * @param loginFlag
	 * @return boolean 
	 */
	public static boolean checkID(String idI, boolean loginFlag) {
		boolean flag = false;
		String fileName = ".\\file\\info.txt";
		String[] info = null;
		info = FileOperation.checkIDExist(fileName, idI);
		// String id = info[0].trim();
		// String name = info[1].trim();
		// String email = info[2].trim();
		// String creditStatus = info[3].trim();
		// String borrowStatus = info[4].trim();
		if (info.length != 1) {
			flag = true;
			if (loginFlag) {
				a = new User(info[0].trim(), info[1].trim(), info[2].trim(), info[3].trim(), info[4].trim());
			}

		}
		return flag;
	}

	/**
	 * From GUI, we can get a user information
	 * This function can write in to file
	 * 
	 * @param ID
	 * @param name
	 * @param email
	 * @return boolean 
	 */

	// 
	public static boolean register(String ID, String name, String email) {
		boolean flag = false;
		String fileName = ".\\file\\info.txt";
		String usageFile = ".\\file\\usage\\" + ID + ".txt";

		FileOperation.checkExistAndCreate(usageFile);
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			if (br.readLine() != null) {
				out.newLine();
			}
			out.write(ID + "!!" + name + "!!" + email + "!!0!!0");
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * Check whether the user has borrowed a scooter
	 * 
	 * @param 
	 * @return boolean 
	 */
	public static boolean checkBorrow() throws Exception {
		boolean flag = true;
		String borrow = a.getBorrow();
		if (borrow.equals("0")) {
			flag = false;
			System.out.println(a.getUserId() + ": No Scooter");
		} else {
			System.out.println(a.getUserId() + ": Has a scooter");
		}
		return flag;
	}

	/**
	 * Check whether the user has borrowed a scooter
	 * 
	 * @param 
	 * @return boolean 
	 */
	public static boolean checkCredit() throws Exception {
		boolean flag = true;
		String credit = a.getCredit();
		if (credit.equals("0")) {
			flag = false;
			System.out.println(a.getUserId() + ": No fine");
		} else {
			System.out.println(a.getUserId() + ": Has fine");
		}
		return flag;
	}

	/**
	 * get the id of the user
	 * 
	 * @param 
	 * @return String
	 */
	public static String getID() {
		return a.getUserId();
	}

	/**
	 * update the status of credit to good file will change
	 * 
	 * @param 
	 * @return
	 */
	public static void changeCreditToWhite() throws Exception {
		a.setCredit("0");
		FileOperation.changeStatus(a.getUserId(), "0", a.getBorrow());
	}

	/**
	 * file will change, update the status of credit to bad
	 * 
	 * @param 
	 * @return
	 * @exception Exception
	 */
	public static void changeCreditToBlack() throws Exception {
		a.setCredit("1");
		FileOperation.changeStatus(a.getUserId(), "1", a.getBorrow());
	}

	/**
	 * update the status of borrow
	 * 
	 * @param 
	 * @return
	 * @exception Exception
	 */
	public static void changeBorrowToZero() throws Exception {
		a.setBorrow("0");
		FileOperation.changeStatus(a.getUserId(), a.getCredit(), "0");
	}

	/**
	 * update the borrow to one
	 * 
	 * @param 
	 * @return
	 * @exception Exception
	 */
	public static void changeBorrowToOne() throws Exception {
		a.setBorrow("1");
		FileOperation.changeStatus(a.getUserId(), a.getCredit(), "1");
	}

	/**
	 * write borrow info to usage file
	 * 
	 * @param  time
	 * @return
	 * @exception Exception
	 */
	public static void borrowS(String time) throws Exception {
		String usageFile = ".\\file\\usage\\" + a.getUserId() + ".txt";
		FileOperation.addToFile(usageFile, time+",");
	}

	/**
	 * write return info to usage file
	 * 
	 * @param  time
	 * @return
	 * @exception Exception
	 */
	public static void returnS(String time) throws Exception {
		String usageFile = ".\\file\\usage\\" + a.getUserId() + ".txt";
		String lastLine = FileOperation.getLastLine(usageFile);
		String info[] = lastLine.split(",");
		String borrow= info[0];
		long count = TimeControl.calUsage(borrow, time);
		FileOperation.addToLastLine(usageFile, time + "," + String.valueOf(count) +",");
		if(checkThisTime(count)||checkDaily(borrow)) {
			changeCreditToBlack();
		}
	}

	/**
	 * check this time fine status
	 * 
	 * @param  time
	 * @return boolean
	 * @exception Exception
	 */
		public static boolean checkThisTime(long time) throws Exception {
			boolean flag  = TimeControl.calThisTime(time);
			if (flag){
				//over time
				//changeCreditToBlack();
				System.out.println("This ride is OverTime");
				return true;
			}
			else {
				System.out.println("This ride is OK");
				return false;
			}
		}
		
		/**
		 * check day fine info
		 * 
		 * @param  borrow
		 * @return boolean
		 * @exception Exception
		 */
		public static boolean checkDaily(String borrow) throws Exception {
			String usageFile = ".\\file\\usage\\" + a.getUserId() + ".txt";
			String day = borrow.substring(0,10); 
			//System.out.println("day"+day);
			long dayUsage = FileOperation.calDaily(usageFile,day);
			boolean flag = TimeControl.calDay(dayUsage);
			if (flag){
				//over time
				//changeCreditToBlack();
				System.out.println("Day ride is OverTime");
				return true;
			}
			else {
				System.out.println("Day ride is OK");
				return false;
			}		
		}
}
