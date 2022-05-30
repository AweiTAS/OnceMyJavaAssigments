package Boundary;

import java.util.*;
import Entity.*;
/**
 * Runnable user interface
 * @author awei
 *
 */
public class cmdInterface {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String read;
		String head = "BankSystem";
		BankSystem s = new BankSystem("buptawei");
		Account a = null;
		Customer c = null;
		/*
		s.addCustomer("awei", "bupt", 19980122, 2, 123456, 70);
		s.addCustomer("awei", "bupt", 19980122, 1, 123456, 70);
		s.addCustomer("tas", "qmul", 20050122, 2, 123456, 70);
		s.addCustomer("tas", "qmul", 20050122, 3, 123456, 70);
		s.addAccount("tas", 3, 123456, 70);
		Account a = s.getC("awei").saver;
		Account b = s.getC("tas").junior;
		a.deposit(40, "nmslwsnggwmlgdsnmde", false);
		a.deposit(30, "nmslwsnggwmlgdsnmdez", false);
		b.deposit(100, "nmslwsnggwmlgdsnmdez", true);
		a.withdraw(60, 12345);
		a.withdraw(60, 123456);
		a.withdraw(60, 123456);
		s.clearAccount("awei", 1, 123456);
		a.withdraw(40, 123456);
		s.clearAccount("awei", 1, 123456);
		try{Thread.sleep(20000);}catch(Exception e){}*/

		while(true) {
			System.out.print(head + ">");
			read = scan.nextLine();
			if(read.split(" ")[0].equals("quit") || read.split(" ")[0].equals("exit")) {
				scan.close();
				s.writeFile();
				return;
			}else if(read.split(" ")[0].equals("addCustomer")) {
				try{
					s.addCustomer(read.split(" ")[1], read.split(" ")[2], Integer.parseInt(read.split(" ")[3]), Integer.parseInt(read.split(" ")[4]), Integer.parseInt(read.split(" ")[5]), Float.parseFloat(read.split(" ")[6]));
				}catch(Exception e){
					System.out.println("input invalid!");
					System.out.println("try format: addCustomer name address dataOfBirth OriginAccountType PIN OriginalBalance");
				}
			}else if(read.split(" ")[0].equals("addAccount")) {
				try{
					if(!s.addAccount(read.split(" ")[1], Integer.parseInt(read.split(" ")[2]), Integer.parseInt(read.split(" ")[3]), Float.parseFloat(read.split(" ")[4]), Account.count))
						System.out.println("Add account failed!");
				}catch(Exception e){
					System.out.println("input invalid!");
					System.out.println("try format: addAccount name AccountType PIN OriginalBalance");
				}
			}else if(read.split(" ")[0].equals("clearAccount")) {
				try{
					if(!s.clearAccount(read.split(" ")[1], Integer.parseInt(read.split(" ")[2]), Integer.parseInt(read.split(" ")[3])))
						System.out.println("Clear account failed!");
				}catch(Exception e){
					System.out.println("input invalid or no such account");
					System.out.println("try format: clearAccount name AccountType PIN");
				}
			}else if(read.split(" ")[0].equals("getC")) {
				try{
					c = s.getC(read.split(" ")[1]);
				}catch(Exception e){
					System.out.println("input invalid!");
				}
				if(null == c) {
					System.out.println("No such customer");
					continue;
				}
				while(c != null) {
					head = "BankSystem/" + c.getName();
					System.out.print(head + ">");
					read = scan.nextLine();
					if(read.split(" ")[0].equals("quit") || read.split(" ")[0].equals("exit")) {
						break;
					}else if(read.split(" ")[0].equals("info")) {
						c.info();
					}else if(read.split(" ")[0].equals("saver")) {
						a = c.saver;
						if(a != null) {
							head = head + "/"  + "saver("+ a.getA() + ")";
						}else {
							System.out.println("account not exist");
						}
					}else if(read.split(" ")[0].equals("junior")) {
						a = c.junior;
						if(a != null) {
							head = head + "/" + "junior(" + a.getA() + ")";
						}else {
							System.out.println("account not exist");
						}
					}else if(read.split(" ")[0].equals("current")) {
						a = c.current;
						if(a != null) {
							head = head + "/" + "current(" + a.getA() + ")";
						}else {
							System.out.println("account not exist");
						}
					}else {
						System.out.println("unknown command: info, saver, junior, current, quit");
					}
					while(a != null) {
						System.out.print(head + ">");
						read = scan.nextLine();
						if(read.split(" ")[0].equals("quit") || read.split(" ")[0].equals("exit")) {
							break;
						}else if(read.split(" ")[0].equals("info")) {
							a.info();
						}else if(read.split(" ")[0].equals("deposit")) {
							try {
								a.deposit(Float.parseFloat(read.split(" ")[1]), read.split(" ")[2], Boolean.parseBoolean(read.split(" ")[3]));
							}catch(Exception e) {
								System.out.println("try format: deposit numberOfFunds ClerkPassword IsCheck");
							}
						}else if(read.split(" ")[0].equals("withdraw")) {
							try {
								a.withdraw(Float.parseFloat(read.split(" ")[1]), Long.parseLong(read.split(" ")[2]));
							}catch(Exception e) {
								System.out.println("try format: withdraw numberOfFunds PIN");
							}
						}else if(read.split(" ")[0].equals("suspend")) {
							try {
								a.suspend(read.split(" ")[1]);
							}catch(Exception e) {
								System.out.println("try format: suspend ClerkPassword");
							}
						}else if(read.split(" ")[0].equals("restate")) {
							try {
								a.restate(read.split(" ")[1]);
							}catch(Exception e) {
								System.out.println("try format: restate ClerkPassword");
							}
						}else if(read.split(" ")[0].equals("notice")&&(a.getClass()==SAccount.class)) {
							try {
								a.notice(Float.parseFloat(read.split(" ")[1]), Long.parseLong(read.split(" ")[2]));
							}catch(Exception e) {
								System.out.println("try format: notice numberOfFunds PIN");
							}
						}else {
							System.out.println("unknown command: info, deposit, withdraw, suspend, restate, quit, notice( for saver account)");
						}
					}
					a = null;
				}
				head = "BankSystem";
			}else if(read.split(" ")[0].equals("getA")) {
				try{
					a = s.getA(Integer.parseInt(read.split(" ")[1]));
				}catch(Exception e){
					System.out.println("input invalid!");
				}
				if(null == a) {
					System.out.println("No such account");
					continue;
				}
				head = head + "/" + a.getA() + "(possessed: " + a.possessed + ")";
				while(a != null) {
					System.out.print(head + ">");
					read = scan.nextLine();
					if(read.split(" ")[0].equals("quit") || read.split(" ")[0].equals("exit")) {
						break;
					}else if(read.split(" ")[0].equals("info")) {
						a.info();
					}else if(read.split(" ")[0].equals("deposit")) {
						try {
							a.deposit(Float.parseFloat(read.split(" ")[1]), read.split(" ")[2], Boolean.parseBoolean(read.split(" ")[3]));
						}catch(Exception e) {
							System.out.println("try format: deposit numberOfFunds ClerkPassword IsCheck");
						}
					}else if(read.split(" ")[0].equals("withdraw")) {
						try {
							a.withdraw(Float.parseFloat(read.split(" ")[1]), Long.parseLong(read.split(" ")[2]));
						}catch(Exception e) {
							System.out.println("try format: withdraw numberOfFunds PIN");
						}
					}else if(read.split(" ")[0].equals("suspend")) {
						try {
							a.suspend(read.split(" ")[1]);
						}catch(Exception e) {
							System.out.println("try format: suspend ClerkPassword");
						}
					}else if(read.split(" ")[0].equals("restate")) {
						try {
							a.restate(read.split(" ")[1]);
						}catch(Exception e) {
							System.out.println("try format: restate ClerkPassword");
						}
					}else if(read.split(" ")[0].equals("notice")&&(a.getClass()==SAccount.class)) {
						try {
							a.notice(Float.parseFloat(read.split(" ")[1]), Long.parseLong(read.split(" ")[2]));
						}catch(Exception e) {
							System.out.println("try format: notice numberOfFunds PIN");
						}
					}else {
						System.out.println("unknown command: info, deposit, withdraw, suspend, restate, quit, notice( for saver account)");
					}
				}
				a = null;
				head = "BankSystem";
			}else {
				System.out.println("unknown command: addCustomer, addAccount, clearAccount, getC, getA");
			}
		}
	}
}