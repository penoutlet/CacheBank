package Menus;


import java.util.Scanner;

import items.Account;
import items.ApprovedAccounts;
import items.UtilityMenus;


public class CustomerMenu  {
	static Scanner sc = new Scanner(System.in);
	private static String[] optionsArray = {"0. View your account.", "1. Deposit", "2. Withdraw", "3. Transfer", "4. Cancel an Account","5. Return to previous menu"};
	
	public static void loginMenu() {
		ApprovedAccounts.customerLogin();
	}
	
	public static void actionMenu(Account a) {
		
		System.out.println("Welcome customer, what would you like to do?");
		
		for(int i =0; i<optionsArray.length;i++) {
			System.out.println(optionsArray[i]);
		}
		
		String choice = sc.nextLine();
		CustomerMenu.handleInput(choice, a);
		
	}
	
	public static void handleInput(String choice, Account a) {
		System.out.println("Choice was " + choice);
		boolean flag = true;
		while(flag) {
			
			switch(choice) {
			case "0": 
				System.out.println(a.toString());
				actionMenu(a);
				flag=!flag;
				break;
			case "1": 
				UtilityMenus.depositMenu(a);
				flag=!flag;
				break;
			case "2":
				UtilityMenus.withdrawMenu(a);
				flag=!flag;
				break;
			case "3":
				UtilityMenus.transferMenu(a);
				flag=!flag;
				break;
			case "4":
				UtilityMenus.cancelMenu(a);
				flag=!flag;
				break;
			case "5":
				MainMenu.mainMenu();;
				flag=!flag;
				break;
			default: 
				System.out.println("Enter 1, 2, or 3.");
				flag=!flag;
				break;
			}
		}
	}
}
