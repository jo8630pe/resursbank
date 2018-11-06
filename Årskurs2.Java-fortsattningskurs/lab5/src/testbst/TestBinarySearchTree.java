package testbst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;

public class TestBinarySearchTree {
	private BinarySearchTree<Integer> bst;
	
	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		bst = null;
	}
	
	/** Test av height */
	@Test
	public final void testHeight() {
		for(int i = 1; i<6; i++) {
			bst.add(i);
		}
		assertTrue(bst.height() == 5);
	}
	
	/** Test av height balanserat */
	@Test
	public final void testHeightBalanced() {
		bst.add(2);
		bst.add(1);
		bst.add(3);
		assertTrue(bst.height() == 2);
	}
	
	/** Test av height med tomt träd*/
	@Test
	public final void testHeightEmptyTree() {
		assertTrue(bst.height() == 0);
	}
	
	/** Test av add */
	@Test
	public final void testAdd() {
		assertTrue(bst.add(10));
		assertFalse(bst.add(10));
	}
	
	/** Test av size */
	@Test
	public final void testSize() {
		bst.add(1);
		bst.add(2);
		bst.add(2);
		bst.add(3);
		bst.add(4);
		bst.add(5);
		assertTrue(bst.size() == 5);
	}
	
	/** Test av size med tomt träd */
	@Test
	public final void testSizeEmptyTree() {
		assertTrue(bst.size() == 0);
	}

}