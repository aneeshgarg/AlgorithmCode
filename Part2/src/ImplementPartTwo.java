import java.util.HashSet;
import java.util.Set;

import tree.AVLTree;
import tree.BinarySearchTree;
import tree.Node;
import exception.EmptyTreeException;
import exception.InvalidTreeOperation;
import exception.KeyNotFoundException;

public class ImplementPartTwo {

	private static final String FORMAT = "%5d\t\t%10d\t%10d\n";
	private static StringBuilder output;
	private static Set<Integer> numberSet;

	public static enum Operation {
		INSERT, DELETE, SEARCH
	}

	public static void main(String[] args) throws EmptyTreeException,
			KeyNotFoundException, InvalidTreeOperation {
		BinarySearchTree.DEBUG = false;

		output = new StringBuilder();

		int[] numberOfElements = { 100, 500, 1000, 5000, 10000 };

		for (int no : numberOfElements) {
			String temp = "-------------------------------- n = " + no
					+ " --------------------------------\n\n";

			temp += String
					.format("Operations\t Height of BST \t Height of AVL \n");
			System.out.print(temp);
			output.append(temp);

			BinarySearchTree bst = new BinarySearchTree();
			AVLTree avl = new AVLTree();
			numberSet = new HashSet<Integer>();
			int maxNo = no * 10;
			print(0, bst.getHeight(), avl.getHeight());

			for (int i = 1; i <= no; i++) {
				int n = getRandomInt(maxNo, true);
				// System.out.println("Inserting " + n);
				// Inserting element
				bst.insertItem(n);
				avl.insertItem(n);
				numberSet.add(n);

				if (i == (no / 2) || i == no) {
					// Printing trees
					bst.printTree();
					avl.printTree();
					print(i, bst.getHeight(), avl.getHeight());
				}
			}

			int inserts = 0, deletes = 0, searches = 0;
			for (int i = no + 1; i <= 2 * no; i++) {
				Operation op = getOperation();
				int n;
				try {
					switch (op) {
					case INSERT:
						n = getRandomInt(maxNo, true);
						System.out.println("Inserting " + n);
						inserts++;
						avl.insertItem(n);
						bst.insertItem(n);
						numberSet.add(n);
						break;

					case DELETE:
						n = getRandomInt(maxNo, false);
						System.out.println("Deleting " + n);
						deletes++;
						avl.removeItem(n);
						bst.removeItem(n);
						numberSet.remove(n);
						break;

					case SEARCH:
						n = getRandomInt(maxNo, false);
						System.out.println("Searching " + n);
						searches++;
						Node item1 = avl.findItem(n);
						Node item2 = bst.findItem(n);
						if (item1 == null || item2 == null)
							throw new KeyNotFoundException("Key " + n
									+ " not found !!!");
						break;
					}
				} catch (InvalidTreeOperation e) {
					System.out.println(e.getMessage());
				} finally {
					if (i == (3 * no / 2) || i == 2 * no) {
						// Printing trees
						bst.printTree();
						avl.printTree();
						print(i, bst.getHeight(), avl.getHeight());
						System.out.println(output.toString());
					}
				}
			}
			temp = "Operations: n inserts = " + no + " PLUS inserts = "
					+ inserts + " PLUS deletes = " + deletes
					+ " PLUS searches = " + searches + " EQUALS Total = "
					+ (no + inserts + searches + deletes) + "\n";
			output.append(temp);
			System.out.println(output.toString());
		}
	}

	private static void print(int n, int bstHeight, int avlHeight) {
		String temp = "";
		temp = String.format(FORMAT, n, bstHeight, avlHeight);
		System.out.print(temp);
		output.append(temp);
	}

	/**
	 * This method will generate a random integer.
	 * 
	 * @param max
	 *            this parameter is used to increase the range of random number
	 * @param isUnique
	 *            this parameter decides whether a unique number not already in
	 *            tree should be generated.
	 * @return int random integer
	 */
	private static int getRandomInt(int max, boolean isUnique) {
		int element = (int) (Math.random() * (max + 1));
		if (isUnique)
			while (numberSet.contains(element))
				element = (int) (Math.random() * (max + 1));
		return element;
	}

	/**
	 * This method return a random operation Insert, Search or Delete with a
	 * probability of 0.3, 0.5 and 0.2 respectively
	 * 
	 * @return Operation enum
	 */
	public static Operation getOperation() {
		int number = (int) ((Math.random() * 10) % 10);
		if (number >= 0 && number <= 2)
			return Operation.INSERT;
		else if (number > 2 && number < 5)
			return Operation.DELETE;
		return Operation.SEARCH;
	}
}
