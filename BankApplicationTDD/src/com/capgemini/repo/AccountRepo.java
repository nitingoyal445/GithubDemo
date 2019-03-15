package com.capgemini.repo;
import com.capgemini.bean.Account;
public interface AccountRepo {
	boolean save(Account account);
	Account searchAccount(int accountNumber);
}
