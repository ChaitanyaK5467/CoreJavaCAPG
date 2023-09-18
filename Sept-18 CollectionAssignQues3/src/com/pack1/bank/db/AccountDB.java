package com.pack1.bank.db;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.pack1.bank.model.Account;

public class AccountDB {
	
	List<Account> allAccounts = new ArrayList<>();
	
	public int addAccDetails(Account a) {
		boolean status = allAccounts.add(a);
		return status?a.getAccountNumber():-1;
	}
	
	public Account getAccountNum(int searchAccNum) {
		for (Account account : allAccounts) {
			if(account.getAccountNumber() == searchAccNum)
			{
				return account;
			}
		}
		return null;
	}
	
	public Set<Account> getAccountByLocation(String searchLocation){
		Set<Account> accountsByLocation = new HashSet<>();
		
		for (Account account : allAccounts) {
			if(account.getLocation().equalsIgnoreCase(searchLocation))
				accountsByLocation.add(account);
		}
		return accountsByLocation;
	}
	
	public Set<Account> getAccountByBalanceRange(int range1, int range2){
		Set<Account> accountsByBalance = new HashSet<>();
		
		for (Account account : allAccounts) {
			if(account.getBalance() >= range1 && account.getBalance() <= range2)
				accountsByBalance.add(account);
		}
		return accountsByBalance;
	}
	
	public List<Account> getAllAccounts(){
		return allAccounts;
	}
}