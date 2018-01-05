package myBinarySearchTree.Construction2;

/**
 * @author Elijah Einstein ENJOY !!
 * 
 */
public class BinarySearchTree<E extends Comparable> {

	private class Node<E extends Comparable> {
		E element;
		Node<E> right;
		Node<E> left;
		Node<E> parent;

		Node(E element) {
			this.element = element;
		}
	}

	private Node<E> root;
	private int size;
	private int modCount;

	public boolean insert(E toInsert) {
		if (root == null) {
			root = new Node(toInsert);
			++size;
			++modCount;
			return true;
		}
		if (toInsert.compareTo(root.element) > 0) {
			if (root.right == null) {
				root.right = new Node(toInsert);
				++size;
				++modCount;
				return true;
			}
			return insert(root.right, toInsert);
		}
		if (toInsert.compareTo(root.element) < 0) {
			if (root.left == null) {
				root.left = new Node(toInsert);
				++size;
				++modCount;
				return true;
			}
			return insert(root.left, toInsert);
		}
		if (toInsert.compareTo(root.element) == 0) {
			throw new UnsupportedOperationException();
		}

		return false;

	}

	private boolean insert(Node<E> node, E toInsert) {
		if (toInsert.compareTo(node.element) > 0) {
			if (node.right == null) {
				node.right = new Node(toInsert);
				++size;
				++modCount;
				return true;
			}
			return insert(node.right, toInsert);
		}
		if (toInsert.compareTo(node.element) < 0) {
			if (node.left == null) {
				node.left = new Node(toInsert);
				++size;
				++modCount;
				return true;
			}
			return insert(node.left, toInsert);
		}
		if (toInsert.compareTo(node.element) == 0)
			throw new UnsupportedOperationException();

		return false;

	}

	public boolean contains(E elem) {
		if (size == 0 || root == null)
			throw new IllegalArgumentException();
		if (root.element.compareTo(elem) == 0)
			return true;
		if (elem.compareTo(root.element) > 0) {
			if (root.right == null)
				return false;
			return contains(root.right, elem);
		}
		if (elem.compareTo(root.element) < 0) {
			if (root.left == null)
				return false;
			return contains(root.left, elem);
		}
		return false;
	}

	private boolean contains(Node<E> node, E elem) {
		if (elem.compareTo(node.element) == 0)
			return true;
		if (elem.compareTo(root.element) > 0) {
			if (node.right == null)
				return false;
			return contains(node.right, elem);
		}
		if (elem.compareTo(node.element) < 0) {
			if (node.left == null)
				return false;
			return contains(root.left, elem);
		}
		return false;
	}

	public Node<E> search(E elem) {
		if (!contains(elem))
			throw new IllegalArgumentException();
		if (root.element.compareTo(elem) == 0)
			return root;
		if (elem.compareTo(root.element) > 0)
			return search(root.right, elem);
		if (elem.compareTo(root.element) < 0)
			return search(root.left, elem);

		return null;
	}

	private Node<E> search(Node<E> node, E elem) {
		if (node.element.compareTo(elem) == 0)
			return node;
		if (elem.compareTo(node.element) > 0)
			return search(node.right, elem);
		if (elem.compareTo(node.element) < 0)
			return search(node.left, elem);

		return null;
	}

	public boolean remove(E elem) {
		Node<E> toRemove = search(elem);
		if (root == toRemove) {
			root = null;
			--size;
			++modCount;
			return true;
		}
		if (toRemove.right == null && toRemove.left == null) {
			Node<E> parentNode = toRemove.parent;
			if (parentNode.right == toRemove)
				parentNode.right = null;
			else
				parentNode.left = null;

			--size;
			++modCount;
			return true;
		}

		if (toRemove.right != null && toRemove.left == null) {
			Node<E> parent = toRemove.parent;
			if (parent.right == toRemove)
				parent.right = toRemove.right;
			else
				parent.left = toRemove.right;
			--size;
			++modCount;
			return true;
		}

		if (toRemove.left != null && toRemove.right == null) {
			Node<E> parent = toRemove.parent;
			if (parent.right == toRemove)
				parent.right = toRemove.left;
			else
				parent.left = toRemove.left;
			--size;
			++modCount;
			return true;
		}

		if (toRemove.left != null && toRemove.right != null) {
			Node<E> current = toRemove.right;
			while (current != null)
				current = current.left;
			Node<E> currentsParent = current.parent;
			toRemove.element = current.element;

			if (currentsParent.right == current)
				currentsParent.right = null;
			else
				currentsParent.left = null;
			--size;
			++modCount;
			return true;
		}
		return false;

	}

	public int size() {
		return size;
	}
	
	
	public int height() {
		return height(root);
	}
	
	
	private int height(Node<E> node) {
		if(node==null)
			return 0;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		int height = 0;
		if(leftHeight>rightHeight)
			height = 1+leftHeight;
		else
			height = 1+rightHeight;
		
		return height;
	}
	
	

}
