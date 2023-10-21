/**
 * 
 */
package sunnyA1_3475_002;


/**
 * Node class to represent a node of linked list
 * @author SunnyLam
 * 
 * @param <T>
 * 
 */
public class Node<T> {
	private T data;			//Entry in bag
	private Node<T> next;	//Link to next node
	
	//constructor
	Node(T dataPortion) {
		this(dataPortion, null);
	}
	
	//constructor with a pointer pointing to next node
	private Node(T dataPortion, Node<T> nextNode) {
		data = dataPortion;
		next = nextNode;
	}	

	
	Node<T> getNextNode() {
		return next;
	}
	
	void setNextNode(Node<T> nextNode) {
		next = nextNode;
	}
	
	T getData() {
		return data;
	}
	
	void setData(T newData) {
		data = newData;
	}
}
