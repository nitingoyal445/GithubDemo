package com.capgemini.service;
import com.capgemini.bean.Account;
import com.java.exception.InsufficientBalanceException;
import com.java.exception.InsufficientOpeningBalanceException;
import com.java.exception.InvalidAccountNumberException;
public interface AccountService {
	Account createAccount(int accountNumber,int amount) throws InsufficientOpeningBalanceException;
	int depositAmount(int accountNumber,int amount) throws InvalidAccountNumberException;
    int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException;
}
