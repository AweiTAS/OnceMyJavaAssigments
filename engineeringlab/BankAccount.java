public class BankAccount{
	private String accNo;
	private String accName;
	private double balance;
	public BankAccount(String accNo, String accName){
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}
	public String getAccNo(){
		return accNo;
	}
	public String getAccName(){
		return accName;
	}
	public double getBalance(){
		return balance;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public void deposit(double amount) {
		balance = balance + amount;
	}
	public void deposit(double amount, boolean cheque) {
		if (cheque == false) { balance = balance + amount; }
		else {
		// code to be added
		}
	}
	public void withdraw(double amount) {
		if (balance >= amount) {
			balance = balance - amount;
		}
	}
	public String toString() {
		return "Account number: " + accNo
		+ "\n" +"Account name: " + accName
		+ "\n" +"Balance: " + balance;
	}
}