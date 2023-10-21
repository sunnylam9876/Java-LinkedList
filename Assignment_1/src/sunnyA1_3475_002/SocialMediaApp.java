/**
 * 
 */
package sunnyA1_3475_002;

/**
 * A Social Media Application that utilizes a linked list class to store a person's information
 * @author SunnyLam
 * 
 */
import java.util.Scanner;

public class SocialMediaApp {
	//global variable
	public static ListInterface<Person> personList = new LList<Person>();	
	public static Scanner scanner;
	
	public static void main(String[] args) {

		int userInput = 0;
		while (userInput != 10) {
			scanner = new Scanner(System.in);
			System.out.println("Please select the following options (1 - 10):");
			System.out.println("1: Add a person to the list");
			System.out.println("2: Add a person at a particular position");
			System.out.println("3: Remove a person");
			System.out.println("4: Remove all the persons from the list");
			System.out.println("5: Retrieve and display the information of everyone in the list");
			System.out.println("6: Search a person by name or email");
			System.out.println("7: Add a friend to the friend list or remove a friend from the friend list");
			System.out.println("8: Count the number of people on the list");
			System.out.println("9: Check if the list is empty");
			System.out.println("10: Exit");
			System.out.print("Please input (1 - 10): ");
		
			userInput = scanner.nextInt();
			scanner.nextLine();		//consume any leftover newline to avoid input error
			switch (userInput) {
				case 1:
					addPerson(0);	//add a person at the end of the Person list
					break;
				case 2:
					addPerson(1);	//Add a person at a particular position of Person list
					break;
				case 3:
					remove();		//Remove a person from the Person list
					break;
				case 4:
					removeAll();	//clear the Person list
					break;
				case 5:
					displayList();	//display the whole Person list
					break;
				case 6:
					search(0);		//search a friend
					break;
				case 7:
					search(1);		//search a friend and then add new friends or remove a friend
					break;
				case 8:
					count();		//count the number of people on the person list
					break;
				case 9:
					checkEmpty();	//check if the list is empty
					break;	
			}
		}
		scanner.close();
	}
	
	//add person to the list
	//param:
	//0 : insert at the end of the list
	//others: insert at the particular position of the list
	public static void addPerson(int option) {
		System.out.print("Enter name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter the person's email: ");
		String email = scanner.nextLine();
		System.out.print("Please enter the person's location: ");
		String location = scanner.nextLine();
		//ListInterface<String> friendList = new LList<String>();
		LList<String> friendList = new LList<String>();
		
		//prompt the user to input friends
		while (true) {
			System.out.print("Enter a friend's name (or press enter to finish): ");
			String friendName = scanner.nextLine();
			if (friendName.equalsIgnoreCase("")) {		//if user press the enter, exit friend's adding process
				break;
			}
			friendList.add(friendName);		//add to friend list
		}
		
		Person person = new Person(name, email, location, friendList);
		if (option == 0) {	//insert the person to the end of list
			personList.add(person);
			System.out.println("**************************");
			System.out.println("The person is added");
			System.out.println("**************************");
			System.out.println("");
		}
		else {
			//add the person to a particular position
			System.out.print("Please input list posistion to add the person: ");
			int position = scanner.nextInt();
			try {
				personList.add(position, person);
				System.out.println("**************************");
				System.out.println("The person is added");
				System.out.println("**************************");
				System.out.println("");
			} catch (IndexOutOfBoundsException e) {		//if the position is wrong, display the error msg
				System.out.println("*******************************************");
				System.out.println(e.getMessage());
				System.out.println("*******************************************");
				System.out.println("");
			}
		}
	}
	
	//remove the person in particular position 
	public static void remove() {
		System.out.print("Enter the position you want to remove: ");
		int position = scanner.nextInt();
		scanner.nextLine();
		try {
			personList.remove(position);
			System.out.println("***********************************************************");
			System.out.println("The person at position: " + position + " was removed.");	
			System.out.println("***********************************************************");
			System.out.println("");
		}
		catch (IndexOutOfBoundsException e){	//if the position is wrong, display the error msg
			System.out.println("*******************************************");
			System.out.println(e.getMessage());
			System.out.println("*******************************************");
			System.out.println("");
		}
		
	}
	
	//clear all data in person list
	public static void removeAll() {
		personList.clear();
		System.out.println("***********************************************************");
		System.out.println("All the persons on the list were removed.");	
		System.out.println("***********************************************************");
		System.out.println("");
	}
	
	//display list content
	public static void displayList() {
		int numberOfEntries = personList.getLength();
		System.out.println("**********************************************************************************************");
		System.out.println("The list contains " + numberOfEntries + " entries, as follows: ");
		
		for (int position = 1; position <= numberOfEntries; position++) {
			System.out.println(position + ":");
			System.out.println("\tName: " + personList.getEntry(position).name);
			System.out.println("\tEmail: " + personList.getEntry(position).email);
			System.out.println("\tLocation: " + personList.getEntry(position).getLocation());
			
			int numberOfFriends = personList.getEntry(position).friendList.getLength();
			System.out.println("\tFriends:");
			for (int pos = 1; pos <= numberOfFriends; pos++) {	//get each item on friend list				
				System.out.println("\t\t" + personList.getEntry(position).friendList.getEntry(pos));				
			}
			System.out.println("");
			
		}
		System.out.println("**********************************************************************************************");
		System.out.println();
	}
	
	//to search a person using name or email
	//option:
	//0: search only
	//others: search and add friend to friend list
	public static void search(int option) {
		System.out.print("Please input name or email to search: ");
		String userInput = scanner.nextLine(); 
		Person foundPerson = personList.searchPerson(userInput);
		if (foundPerson != null) {
			System.out.println("**********************************************************************************************");
			System.out.println("Person found: ");
			ListInterface<String> friendList = foundPerson.getFriendList();
			System.out.println("\tName: " + foundPerson.getName());
			System.out.println("\tEmail: " + foundPerson.getEmail());
			System.out.println("\tLocation: " + foundPerson.getLocation());	
			System.out.println("\tFriends: ");	//display all the friends
			for (int i = 1; i <= friendList.getLength(); i++) {
				System.out.println("\t\t" + friendList.getEntry(i));
			}
			System.out.println("**********************************************************************************************");
			System.out.println("");
			
			if (option == 0)	//if user choose 'search only', exit the function
				return;
			else {				//otherwise let the user choose add or remove a friend
				System.out.println("Choose an option:");
				System.out.println("1. ADD a friend to the friend list");
				System.out.println("2. REMOVE a friend to the friend list");
				
				int userOption = 0;
				while (userOption != 1 && userOption != 2) {
					System.out.print("Choose an option (1 or 2):");
					userOption = scanner.nextInt();
					scanner.nextLine();		//consume any leftover newline
				}				
				
				if (userOption == 1) {		//add a friend
					System.out.print("Enter a friend's name: ");
					String friendName = scanner.nextLine();
					friendList.add(friendName);
					System.out.println("***********************");
					System.out.println("New friend added.");
					System.out.println("***********************");
					System.out.println("");
				}
				else if (userOption == 2) {	//remove a friend
					boolean foundFlag = false;
					while (foundFlag == false) {
						System.out.print("Enter a friend's name: ");
						String friendName = scanner.nextLine();
						for (int i = 1; i <= friendList.getLength(); i++) {
							if (friendList.getEntry(i).equalsIgnoreCase(friendName)) {
								friendList.remove(i);
								System.out.println("**************************************");
								System.out.println("Friend: " + friendName + " removed");
								System.out.println("**************************************");
								System.out.println("");
								foundFlag = true;
								return;
							}
						}
						System.out.println("******************************************");
						System.out.println("Cannot find this friend, please re-enter");
						System.out.println("******************************************");
						System.out.println("");
						foundFlag = false;
					}
				}
			}
		}
		else {
			System.out.println("***************************");
			System.out.println("Person not found");
			System.out.println("***************************");
			System.out.println("");
		}
	}
	
	//count the number of person in the Person list
	public static void count() {
		int numberOfEntries = personList.getLength();
		System.out.println("**********************************************************************************************");
		System.out.println("The list contains " + numberOfEntries + " people");
		System.out.println("**********************************************************************************************");
		System.out.println("");
	}
	
	//check if the Person list is empty or not
	public static void checkEmpty() {
		boolean isEmpty = personList.isEmpty();
		System.out.println("***************************");
		if (isEmpty)
			System.out.println("The list is empty");
		else
			System.out.println("The list is not empty");
		System.out.println("***************************");
		System.out.println("");		
	}	
}
