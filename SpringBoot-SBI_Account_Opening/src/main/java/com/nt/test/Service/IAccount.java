package com.nt.test.Service;

import java.util.List;

import com.nt.test.Entity.AccountHolder;

public interface IAccount {
	List<AccountHolder> getallHoldersLIst();
	String deleteAccountHolder(Integer id);
	String addAmount(Integer id,Double amount);
	String addAccount(AccountHolder acount);
	AccountHolder getAccountDetails(Integer id);
	
}
