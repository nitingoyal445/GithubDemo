package com.capgemini.service;

import com.capgemini.bean.Account;
import com.capgemini.repo.AccountRepo;
import com.java.exception.InsufficientBalanceException;
import com.java.exception.InsufficientOpeningBalanceException;
import com.java.exception.InvalidAccountNumberException;

public class AccountServiceImpl implements AccountService {

	AccountRepo accountRepo;
	public AccountServiceImpl(AccountRepo accountRepo){
		super();
		this.accountRepo=accountRepo;
	}
	@Override
	public Account createAccount(int accountNumber,int amount) throws InsufficientOpeningBalanceException{
		
		if(amount<500) {
			throw new InsufficientOpeningBalanceException();
		}
		Account account=new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);
		
		if(accountRepo.save(account)) {
			return account;
		}
		return null;
	}
	@Override
	public int depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException{
		Account account= accountRepo.searchAccount(accountNumber);
		if(account==null) {
			System.out.println("From if statement");
			throw new InvalidAccountNumberException();
		}
		
		account.setAmount(account.getAmount()+amount);
		accountRepo.save(account);
		System.out.println("After save");
		return account.getAmount();
		
	}
	@Override
	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException{
		Account account=accountRepo.searchAccount(accountNumber);
		if(account==null) {
			System.out.println("inside if withdraw");
			throw new InvalidAccountNumberException();
		}
		if(account.getAmount()<amount) {
			throw new InsufficientBalanceException();
		}
		account.setAmount(account.getAmount()-amount);
		accountRepo.save(account);
		return account.getAmount();
	}	
	
}
