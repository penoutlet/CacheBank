package items;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1619123874811695284L;
	private String username;
	private transient String password;
	private double balance;
	private boolean isApproved=false;
	private String username2;
	private String firstname;
	private String lastname;
	
	

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", balance=" + balance + ", isApproved="
				+ isApproved + ", username2=" + username2 + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}

	public Account(String username, String password, double balance, String username2, String firstname,
			String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.username2 = username2;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Account(String username, String password, double balance, String firstname, String lastname) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
