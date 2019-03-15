package com.capgemini.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.bean.Account;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;
import com.java.exception.InsufficientBalanceException;
import com.java.exception.InsufficientOpeningBalanceException;
import com.java.exception.InvalidAccountNumberException;

public class AccountServiceImplTest {
AccountService accountService;
	
	@Mock
	AccountRepo accountRepo;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		accountService = new AccountServiceImpl(accountRepo);
	}

	@Test(expected=com.java.exception.InsufficientOpeningBalanceException.class)
	 public void amountLessThanFiveHundred() throws InsufficientOpeningBalanceException
	 {
		 throw new InsufficientOpeningBalanceException();
	 }
	 
	 @Test
	 public void validInfoIsPassedAccountCreated() throws InsufficientOpeningBalanceException{
		 Account account= new Account();
		 account.setAccountNumber(201);
		 account.setAmount(3000);
		 when(accountRepo.save(account)).thenReturn(true);
		 assertEquals(account, accountService.createAccount(201, 3000));
		 
	 }
	 @Test(expected=com.java.exception.InvalidAccountNumberException.class)
	 public void accountNumberIsNotValid() throws InvalidAccountNumberException {
		 accountService.depositAmount(201, 3000);
	 }
	 
	 @Test
	 public void validInfoIsPassedForDeposit() throws InvalidAccountNumberException{
		 Account account=new Account();
		account.setAccountNumber(201);
		 account.setAmount(3000);
		 when(accountRepo.searchAccount(201)).thenReturn(account);
		 assertEquals(account.getAmount()+500,accountService.depositAmount(201, 500));
		  }
	 @Test(expected=com.java.exception.InvalidAccountNumberException.class)
	 public void accountNumberIsNotValidForWithdraw() throws InvalidAccountNumberException, InsufficientBalanceException{
		 Account account=new Account();
		 account.setAccountNumber(201);
		 account.setAmount(3000);
		 when(accountRepo.searchAccount(201)).thenReturn(account);
		 accountService.withdrawAmount(202,3000);
	 }
	 
	 @Test(expected=com.java.exception.InsufficientBalanceException.class)
	 public void whenAmountIsNotSufficientForWithdraw() throws InsufficientBalanceException, InvalidAccountNumberException{
		 Account account=new Account();
		 account.setAccountNumber(201);
		 account.setAmount(3000);
		 when(accountRepo.searchAccount(201)).thenReturn(account);
		 accountService.withdrawAmount(201, 5000);
	 }
	 
	 @Test
	 public void validInfoIsPassedForWithdraw() throws InvalidAccountNumberException,InsufficientBalanceException{
		 Account account=new Account();
		 account.setAccountNumber(201);
		 account.setAmount(3000);
		 when(accountRepo.searchAccount(201)).thenReturn(account);
		 assertEquals(account.getAmount()-1000,accountService.withdrawAmount(201, 1000));
	 }
	 
	 
	 
}
