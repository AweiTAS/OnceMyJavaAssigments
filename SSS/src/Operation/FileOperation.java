package Operation;

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
import java.text.ParseException;
import java.util.ArrayList;

import Entity.User;

public class FileOperation {

	/**
	 * get the last line of the text
	 * 
	 * @param usageFile
	 * @return content
	 * @exception IOException
	 */
	public static String getLastLine(String usageFile) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(usageFile)));
		String fileLine;
		String content = "";
		while ((fileLine = br.readLine()) != null) {
			content = fileLine;
		}
		return content;
	}

	/**
	 * add content to file
	 * 
	 * @param file
	 * @param content
	 * @return
	 */
	public static void addToFile(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			if (br.readLine() != null) {
				out.newLine();
			}
			out.write(conent);
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
	}

	/**
	 * clear the file
	 * 
	 * @param fileName
	 * @param content
	 * @return
	 * @exception IOException
	 */
	public static void clearFile(String fileName) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		StringBuffer buf = new StringBuffer();
		bw.write(buf.toString());
		bw.close();
	}

	//
	/**
	 * check whether the file is existed, otherwise it will create
	 * 
	 * @param fileName
	 * @param
	 * @return
	 */
	public static void checkExistAndCreate(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Wrong to create the usage file!");
			}
		}
	}

	/**
	 * When a user wants to use this system, check whether the user id in
	 * this system, return info to the system
	 * 
	 * @param fileName
	 * @param idI
	 * @return infoo
	 */
	public static String[] checkIDExist(String fileName, String idI) {
		// TODO Auto-generated method stub
		String[] infoo = { "1" };
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fileLine;
		String idSQL;
		try {
			while ((fileLine = br.readLine()) != null) {
				String[] info = fileLine.split("!!");
				idSQL = info[0].trim();
				if (idI.equals(idSQL)) {
					System.out.println(idI + " Login successful");
					return info;
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return infoo;
	}

	/**
	 * credit borrow change status & use for credit and borrow
	 * 
	 * @param file
	 * @param content
	 * @return
	 * throws IOException
	 */
	public static void changeStatus(String ID, String credit, String borrow) throws IOException {
		// TODO Auto-generated method stub
		String fileName = ".\\file\\info.txt";
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String fileLine;
		String content = "";
		int i = 0;
		while ((fileLine = br.readLine()) != null) {
			String info[] = fileLine.split("!!");
			if (info[0].equals(ID)) {
				content = content + "!!!!" + info[0] + "!!" + info[1] + "!!" + info[2] + "!!" + credit + "!!" + borrow;
			} else {
				content = content + "!!!!" + fileLine;
			}
			i++;
		}
		br.close();
		FileOperation.clearFile(fileName);

		String info[] = content.split("!!!!");
		for (int k = 0; k < info.length; k++) {
			FileOperation.addToFile(fileName, info[k]);
		}
	}

	/**
	 * add content to file last line
	 * @param usageFile
	 * @param back
	 * @return
	 * @throws Exception
	 */
	static void addToLastLine(String usageFile, String back) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(usageFile)));
		String fileLine;
		String content = "";
		while ((fileLine = br.readLine()) != null) {
			content = content + "!!!!" + fileLine;
		}
		br.close();
		FileOperation.clearFile(usageFile);
		content = content + back;
		String info[] = content.split("!!!!");
		for (int k = 0; k < info.length; k++) {
			FileOperation.addToFile(usageFile, info[k]);
		}
	}

	/**
	 * calculate a value from a dat
	 * @param userFileName
	 * @param time
	 * @return val
	 * @throws Exception
	 */
	public static long calDaily(String userFileName, String time) throws Exception {
		// TODO Auto-generated method stub
		long val = 0;
		FileReader readerb = new FileReader(userFileName);
		BufferedReader brb = new BufferedReader(readerb);
		String strr = "";
		while ((strr = brb.readLine()) != null) {
			if (strr.startsWith(time)) {
				String str2[] = strr.split(",");
				val = Long.valueOf(str2[2]) + val;
			}
		}
		brb.close();
		readerb.close();
		return val;
	}

	/**
	 * get content
	 * 
	 * @param fileName
	 * @param delete
	 * @return Arraylist
	 * @throws Exception
	 */
	public static ArrayList<String> getFileContent(String fileName, int delete) throws Exception {//check usage
		ArrayList<String> list = new ArrayList<String>();
		FileReader reader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(reader);
		String str = null;

		while ((str = br.readLine()) != null) {
			if (delete == 0) {
				list.add(str);
			} else {
				String str1[] = str.split(",");
				String borrowTime = str1[0];
				String time = borrowTime.substring(0, borrowTime.length() - delete);
				if (list.indexOf(time) == -1) {
					list.add(time);
				}
			}

		}
		br.close();
		reader.close();
		return list;
	}

}
