package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    boolean addReturn;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		root = add(root,x);
		
		if(addReturn) {
			size++;
		}
		
		return addReturn;
	}
	
	private BinaryNode<E> add(BinaryNode<E> localRoot,E x) {
		if(localRoot == null) {
//			Replace empty tree with a new tree with the item at the root and return true.
			addReturn = true;
			return new BinaryNode<E>(x);
			
		}
		
		else if(x.compareTo(localRoot.element) == 0) {
//			The item is already in the tree; return false.
			addReturn = false;
			return localRoot;
		}
		
		else if(x.compareTo(localRoot.element) < 0) {
//			Recursively insert the item in the left subtree.
			localRoot.left = add(localRoot.left,x);
			return localRoot;
		}
		
		else {
//			Recursively insert the item in the left subtree.
			localRoot.right = add(localRoot.right,x);
			return localRoot;
		}
		
//		The algorithm returns true when the new object is inserted and false if it is a duplicate
//		(the second stopping case). The first stopping case tests for an empty tree. If so, a new
//		BinarySearchTree is created and the new item is stored in its root node (Step 2).
		
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	
	public int height() {

		
		return height(root);
	}
	
	/*definition of the height of a tree is the number of branches in the longest path
	from the root node to a leaf node +1*/
	private int height(BinaryNode<E> node) {
		 if (node == null) {
			 return 0;
		 }
		 
		 return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		if(root == null) {
			System.out.println("Nothing to print");
		}
		else {
			printInOrder(root);
		}
	}
	
	public void printInOrder(BinaryNode<E> localRoot) {
		if(localRoot != null) {
			printInOrder(localRoot.left);
			System.out.println(" " + localRoot.element);
			printInOrder(localRoot.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		if(size > 0) {
			
			@SuppressWarnings("unchecked")
			E[] temp = (E[]) new Comparable[size];
			
			toArray(root, temp, 0);
			
			root = buildTree(temp, 0, size - 1);
		}

	}


	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		
		if(n != null) {
			
			if(n.left != null) {
				index = toArray(n.left,a, index);;
			}

			a[index] = n.element;
			index++;

			if(n.right != null) {
				index = toArray(n.right,a, index);
			}
		}


		//		Att få toArray att returnera korrekt värde kräver lite eftertanke. Tänk på att det
		//		i de rekursiva anropen för subträden kan läggas till element i vektorn a. Tänk också på
		//		att dessa anrop returnerar värden som ska tas om hand.

		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int mid = first + (last - first)/2;

		if (first <= last) {
			BinaryNode<E> midNode = new BinaryNode<E>(a[mid]);
			midNode.left = buildTree(a, first, mid -1);
			midNode.right = buildTree(a, mid + 1, last);
			return midNode;
		} else {
			return null;
		} 

	}
	
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		for(int i = 1; i<6; i++) {
			bst.add(i);
		}
		bst.add(10);
		bst.add(9);
		bst.add(6);
		bst.add(54);
		bst.add(55);
		bst.add(52);
		bst.add(13);
		bst.add(27);
		bst.add(1);
		

		
		BSTVisualizer bstv = new BSTVisualizer("Tree", 600,  400);
		BSTVisualizer bstvBalanced = new BSTVisualizer("Balanced tree", 600,  400);
		

		bstv.drawTree(bst);
		bst.rebuild();
		bst.printTree();
		bstvBalanced.drawTree(bst);
		
		
	}
	
}
