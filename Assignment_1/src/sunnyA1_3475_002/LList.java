/**
 * 
 */
package sunnyA1_3475_002;

/**
 * LList class implementation of ListInterface
 * @author SunnyLam
 * 
 * @param <T>
 */
public class LList<T> implements ListInterface<T> {
	private Node<T> firstNode;
	private int numberOfEntries;
	
	//constructor
	public LList() {
		initializeDataFields();
	}
	
	public void clear() {
		initializeDataFields();
	}
	
	//initializes the class's data fields to indicate an empty list
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	// Returns a reference to the node at a given position.
	// Precondition: The chain is not empty;
	//               1 <= givenPosition <= numberOfEntries.
	private Node<T> getNodeAt(int givenPosition) {
	// Assertion: (firstNode != null) &&
	//            (1 <= givenPosition) && (givenPosition <= numberOfEntries)
		Node<T> currentNode = firstNode;
	      
	    // Traverse the chain to locate the desired node
	    // (skipped if givenPosition is 1)
	    for (int counter = 1; counter < givenPosition; counter++)
	       currentNode = currentNode.getNextNode();
	    // Assertion: currentNode != null
	    return currentNode;
	} // end getNodeAt
	
	public void add(T newEntry) {	//add a new node to the end of list
		
		Node<T> newNode = new Node<T>(newEntry);
		
		if (isEmpty())
			firstNode = newNode;
		else {	//add to end of nonempty list
			Node<T> lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);	//Make last node reference new node
		}
		numberOfEntries++;		
	}
	
	public void add(int givenPosition, T newEntry) {	//add a new node to a given position
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
			Node<T> newNode = new Node<T> (newEntry);
			if (givenPosition == 1) {	//case 1: new list
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else {		//case 2: list is not empty and givenPosition > 1
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			}
			numberOfEntries++;
		}
		else
			throw new IndexOutOfBoundsException("Add operation failed, the position is out of bound.");
	}

	public boolean isEmpty() {	//check if the list is empty or not, return true if empty
		boolean result;
		
		if (numberOfEntries == 0) {
			result = true;
		}
		else {
			result = false;
		}		
		return result;
	}
	
	public T[] toArray() {		//convert the linked list to an array
		@SuppressWarnings("unchecked")
		T[] result = (T[])new Object[numberOfEntries];
		
		int index = 0;
		Node<T> currentNode = firstNode;
		
		while((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.getData();
			currentNode = currentNode.getNextNode();
			index++;
		}
		
		return result;
	}
	
	public T remove(int givenPosition) {	//remove a node from a given position
		T result = null;	//Return value
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			if (givenPosition == 1) {	//case 1: remove first entry
				result = firstNode.getData();	//save entry to be removed
				firstNode = firstNode.getNextNode();
			}
			else {
				Node<T> nodeBefore = getNodeAt(givenPosition - 1);
				Node<T> nodeToRemove = nodeBefore.getNextNode();
				result = nodeToRemove.getData();	//save entry to be removed
				Node<T> nodeAfter = nodeToRemove.getNextNode();
				nodeBefore.setNextNode(nodeAfter);	//remove entry
			}
			numberOfEntries--;	//update count
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Remove operation failed, the position is out of bound.");		
	}
	
	public T replace(int givenPosition, T newEntry) {	//replace a node at a given position
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			Node<T> desiredNode = getNodeAt(givenPosition);
			T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
			return originalEntry;
		}
		else
			throw new IndexOutOfBoundsException("Replace operation failed, the position is out of bound.");
	}
	
	public int getLength() {	//get the length of linked list
		return numberOfEntries;
	}
	
	public T getEntry(int givenPosition) {		//get the content of the linked list in a given position
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			return getNodeAt(givenPosition).getData();
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	}
	
	public boolean contains(T anEntry) {		//check if the content is existed
		boolean found = false;
		Node<T> currentNode = firstNode;
		
		while (!found && (currentNode != null)) {
			if (anEntry.equals(currentNode.getData())) 
				found = true;
			else
				currentNode = currentNode.getNextNode();
		}
		return found;
	}
	
	public Person searchPerson(String query) {	//search for a person in Person list
		Node<T> currentNode = firstNode;
		while (currentNode != null) {
			Person person = (Person) currentNode.getData();
			if (person.getName().equalsIgnoreCase(query) || person.getEmail().equalsIgnoreCase(query)) {
				return person;
			}
			currentNode = currentNode.getNextNode();
		}
		return null;
	}
	
}
