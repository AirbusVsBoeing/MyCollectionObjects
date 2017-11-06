package singlyLinkedList.Construction1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * @author:- Elijah Einstein
 */

public class MySinglyLinkedList<E> implements List<E> {
	
	
	Node<E> head;
	Node<E> tail;
	private int size;
	private int modCount;
	
	
	private void unlinkAndAdd(Node beforeIndex, Node toAdd) {
		Node<E> temp = beforeIndex.next;
		beforeIndex.next=toAdd;
		toAdd.next = temp;
		++size;
		if(temp.equals(tail)){
			tail = temp;
			tail.next=null;
		}
	}
	
	private void unlinkAndRemove(Node beforeIndex) {
		Node<E> temp = beforeIndex.next;
		Node<E> next = temp.next;
		beforeIndex.next = next;
		--size;
	}

	@Override
	public boolean add(E e) {
		Node<E> newElement = new Node<E>(e);
		if(head==null) {
			head = newElement;
			tail = head;
			tail.next = null;
			++size;
			return true;
		}
		if(size==1) {
			head.next = newElement;
			tail = newElement;
			tail.next = null;
			++size;
			return true;
		}
		
		tail.next = newElement;
		tail = newElement;
		tail.next = null;
		++size;
		return true;
	}

	@Override
	public void add(int index, E element) {
		int count=1;
		Node <E> newElement = new Node<E>(element);
		if(index>size || index<0)
			throw new IllegalArgumentException();
		
		else if(index==0) {
			newElement.next = head;
			head = newElement;
			size++;
		}
		else if(index==size){
			tail.next= newElement;
			tail = newElement;
			size++;
		}	
		
		else {
			Node<E> current = head;
			while(current!=null) {
				if(index==count){ 
					unlinkAndAdd(current,newElement);
					break;
				}
				current = current.next;
					++count;
			}
		}
		
		
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object o) {
		if(head==null || size==0)
		throw new EmptyMySinglyLinkedListException("");
		
		Node<E> current = head;
		while(current!=null) {
			if(o==null && current.element==null) 
				return true;
			else if (o.equals(current.element))
				return true;
			current = current.next;
		}
		
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		int count = 0;
		if(head==null||size==0) 
			throw new EmptyMySinglyLinkedListException("");
		else if(index>size-1 || index<0)
			throw new IllegalArgumentException();
		Node<E> current = head;
		while(current!=null) {
			if(count==index)
				return current.element;
			++count;
			current = current.next;
		}
		
		return null;
	}

	@Override
	public int indexOf(Object o) {
		int count =0;
		if(head==null||size==0) 
		throw new EmptyMySinglyLinkedListException("");
		Node<E> current = head;
		while(current!=null) {
			if(o==null && current.element==null)
				return count;
			else if(o.equals(current.element))
				return count;
			++count;
			current = current.next;
		}
		
		throw new NoSuchElementException();
	}

	@Override
	public boolean isEmpty() {
		
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		if(head==null||size==0)
			throw new EmptyMySinglyLinkedListException("");
		else if(!contains(o))
			throw new NoSuchElementException();
		remove(indexOf(o));
		
		
		return true;
	}

	@Override
	public E remove(int index) {
		int count =1;
		if(head==null||size==0)
			throw new EmptyMySinglyLinkedListException("");
		else if(index>size-1||index<0)
			throw new IllegalArgumentException();
		if(index ==0){ 
			E toReturn = head.element;
			head=head.next;
			--size;
			return toReturn;
		}
		
		Node<E> current = head;
		while(current!=null) {
			if(index==count) {
				unlinkAndRemove(current); // decreases size;
			}
			++count;
			current = current.next;
		}
		
		
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E set(int index, E element) {
		int count=0;
		if(head==null|| size==0)
			throw new EmptyMySinglyLinkedListException("");
		
		
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public class Node<E>{
		E element;
		Node<E> next;
		
		public Node(E e) {
			element = e;
		}
		
	}

}
