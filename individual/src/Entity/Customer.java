package Entity;

import java.util.*;
/**
 * Customer entity
 * @author awei
 *
 */
public class Customer{
	private String name;
	private String address;
	private int DoB;
	public SAccount saver;
	public JAccount junior;
	public CAccount current;
	
	Customer(String name, String address, int DoB){
		this.name = name;
		this.address = address;
		this.DoB = DoB;
	}
	/**
	 * print current customer info
	 */
	public void info() {
		System.out.println("name: " + name);
		System.out.println("Address: " + address);
		System.out.println("Birthday: " + DoB);
	}
	/**
	 * @return current customer age
	 */
	public int age(){
		if( Calendar.getInstance().get(Calendar.MONTH)+1 - ((DoB % 10000) / 100) <= 0){
			if( Calendar.getInstance().get(Calendar.DATE) - (DoB % 100) < 0) {
				return (int)(Calendar.getInstance().get(Calendar.YEAR) - (DoB / 10000) - 1);
			}
		}
		return (int)(Calendar.getInstance().get(Calendar.YEAR) - (DoB / 10000));
	}
	public static int age(long DoB){
		if( Calendar.getInstance().get(Calendar.MONTH)+1 - ((DoB % 10000) / 100) <= 0){
			if( Calendar.getInstance().get(Calendar.DATE) - (DoB % 100) < 0) {
				return (int)(Calendar.getInstance().get(Calendar.YEAR) - (DoB / 10000) - 1);
			}
		}
		return (int)(Calendar.getInstance().get(Calendar.YEAR) - (DoB / 10000));
	}
	/**
	 * create a current account for this customer
	 * @param s target bank system
	 * @param b initial balance
	 * @param ab account number, if not add by readfile(), it will be Account.count
	 */
	public void CAccount(BankSystem s, float b, int ab){
		if(this.current == null) {
			this.current = new CAccount(s, b, this.name, ab);
			System.out.println(name + " create current account succeed!");
		}
	}
	/**
	 * create a junior account for this customer
	 * @param s target bank system
	 * @param b initial balance
	 * @param ab account number, if not add by readfile(), it will be Account.count
	 */
	public void JAccount(BankSystem s, float b, int ab){
		if(this.junior == null) {
			if(age()<16) {
				this.junior = new JAccount(s, b, this.name, ab);
				System.out.println(name + " create junior account succeed!");
				return;
			}
			System.out.println("you are too old!");
		}
	}
	/**
	 * create a saver account for this customer
	 * @param s target bank system
	 * @param b initial balance
	 * @param ab account number, if not add by readfile(), it will be Account.count
	 */
	public void SAccount(BankSystem s, float b, int ab){
		if(this.saver == null) {
			this.saver = new SAccount(s, b, this.name, ab);
			System.out.println(name + " create saver account succeed!");
		}
	}
	/**
	 * clear an account in the possess of this customer
	 * @param type type of account(saver, junior or current)
	 */
	public void clearAccount(int type) {
		if(type == 1) {
			this.saver = null;
		}else if(type == 2) {
			this.junior = null;
		}else if(type == 3) {
			this.current = null;
		}
	}
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public long getDoB() {
		return DoB;
	}
}