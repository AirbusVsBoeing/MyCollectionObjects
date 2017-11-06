package myStack.Construction1;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import myArrayList.Construction1.MyArrayList;

/*
*@author:- Elijah Einstein
*	   This is an array based implementation of stack
**/

public class MyStackArray<E> implements List<E> {
	
	MyArrayList<E> ma;
	int size;
	int modCount;
	
	public MyStackArray() {
		ma = new MyArrayList<E>();
		size =0;
		modCount=0;
	}
	
	public MyStackArray(int initialCapacity) {
		ma = new MyArrayList<E>(initialCapacity);
		size=0;
		modCount =0;
	}
	
	public E push(E e) {
		ma.add(e);
		++size;
		++modCount;
		return e;
		}
	
	public E pop () {
		if(size==0) 
			throw new EmptyStackException();
		E toReturn = ma.remove(ma.size()-1);
		--size;
		--modCount;
		return toReturn;
		
	}
	
	public E peek(){
		if (size==0)
			throw new EmptyStackException();
		return ma.get(ma.size()-1);

	}

	@Override
	public boolean add(E e) {
		
		return false;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
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
	ma.clear();
	size=0;
	}

	@Override
	public boolean contains(Object o) {
		if(this.isEmpty())
			throw new EmptyStackException();
		
		return ma.contains(o);
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
		return size;
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
	
	public MyStackArrayIterator myStackArrayIterator() {
		return new MyStackArrayIterator();
	}
	
	public class MyStackArrayIterator<E> implements Iterator<E> {
		
		int cursor;
		int expectedModcount;
		
		public MyStackArrayIterator() {
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
			if(hasNext()) {
				if(expectedModcount!=modCount)
					throw new ConcurrentModificationException();
				E toReturn = (E) ma.get(cursor);
				++cursor;
				return toReturn;
			}
			throw new NoSuchElementException();
		}
		
		public E previous() {
			if(hasPrevious()){
				if(expectedModcount!=modCount)
					throw new ConcurrentModificationException();
					
			E toReturn = (E) ma.get(cursor);
			++cursor;
			return toReturn;
			}
			throw new NoSuchElementException();
		}
		
	}
	
	public class MyStackArrayListIterator<E> implements ListIterator<E> {

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public E previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	

}
