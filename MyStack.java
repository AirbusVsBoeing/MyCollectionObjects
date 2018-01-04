package myStack.Construction1;

import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;

/**
 * @author Elijah Einstein 
 * This is a node based stack.
 * 
 */
public class MyStack<E> {

	private class Node<E> {
		E element;
		Node<E> below;

		Node(E element) {
			this.element = element;
		}
	}

	private Node<E> top;
	private int size;
	private int modCount;

	public boolean push(E e) {
		if (top == null) {
			top = new Node(e);
			++size;
			++modCount;
			return true;
		}
		Node<E> newNode = new Node<E>(e);
		newNode.below = top;
		top = newNode;
		++size;
		++modCount;
		return true;
	}

	public E pop() {
		if (size == 0 || top == null)
			throw new EmptyStackException();
		if (size == 1) {
			E toReturn = top.element;
			top = null;
			--size;
			++modCount;
			return toReturn;
		}
		E toReturn = top.element;
		top = top.below;
		--size;
		++modCount;
		return toReturn;
	}

	public E peek() {
		if (size == 0 || top == null)
			throw new EmptyStackException();
		return top.element;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	private MyStack outer() {
		return this;
	}

	public StackIterator<E> stackIterator() {
		return new StackIterator<E>();
	}

	class StackIterator<E> {
		int expectedModcount;
		Node<E> current;

		StackIterator() {
			expectedModcount = modCount;
			current = outer().top;
		}

		public boolean hasElement() {
			return current != null;
		}

		public E element() {
			if (modCount != expectedModcount)
				throw new ConcurrentModificationException();
			if (hasElement()) {
				E toReturn = (E) current.element;
				current = current.below;
				return toReturn;
			}
			throw new IllegalArgumentException();
		}
	}

}
