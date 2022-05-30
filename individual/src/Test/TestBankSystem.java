package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import Entity.BankSystem;
import Entity.Account;
/**
 * JUnit tests, works only when storage is empty
 * @author awei
 *
 */
public class TestBankSystem {

	@Test
	void test() {
		BankSystem s = new BankSystem("nmsl");
		
		assertEquals(false, s.addCustomer("awei", "bupt", 19980122, 2, 562123456, 70));
		assertEquals(false, s.addAccount("awei", 1, 123456, 70, Account.count));
		assertEquals(false, s.addCustomer("awei", "bupt", 19981322, 3, 562123456, 70));
		assertEquals(true, s.addCustomer("awei", "bupt", 19980122, 3, 562123456, 70));
		assertEquals(true, s.addCustomer("tas", "qmu", 22101111, 3, 562123456, 70));
		assertEquals(true, s.addAccount("awei", 1, 123456, 70, Account.count));
		
		Account a = s.getA(2);
		assertEquals(s.getC("awei").saver, a);
		assertEquals(70, a.deposit(40, "nmslwcnmb", false));
		assertEquals(110, a.deposit(40, "nmsl", false));
		assertEquals(110, a.deposit(40, "nmsl", true));
		try{Thread.sleep(15000);}catch(Exception e) {}
		assertEquals(150, a.deposit(40, "nmslwcnmb", false));
		
		
		assertEquals(110, a.withdraw(40, 123456));
		assertEquals(110, a.withdraw(120, 123456));
		
		a.suspend("nmsl");
		assertEquals(110, a.withdraw(40, 123456));
		a.restate("nmsl");
		assertEquals(false, s.addAccount("awei", 1, 123456, 70, Account.count));
		assertEquals(70, a.withdraw(40, 123456));
		
		assertEquals(false, s.clearAccount("awei", 1, 123456));
		assertEquals(0, a.withdraw(70, 123456));
		assertEquals(true, s.clearAccount("awei", 1, 123456));
		assertEquals(null, s.getC("awei").saver);
		assertEquals(null, s.getA(2));
		s.writeFile();
	}
}