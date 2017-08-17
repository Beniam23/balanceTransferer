package org.revolute.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String Id ;
	private String Name;
	private String Address;
	
	private List<Account> accounts = new ArrayList<Account>();
	
	public User(String id, String name, String address) {
		super();
		Id = id;
		Name = name;
		Address = address;
	}
	
	public User(String name, String address) {
		super();
		Id = name + "Id";
		Name = name;
		Address = address;
	}

	public String getId() {
		return Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}
	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		this.Address = address;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account account) {
		this.accounts.add(account);
	}
	
	public void addAccount(AccountType accountType, double amount) {	
		switch(accountType) {
			case SAVING:
				this.addAccount(new SavingAccount(getId() + "SavingAccount",amount));
				break;
			case CLASSIC:
				this.addAccount(new ClassicAccount(getId() + "ClassicAccount",amount));	
		}
	}
	
	@Override
	public String toString() {
		return "user [Id=" + Id + ", Name=" + Name + ", Address=" + Address + "]";
	}

	

	

}
