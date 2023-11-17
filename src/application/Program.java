package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;
import exceptions.DomainException;

public class Program {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		List<Account> accounts = new ArrayList<>();
		
		System.out.println("Enter account data");
		
		try {
			System.out.print("Type (c/b/s): ");
			String type = sc.nextLine();
			System.out.print("Number: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Holder: ");
			String holder = sc.nextLine();
			System.out.print("Initital balance: ");
			double balance = sc.nextDouble();
			System.out.print("Withdraw Limit: ");
			double withdrawLimit = sc.nextDouble();
			
			if(type.toUpperCase().equals("C")) {
				Account account = new Account(number, holder, balance, withdrawLimit);
				accounts.add(account);
			} else if(type.toUpperCase().equals("B")) {
				System.out.print("Loan limit: ");
				double loanLimit = sc.nextDouble();
				
				BusinessAccount account = new BusinessAccount(number, holder, balance, withdrawLimit, loanLimit);		
				accounts.add(account);
			} else if(type.toUpperCase().equals("S")) {
				System.out.print("Interest rate: ");
				double interestRate = sc.nextDouble();
				
				Account account = new SavingsAccount(number, holder, balance, withdrawLimit, interestRate);
				accounts.add(account);
			}
			
			System.out.println();
			System.out.print("Enter amount for deposit: ");
			double amount = sc.nextDouble();
			
			accounts.get(0).deposit(amount);
			
			System.out.println();
			System.out.print("Enter amount for withdraw: ");
			amount = sc.nextDouble();
			
			accounts.get(0).withdraw(amount);
			
			if(type.toUpperCase().equals("B")) {
				BusinessAccount businessAccount = (BusinessAccount)accounts.get(0);
				//Realizado downcasting de Account para BusinessAccount
				System.out.printf("\nEnter amount for loan: ");
				double loan = sc.nextDouble();
				businessAccount.loan(loan);
			}
			if(type.toUpperCase().equals("S")) {
				SavingsAccount savingsAccount = (SavingsAccount)accounts.get(0);
				System.out.printf("\nEnter the number of months to see the total income: ");
				int months = sc.nextInt();
				
				System.out.printf("After %d months, you will have R$ %.2f", 
						months, savingsAccount.updateBalance(months));
			}
		
		}
		catch (InputMismatchException e) {
			System.out.println("Error in input: invalid input type");
		}
		catch (DomainException e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
		
	}

}
