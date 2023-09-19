package com.pack1.bank.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.pack1.bank.ctr.AccountServer;
import com.pack1.bank.ctr.PolicyServer;
import com.pack1.bank.model.Account;
import com.pack1.bank.model.Policy;

public class RunnerApp {
	
	private PolicyServer ps;
	private AccountServer server;
	
	public RunnerApp() {
		ps = new PolicyServer();
		server = new AccountServer();
	}

	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	
	Map<Integer, Account> map = new HashMap<>(); 
	
	public static void main(String[] args) {
		RunnerApp app = new RunnerApp();
		
		while(true) {
			System.out.println("----* Select Option *----");
			System.out.println("1. Add Account Details and Policy Details");
			System.out.println("2. Display all Accounts and policies attached");
			System.out.println("3. Display all Accounts based on Account Number");
			System.out.println("4. Display all Accounts based on Policy Number");
			System.out.println("5. Display all Accounts based on Policy Name");
			System.out.println("6. Display all Accounts not having Policy");
			System.out.println("7. Accounts where Policy Sum Assured is Above Certain Amount");
			System.out.println("8. Accounts where Policy Premium is Below Certain Amount");
			System.out.println("9. All Accounts for the location also who has policies");
			System.out.println("10. Display all Accounts based on Policy Number using Map<String,Account");
			
			System.out.println("0. Exit\n");

			System.out.println("Enter Option : ");
			
			switch (new Scanner(System.in).nextInt()) {
			case 1:
				app.addAccountDetails();
				break;
			case 2:
				app.getAllAccounts();
				break;
			case 3:
				app.getAccountByAccNum();
				break;
			case 4:
				app.getAccountByPolicyNumber();
				break;
			case 5:
				app.getAccountByPolicyName();
				break;
			case 6:
				app.getAccountNotHavingPolicy();
				break;
			case 7:
				app.getAccountsByPolicySumAssuredAmount();
				break;
			case 8:
				app.getAccountsByPolicyPremiumAmount();
				break;
			case 9:
				app.getAccountsByPolicyAndLocation();
				break;
			case 10:
				app.getAccountsByPolicyPremiumAmount();
				break;
			case 0:
				System.exit(0);
			}
		}	
	}

	public void addAccountDetails() {
		System.out.println("Enter Account Holder Name : ");
		String name = sc1.nextLine();

		System.out.println("Enter Location : ");
		String location = sc1.nextLine();

		System.out.println("Enter Initial Balance : ");
		int initialBalance = Integer.parseInt(sc1.nextLine());

		int accNum = new Random().nextInt(50000);
		
		System.out.println("Do you want to attach a policy? (yes/no): ");
	    String attachPolicyOption = sc1.nextLine();

	    Policy policy = null;
	    if ("yes".equalsIgnoreCase(attachPolicyOption)) {
	        policy = addPolicyDetails();
	    }
		
		Account a = new Account(accNum, name, initialBalance, location, policy);

		String notification = server.addAccountDetails(a);
		System.out.println("\nNotification : " + notification+"\n");
		
	}
	
	public Policy addPolicyDetails() {
		System.out.println("\n---Policy details---\n");
		System.out.println("Enter Policy Number : ");
		int policyNumber = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter Policy Name : ");
		String policyName = sc1.nextLine();

		System.out.println("Enter Premium Amount : ");
		int premiumAmount = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter Sum Assured : ");
		int sumAssured = Integer.parseInt(sc.nextLine());
		
		return new Policy(policyNumber, policyName, premiumAmount, sumAssured);
	}
	
	//Map
	public void getAccountByAccNum() {
		System.out.println("Enter Account Number to be Searched : ");
		int searchAccNum = Integer.parseInt(sc1.nextLine());

		Account a = server.getAccountNum(searchAccNum);

		if (a != null) {
			for (Map.Entry<Integer, Account> entry : map.entrySet()) {
				int key = entry.getKey();
				Account account = entry.getValue();

				if (account.getAccountNumber() == searchAccNum) {
					displayAccountDetails(account);
					break;
				}
			}
		} else {
			System.out.println("No account found for the specified account number.");
		}
	}
	
	//List
	public void getAccountByPolicyNumber() {
		System.out.println("Enter Policy Number to be Searched : ");
		int policyNumber = Integer.parseInt(sc1.nextLine());
		
		List<Account> list = server.getAccountByPolicyNumber(policyNumber);
		
		System.out.println("Accounts having Policy Number " + policyNumber + " : ");
        for (Account account : list) {
            displayAccountDetails(account);
        }
	}
	
	//List
	public void getAccountByPolicyName() {
		System.out.println("Enter Policy Name to be Searched : ");
		String policyName = sc.nextLine();
		
		List<Account> list = server.getAccountByPolicyName(policyName);
		
		System.out.println("Accounts having Policy Name " + policyName + " : ");
        for (Account account : list) {
            displayAccountDetails(account);
        }
	}
	
	//List
	public void getAccountNotHavingPolicy() {
		List<Account> accountsWithoutPolicy = server.getAccountsWithoutPolicy();
		System.out.println("Accounts without policy : ");
		
	    for (Account account : accountsWithoutPolicy) {
	    	displayAccountDetails(account);
	    }
	}
	
	//List
	public void getAccountsByPolicySumAssuredAmount() {
		System.out.println("Enter the Sum Assured Amount");
		int minSumAssured = Integer.parseInt(sc.nextLine());
		
		List<Account> list = server.getAccountsByPolicySumAssuredAmount(minSumAssured);
		
		System.out.println("Accounts with policy sum assured above " + minSumAssured + " : ");
        for (Account account : list) {
            displayAccountDetails(account);
        }
	}
	
	//List
	public void getAccountsByPolicyPremiumAmount() {
		System.out.println("Enter the Sum Assured Amount");
		int premiumAmt = Integer.parseInt(sc.nextLine());
		
		List<Account> list = server.getAccountsByPolicyPremiumAmount(premiumAmt);
		
		System.out.println("Accounts with Policy Premium Below " + premiumAmt + " : ");
        for (Account account : list) {
            displayAccountDetails(account);
        }
	}
	
	//List
	public void getAccountsByPolicyAndLocation() {
        System.out.println("Enter the policy location to filter by (e.g., Bangalore): ");
        String location = sc1.nextLine();

        List<Account> tempList = server.getAccountsByPolicyLocation(location);

        if (tempList.size() == 0) {
            System.out.println("No accounts found with policies in " + location);
        } else {
            System.out.println("Accounts with policies in " + location + ":");
            for (Account account : tempList) {
                displayAccountDetails(account);
            }
        }
    }
	
	//Map
	public void getAccountsByPolicyNameMap() {
		System.out.println("");
	}
	
	public void displayAccountDetails(Account a) {
		String accountInfo = " [ Account Holder Name : " + a.getAccountHolderName() +", Account Number : " + a.getAccountNumber() +", Location : " + a.getLocation() +", Balance : " + a.getBalance()+" ] ";
	    System.out.println(accountInfo);
	    
	    if (a.getPolicy() != null) {
	        Policy policy = a.getPolicy();
	        String policyInfo = " [ Policy Number : " + policy.getPolicyNumber()+
	                            ", Policy Name : " + policy.getPolicyName()+
	                            ", Premium Amount : " + policy.getPremiumAmount()+
	                            ", Sum Assured : " + policy.getSumAssuredAmount()+" ] ";
	        
	        System.out.println(policyInfo);
	    } else {
	        System.out.println("No Policy Attached");
	    }
	}

	public void getAllAccounts() {
		List<Account> allSavedAccount = server.getAllAccounts();

		for (Account account : allSavedAccount) {
			displayAccountDetails(account);
		}
	}
	
}
