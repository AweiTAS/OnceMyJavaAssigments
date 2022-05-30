import java.util.*;

public class Bank{
	
	private ArrayList<BankAccount> list = new ArrayList<BankAccount>();
	private BankAccount currentAccount = null;
	
	public BankAccount openAccount(String accNo, String accName){
		BankAccount a = new BankAccount(accNo, accName);
		list.add(a);
		return a;
	}
	
	public boolean closeAccount(BankAccount account){
		return list.remove(account);
	}
	
	public boolean setCurrentAccount(BankAccount account){
		int x = list.indexOf(account);
		if (x == -1){
			return false;
		}else{
			currentAccount = list.get(x);
			return true;
		}
	}
	
	public String getAccNo(){
		if (currentAccount != null)
		return currentAccount.getAccNo();
		return null;
	}
	public String getAccName(){
		if (currentAccount != null)
		return currentAccount.getAccName();
		return null;
	}
	public double getBalance(){
		if (currentAccount != null)
		return currentAccount.getBalance();
		return -1.0;
	}
	public void setAccName(String accName) {
		if (currentAccount != null)
		currentAccount.setAccName(accName);
	}
	public void deposit(double amount) {
		if (currentAccount != null)
		currentAccount.deposit(amount);
	}
	public void deposit(double amount, boolean cheque) {
		if (currentAccount != null)
		currentAccount.deposit(amount, cheque);
	}
	public void withdraw(double amount) {
		if (currentAccount != null)
		currentAccount.withdraw(amount);
	}
	public String toString() {
		if (currentAccount != null)
		return currentAccount.toString();
		return null;
	}
}