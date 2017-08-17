package org.revolute.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.revolute.domain.User;

public class UserService {
	
	List<User> users = new ArrayList<User>(); 
	
	public UserService() {
		
	}
	
	public List<User> getAllUsers() {
		return users;
	}
   
	public User getUser(String id) {
		Optional<User> options = users.stream().filter(user -> user.getId().equals(id)).findFirst();
	    return options.get();
	}
	 
	public void addUser(User user) {
		this.users.add(user);
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
	
}
