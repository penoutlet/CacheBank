package Menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import items.ApprovedAccounts;
import items.PendingAccounts;
import items.Persistence;

public class MainMenu {
	static Scanner sc = new Scanner(System.in);
	static String[] optionsArray = { "1. Customer", "2. Employee", "3. Admin", "4. Return to login menu." };

	public static void firstLoginMenu() {
		boolean flag = true;
		// Persistence.readData("./pendingaccounts.txt", "./approvedaccounts.txt");
//		Object loadedDataPending = Persistence.readData("./pendingaccounts.txt");
//		Object loadedDataApproved = Persistence.readData("./approvedaccounts.txt");
//		PendingAccounts.deserialize();
//		if (loadedDataPending == null) {
//			System.out.println("No pending accounts loaded.");
//		}
//		else {
//			PendingAccounts.addAll(loadedDataPending);
//		}
//		if (loadedDataApproved == null) {
//			System.out.println("No approved accounts loaded");
//		} else {
//			ApprovedAccounts.addAll(loadedDataApproved);
//		}

		System.out.println("Welcome to Stacks of Cache Bank.");
		System.out.println("1. Sign up.\n2. Sign in.\n3. Add test dummies.");
		while (flag) {
		
			String newUser = sc.nextLine();
			switch (newUser) {
			case "0":
				PendingAccounts.serializeAll();
				ApprovedAccounts.serializeAll();
				break;
			case "1":
				PendingAccounts.createNewUser();
				flag = !flag;
				break;
			case "2":
				MainMenu.mainMenu();
				flag = !flag;
				break;
			case "3":
				ApprovedAccounts.addDummies();
				firstLoginMenu();
				flag = !flag;
				break;
			default:
				firstLoginMenu();
				flag = !flag;
				break;
			}
		}
	}

	public static void mainMenu() {
		for (String s : optionsArray) {
			System.out.println(s);
		}
		System.out.println("Please select an option to continue");
		// int choice = sc.nextInt();
		//
		// loginHandler(choice);
		try {
			String choice = sc.nextLine();
			loginHandler(choice);
		} catch (InputMismatchException e) {
			e.printStackTrace();
			System.out.println("Enter a numeric option.");
		}
	}

	public static void loginHandler(String choice) {
		boolean flag = true;
		while (flag) {

			switch (choice) {
			case "1":
				CustomerMenu.loginMenu();
				flag = !flag;
				break;
			case "2":
				ApprovedAccounts.employeeLogin();
				flag = !flag;
				break;
			case "3":
				ApprovedAccounts.adminLogin();
				flag = !flag;
				break;
			case "4":
				firstLoginMenu();
			default:
				System.out.println("Select a choice.");
				MainMenu.mainMenu();
				flag = !flag;
				break;
			}
		}
	}
}
