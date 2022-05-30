package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import Operation.BackendLogic;

public class TestFlagBorrow {
	@Test
	void test() {
		boolean flag = BackendLogic.checkID("160921922", true);
		if (flag) {
			try {
				assertEquals(false,BackendLogic.checkBorrow());
				BackendLogic.changeBorrowToOne();
				assertEquals(true,BackendLogic.checkBorrow());
				System.out.println("None to One");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("******************");
		flag = BackendLogic.checkID("160921923", true);
		if (flag) {
			try {
				assertEquals(true,BackendLogic.checkBorrow());
				BackendLogic.changeBorrowToZero();
				assertEquals(false,BackendLogic.checkBorrow());
				System.out.println("One to None");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
