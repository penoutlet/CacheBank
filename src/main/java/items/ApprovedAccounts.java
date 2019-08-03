package items;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import Menus.AdminMenu;
import Menus.CustomerMenu;
import Menus.EmployeeMenu;
import Menus.MainMenu;
import Users.Admin;
import Users.Employee;

public class ApprovedAccounts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5878601051619539591L;
	static HashMap<String, Account> approvedAccounts = new HashMap<String, Account>();
	private static Scanner sc = new Scanner(System.in);
	private static final Admin admin = new Admin();
	private static final Employee emp = new Employee();
	// private static Logger log = Logger.getLogger(ApprovedAccounts.class);

	// add test peoples
	public static void addDummies() {
		addOne("testa", new Account("testa", "testa", 10000, "testa", "testa"));
		addOne("Yum", new Account("Yum", "Yum", 100000, "test", "test"));
		PendingAccounts.addOne("testp", new Account("testp", "testp", 10000, "testp", "testp"));
	}

	public static void addAll(ArrayList<Account> data) {
		System.out.println(data.toString());
		for (Account a : data) {
			approvedAccounts.put(a.getUsername(), a);
			System.out.println(a.getUsername() + " added");
		}
	}

	public static void deserialize() throws FileNotFoundException {
		ArrayList<Account> accounts = new ArrayList<Account>();
		System.out.println("deserialize inside of approved.");
		accounts.add((Account)Persistence.readData("./approvedaccounts.txt"));
		addAll(accounts);
	}

	public static void serializeAll() {
		ArrayList<Account> accounts = fetchAll();
		Persistence.writeList(accounts, "approvedaccounts");
	}

	// methods for approved accounts hashmap
	public static void addOne(String username, Account account) {
		approvedAccounts.put(username, account); // put it into the hashmap
		// Persistence.writeOne(account, "approvedaccounts"); // write the new account
		// to the text file
		serializeAll();
	}

	public static ArrayList<Account> fetchAll() {
		ArrayList<Account> list = new ArrayList<Account>();
		Set<String> keys = approvedAccounts.keySet();
		for (String key : keys) {
			// System.out.println(fetchOne(key).toString());
			Account a = approvedAccounts.get(key);
			list.add(a);
		}
		if (approvedAccounts.isEmpty()) {
			System.out.println("No pending accounts.");
		}

		return list;
	}

	public static Account fetchOne(String username) {
		Set<String> keys = approvedAccounts.keySet();
		Account account = null;
		for (String u : keys) {
			if (username.equals(u)) {
				account = approvedAccounts.get(u);
			} else {
				System.out.println("Unable to fetch.");
				MainMenu.mainMenu();
			}
		}
		return account;

	}

	public static void updateOne(Account a) {
		approvedAccounts.replace(a.getUsername(), a);
		System.out.println("New balance for " + a.getUsername() + " is " + a.getBalance());
		serializeAll(); // calls fetchAll, which creates an arraylist of current accounts, and then
						// calls writeList and writes them to a file
	}

	public static void removeOne(String username) {
		Account a = fetchOne(username);
		if (a == null) {
			AdminMenu.mainMenu();
		} else {
			approvedAccounts.remove(username);
			serializeAll();
		}
	}

	// login methods
	public static void customerLogin() {
		Account a = null;
		sc.nextLine();
		System.out.println("Enter a username and password separated by a space");
		String usernamePW[] = sc.nextLine().split("\\s+");

		String u = "";
		String pw = "";

		try {
			u += usernamePW[0];
			pw += usernamePW[1];
			System.out.println("pw entered " + pw);
			a = fetchOne(u);
			if (a == null) {
				MainMenu.mainMenu();
			} else if (pw.equals(a.getPassword())) {
				System.out.println("PW on file" + a.getPassword());
				CustomerMenu.actionMenu(a);
			} else {
				System.out.println("Incorrect password.");
				MainMenu.mainMenu();
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Enter username + space + password");
		}
	}

	public static void employeeLogin() {
		sc.nextLine();
		System.out.println("Enter a username and password separated by a space");
		String usernamePW[] = sc.nextLine().split("\\s+");

		String u = "";
		String pw = "";
		try {
			u += usernamePW[0];
			pw += usernamePW[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Enter username + space + password");
		}

		if (u.equals(emp.getUsername()) && pw.equals(emp.getPassword())) {
			System.out.println("Login success");
			EmployeeMenu.actionMenu();
		} else {
			System.out.println("Incorrect login creds");
			MainMenu.firstLoginMenu();
		}
	}

	public static void adminLogin() {
		String u = "";
		String pw = "";
		System.out.println("Enter a username and password separated by a space");
		String usernamePW[] = sc.nextLine().split("\\s+");
		try {
			u += usernamePW[0];
			pw += usernamePW[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Enter username + space + password");
		}
		if (u.equals(admin.getUsername()) && pw.equals(admin.getPassword())) {
			System.out.println("Login success");
			AdminMenu.mainMenu();
		} else {
			System.out.println("Incorrect login creds");
			MainMenu.firstLoginMenu();
		}
	}

}
