package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Operation.BackendLogic;
import Operation.StationControl;

class TestStation {

	@Test
	void test() throws Exception {
		StationControl a = new StationControl();
		int pos = a.getStation(1).getPosition("1");
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "0");
			assertEquals("0", a.getStation(1).checkPosition(pos));
		}
		pos = a.getStation(1).getPosition("0");
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "1");
			assertEquals("1", a.getStation(1).checkPosition(pos));
		}
	}

}
