package com.pack1.bank.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.Scanner;
import java.util.Set;

import com.pack1.bank.db.AccountDB;
import com.pack1.bank.model.Account;
import com.pack1.bank.service.AccountServer;
import com.pack1.bank.service.LowBalanceException;

public class AccountMain {
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);
	
	AccountServer server;
	AccountDB db;

	public AccountMain() {
		server = new AccountServer();
		db = new AccountDB();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		AccountMain app = new AccountMain();
		
		while(true) {
			System.out.println("----* Select Option *----");
			System.out.println("1. Add Account Details");
			System.out.println("2. Display Account Details");
			System.out.println("3. Display Account Details By Account Number");
			System.out.println("4. Display Account Details By Location");
			System.out.println("5. Display Account Details Based on Balance Range");
			System.out.println("6. Credit Account");
			System.out.println("7. Debit Account");
			System.out.println("8. Fund Transfer");
			
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
				app.getAccountByLocation();
				break;
			case 5:
				app.getAccountByBalanceRange();
				break;
			case 6:
				app.creditAccount();
				break;
			case 7:
				app.debitAccount();
				break;
			case 8:
				app.fundTransfer();
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
		
		Account a = new Account(accNum, name, initialBalance, location);
		
		String notification = server.addAccDetails(a);
		System.out.println("Notification : "+notification);
	}
	
	public void displayAccountDetails(Account a) {
		System.out.println(a.getAccountHolderName()+" "+a.getAccountNumber()+" "+a.getLocation()+" "+a.getBalance());
	}
	
	public void getAccountByAccNum() {
		System.out.println("Enter Account Number to be Searched : ");
		int searchAccNum = Integer.parseInt(sc1.nextLine());
		
		Account a = server.getAccountNum(searchAccNum);
		
		if(a != null)
			displayAccountDetails(a);
		else
			System.out.println("Employee Not Found for ID : "+searchAccNum);
	}
	
	public void getAccountByLocation() {
		System.out.println("Enter Location to be searched : ");
		String searchLocation = sc1.nextLine();
		
		Set<Account> accountByLocation = server.getAccountByLocation(searchLocation);
		for (Account account : accountByLocation) {
			displayAccountDetails(account);
		}			
	}

	public void getAccountByBalanceRange() {
		System.out.println("Enter Balance Range 1 : ");
		int balance1 = Integer.parseInt(sc1.nextLine());
		
		System.out.println("Enter Balance Range 2 : ");
		int balance2 = Integer.parseInt(sc1.nextLine());
		
		Set<Account> accountByBalanceRange = server.getAccountByBalanceRange(balance1, balance2);
		for (Account account : accountByBalanceRange) {
			displayAccountDetails(account);
		}
	}
	
	public void creditAccount() {
		System.out.println("Enter Account Number For Deposit : ");
		int accNumber = Integer.parseInt(sc1.nextLine());
		
		Account a = server.getAccountNum(accNumber);
		
		if (a != null) {
			System.out.println("Enter Credit Amount : ");
			int creditAmount = Integer.parseInt(sc1.nextLine());
			
			try {
				server.credit(a, creditAmount);
				System.out.println("Credit Successful, Updated Balance : "+a.getBalance());
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				System.out.println("Invalid Credit Amount : "+e.getMessage());
			}
		}
		else
			System.out.println("!! Account Not Found !! ");
	}
	
	public void debitAccount() {
		System.out.println("Enter Account Number For Withdrawl : ");
		int accNumber = Integer.parseInt(sc1.nextLine());
		
		Account a = server.getAccountNum(accNumber);
		
		if (a != null) {
			System.out.println("Enter Debit Amount : ");
			int debitAmount = Integer.parseInt(sc1.nextLine());
			
			try {
				server.debit(a, debitAmount);
				System.out.println("Debit Successful, Updated Balance : "+a.getBalance());
			} catch (IllegalArgumentException | LowBalanceException e) {
				// TODO: handle exception
				System.out.println("Debit Failed : "+e.getMessage());
			}
		}
		else
			System.out.println("!! Account Not Found !! ");
	}
	
	public void fundTransfer() {
		System.out.println("Enter Source Account Number : ");
		int sourceAccountNum = Integer.parseInt(sc1.nextLine());
		
		System.out.println("Enter Source Account Number : ");
		int receiverAccountNum = Integer.parseInt(sc1.nextLine());
		
		Account sourceAccount = server.getAccountNum(sourceAccountNum);
		Account receiverAccount = server.getAccountNum(receiverAccountNum);
		
		if(sourceAccount != null && receiverAccount != null) {
			System.out.println("Enter Transfer Amount : ");
			int transferAmount = Integer.parseInt(sc1.nextLine());
			
			try {
				server.fundTransfer(sourceAccount, receiverAccount, transferAmount);
				System.out.println("Fund Transfer Successful. Updated Balance in Source Account : "+sourceAccount.getBalance());
				System.out.println("Updated Balance in Receiver Account : "+receiverAccount.getBalance());
			} catch (IllegalArgumentException | LowBalanceException e) {
				// TODO: handle exception
				System.out.println("Fund Transfer Failed : "+e.getMessage());
			}
		}
		else
			System.out.println("Account not found !!");
	}
	
	public void getAllAccounts() {
		List<Account> allSavedAccount = server.getAllAccounts();
		
		for (Account account : allSavedAccount) {
			displayAccountDetails(account);
		}
	}
}