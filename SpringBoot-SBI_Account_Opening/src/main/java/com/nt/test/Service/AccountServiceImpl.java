package com.nt.test.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.test.Entity.AccountHolder;
import com.nt.test.Exception.CustomException;
import com.nt.test.Repository.IAccountRepo;

@Service
public class AccountServiceImpl implements IAccount {
	@Autowired
	private IAccountRepo accountRepo;

	@Override
	public List<AccountHolder> getallHoldersLIst() {
		
		return accountRepo.findAllActiveHolders();
	}

	@Override
	public String deleteAccountHolder(Integer id) {
		accountRepo.deleteActiveHolder(id);
		return "Account holder with " + id + " is deleted";
	}

	@Override
	public String addAmount(Integer id, Double amount) {
		accountRepo.updateAmount(id, amount);
		String value = "Update Amount is ";
		Optional<AccountHolder> holder = accountRepo.findById(id);
		if (holder.isPresent()) {
			value = value + holder.get().getAmount();
		}
		return value;
	}

	@Override
	public String addAccount(AccountHolder account) {
		String name = account.getName();
		String type = account.getAccountType();
		String Branch = account.getBranch();
		if (accountRepo.existsByNameAndBranchAndAccountType(name, Branch, type)) {
			throw new CustomException("Account already present with the name ==> " + name + ", branch ==> " + Branch
					+ ", accountType ==> " + type);
		} else {
			account.setIsActive("Y");
			AccountHolder holder = accountRepo.save(account);
			return "Account Created Successfully with accountNumber " + holder.getId();
		}
	}
	@Override
	public AccountHolder getAccountDetails(Integer id) {
		Optional<AccountHolder> details = accountRepo.findByIdAndIsActive(id,"Y");
		if (details.isPresent()) {
			return  details.get();
		} else {
			throw new CustomException("No records found on "+id);
		}

	}
	
}
