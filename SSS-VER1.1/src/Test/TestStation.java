package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Control.BackendLogic;
import Control.StationControl;

class TestStation {

	@Test
	void test() throws Exception {
		StationControl a = new StationControl();
		// 借车 得到一个station B 的非空位置 的值
		int pos = a.getStation(1).getPosition("1");
		// 选中后看 设置为0
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "0");
			assertEquals("0", a.getStation(1).checkPosition(pos));
		}
		// 得到一个station B 的空位置 的值
		pos = a.getStation(1).getPosition("0");
		// 选中后看 设置为1
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "1");
			assertEquals("1", a.getStation(1).checkPosition(pos));
		}
	}

}
