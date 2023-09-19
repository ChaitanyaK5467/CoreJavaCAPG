package com.pack1.bank.model;

public class Account {
	private int accountNumber;
	private String accountHolderName;
	private int balance;
	private String location;
	
	Policy policy;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountNumber, String accountHolderName, int balance, String location, Policy policy) {
		super();
		this.accountNumber = accountNumber;
		this.accountHolderName = accountHolderName;
		this.balance = balance;
		this.location = location;
		this.policy = policy;
	}
	

	public int getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}


	public String getAccountHolderName() {
		return accountHolderName;
	}


	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName + ", balance="
				+ balance + ", location=" + location + ", policy=" + policy + "]";
	}

	@Override
	public int hashCode() {
		return accountNumber+1;
	}

	@Override
	public boolean equals(Object obj) {
		Account a = (Account) obj;
		
		boolean b1 = (this.accountNumber == a.getAccountNumber());
		boolean b2 = (this.accountHolderName.equals(a.getAccountHolderName()));
		boolean b3 = (this.balance == a.getBalance());
		boolean b4 = (this.location.equals(a.getLocation()));
		
		return b1&& b2&& b3 && b4;
	}

	public int getPolicySumAssuredAmount() {
		if (policy != null)
	        return policy.getSumAssuredAmount();
	    else
	        return 0; // Return 0 if no policy is attached
	}

	public int getAccountsByPolicyPremiumAmount() {
		if (policy != null)
	        return policy.getPremiumAmount();
	    else
	        return 0; // Return 0 if no policy is attached
	}	
}