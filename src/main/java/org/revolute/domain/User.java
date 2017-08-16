package org.revolute.domain;

public class User {
	
	private String Id ;
	private String Name;
	private String Address;
	
	public User(String id, String name, String address) {
		super();
		Id = id;
		Name = name;
		Address = address;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	@Override
	public String toString() {
		return "user [Id=" + Id + ", Name=" + Name + ", Address=" + Address + "]";
	}

}
