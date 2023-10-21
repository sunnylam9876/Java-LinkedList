/**
 * 
 */
package sunnyA1_3475_002;

/**
 * The interface for linked list
 * @author SunnyLam
 * 
 * @param <T>
 */
public interface ListInterface<T> {
	public void add(T newEntry);
	public void add(int newPosition, T newEntry);
	public T remove(int givenPosition);
	public void clear();
	public T replace(int givenPosition, T newEntry);
	public T getEntry(int givenPosition);
	public T[] toArray();
	public boolean contains(T anEntry);
	public int getLength();
	public boolean isEmpty();
	public Person searchPerson(String query);
}
