package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Control.BackendLogic;

class TestLogin {
	@Test
	void test() {
		assertEquals(false,BackendLogic.checkID("1234",true));
		assertEquals(false,BackendLogic.checkID("123",true));
		assertEquals(true,BackendLogic.checkID("161196499",true));
		assertEquals(true,BackendLogic.checkID("161196499",true));
	}

}
