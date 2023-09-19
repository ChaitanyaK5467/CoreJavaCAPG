package com.pack1.bank.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pack1.bank.db.AccountDB;
import com.pack1.bank.model.Account;

public class AccountServer {
	private AccountDB db;
	private Map<Integer, List<Account>> accountMap;
	
	public AccountServer() {
		db = new AccountDB();
		accountMap = new HashMap<>();
	}
	
	public String addAccDetails(Account a) {
		int fromDBAccNum = db.addAccDetails(a);
		if(fromDBAccNum != -1)
			return "Account Number Generated "+fromDBAccNum;
		else
			return "Error !! Contact Bank representative";
	}
	
	public List<Account> getAllAccounts(){
		return db.getAllAccounts();
	}
	
	public Account getAccountNum(int searchAccNum) {
		return db.getAccountNum(searchAccNum);
	}
	
	public Set<Account> getAccountByLocation(String searchLocation){
		return db.getAccountByLocation(searchLocation);
	}
	
	public Set<Account> getAccountByBalanceRange(int range1, int range2){
		return db.getAccountByBalanceRange(range1, range2);
	}
	
	
	// Inserting account in map
	public Map<Integer, List<Account>> addAccDetailsMap(Account a) {
        int fromDBAccNum = db.addAccDetails(a);
        
        Map<Integer, List<Account>> accountGroups = new HashMap<>();
        
        // db.getAllAccounts().contains(fromDBAccNum)
        
        if(fromDBAccNum != -1) {
        	List<Account> allAccounts = db.getAllAccounts();
        	
        	for (Account account : allAccounts) {
				int accNum = account.getAccountNumber();
				List<Account> savedList = accountGroups.get(accNum);
				
				if (savedList == null) {
	                List<Account> list = new ArrayList<>();
	                list.add(account);
	                accountGroups.put(accNum, list);
	            }
	            else if(savedList != null) {
	            	List<Account> list = accountGroups.get(accNum);
	                list.add(account);
	            }
			}	
        }
		return accountGroups;
    }
	
	public Map<String, List<Account>> groupAccountsByLocation() {
        List<Account> allAccounts = db.getAllAccounts();
        Map<String, List<Account>> accountGroups = new HashMap<>();

        for (Account account : allAccounts) {
            String location = account.getLocation();
            List<Account> savedList = accountGroups.get(location);
            
            if (savedList == null) {
                List<Account> list = new ArrayList<>();
                list.add(account);
                accountGroups.put(location, list);
            }
            else if(savedList != null) {
            	List<Account> list = accountGroups.get(location);
                list.add(account);
            }
        }
        return accountGroups;
    }
	
	public List<Account> getAccountByAccountNumber(int accountNumber) {
        return accountMap.get(accountNumber);
    }
	
	public Map<Integer, List<Account>> getAccountMap() {
        return accountMap;
    }	
}