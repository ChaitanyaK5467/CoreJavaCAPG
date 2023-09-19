package com.pack1.bank.ctr;

import java.util.ArrayList;
import java.util.List;

import com.pack1.bank.model.Account;
import com.pack1.bank.model.Policy;

public class PolicyServer {
	
	List<Policy> allPolicies = new ArrayList<>();
	
	public int addPolicyDetailsDB(Policy p) {
		boolean status = allPolicies.add(p);
		return status?p.getPolicyNumber():-1;
	}
	
	public Policy getAccountByName(String name) {
		for (Policy policy : allPolicies) {
			if(policy.getPolicyName() == name) {
				return policy;
			}
		}
		return null;
	}
	
	public Policy getAccountByNumber(int policyNumber) {
		for (Policy policy : allPolicies) {
			if(policy.getPolicyNumber() == policyNumber) {
				return policy;
			}
		}
		return null;
	}

}
