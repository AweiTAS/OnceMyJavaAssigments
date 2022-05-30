package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Control.StationControl;

class TestManager {

	@Test
	void test() throws Exception {
		StationControl a = new StationControl();
		// 借车 得到一个station B 的非空位置 的值
		assertEquals(9,a.getStatus("0"));
		assertEquals(15,a.getStatus("1"));
		
		
	}

}
