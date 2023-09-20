package pack3.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunnerCode {
	static List<Policy> allPolicies;
	static List<Account> allAccounts;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Policy p1 = new Policy(1234, "Bajaj", 3500, 100000);
		Policy p2 = new Policy(2341, "HDFC-Life", 4000, 250000);
		Policy p3 = new Policy(3412, "SBI-Life", 2000, 430000);
		Policy p4 = new Policy(4123, "Bajaj", 1500, 87000);
		Policy p5 = new Policy(4321, "Tata AIG", 7000, 450000);
		
		allPolicies = Arrays.asList(p1,p2,p3,p4,p5);
		
		
		Account a1 = new Account(10101, "Shubham", 7600, "Banglore", p2);
		Account a2 = new Account(10111, "Vishal", 4500, "Mumbai", null);
		Account a3 = new Account(10121, "Prajwal", 2400, "Banglore", p1);
		Account a4 = new Account(10131, "Sakshi", 15000, "Pune", p3);
		Account a5 = new Account(10141, "Durga", 9800, "Kolkata", p4);
		Account a6 = new Account(10151, "Ashwin", 8777, "Banglore", null);
		Account a7 = new Account(10161, "Chaitanya", 4567, "Mumbai", p5);
		Account a8 = new Account(10171, "Vivek", 7632, "Pune", p2);
		
		allAccounts = Arrays.asList(a1,a2,a3,a4,a5,a6,a7,a8);
		
		List<Account> list = filterAndModify();
		list.stream().forEach((a)->System.out.println(a));
		
	}
	
	public static List<Account> filterAndModify() {
		List<Account> modifiedAccount = allAccounts.stream().filter((account)->{
			return account.getPolicy() != null;
		}).map((account)->{
			Policy policy = account.getPolicy();
			int premiumAmount = policy.getPremiumAmount();
			int currentBalance = account.getBalance();
			int updatedBalance = currentBalance - premiumAmount;
			account.setBalance(updatedBalance);
			return account;
		}).collect(Collectors.toList());
		
		return modifiedAccount;
	}

}
