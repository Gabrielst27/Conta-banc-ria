package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Account;
import entities.BusinessAccount;
import exceptions.DomainException;

public class Program {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter account data");
		
		try {
			System.out.print("Type (c/b/s): ");
			String type = sc.nextLine();
			System.out.print("Number: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Holder: ");
			String holder = sc.nextLine();
			System.out.println("Initital balance: ");
			double balance = sc.nextDouble();
			System.out.println("Withdraw Limit: ");
			double withdrawLimit = sc.nextDouble();
			
			if(type.toUpperCase().equals("C")) {
				Account account = new Account(number, holder, balance, withdrawLimit);
				
				System.out.println("Enter amount for withdraw: ");
				double amount = sc.nextDouble();
				
				account.withdraw(amount);
			} else if(type.toUpperCase().equals("B")) {
				System.out.print("Loan limit: ");
				double loanLimit = sc.nextDouble();
				
				BusinessAccount account = new BusinessAccount(number, holder, balance, withdrawLimit, loanLimit);
				
				System.out.println("Enter amount for withdraw: ");
				double amount = sc.nextDouble();
				
				account.withdraw(amount);
			} else if(type.toUpperCase().equals("S")) {
				
			}
			
		}
		catch (InputMismatchException e) {
			System.out.println("Error in input: invalid input type");
		}
		catch (DomainException e) {
			System.out.println(e);
		}
		
		sc.close();
		
	}

}
