package Entity;
/**
 * Junior account
 * @author awei
 *
 */
public class JAccount extends Account{
	JAccount(BankSystem s, float balance, String possessed, int absolute){
		super(s, balance, possessed, absolute);
		this.type = 2;
	}
}
