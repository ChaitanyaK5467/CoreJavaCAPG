package pack2.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class AccountMain {
	static List<Account> allAccounts;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Account a1 = new Account(10101, "Shubham", 7000, "Banglore");
		Account a2 = new Account(10112, "Pratik", 12000, "Pune");
		Account a3 = new Account(10123, "Mandar", 5400, "Mumbai");
		Account a4 = new Account(11234, "Bhavesh", 9000, "Nagppur");
		Account a5 = new Account(10100, "Mukta", 8993, "Delhi");
		Account a6 = new Account(10140, "Chaitanya", 88000, "Banglore");
		
		allAccounts = Arrays.asList(a1,a2,a3,a4,a5,a6);
		
		callForEach();
		System.out.println("\n");
		
		List<Account> list = filterWithCollectOperation();
		list.stream().forEach(a->System.out.println(a));
		System.out.println("\n");
		
		List<Account> list2 = increment();
		list2.stream().forEach(a->System.out.println(a));
		System.out.println("\n");
		
		Account a = getAccountWithMinimumBalanceFromBanglore();
		System.out.println(a);
		System.out.println("\n");
		
		Account ac = getAccountWithMaximumBalanceFromBanglore();
		System.out.println(ac);
		
	}
	public static void callForEach(){
		allAccounts.forEach((account) -> {
		    System.out.println(account);
		});	
	}
	
	public static List<Account> filterWithCollectOperation() {
		List<Account> tempList = allAccounts.stream().filter((account)->{
			if(account.getBalance() < 10000) 
				return true;
			else return false;
		}).collect(Collectors.toList());
		return tempList;
	}
	
	public static List<Account> increment(){
		List<Account> tempList = allAccounts.stream().filter((account)->{
	//		if(account.getBalance() > 0 ) return true;
	//		else return false;
			return true;
		}).map((account)->{
			int currentBalance = account.getBalance();
			int updatedBalance = (int)(currentBalance + (currentBalance*0.02));
			account.setBalance(updatedBalance);
			return account;
		}).collect(Collectors.toList());
	return tempList;
	}

	public static Account getAccountWithMinimumBalanceFromBanglore() {
		List<Account> bangloreAccounts = allAccounts.stream().filter((account)-> {
	    	return "Banglore".equals(account.getLocation());
	    }).collect(Collectors.toList());
		
		if(!bangloreAccounts.isEmpty()) {
			Comparator<Account> com = (o1, o2) -> {
				return o1.getBalance() - o2.getBalance();
			};
			Account acc = bangloreAccounts.stream().min(com).get();
			return acc;
		}
		else return null;
		
	}
	
	public static Account getAccountWithMaximumBalanceFromBanglore() {
		List<Account> bangloreAccounts = allAccounts.stream().filter((account)-> {
	    	return "Banglore".equals(account.getLocation());
	    }).collect(Collectors.toList());
		
		if(bangloreAccounts != null) {
			Comparator<Account> com = (o1, o2) -> {
				return o1.getBalance() - o2.getBalance();
			};
			Account acc = bangloreAccounts.stream().max(com).get();
			return acc;
		}
		else return null;
		
	}
}
