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
	
	public AccountServer() {
		db = new AccountDB();
	}
	
	public String addAccDetails(Account a) {
		int fromDBAccNum = db.addAccDetails(a);
		if(fromDBAccNum != -1)
			return "Account Number Generated "+fromDBAccNum;
		else
			return "Error !! Contact Bank representative";
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
	
	public List<Account> getAllAccounts(){
		return db.getAllAccounts();
	}
	
	public void credit(Account a, int creditAmount) {
		if(creditAmount <= 0)
			throw new IllegalArgumentException("Amount should be greater than 0 for deposit");
		a.setBalance(a.getBalance()+creditAmount);
	}
	
	public void debit(Account a, int debitAmount) throws LowBalanceException{
		if(debitAmount <= 0)
			throw new IllegalArgumentException("Amount should be greater than 0 for withdrawl");
		if(a.getBalance() - debitAmount < 5000)
			throw new LowBalanceException("Insufficient Balance for withdrawl");
		a.setBalance(a.getBalance()-debitAmount);
	}
	
	public void fundTransfer(Account sourceAccount, Account receiverAccount, int transferAmount) throws LowBalanceException{
		if(transferAmount <= 0)
			throw new IllegalArgumentException("Amount should be greater than 0 for Fund Transfer");
		if(sourceAccount.getBalance() - transferAmount < 5000)
			throw new LowBalanceException("Insufficient Balance for Fund Transfer");
		sourceAccount.setBalance(sourceAccount.getBalance() - transferAmount);
        receiverAccount.setBalance(receiverAccount.getBalance() + transferAmount);
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
}
