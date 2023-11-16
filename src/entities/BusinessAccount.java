package entities;

import exceptions.DomainException;

public final class BusinessAccount extends Account {
	
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
	public void withdraw(Double amount) throws DomainException {
		if(amount > withdrawLimit) {
			throw new DomainException("Error in withdraw: the amount exceeds withdraw limit!");
		}
		if(amount > balance) {
			throw new DomainException("Error in withdraw: not enough balance!");
		}
		balance -= (amount + (amount * 0.1));
	}

}
