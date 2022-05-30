package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Control.BackendLogic;
import Control.StationControl;

class TestStation {

	@Test
	void test() throws Exception {
		StationControl a = new StationControl();
		// �賵 �õ�һ��station B �ķǿ�λ�� ��ֵ
		int pos = a.getStation(1).getPosition("1");
		// ѡ�к� ����Ϊ0
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "0");
			assertEquals("0", a.getStation(1).checkPosition(pos));
		}
		// �õ�һ��station B �Ŀ�λ�� ��ֵ
		pos = a.getStation(1).getPosition("0");
		// ѡ�к� ����Ϊ1
		if (pos == -1) {
			System.out.println("No place ");
		} else {
			a.getStation(1).setPosition(pos, "1");
			assertEquals("1", a.getStation(1).checkPosition(pos));
		}
	}

}
