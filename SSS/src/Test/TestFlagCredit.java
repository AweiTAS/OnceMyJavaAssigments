package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Operation.BackendLogic;

class TestFlagCredit {
	//part 1 check status
	//part 2 change status
	@Test
	void test() {
		boolean flag = BackendLogic.checkID("160921920", true);
		//System.out.print(flag);
		if (flag) {
			try {
				assertEquals(false,BackendLogic.checkCredit());
				BackendLogic.changeCreditToBlack();
				assertEquals(true,BackendLogic.checkCredit());
				System.out.println("White to Black");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("******************");
		flag = BackendLogic.checkID("160921921", true);
		//System.out.print(flag);
		if (flag) {
			try {
				assertEquals(true,BackendLogic.checkCredit());
				BackendLogic.changeCreditToWhite();
				assertEquals(false,BackendLogic.checkCredit());
				System.out.println("Black to White");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}