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
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		LLNode<E> current = head;
		LLNode<E> newNode = new LLNode<E>(element);
		
		while(current.next!= tail)
			current = current.next;
		
		current.next = newNode;
		newNode.next = tail;
		tail.prev = newNode;
		newNode.prev = current;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index<0 || index >=size)
			throw new IndexOutOfBoundsException();
		
		//start iterating from the First node.
		LLNode<E> current = head.next;
		for(int i=0; i<index; i++) {
			current = current.next;
		}
		
		return current.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(index<0 || index >size)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> newNode = new LLNode<E>(element);
		LLNode<E> current = head;
		
		for(int i=0; i<index; i++) {
			current = current.next;
		}
		
		newNode.next = current.next;
		current.next.prev = newNode;
		newNode.prev = current;
		current.next = newNode;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index<0 || index >=size)
			throw new IndexOutOfBoundsException();
	
		E returnElement;
		
		LLNode<E> current = head;
		
		for(int i=0; i<index; i++) {
			current = current.next;
		}
		
		returnElement = current.next.data;
		current.next.next.prev = current;
		current.next = current.next.next;
		size--;
		return returnElement;
	}
	
	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element==null)
			throw new NullPointerException("Null values not allowed in the list");
		
		if(index>=size ||size<0){
			throw new IndexOutOfBoundsException();
		}
		
		//start iterating from the first node.
		LLNode<E> current = head.next;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		
		E returnElement;
		returnElement = current.data;
		current.data = element;
		return returnElement;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
}
