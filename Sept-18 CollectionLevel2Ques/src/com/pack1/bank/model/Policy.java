package com.pack1.bank.model;

public class Policy {
	private int policyNumber;
	private String policyName;
	private int premiumAmount;
	private int sumAssuredAmount;
	
	public Policy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Policy(int policyNumber, String policyName, int premiumAmount, int sumAssuredAmount) {
		super();
		this.policyNumber = policyNumber;
		this.policyName = policyName;
		this.premiumAmount = premiumAmount;
		this.sumAssuredAmount = sumAssuredAmount;
	}
	
	public int getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(int policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(int premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public int getSumAssuredAmount() {
		return sumAssuredAmount;
	}

	public void setSumAssuredAmount(int sumAssuredAmount) {
		this.sumAssuredAmount = sumAssuredAmount;
	}

	@Override
	public String toString() {
		return "Policy [policyNumber=" + policyNumber + ", policyName=" + policyName + ", premiumAmount="
				+ premiumAmount + ", sumAssuredAmount=" + sumAssuredAmount + "]";
	}
	
	
}
