package sunnyA1_3475_002;

/**
 * Person class to represent a Person
 * @author SunnyLam
 * 
 * @param String
 */

public class Person {
	public String name;
	public String email;
	public String location;
	//public ListInterface<String> friendList;
	public LList<String> friendList;
	
	//constructor
	//public Person(String name, String email, String location, ListInterface<String> friendList) {
	public Person(String name, String email, String location, LList<String> friendList) {
		this.name = name;
		this.email = email;
		this.location = location;
		this.friendList = friendList;
	}
	
	//getter and setter
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getLocation() {
		return location;
	}

	public ListInterface<String> getFriendList() {
		return friendList;
	}
	
	
}
