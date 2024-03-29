package entities;

import exceptions.DomainException;

public class Account {
	
	protected Integer number;
	protected String holder;
	protected Double balance;
	protected Double withdrawLimit;
	
	public Account() {
	}

	public Account(Integer number, String holder, Double balance, Double withdrawLimit) {
		this.number = number;
		this.holder = holder;
		this.balance = balance;
		this.withdrawLimit = withdrawLimit;
	}

	public Integer getNumber() {
		return number;
	}

	public String getHolder() {
		return holder;
	}

	public Double getBalance() {
		return balance;
	}

	public Double getWithdrawLimit() {
		return withdrawLimit;
	}
	
	public void deposit(Double amount) {
		balance += amount;
	}
	
	public void withdraw(Double amount) throws DomainException{
		if(amount > withdrawLimit) {
			throw new DomainException("Error in withdraw: the amount exceeds withdraw limit!");
		}
		if(amount > balance) {
			throw new DomainException("Error in withdraw: not enough balance!");
		}
		balance -= (amount + (amount * 0.03));
	}

}
