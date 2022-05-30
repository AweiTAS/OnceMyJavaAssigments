package Entity;

import java.util.*;
import java.io.*;
import Control.*;
/**
 * Bank system, read from file every start and write every terminate.
 * @author awei
 *
 */
public class BankSystem{
	private String clerkPassword;
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();
	private HashMap<Integer, Long> PINs = new HashMap<Integer, Long>();
	private HashMap<Integer, Account> accounts = new HashMap<Integer, Account>();
	private ArrayList<Account> unclearA = new ArrayList<Account>();
	private ArrayList<Account> noticeA = new ArrayList<Account>();
	private Timer timer;
	
	TimerTask clearTask = new TimerTask() {    
	    public void run() {
	    	if(unclearA.size() != 0) System.out.println("Clearing funds!");
	    	while(unclearA.size() != 0) {
	    		unclearA.get(unclearA.size() - 1).clearFund();
	    		unclearA.remove(unclearA.size() - 1);
	    	}
	    }
	};
	
	TimerTask noticeTask = new TimerTask() {    
	    public void run() {
	    	if(noticeA.size() != 0) System.out.println("Notice received!");
	    	while(noticeA.size() != 0) {
	    		noticeA.get(noticeA.size() - 1).noticed();
	    		noticeA.remove(noticeA.size() - 1);
	    	}
	    }
	};
	
	public BankSystem(String clerkPassword){
		this.clerkPassword = clerkPassword;
		timer = new java.util.Timer(true);
		timer.schedule(clearTask, 0, 15000);
		timer.schedule(noticeTask, 0, 20000);
		this.readFile();
	}
	
	
	/**
	 *  write customers and accounts in memory to external
	 */
	public void writeFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\storage\\customers.txt")));
			Set<String> names = customers.keySet();
			Iterator<String> n = names.iterator();
			while(n.hasNext()) {
				Customer temp = customers.get(n.next());
				writer.write(temp.getName() + "," + temp.getAddress() + "," + temp.getDoB() + "\n");
			}
			writer.close();
		}catch(Exception e) {}
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\storage\\accounts.txt")));
			Set<Integer> names = accounts.keySet();
			Iterator<Integer> n = names.iterator();
			while(n.hasNext()) {
				Account temp = accounts.get(n.next());
				writer.write(temp.getA()  + "," + temp.getType() + "," + temp.getBalance() + "," + temp.getSuspend() + "," + temp.getPossessed() + "," + PINs.get(temp.getA()) + "\n");
			}
			writer.close();
		}catch(Exception e) {}
	}
	/**
	 * read external stored customers and accounts to the memory
	 */
	public void readFile() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\storage\\customers.txt")));
			String[] temp;
			while(true) {
				temp = reader.readLine().split(",");
				if(temp == null)
					break;
				customers.put(temp[0], new Customer(temp[0], temp[1], Integer.parseInt(temp[2])));
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			String[] temp;
			BufferedReader Areader = new BufferedReader(new InputStreamReader(new FileInputStream(".\\storage\\accounts.txt")));
			while(true) {
				temp = Areader.readLine().split(",");
				if(temp == null)
					break;
				this.addAccount(temp[4], Integer.parseInt(temp[1]), Long.parseLong(temp[5]), Float.parseFloat(temp[2]), Integer.parseInt(temp[0]));
			}
			Areader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * add a customer, while choose an initial account type, 
	 * @param name of the new customer
	 * @param address address of the new customer
	 * @param DoB date of birth of the new customer,
	 * @param type initial account type 
	 * @param PIN initial account PIN
	 * @param b initial account balance
	 * @return success or not
	 */
	public boolean addCustomer(String name, String address, int DoB, int type, long PIN, float b) {
		if(!customers.containsKey(name)) {
			switch((DoB % 10000) / 100) {
				case 1:
					if((DoB % 100) < 1 || DoB % 100 > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 2:
					if((DoB % 100) < 1 || (DoB % 100) > 28) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 3:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 4:
					if((DoB % 100) < 1 || (DoB % 100) > 30) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 5:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 6:
					if((DoB % 100) < 1 || (DoB % 100) > 30) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 7:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 8:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 9:
					if((DoB % 100) < 1 || (DoB % 100) > 30) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 10:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 11:
					if((DoB % 100) < 1 || (DoB % 100) > 30) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				case 12:
					if((DoB % 100) < 1 || (DoB % 100) > 31) {
						System.out.println("invalid date of birth!");
						return false;
					}
					break;
				default:
					System.out.println("invalid date of birth!");
					return false;
			}
			customers.put(name, new Customer(name, address, DoB));
			if(!addAccount(name, type, PIN, b, Account.count)) {
				customers.remove(name);
				System.out.println("create customer " + name + " failed!");
				return false;
			}
			System.out.println("create customer " + name + " succeed!");
			return true;
		}
		System.out.println(name + " already exist!");
		return false;
	}
	
	/**
	 * add an account for a existed customer
	 * @param name customer's name
	 * @param type account type
	 * @param PIN account PIN
	 * @param b initial balance
	 * @param ab account number, if not add by readfile(), it will be Account.count
	 * @return success or not
	 */
	public boolean addAccount(String name, int type, long PIN, float b, int ab) {
		if(customers.containsKey(name) && CreditAgency.searchCredit(customers.get(name)) && b>0) {
			if (type == 3) {
				customers.get(name).CAccount(this, b, ab);
				if(accounts.containsKey(customers.get(name).current.getA())) {
					System.out.println("this customer already have this type of account");
					return false;
				}
				accounts.put(customers.get(name).current.getA(),customers.get(name).current);
				PINs.put(customers.get(name).current.getA(), PIN % 1000000);
				return true;
			}else if (type == 2) {
				customers.get(name).JAccount(this, b, ab);
				try {
					if(accounts.containsKey(customers.get(name).junior.getA())) {
						System.out.println("this customer already have this type of account");
						return false;
					}
				}catch(Exception e) {
					System.out.println(name + " create junior account failed!");
					return false;
				}
				try {
					accounts.put(customers.get(name).junior.getA(),customers.get(name).junior);
					PINs.put(customers.get(name).junior.getA(), PIN % 1000000);
					return true;
				}catch(Exception e) {
					System.out.println(name + " create junior account failed!");
					return false;
				}
			}else if (type == 1) {
				customers.get(name).SAccount(this, b, ab);
				if(accounts.containsKey(customers.get(name).saver.getA())) {
					System.out.println("this customer already have this type of account");
					return false;
				}
				accounts.put(customers.get(name).saver.getA(),customers.get(name).saver);
				PINs.put(customers.get(name).saver.getA(), PIN % 1000000);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * clear a account, whose balance need to be zero
	 * @param name customer's name
	 * @param type type of account
	 * @param PIN account PIN
	 * @return success or not
	 */
	public boolean clearAccount(String name, int type, long PIN) {
		if(type == 1){
			if(customers.get(name).saver.getBalance() == 0) {
				if(PINs.get(customers.get(name).saver.getA()) == PIN) {
					accounts.remove(customers.get(name).saver.getA());
					customers.get(name).clearAccount(type);
					System.out.println("Clear account succeed!");
					return true;
				}
				System.out.println("PIN incorrect!");
				return false;
			}
			System.out.println("Balance not zero!");
			return false;
		}else if(type == 2){
			if(customers.get(name).junior.getBalance() == 0) {
				if(PINs.get(customers.get(name).junior.getA()) == PIN) {
					accounts.remove(customers.get(name).junior.getA());
					customers.get(name).clearAccount(type);
					System.out.println("Clear account succeed!");
					return true;
				}
				System.out.println("PIN incorrect!");
				return false;
			}
			System.out.println("Balance not zero!");
			return false;
		}else if(type == 3){
			if(customers.get(name).current.getBalance() == 0) {
				if(PINs.get(customers.get(name).current.getA()) == PIN) {
					accounts.remove(customers.get(name).current.getA());
					customers.get(name).clearAccount(type);
					System.out.println("Clear account succeed!");
					return true;
				}
				System.out.println("PIN incorrect!");
				return false;
			}
			System.out.println("Balance not zero!");
			return false;
		}
		return false;
	}
	/**
	 * clear specific account's unfund money
	 * @param a account's pointer
	 */
	public void addUnFund(Account a) {
		unclearA.add(a);
	}
	public void addNotice(Account a) {
		noticeA.add(a);
	}
	/**
	 * get pointer of a customer
	 * @param name name of customer
	 * @return pointer of the customer
	 */
	public Customer getC(String name) {
		if(customers.containsKey(name)) {
			return customers.get(name);
		}
		
		return null;
	}
	/**
	 * get pointer of a account
	 * @param num number of customer
	 * @return pointer of the account
	 */
	public Account getA(int num) {
		if(accounts.containsKey(num)) {
			return accounts.get(num);
		}
		System.out.println("Not Exist!");
		return null;
	}
	/**
	 * verify clerkPassword is right
	 * @param clerkPassword bank system's clerkPassword required
	 * @return is right or not
	 */
	public boolean verify(String clerkPassword){
		return this.clerkPassword.equals(clerkPassword);
	}
	/**
	 * verify PIN of a specific account
	 * @param ANumber account number 
	 * @param PIN and PIN
	 * @return is right or not
	 */	
	public boolean verify(int ANumber, long PIN){
		return this.PINs.get(ANumber) == PIN;
	}
}