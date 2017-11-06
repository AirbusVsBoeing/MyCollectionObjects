package singlyLinkedList.Construction1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*
 * @author:- Elijah Einstein
 * Will add an iterator soon !
 */

public class MySinglyLinkedList<E> implements List<E> {
	
	
	Node<E> head;
	Node<E> tail;
	int size;
	int modCount;
	
	
	public void unlinkAndAdd(Node beforeIndex, Node toAdd) {
		Node<E> temp = beforeIndex.next;
		beforeIndex.next=toAdd;
		toAdd.next = temp;
		++size;
		if(temp.equals(tail)){
			tail = temp;
			tail.next=null;
		}
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
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
