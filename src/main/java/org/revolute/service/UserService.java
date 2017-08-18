package org.revolute.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.revolute.domain.Account;
import org.revolute.domain.User;

/**
 * @author BINIAM GEBREYESUS	
 * @since 16/08/17 
 * @version 1.0 
 * */
public class UserService {
	
	List<User> users = new ArrayList<User>(); 
	
	public List<User> getAllUsers() {
		return users;
	}
   
	public List<Account> getAllUsersAccounts(){
		List<Account> allUsersAccounts = new ArrayList<Account>() ;
		for(User user: users) {
			allUsersAccounts.addAll(user.getAccounts());
		}
		
		return allUsersAccounts;
	}
	
	public User getUser(String id) {
		Optional<User> options = users.stream().filter(user -> user.getId().equals(id)).findFirst();
		if(options.isPresent())
			return options.get();
	
		return null;
	}
	 
	public void addUser(User user) {
		this.users.add(user);
	}
	
	public void deleteUser(String id) {
		users.remove(getUser(id));
	}
	
	public User createUser(String name, String address) {
		return new User(name+"Id", name, address);
	}
	
	public User updateUser(String id, String name, String address) {
		User userToUpdate = getUser(id);
		if(userToUpdate != null) {
			userToUpdate.setName(name);
			userToUpdate.setAddress(address);
		}
		return userToUpdate;
	}

	public Account getAccount(String accountId) {

		for(Account account: getAllUsersAccounts()) {
			if(account.getId().equals(accountId))
				return account;
		}
		
		return null;
	}
	
}
