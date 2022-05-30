package Control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;

public class GenerateReport {
	/**
	 * �õ�����ʹ�����
	 * get usage 
	 * @param id
	 * @param num
	 * @return 
	 */
	public static void getElement(String id,int num) throws Exception {
		String usageFile = ".\\file\\usage\\"+id +".txt";
		ArrayList<String> list = FileOperation.getFileContent(usageFile, num);
		for (String temp : list) {
			long aa = calDaily(usageFile, temp);
			String value = TimeControl.transTime(aa);
			//System.out.println(temp +": "+ value);
		}
	}
	
	/**
	 * �õ�һ�ܵ�����
	 * get the day of this week
	 * @param id
	 * @param date
	 * @return 
	 */
	public static void getWeek(String id,String date) throws Exception {
		String usageFile = ".\\file\\usage\\"+id +".txt";
		for (int  i = 0 ; i < 7; i++) {
			String temp = TimeControl.getDayOfWeek(date,i);
			//System.out.println(temp);
			long aa = calDaily(usageFile, temp);
			String value = TimeControl.transTime(aa);
			//System.out.println(temp +": "+ value);
		}
	}
	
	// 2019-04-09 11:02:11
	/**
	 * ��ĳֵ��ʼ��ȫ���е�ֵ ��2019-04-09 ��ʼ�ĵ��յ�ȫ������
	 * get usage from a value,eg 2019-04-09
	 * @param userFileName
	 * @param time
	 * @param val
	 * @return 
	 */
	public static long calDaily(String userFileName, String time) throws Exception {
		// TODO Auto-generated method stub
		long val = 0;
		FileReader readerb = new FileReader(userFileName);
		BufferedReader brb = new BufferedReader(readerb);
		// �ϲ��·�
		String strr = "";
		while ((strr = brb.readLine()) != null) {
			if (strr.startsWith(time)) {
				String str2[] = strr.split(",");
				//System.out.println(str2.length);
				if(str2.length ==3 ) {
					val = Long.valueOf(str2[2]) + val;
				}
			}
		}
		brb.close();
		readerb.close();
		return val;
	}
}
