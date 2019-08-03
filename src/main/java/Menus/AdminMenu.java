package Menus;


import java.util.Scanner;

import Users.Admin;
import items.Account;
import items.AdminUtilities;
import items.ApprovedAccounts;
import items.PendingAccounts;
import items.UtilityMenus;

public class AdminMenu {
	static Scanner sc = new Scanner(System.in);
	private static String[] optionsArray1 = {"1. Find an approved account and perform an action on it", "2. Find a pending account","3. Return to prior menu (logout)."};
	private static String[] optionsArray2 = {"0. View account","1. Deposit", "2. Withdraw", "3. Transfer", "4. Cancel an Account ", "5. Return to admin menu."};
	private AdminMenu() {};
	
	public static void mainMenu() {
		System.out.println("Welcome, what would you like to do?");
		
		for(int i =0; i<optionsArray1.length;i++) {
			System.out.println(optionsArray1[i]);
		}
		String choice = sc.nextLine();
		firstInputHandler(choice);
	}
	
	public static void actionMenu(Account a) {
		for(int i =0; i<optionsArray2.length;i++) {
			System.out.println(optionsArray2[i]);
		}
		String choice = sc.nextLine();
		secondInputHandler(choice, a);
	}
	
	public static void firstInputHandler(String choice)  {
		boolean flag = true;
		while(flag) {
			
			switch(choice) {
			case "1": 
				System.out.println("Enter a username to fetch.");
				sc.nextLine();
				String username = sc.nextLine();
				Account a = ApprovedAccounts.fetchOne(username);
				if(a==null) {
					System.out.println("No such user exists.");
					AdminMenu.mainMenu();
				} 
				System.out.println(a.getUsername() + " found.");
				actionMenu(a);
//				try { 
//					Account a = ApprovedAccounts.fetchOne(username);
//					actionMenu(a);
//				} catch(NullPointerException e) {
//					e.printStackTrace();
//					System.out.println("Woops account not found.");
//				}
				
				flag=!flag;
				break;
			case "2":
				System.out.println("Enter a username to fetch.");
				username = sc.nextLine();
				a = PendingAccounts.fetchOne(username);
				if(a!=null) {
					approveOrDenyMenu(a);
				} else {
					System.out.println("Account is null in AdminMenu.");
				}
				mainMenu();
				flag=!flag;
				break;
			case "3":
				MainMenu.mainMenu();
				flag=!flag;
				break;
			}
	}
	}
	public static void secondInputHandler(String choice, Account a) {
		boolean flag = true;
		while(flag) {
			
			switch(choice) {
			case "0":
				System.out.println(a.toString());
				actionMenu(a);
				flag=!flag;
				break;
			case "1": 
				AdminUtilities.depositMenu(a);
				flag=!flag;
				break;
			case "2":
				AdminUtilities.withdrawMenu(a);
				flag=!flag;
				break;
			case "3":
				AdminUtilities.transferMenu(a);
				flag=!flag;
				break;
			case "4":
				AdminUtilities.cancelMenu(a);
				flag=!flag;
				break;
			case "5":
				AdminMenu.mainMenu();
				flag=!flag;
				break;
			default: 
				System.out.println("Select an option.");
				flag=!flag;
				break;
			}
		}
	}
	public static void approveOrDenyMenu(Account a) {
		System.out.println("1. Approve\n2. Deny\n3.Return to previous menu");
		String choice = sc.nextLine();
		switch (choice) {
		case "1":
			Admin.approve(a);
			mainMenu();
			break;
		case "2":
			Admin.deny(a);
			mainMenu();
			break;
		case "3":
			MainMenu.mainMenu();
			;
		}
	}
}
