package ds.module1;

import java.util.NoSuchElementException;

/**
 * @author Elijah Einstein
 * 			This BST has no parent node reference!
 *
 * @param <E> is a generic type that must be a class that implements Comparable interface
 */
public class BST<E extends Comparable> {
	
	

	private class Node<E extends Comparable> {
		E element;
		Node<E> right, left;

		Node(E element) {
			this.element = element;
		}
	}

	private Node<E> root;
	private int size, modCount;

	public void insert(E toInsert) {
		if (root == null) {
			root = new Node<E>(toInsert);
			++size;
			++modCount;
		} else {

			if (toInsert.compareTo(root.element) == 0)
				throw new UnsupportedOperationException();
			else if (toInsert.compareTo(root.element) > 0) {
				if (root.right == null) {
					root.right = new Node<E>(toInsert);
					++size;
					++modCount;
				} else
					insert(root.right, toInsert);
			} else {
				if (root.left == null) {
					root.left = new Node<E>(toInsert);
					++size;
					++modCount;
				} else
					insert(root.left, toInsert);
			}

		}
	}

	public void insert(Node<E> node, E toInsert) {
		if (toInsert.compareTo(node.element) == 0)
			throw new UnsupportedOperationException();
		else if (toInsert.compareTo(node.element) > 0) {
			if (node.right == null) {
				node.right = new Node<E>(toInsert);
				++size;
				++modCount;
			} else
				insert(node.right, toInsert);
		} else {
			if (node.left == null) {
				node.left = new Node<E>(toInsert);
				++size;
				++modCount;
			} else
				insert(node.left, toInsert);
		}
	}

	private Node<E> returnNode(Node<E> node, E toFind) {
		if (toFind.compareTo(node.element) == 0)
			return node;
		else if (toFind.compareTo(node.element) > 0)
			return returnNode(node.right, toFind);
		else if (toFind.compareTo(node.element) < 0)
			return returnNode(node.left, toFind);
		throw new NoSuchElementException();
	}

	public void delete(E toDelete) {
		Node<E> node = returnNode(root, toDelete);
		if (node == root) {
			if (node.right == null && node.left == null) {
				root = null;
				--size;
				++modCount;
			} else if (node.right != null && node.left == null) {
				root = node.right;
				--size;
				++modCount;
			} else if (node.right == null && node.left != null) {
				root = node.left;
				--size;
				++modCount;
			} else if (node.right != null && node.left != null) {
				Node<E> current = node.right;
				if (current.left != null) {
					while (current.left.left != null)
						current = current.left;
					Node<E> temp = current.left;
					node.element = temp.element;
					current.left = null;
					--size;
					++modCount;
				} else {
					Node<E> temp = current.right;
					node.element = current.element;
					node.right = temp;
					--size;
					++modCount;
				}
			}
		} else {
			Node<E> parentNode = parentNode(root, toDelete);
			Node<E> toRemove = returnNode(root, toDelete);
			if (toRemove.right == null && toRemove.left == null) {
				if (parentNode.right == toRemove)
					parentNode.right = null;
				else
					parentNode.left = null;
				--size;
				++modCount;
			} else if (toRemove.right != null && toRemove.left == null) {
				Node<E> current = toRemove.right;
				if (current.left != null) {
					while (current.left.left != null)
						current = current.left;
					Node<E> temp = current.left;
					toRemove.element = temp.element;
					current.left = null;
					--size;
					++modCount;
				} else {
					if (parentNode.right == toRemove)
						parentNode.right = current;
					else
						parentNode.left = current;
					--size;
					++modCount;
				}
			} else if (toRemove.right == null && toRemove.left != null) {
				Node<E> current = toRemove.left;
				if (current.right != null) {
					while (current.right.right != null)
						current = current.right;
					Node<E> temp = current.right;
					toRemove.element = temp.element;
					current.right = null;
					--size;
					++modCount;
				} else {
					if (parentNode.right == toRemove)
						parentNode.right = current;
					else
						parentNode.left = current;
					--size;
					++modCount;
				}
			} else {
				Node<E> current = toRemove.right;
				if (current.left != null) {
					while (current.left.left != null)
						current = current.left;
					Node<E> temp = current.left;
					toRemove.element = temp.element;
					current.left = null;
					--size;
					++modCount;
				} else {
					if (parentNode.right == toRemove)
						parentNode.right = current;
					else
						parentNode.left = current;
					--size;
					++modCount;
				}
			}
		}
	}
	
	public boolean contains(E elem){
		return this.returnNode(root, elem)!=null;
	}

	private Node<E> parentNode(Node<E> node, E elem) {
		if (node == null)
			throw new NoSuchElementException();
		if (elem.compareTo(node.element) > 0) {
			if (node.right != null && elem.compareTo(node.right.element) == 0)
				return node;
			else
				return parentNode(node.right, elem);
		} else if (elem.compareTo(node.element) < 0) {
			if (node.left != null && elem.compareTo(node.left.element) == 0)
				return node;
			else
				return parentNode(node.left, elem);
		} else
			throw new UnsupportedOperationException();
	}

}
