package bst;

import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> ccomparator;

	public static void main(String[] args) {
		Random rand = new Random();
		BinarySearchTree<Integer> inttree = new BinarySearchTree<Integer>();
		for (int i = 0; i < 23; i++) {
			inttree.add(rand.nextInt(100));
		}

		BinarySearchTree<Person> ptree1 = new BinarySearchTree<Person>();
		ptree1.add(new Person("Anna", 4));
		ptree1.add(new Person("Bengt", 3));
		ptree1.add(new Person("Charlie", 3));
		ptree1.add(new Person("Daniella", 2));
		ptree1.add(new Person("Anna", 1));
		BinarySearchTree<Person> ptree2 = new BinarySearchTree<Person>(
				(p1, p2) -> p1.getName().compareTo(p2.getName()));
		ptree2.add(new Person("Anna", 4));
		ptree2.add(new Person("Bengt", 3));
		ptree2.add(new Person("Charlie", 3));
		ptree2.add(new Person("Daniella", 2));
		ptree2.add(new Person("Anna", 1));

		BinarySearchTree<Person> ptree3 = new BinarySearchTree<Person>();
		ptree3.add(new Person("Anna", rand.nextInt(100)));
		ptree3.add(new Person("Bengt", rand.nextInt(100)));
		ptree3.add(new Person("Charlie", rand.nextInt(100)));
		ptree3.add(new Person("Daniella", rand.nextInt(100)));
		ptree3.add(new Person("Elias", rand.nextInt(100)));
		ptree3.add(new Person("Fanny", rand.nextInt(100)));
		ptree3.add(new Person("Göran", rand.nextInt(100)));
		ptree3.add(new Person("Hanna", rand.nextInt(100)));
		ptree3.add(new Person("Ivar", rand.nextInt(100)));
		ptree3.add(new Person("Johanna", rand.nextInt(100)));
		ptree3.add(new Person("Khris", rand.nextInt(100)));
		ptree3.add(new Person("Linnea", rand.nextInt(100)));

		BSTVisualizer visual1 = new BSTVisualizer("Binary Search Tree 1", 500, 500);
		BSTVisualizer visual2 = new BSTVisualizer("Binary Search Tree 2", 600, 500);
		BSTVisualizer visual3 = new BSTVisualizer("Binary Search Tree 3", 300, 300);
		BSTVisualizer visual4 = new BSTVisualizer("Binary Search Tree 4", 300, 300);
		BSTVisualizer visual5 = new BSTVisualizer("Binary Search Tree 5", 400, 400);
		BSTVisualizer visual6 = new BSTVisualizer("Binary Search Tree 6", 400, 400);
		visual1.drawTree(inttree);
		inttree.rebuild();
		visual2.drawTree(inttree);
		visual3.drawTree(ptree1);
		visual4.drawTree(ptree2);
		visual5.drawTree(ptree3);
		ptree3.rebuild();
		visual6.drawTree(ptree3);
		System.out.println(inttree.size());

	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		ccomparator = (e1, e2) -> ((Comparable) e1).compareTo(e2);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		ccomparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(x, root);
	}

	private boolean add(E x, BinaryNode<E> n) {
		if (n == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		int compresult = ccomparator.compare(n.element, x);
		if (compresult > 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(x, n.left);
			}
		} else if (compresult == 0){ 
			return false;
		} else {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(x, n.right);
			}
		}
			
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		}
		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element.toString());
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		if (!(sorted.size() == 0)) {
			root = buildTree(sorted, 0, sorted.size() - 1);
		}
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		int mid = (first + last) / 2;
		if (first == last) {
			return new BinaryNode<E>(sorted.get(last));
		}
		BinaryNode<E> newNode = new BinaryNode<E>(sorted.get(mid));
		if (mid > first) {
			newNode.left = buildTree(sorted, first, mid - 1);
		}
		newNode.right = buildTree(sorted, mid + 1, last);
		return newNode;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
