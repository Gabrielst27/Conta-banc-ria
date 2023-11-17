package entities;

import exceptions.DomainException;

public final class SavingsAccount extends Account {

	private Double interestRate;
	
	public SavingsAccount() {
		super();
	}
	
	public SavingsAccount(Integer number, String holder, Double balance, Double withdrawLimit, Double interestRate) {
		super(number, holder, balance, withdrawLimit);
		this.interestRate = interestRate;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	public final Double updateBalance(Integer months) throws DomainException {
		if (balance <= 0) {
			throw new DomainException ("Error in ratings: account has no balance!");
		}
		
		return balance += (balance * interestRate * months);
	}

	@Override
	public void withdraw(Double amount) throws DomainException {
		if(amount > withdrawLimit) {
			throw new DomainException("Error in withdraw: the amount exceeds withdraw limit!");
		}
		if(amount > balance) {
			throw new DomainException("Error in withdraw: not enough balance!");
		}
		
		balance -= amount;
	}

}
