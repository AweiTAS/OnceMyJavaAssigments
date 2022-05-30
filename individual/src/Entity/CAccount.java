package Entity;
/**
 * Current account
 * @author awei
 *
 */
public class CAccount extends Account{
	private float limit = 100;
	
	CAccount(BankSystem s, float balance, String possessed, int absolute){
		super(s, balance, possessed, absolute);
		this.suspendFlag = true;
		this.type = 3;
	}
	
	public float withdraw(float funds, long PIN){
		if(this.suspendFlag){
			System.out.println("withdrawing");
			if (system.verify(this.ANumber, PIN)){
				System.out.println("verify for withdrawing succeed!");
				if (funds != -1) {
					if(funds <= balance + limit) {
						balance -= funds;
						System.out.println("account: " + ANumber + " have " + balance + " balance left");
						return balance;
					}
					System.out.println("not enough! " + ANumber + " have " + balance + " balance left");
					return balance;
				}
				balance = 0;
				System.out.println("cleard!");
				return 0;
			}
			System.out.println("falied when checking account number with PIN!");
			return balance;
		}else {
			System.out.println("Suspended!");
		}
		return balance;
	}
}
