package myArrayList.Construction1;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
*@author:- Elijah Einstein.
* 	   
*	   This list object has a myArrayListIterator() which returns a MyArrayListIterator Object. This iterator object implements 
*	    Iterator interface. 
*
*/

public class MyArrayList<E> implements List<E> {
	
	
	
	
	private static final int defaultCapacity = 10;
	
	
	Object elementData[];
	
	int size;
	
	int modCount;
	
	
	public MyArrayList() {
		elementData = new Object[defaultCapacity];
	}
	
	public MyArrayList(int initialCapacity) {
		if (initialCapacity<0) {
			throw new IllegalArgumentException();
		}
		
		elementData = new Object[initialCapacity];
	}
	
	public void ensureCapacity() {
		
		if(elementData.length==size) {
			Object [] copy = copyElementData(elementData);
			elementData = new Object[(copy.length*3/2)+1];
			for (int i=0;i<copy.length;i++) {
				elementData[i] = copy[i];
			}
		}
	}

	public void shiftElementsBackward(int index) {
	
		for (int i = index; i <size-1;i++) {
			elementData[i] = elementData[i+1];
		}
	}
	
	public void shiftElementsForward(int index){
		
		ensureCapacity();
		for (int i=size;i>index;i--) {
			elementData[size] = elementData[size-1];
		}
	}
	
	public Object[] copyElementData(Object[] o) {
		Object toReturn [] = new Object [size];
 		for (int i = 0; i<size;i++) {
 			toReturn[i] = o[i];
		}
 		return toReturn;
	}
	

	@Override
	public boolean add(E e) {
		ensureCapacity();
		elementData[size++] = e;
		modCount++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if(index>size || index<0)
			throw new IllegalArgumentException();
		
		shiftElementsForward(index);
		elementData[index] = element;
		size++;
		modCount++;
		
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
			int currentSize = elementData.length;
			elementData = new Object[currentSize];
			size = 0;
			modCount++;
	}

	@Override
	public boolean contains(Object o) {
		for (int i=0;i<elementData.length;i++) {
			if (o ==null) {
				if (elementData[i] == null) {
					return true;
				}
			}
			else {
				if (o.equals(elementData[i])){
					return true;
				}
			}
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
		if (index>size-1 || index <0) {
			throw new IllegalArgumentException();
		}
		
		return (E)elementData[index];
	}

	@Override
	public int indexOf(Object o) {
		for (int i=0;i<elementData.length;i++) {
			if(o==null) {
				if(elementData[i]==null) {
					return i;
				}
			}
			else if (o.equals(elementData[i])) {
				return i;
			}
		}
		
			return -1;
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
	
		for (int i=size-1;i>=0;i--) {
			if(o==null){
				if(elementData[i]==null) {
					return i;
				}
			}
		else if (o.equals(elementData[i])) {
			return i;
		}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		
		return null;
	}

	

	@Override
	public boolean remove(Object o) {
		
		remove(indexOf(o));
		
		return true;
	}

	@Override
	public E remove(int index) {
		if (index>size-1 || index<0) 
			throw new IllegalArgumentException();	
			
		E toReturn  =  (E)elementData[index];
		shiftElementsBackward(index);
		size--;
		modCount--;
		return toReturn;
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		return false;
	}

	@Override
	public E set(int index, E element) {
		if(index>size-1 || index ==0) 
			throw new IllegalArgumentException();
		
		elementData[index] = element;
		modCount++;
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if(fromIndex>size-1 || fromIndex<0 || toIndex>size-1 || toIndex<0 ||fromIndex>toIndex) 
			throw new IllegalArgumentException();
		
		List l = new MyArrayList();
		for(int i=fromIndex; i<=toIndex;i++) {
			l.add(elementData[i]);
		}
		
		
		
		return l;
	}

	@Override
	public Object[] toArray() {
		
		/*Object [] toReturn = new Object [size-1];
		for(int i=0;i<size;i++) 
			toReturn[i] = elementData[i]; */
		
		return copyElementData(elementData);
	}

	@Override
	public <T> T[] toArray(T[] a) {
	
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		
		return null;
	}
	
	public MyArrayListIterator<E> myArrayListIterator() {
		return new MyArrayListIterator<E>();
	}
	
	private class MyArrayListIterator<E> implements Iterator<E> {
		
		int cursor;
		int expectedModcount;
		
		public MyArrayListIterator() {
			cursor = 0;
			expectedModcount = modCount;
		}

		@Override
		public boolean hasNext() {
			
			return cursor<size;
		}
		
		public boolean hasPrevious() {

			return cursor>=0;
		}

		@Override
		public E next() {
			
			
			if(hasNext()){
			if(modCount!=expectedModcount) 
				throw new ConcurrentModificationException();
			E toReturn = (E)elementData[cursor];
				cursor+=1;
				return toReturn;
			}
			
			throw new NoSuchElementException();
		}
		
		public E previous() {
			
			if(hasPrevious()){
				if(modCount!=expectedModcount) 
					throw new ConcurrentModificationException();
				E toReturn = (E) elementData[cursor];
				cursor-=1;
				return toReturn;
			}
			throw new NoSuchElementException();
		}
		
	}
	

}
