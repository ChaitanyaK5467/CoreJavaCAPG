package pack1;

import java.util.Comparator;

public class SortAccountsBasedonBalanceLowToHigh implements Comparator<Account> {

	@Override
	public int compare(Account o1, Account o2) {
		// TODO Auto-generated method stub
		return o1.getBalance() - o2.getBalance();
	}

}
