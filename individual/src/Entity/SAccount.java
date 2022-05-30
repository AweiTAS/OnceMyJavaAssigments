package Entity;
/**
 * Saver account
 * @author awei
 *
 */
public class SAccount extends Account{
	private float drawable;
	private float notice;
	
	SAccount(BankSystem s, float balance, String possessed, int absolute){
		super(s, balance, possessed, absolute);
		this.suspendFlag = true;
		this.type = 1;
	}
	
	public float notice(float funds, long PIN){
		if(this.suspendFlag) {
			if (system.verify(this.ANumber, PIN)){
				System.out.println("verify for withdrawing succeed!");
				if (funds != -1) {
					if(funds <= balance) {
						balance -= funds;
						notice += funds;
						system.addNotice(this);
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
	
	public void noticed(){
		drawable += notice;
		notice = 0;
	}
	
	public float withdraw(float funds, long PIN){
		if(this.suspendFlag) {
			if (system.verify(this.ANumber, PIN)){
				System.out.println("verify for withdrawing succeed!");
				if (funds != -1) {
					if(funds <= drawable) {
						drawable -= funds;
						System.out.println("account: " + ANumber + " have " + drawable + " drawable dollar left");
						return drawable;
					}
					System.out.println("not enough! " + ANumber + " have " + drawable + " drawable dollar left");
					return drawable;
				}
				drawable = 0;
				System.out.println("cleard!");
				return 0;
			}
			System.out.println("falied when checking account number with PIN!");
			return drawable;
		}else {
			System.out.println("Suspended!");
		}
		return drawable;
	}
}
