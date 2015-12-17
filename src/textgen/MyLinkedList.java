package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		// TODO: Implement this method
		if (element == null) {
			throw new NullPointerException("null element");
		}
		LLNode<E> toAdd = new LLNode<E>(element);
		LLNode<E> oldLast = tail.prev;
		toAdd.next = tail;
		toAdd.prev = oldLast;
		tail.prev = toAdd;
		oldLast.next = toAdd;
		size ++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		LLNode<E> curr = new LLNode<E>(null);
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index out of bounds");
		else {
			curr = head.next;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
		}
		return curr.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index out of bounds");
		else if (element == null)
			throw new NullPointerException("null element");
		else {
			LLNode<E> curr = head.next;
			LLNode<E> toAdd = new LLNode<E>(element);
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			LLNode<E> previous = curr.prev;
			toAdd.next = curr;
			toAdd.prev = previous;
			previous.next = toAdd;
			curr.prev = toAdd;
			size ++;
		}
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		LLNode<E> curr = new LLNode<E>(null);
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index out of bounds");
		else {
			curr = head.next;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
			curr.next = null;
			curr.prev = null;
			size --;
		}
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException, NullPointerException
	{
		// TODO: Implement this method
		E oldData = null;
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index out of bounds");
		else if (element == null)
			throw new NullPointerException("null element");
		else {
			LLNode<E> curr = head.next;
			for (int i = 0; i < index; i++) {
				curr = curr.next;
			}
			oldData = curr.data;
			curr.data = element;
		}
		return oldData;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
