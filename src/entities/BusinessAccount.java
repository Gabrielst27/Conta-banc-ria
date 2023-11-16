package entities;

import exceptions.DomainException;

public class BusinessAccount extends Account {
	
	private Double loanLimit;
	
	public BusinessAccount() {
		super();
	}

	public BusinessAccount(Integer number, String holder, Double balance, Double withdrawLimit, Double loanLimit) {
		super(number, holder, balance, withdrawLimit);
		this.loanLimit = loanLimit;
	}

	public Double getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(Double loanLimit) {
		this.loanLimit = loanLimit;
	}
	
	public void loan(Double amount) throws DomainException {
		if(amount > loanLimit) {
			throw new DomainException ("Error in amount value: amount exceeds loan limit!");
		}
		
		deposit(amount);
	}

	@Override
	public void deposit(Double amount) {
		balance += amount;	
	}

	@Override
	public void withdraw(Double amount) throws DomainException {
		if(balance <= 0) {
			throw new DomainException ("Error in withdraw: withdraw value exceeds account's balance!");
		}
		balance -= amount + (amount * 0.1);
	}

}
