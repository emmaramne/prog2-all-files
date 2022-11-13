import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bst.BinarySearchTree;

class testTree {
	private BinarySearchTree<Integer> tree;
	private BinarySearchTree<Person> persontree;

	@BeforeEach
	void setUp() throws Exception {
		tree = new BinarySearchTree();
		persontree = new BinarySearchTree();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmptyTreeSize() {
		assertEquals(0, tree.size(), "Wrong size of empty tree");
	}
	
	@Test
	void testEmptyTreeHeight() {
		assertEquals(0, tree.height(), "Wrong height of empty tree");
	}
	
	@Test
	void testTreeSize1() {
		tree.add(5);
		assertEquals(1, tree.size(), "Wrong size of tree, right size 1");
	}
	
	@Test
	void testTreeHeight1() {
		tree.add(5);
		assertEquals(1, tree.height(), "Wrong height of tree, right height 1");
	}
	
	
	@Test
	void testTreeSize5() {
		tree.add(5);
		tree.add(3);
		tree.add(1);
		tree.add(4);
		tree.add(2);
		assertEquals(5, tree.size(), "Wrong size of tree, right size 5");
	}
	
	@Test
	void testTreeHeight5() {
		tree.add(5);
		tree.add(3);
		tree.add(1);
		tree.add(4);
		tree.add(2);
		assertEquals(4, tree.height(), "Wrong height of tree, right height 4");
	}
	
	
	@Test
	void testAddDuplicate() {
		tree.add(5);
		tree.add(3);
		tree.add(5);
		assertEquals(2, tree.size(), "Wrong size of tree, right size 2");
		assertFalse(tree.add(3), "Add duplicate came back true");
	}
	
	@Test
	void testClear() {
		tree.add(5);
		tree.add(3);
		tree.add(1);
		tree.add(4);
		tree.add(2);
		tree.clear();
		assertEquals(0, tree.size(), "Wrong size of cleared tree");
		assertEquals(0, tree.height(), "Wrong height of cleared tree");
	}
	
	@Test
	void testPersonTreeHeight() {
		persontree.add(new Person("Anna", 1));
		assertEquals(1, persontree.height(), "Wrong height of tree, right height 1");
	}
	
	@Test
	void testPersonTreeSize() {
		persontree.add(new Person("Anna", 1));
		assertEquals(1, persontree.size(), "Wrong height of tree, right height 1");
	}
	
	@Test
	void testPersonTreeAdd() {
		Person a = new Person("Anna", 1);
		Person b = new Person("Bengt", 2);
		Person c = new Person("Charlie", 3);
		persontree.add(b);
		persontree.add(a);
		persontree.add(c);
		assertEquals(2, persontree.height(), "Wrong height of tree, right height 2");
		assertEquals(3, persontree.size(), "Wrong size of tree, right size 3");
	}
	
	@Test
	void testPersonTreeAddDuplicate() {
		Person a = new Person("Anna", 1);
		Person b = new Person("Bengt", 1);
		Person c = new Person("Charlie", 2);
		persontree.add(b);
		persontree.add(a);
		persontree.add(c);
		assertEquals(2, persontree.height(), "Wrong height of tree, right height 2");
		assertEquals(2, persontree.size(), "Wrong size of tree, right size 2");
	}
	
	@Test
	void testPersonTreeLambda() {
		BinarySearchTree<Person> ptree = new BinarySearchTree<Person>((p1, p2) -> p1.getName().compareTo(p2.getName()));
		Person a = new Person("Anna", 1);
		Person b = new Person("Bengt", 1);
		Person c = new Person("Charlie", 2);
		Person d = new Person("Anna", 3);
		ptree.add(b);
		ptree.add(a);
		ptree.add(c);
		ptree.add(d);
		assertEquals(2, ptree.height(), "Wrong height of tree, right height 2");
		assertEquals(3, ptree.size(), "Wrong size of tree, right size 2");
	}

}
