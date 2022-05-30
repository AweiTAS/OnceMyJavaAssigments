package Control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Entity.Station;

//This class used to save station elements and control(get,set) station elements
public class StationControl {
	ArrayList<Station> stationList = new ArrayList<Station>();
	static String fileName = ".\\file\\station.txt";

	public StationControl() throws Exception {
		StringBuffer sb = new StringBuffer("");
		FileReader reader = new FileReader(fileName);
		BufferedReader br = new BufferedReader(reader);
		StringBuffer buf = new StringBuffer();
		String str = null;
		boolean flag = false;
		int i = 0;
		while ((str = br.readLine()) != null) {
			stationList.add(new Station(str, i));
			i++;
		}
		br.close();
		reader.close();
		System.out.println("Station init success");
		
	}


	/**
	 * �õ�sation ��i�����
	 * get the i element of the station 
	 * @param i
	 * @return Station
	 */
	public Station getStation(int i) {
		return stationList.get(i);
	}

	/**
	 * �õ�sation ����
	 * get the numbers of stations 
	 * @param i
	 * @return int
	 */
	public int getStationNum() {
		return stationList.size();
	}
	
	public int getSlotNum() {
		int a = 0;
		for (int i = 0 ; i < stationList.size();i++) {
			int sizeNum  = stationList.get(i).getStationSize();
			a = a + sizeNum;
		}
		
		return a/stationList.size();		
	}
	/**
	 * �õ�ֵΪvalue��station���
	 * get the element value of the station 
	 * @param value
	 * @return count
	 */
	public int getStatus(String value) throws Exception {//�������station��valueֵ�ø���
		int count = 0;
		for (int k = 0; k < stationList.size(); k++) {
			count = count + stationList.get(k).checkPosition(value);
		}
		return count;
	}

	// 
	
	/**
	 * �޸�Station״̬, station�ļ���line�� posλ�� ֵΪvalue
	 * change status
	 * @param value
	 * @param line
	 * @param pos
	 * @return 
	 */
	public static void changeStationStatus(int line, int pos, String value) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String fileLine;
		String content = "";
		int i = 0;
		while ((fileLine = br.readLine()) != null) {
			if (i == line) {
				String info[] = fileLine.split(",");
				content = content + "!!!!";
				for (int j = 0; j < info.length; j++) {
					if (j != pos) {
						content = content + info[j] + ",";
					} else {
						content = content + value + ",";
					}
				}
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

}
