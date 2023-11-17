package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;
import exceptions.DomainException;

public class Program {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Account account = new Account();
		
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
				account = new Account(number, holder, balance, withdrawLimit);
				
			} else if(type.toUpperCase().equals("B")) {
				System.out.print("Loan limit: ");
				double loanLimit = sc.nextDouble();
				
				account = new BusinessAccount(number, holder, balance, withdrawLimit, loanLimit);		
				
			} else if(type.toUpperCase().equals("S")) {
				System.out.print("Interest rate: ");
				double interestRate = sc.nextDouble();
				
				account = new SavingsAccount(number, holder, balance, withdrawLimit, interestRate);
				
			}
			
			System.out.println();
			System.out.print("Enter amount for deposit: ");
			double amount = sc.nextDouble();
			
			account.deposit(amount);
			
			System.out.println();
			System.out.print("Enter amount for withdraw: ");
			amount = sc.nextDouble();
			
			account.withdraw(amount);
			
			if(type.toUpperCase().equals("B")) {
				BusinessAccount businessAccount = (BusinessAccount)account;
				//Realizado downcasting de Account para BusinessAccount
				System.out.printf("\nEnter amount for loan: ");
				double loan = sc.nextDouble();
				businessAccount.loan(loan);
			}
			if(type.toUpperCase().equals("S")) {
				SavingsAccount savingsAccount = (SavingsAccount)account;
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
