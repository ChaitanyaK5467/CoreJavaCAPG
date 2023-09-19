package com.pack1.bank.ui;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

import java.util.Scanner;
import java.util.Set;

import com.pack1.bank.db.AccountDB;
import com.pack1.bank.model.Account;
import com.pack1.bank.service.AccountServer;

public class AccountMain {
	Scanner sc = new Scanner(System.in);
	Scanner sc1 = new Scanner(System.in);

	AccountServer server;
	AccountDB db;

	public AccountMain() {
		server = new AccountServer();
		db = new AccountDB();
	}

	Map<Integer, Account> map = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AccountMain app = new AccountMain();

		while (true) {
			System.out.println("----* Select Option *----");
			System.out.println("1. Add Account Details");
			System.out.println("2. Display Account Details");
			System.out.println("3. Display Account Details By Account Number");
			System.out.println("4. Display Account Details By Location");
			System.out.println("5. Display Account Details Based on Balance Range");

			System.out.println("9. Add Account in Map"); // Adding Map<Integer,List<Account>> where Integer is already
															// generated in addAccountDetails();

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
			case 9:
				app.addDetailsInMap();
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
		System.out.println("Notification : " + notification);
	}

	public void displayAccountDetails(Account a) {
		System.out.println(
				a.getAccountHolderName() + " " + a.getAccountNumber() + " " + a.getLocation() + " " + a.getBalance());
	}

	public void getAccountByAccNum() {
		System.out.println("Enter Account Number to be Searched : ");
		int searchAccNum = Integer.parseInt(sc1.nextLine());

		Account a = server.getAccountNum(searchAccNum);

		if (a != null) {
			for (Map.Entry<Integer, Account> entry : map.entrySet()) {
				int key = entry.getKey();
				Account account = entry.getValue();

				if (account.getAccountNumber() == searchAccNum) {
					displayAccountDetailsMap(account);
					break;
				}
			}
		} else {
			System.out.println("No account found for the specified account number.");
		}
	}

	public void getAccountByLocation() {
		System.out.println("Enter Location to be searched : ");
		String searchLocation = sc1.nextLine();

		for (Map.Entry<Integer, Account> entry : map.entrySet()) {
			int key = entry.getKey();
			Account a = entry.getValue();

			if (a.getLocation().equals(searchLocation)) {
				displayAccountDetailsMap(a);
			}
		}
	}

	public void getAccountByBalanceRange() {
		System.out.println("Enter Balance Range 1 : ");
		int balance1 = Integer.parseInt(sc1.nextLine());

		System.out.println("Enter Balance Range 2 : ");
		int balance2 = Integer.parseInt(sc1.nextLine());

		for (Map.Entry<Integer, Account> entry : map.entrySet()) {
			int key = entry.getKey();
			Account a = entry.getValue();

			if (a.getBalance() >= balance1 && a.getBalance() <= balance2) {
				displayAccountDetailsMap(a);
			}
		}
	}

	public void getAllAccounts() {
		List<Account> allSavedAccount = server.getAllAccounts();

		for (Account account : allSavedAccount) {
			displayAccountDetails(account);
		}
	}

	public void addDetailsInMap() {
		System.out.println("Enter Account Number to be Searched : ");
		int searchAccNum = Integer.parseInt(sc1.nextLine());

		Account a = server.getAccountNum(searchAccNum);

		
		if (a != null) {
			Map<Integer, List<Account>> accountGroups = server.addAccDetailsMap(a);
			if (accountGroups.containsKey(a.getAccountNumber())) {
	            map.put(a.getAccountNumber(), a);
	            displayAccountDetailsMap(a);
	            System.out.println("Notification : " + accountGroups);
	        } else {
	            System.out.println("No accounts found for the specified account number.");
	        }
		}
		else {
		  System.out.println("No account found for the specified account number."); }
		
	}

	public void displayAccountDetailsMap(Account a) {
		System.out.println(
				a.getAccountHolderName() + " " + a.getAccountNumber() + " " + a.getLocation() + " " + a.getBalance());
	}
}