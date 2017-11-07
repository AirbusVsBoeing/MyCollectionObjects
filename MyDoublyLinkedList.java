package myDoublyLinkedList.Construction1;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * author:- Elijah Einstein
 */

public class MyDoublyLinkedList<E> implements List<E> {
	
	Node<E> head;
	Node<E> tail;
	private int size;
	private int modCount;
	
	private E unlinkAndRemove(Node<E>beforeIndex) {
		E toReturn = beforeIndex.next.element;
		Node<E> temp = beforeIndex.next.next;
		beforeIndex.next = temp;
		temp.previous=beforeIndex;
		--size;
		++modCount;
		return toReturn;
	}
	
	private void unlinkAndAdd(Node<E> beforeIndex, Node<E> toAdd) {
		Node<E> temp = beforeIndex.next;
		beforeIndex.next = toAdd;
		toAdd.previous = beforeIndex;
		toAdd.next=temp;
		temp.previous = toAdd;
		++size;
		++modCount;
	}

	@Override
	public boolean add(E e) {
		Node<E> newNode = new Node<E> (e);
		if(head==null){
			head = newNode;
			tail = newNode;
			++size;
			++modCount;
			return true;
		}
		if(size==1) {
			head.next = newNode;
			tail = newNode;
			tail.previous = head;
			++size;
			++modCount;
			return true;
		}
		tail.next = newNode;
		newNode.previous = tail;
		tail = newNode;
		++size;
		++modCount;
		return false;
	}

	@Override
	public void add(int index, E element) {
		int count =1;
		Node<E> newNode = new Node<E> (element);
		if(index>size-1||index<0)
			throw new IllegalArgumentException();
		if(index==0){
			newNode.next = head;
			head.previous = newNode;
			head = newNode;
			++size;
			++modCount;
		}
		
		Node<E> current = head;
		while(current.next!=null) {
			if(count==index) {
				unlinkAndAdd(current,newNode); // increments size and modCount
			}
			++count;
			current = current.next;
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
		head = null;
		size=0;
		++modCount;
		
	}

	@Override
	public boolean contains(Object o) {
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
	
		Node<E> current = head;
		while(current!=null) {
			if(current.element==null && o==null)
				return true;
			else if(current.element.equals(o))
				return true;
			current=current.next;
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
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
		if(index>size-1||index<0)
			throw new IllegalArgumentException();
		int count = 0;
		Node<E> current = head;
		while(current!=null){
			if(count==index)
				return current.element;
			++count;
			current = current.next;
		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
		int count =0;
		Node<E> current = head;
		while(current.next!=null) {
			if(current.element==null&&o==null)
				return count;
			else if (current.element.equals(o))
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
		remove(indexOf(o));
		return true;
	}

	@Override
	public E remove(int index) {
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
		if(index>size-1||index<0)
			throw new IllegalArgumentException();
		E toReturn = null;
		if(index==0) {
			toReturn = head.element;
			head=head.next;
			head.previous=null;
			--size;
			++modCount;
			return toReturn;
		}
		if(index==size-1) {
			toReturn = tail.element;
			tail= tail.previous;
			tail.next= null;
			--size;
			++modCount;
			return toReturn;
		}
		int count = 1;
		Node<E> current = head;
		while(current!=null) {
			if(count==index)
				return unlinkAndRemove(current); //decrements size and increments modCount
			++count;
			current = current.next;

		}
		return toReturn;
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
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
		if(index>size-1||index<0)
			throw new IllegalArgumentException();
		int count = 0;
		E toReturn = null;
		Node<E> current = head;
		while(current!=null) {
			if(index==count) {
				toReturn = current.element;
				current.element = element;
				}
			
			++count;
			current=current.next;
		}
		return toReturn;
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
		if(head==null||size==0)
			throw new EmptyMyDoublyLinkedListException("");
		
		Object [] toReturn = new Object[size];
		Node<E> current = head;
		while(current.next!=null){
			int count = 0;
			toReturn[count] = current.element;
			++count;
			current = current.next;
		}
		
		return toReturn;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MyDoublyLinkedList outer() {
		return this;
	}
	
	public MyDoublyLinkedListIterator<E> myDoubleLinkedListIterator(){
		return new MyDoublyLinkedListIterator();
	}
	
	public class Node<E> {
		E element;
		Node<E> next;
		Node<E> previous;
		public Node(E e) {
			element = e;
		}
	}
	
	public class MyDoublyLinkedListIterator<E> implements ListIterator<E> {
		
		
		int expectedModcount;
		Node<E> current;
		
		public MyDoublyLinkedListIterator() {
			expectedModcount = modCount;
			current = outer().head;
		}

		@Override
		public void add(E arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() {
			if(current.next!=null)
				return true;
			return false;
		}

		@Override
		public boolean hasPrevious() {
			if(current.previous!=null)
				return true;
			return false;
		}

		@Override
		public E next() {
			if(hasNext()){
				if(expectedModcount!=modCount)
					throw new ConcurrentModificationException();
				E toReturn = current.element;
				current = current.next;
				return toReturn;
				
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			if(current.equals(outer().head)) 
				return 1;
			else if(current.equals(outer().tail.previous))
			return size()-1;
			else if(current.equals(outer().tail))
				throw new IllegalStateException();
			
			return indexOf(current.next);
		}

		@Override
		public E previous() {
			if(hasPrevious()){
				if(expectedModcount!=modCount)
					throw new ConcurrentModificationException();
				E toReturn = current.element;
				current = current.previous;
				return toReturn;
			}
			throw new NoSuchElementException();
		}

		@Override
		public int previousIndex() {
			if(current.equals(outer().head))
					throw new IllegalStateException();
			else if (current.equals(outer().tail))
				return size()-2;
			
			return indexOf(current.previous);
		}

		@Override
		public void remove() {
			if(current.equals(outer().head)) {
				outer().head = outer().head.next;
				outer().head.previous = null;
			}
			else	if(current.equals(outer().tail)) {
				outer().tail = outer().tail.previous;
				outer().tail.next = null;
			}
			else {
				outer().unlinkAndRemove(current.previous);
			}
		}

		@Override
		public void set(E arg0) {
			current.element = arg0;
			}
		
	}
	
	
}