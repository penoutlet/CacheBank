package items;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Menus.MainMenu;

public class Driver {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MainMenu.loginMenu();
		try {
			PendingAccounts.deserialize();
			ApprovedAccounts.deserialize();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			MainMenu.firstLoginMenu();
		}
//		ApprovedAccounts.serializeAll();
	}

}
