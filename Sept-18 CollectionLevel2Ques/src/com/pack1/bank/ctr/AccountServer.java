package com.pack1.bank.ctr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pack1.bank.model.Account;
import com.pack1.bank.model.Policy;

public class AccountServer {
	
	private PolicyServer ps;
	
	public AccountServer() {
		ps = new PolicyServer();
	}
	
	List<Account> allAccounts = new ArrayList<>();
	
	public int addAccountDetailsDB(Account a) {
		boolean status = allAccounts.add(a);
		return status?a.getAccountNumber():-1;
	}
	
	public String addAccountDetails(Account a) {
		int accountNumber = addAccountDetailsDB(a);
		if(accountNumber != -1)
			return "Account Number Generated "+accountNumber;
		else
			return "Error !!";
	}
	
	public Account getAccountNum(int searchAccNum) {
		for (Account account : allAccounts) {
			if(account.getAccountNumber() == searchAccNum){
				return account;
			}
		}
		return null;
	}
	
	public List<Account> getAccountsWithoutPolicy() {
	    List<Account> accountsWithoutPolicy = new ArrayList<>();

	    for (Account account : allAccounts) {
	        if (account.getPolicy() == null) {
	            accountsWithoutPolicy.add(account);
	        }
	    }
	    return accountsWithoutPolicy;
	}
	
	public List<Account> getAccountsByPolicySumAssuredAmount(int amount){
		List<Account> tempList = new ArrayList<>();
		
		for (Account account : tempList) {
			if (account.getPolicySumAssuredAmount() > amount) {
	            tempList.add(account);
	        }
		}
		return tempList;
	}
	
	public List<Account> getAccountsByPolicyPremiumAmount(int amount){
		List<Account> tempList = new ArrayList<>();
		
		for (Account account : tempList) {
			if (account.getAccountsByPolicyPremiumAmount() < amount) {
	            tempList.add(account);
	        }
		}
		return tempList;
	}
	
	public List<Account> getAccountsByPolicyLocation(String location) {
	    List<Account> tempList = new ArrayList<>();

	    for (Account account : allAccounts) {
	        if (account.getLocation() != null && account.getLocation().equalsIgnoreCase(location) && account.getPolicy() != null) {
	        	tempList.add(account);
	        }
	    }
	    return tempList;
	}
	
	public List<Account> getAccountByPolicyNumber(int policyNumber) {
		ps.getAccountByNumber(policyNumber);
		return allAccounts;
	}
	
	public List<Account> getAccountByPolicyName(String name) {
		ps.getAccountByName(name);
		return allAccounts;
	}
	
	public List<Account> getAllAccounts(){
		return allAccounts;
	}
}
