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
		
		createAccount();
		
		try {
			System.out.print("Number: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Holder: ");
			String holder = sc.nextLine();
			System.out.print("Initital balance: ");
			double balance = sc.nextDouble();
			System.out.print("Withdraw Limit: ");
			double withdrawLimit = sc.nextDouble();
			
			if(createAccount().toUpperCase().equals("C")) {
				account = new Account(number, holder, balance, withdrawLimit);
				
			} else if(createAccount().toUpperCase().equals("B")) {
				System.out.print("Loan limit: ");
				double loanLimit = sc.nextDouble();
				
				account = new BusinessAccount(number, holder, balance, withdrawLimit, loanLimit);		
				
			} else if(createAccount().toUpperCase().equals("S")) {
				System.out.print("Interest rate: ");
				double interestRate = sc.nextDouble();
				
				account = new SavingsAccount(number, holder, balance, withdrawLimit, interestRate);
				
			}
			
			System.out.println();
			System.out.print("Enter amount for deposit: ");
			account.deposit(sc.nextDouble());
			System.out.printf("Updated balance: $ %.2f", account.getBalance());
			
			System.out.println();
			System.out.print("Enter amount for withdraw: ");
			account.withdraw(sc.nextDouble());
			System.out.printf("Updated balance: $ %.2f", account.getBalance());
			
			if(createAccount().toUpperCase().equals("B")) {
				BusinessAccount businessAccount = (BusinessAccount)account;
				//Realizado downcasting de Account para BusinessAccount
				System.out.printf("\nEnter amount for loan: ");
				businessAccount.loan(sc.nextDouble());
				System.out.printf("Updated balance: $ %.2f", account.getBalance());
			}
			if(createAccount().toUpperCase().equals("S")) {
				SavingsAccount savingsAccount = (SavingsAccount)account;
				System.out.printf("\nEnter the number of months to see the total income: ");
				int months = sc.nextInt();
				System.out.printf("After %d months, you will have $ %.2f", 
						months, savingsAccount.updateBalance(months));
			}
		
		}
		catch (InputMismatchException e) {
			System.out.println("Error in input: invalid input type");
		} catch (DomainException e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
			
		}
	
	public static String createAccount() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Type (c/b/s): ");
		String type = sc.nextLine();
		
		if(!type.toUpperCase().equals("C") && !type.toUpperCase().equals("B") && !type.toUpperCase().equals("S")) {
			System.out.printf("Invalid value! Try again.\n\n");
			createAccount();
		}
			
		sc.close();
		
		return type;
		
	}

}
