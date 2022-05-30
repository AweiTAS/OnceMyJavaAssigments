package Entity;
/**
 * parent account
 * @author awei
 *
 */
public class Account{

	public static int count = 0;
	protected BankSystem system;
	protected int ANumber;
	protected float balance;
	protected float unFund;
	protected boolean suspendFlag;
	protected int type;
	public String possessed;
	
	Account(BankSystem s, float balance, String possessed, int absolute){
		this.ANumber = Account.count++;
		if(Account.count < absolute + 1)
			Account.count = absolute + 1;
		this.balance = balance;
		this.system = s;
		this.suspendFlag = true;
		this.possessed = possessed;
	}
	/**
	 * get account number
	 * @return account number
	 */
	public int getA() {
		return ANumber;
	}
	
	public int getType() {
		return type;
	}
	
	public boolean getSuspend() {
		return suspendFlag;
	}
	
	public String getPossessed() {
		return possessed;
	}
	/**
	 * get account number
	 * @return balance
	 */
	public float getBalance() {
		return balance + unFund;
	}
	/**
	 * clear balance saved by check
	 */
	public void clearFund() {
		balance += unFund;
		System.out.println("account: " + ANumber + " have " + balance + " balance left");
		unFund = 0;
	}
	/**
	 * deposit specific number of money
	 * @param funds deposit money number
	 * @param clerkPassword bank system's clerkPassword required
	 * @param check is check used or not
	 * @return new balance
	 */
	public float deposit(float funds, String clerkPassword, boolean check){
		if(this.suspendFlag) {
			if (system.verify(clerkPassword)){
				if(funds >= 300) {
					System.out.println("deposit money more than 300");
					return balance;
				}
				System.out.println("verify for depositing succeed!");
				if(!check) {
					balance += funds;
				}else {
					system.addUnFund(this);
					unFund += funds;
				}
				System.out.println("account: " + ANumber + " have " + balance + " balance left");
				return balance;	
			}
			System.out.println("falied when checking clerkPassword!");
			return balance;
		}else {
			System.out.println("Suspended!");
		}
		return balance;
	}
	/**
	 * withdraw specific number of money
	 * @param funds withdraw money number
	 * @param PIN PIN number of this account
	 * @return new balance
	 */
	public float withdraw(float funds, long PIN){
		if(this.suspendFlag) {
			if (system.verify(this.ANumber, PIN)){
				System.out.println("verify for withdrawing succeed!");
				if (funds != -1) {
					if(funds <= balance) {
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
	/**
	 * suspend this account
	 * @param clerkPassword bank system's clerkPassword required
	 */
	public void suspend(String clerkPassword) {
		if (system.verify(clerkPassword)){
			this.suspendFlag = false;
		}
	}
	/**
	 * restate this account
	 * @param clerkPassword bank system's clerkPassword required
	 */
	public void restate(String clerkPassword) {
		if (system.verify(clerkPassword)){
			this.suspendFlag = true;
		}
	}
	/**
	 * print current account info
	 */
	public void info() {
		System.out.println("Account number: " + ANumber);
		System.out.println("Balance: " + balance + "(+" + unFund + ")");
		System.out.println("Unsuspend: " + suspendFlag);
		System.out.println("Owner: " + possessed);
	}
	public float notice(float parseFloat, long parseLong) {
		System.out.println("not avalible in this type");
		return -1;
	}
	public void noticed() {
		System.out.println("not avalible in this type");
	}
}