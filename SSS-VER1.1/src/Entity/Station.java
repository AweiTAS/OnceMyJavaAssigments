package Entity;

import java.io.IOException;
import java.util.ArrayList;

import Control.StationControl;

public class Station {
	int line;
	ArrayList<String> station = new ArrayList<String>();
	public Station(String contents,int line) {
		this.line = line;
		String str2[] = contents.split(",");
		for (int i  = 0 ; i < str2.length;i++) {
			station.add(str2[i]);
		}
	}
	
	//返回以一个为 value 的位置
	//return the first pos with value 
	public int getPosition(String value) {
		int pos = -1;
		for (int j = 0 ; j < station.size(); j++) {
			if (station.get(j).equals(value)) {
				return j;
			}
		}
		return pos;
	}
	
	//将位置为pos的值变为 value
	//将文件修改
	//change the pos value to value
	//write the change in to file
	public void setPosition(int pos,String value) throws IOException {
		station.set(pos, value);
		StationControl.changeStationStatus(line, pos, value);
		System.out.println("Station " + this.line + " position "+String.valueOf(pos)+" Changed to " +value);
	}
	
	//用于test 检查
	public String checkPosition(int pos) throws IOException {
		return station.get(pos);
	}
	
	//计算值为 value的个数
	public int checkPosition(String value) throws IOException {
		int count = 0;
		for (int j = 0 ; j < station.size();j++) {
			if (station.get(j).equals(value)) {
				count ++;
			}
		}
		return count;
	}
	
	
	public ArrayList<String> getStation() {
		return station;
	}
	public void setStation(ArrayList<String> station) {
		this.station = station;
	}
	
	public int getStationSize() {
		return station.size();
	}
}
