package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Operation.StationControl;

class TestManager {

	@Test
	void test() throws Exception {
		StationControl a = new StationControl();
		assertEquals(9,a.getStatus("0"));
		assertEquals(15,a.getStatus("1"));
		
		
	}

}
