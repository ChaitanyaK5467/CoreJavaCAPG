package pack1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class AccountMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeSet<Account> allAccounts = new TreeSet<Account>();
		
		Account a1 = new Account(10101, "Shubham", 7000, "Banglore");
		Account a2 = new Account(10112, "Pratik", 12000, "Pune");
		Account a3 = new Account(10123, "Mandar", 5400, "Mumbai");
		Account a4 = new Account(11234, "Bhavesh", 9000, "Nagppur");
		Account a5 = new Account(10100, "Mukta", 8993, "Delhi");
		
		allAccounts.add(a1);
		allAccounts.add(a2);
		allAccounts.add(a3);
		allAccounts.add(a4);
		allAccounts.add(a5);
		
		System.out.println("Sort By Location");
		System.out.println("Default (Comparable interface)");
		for (Account account : allAccounts) {
			System.out.println(account);
		}
		
		System.out.println("\n");
		System.out.println("Sort Account Based on Balance Low To High");
		getAccountBasedOnBalanceLowToHigh(allAccounts);
		
		System.out.println("\n");
		System.out.println("Sort Account Based on Balance High To Low");
		getAccountBasedOnBalanceHighToLow(allAccounts);
	}
	
	public static void getAccountBasedOnBalanceHighToLow(TreeSet<Account> allAccounts) {
		List<Account> tempList = new ArrayList<>();
		tempList.addAll(allAccounts);
		
		SortAccountBasedOnBalanceHighToLow sort = new SortAccountBasedOnBalanceHighToLow();
		
		Collections.sort(tempList, sort);
		
		for (Account account : tempList) {
			System.out.println(account);
		}
	}
	
	public static void getAccountBasedOnBalanceLowToHigh(TreeSet<Account> allAccounts) {
		List<Account> tempList = new ArrayList<>();
		tempList.addAll(allAccounts);
		
		SortAccountsBasedonBalanceLowToHigh sort = new SortAccountsBasedonBalanceLowToHigh();
		
		Collections.sort(tempList, sort);
		
		for (Account account : tempList) {
			System.out.println(account);
		}
	}

}
