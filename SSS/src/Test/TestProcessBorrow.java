package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Operation.BackendLogic;
import Operation.FileOperation;

class TestProcessBorrow {

	@Test
	void test() {
		boolean flag = BackendLogic.checkID("161196499", true);
		if (flag) {
			try {
				BackendLogic.borrowS("2019-05-09 11:01:11");
				BackendLogic.changeBorrowToOne();
				assertEquals(true,BackendLogic.checkBorrow());
				assertEquals("2019-05-09 11:01:11,",FileOperation.getLastLine(".\\file\\usage\\161196499.txt"));
				System.out.println("Borrow a scooter");
				
				BackendLogic.returnS("2019-05-09 11:02:11");
				BackendLogic.changeBorrowToZero();
				assertEquals(false,BackendLogic.checkBorrow());
				assertEquals(false,BackendLogic.checkThisTime(60));
				assertEquals(false,BackendLogic.checkDaily("2019-05-09 11:02:11"));
				assertEquals(60,FileOperation.calDaily(".\\file\\usage\\161196499.txt","2019-05-09"));
				assertEquals("2019-05-09 11:01:11,2019-05-09 11:02:11,60,",FileOperation.getLastLine(".\\file\\usage\\161196499.txt"));
				System.out.println("Return a scooter");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
