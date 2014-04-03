import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import exception.EmptyTreeException;
import exception.InvalidTreeOperation;
import exception.KeyNotFoundException;
import tree.BinarySearchTree;

public class ImplementPartTwo {

	public static void main(String[] args) throws EmptyTreeException, KeyNotFoundException, InvalidTreeOperation {
		BinarySearchTree.DEBUG = true;
		
		int[] numberOfElements = {10};
		
		for (int no : numberOfElements) {
			/*HashSet<Integer> numberSet = new HashSet<Integer>();
			while (numberSet.size() < no)
				numberSet.add(new Integer((int)((Math.random() * no * 10 * 100) % 100)));
			ArrayList<Integer> numbers = new ArrayList<Integer>(numberSet);*/
				
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			while (numbers.size() < no)
				numbers.add(new Integer((int)((Math.random() * no * 10 * 100) % 100)));
			
			Integer[] n = {68, 0, 69, 32, 86, 52, 56, 73, 31, 61};
			numbers = new ArrayList<Integer>(Arrays.asList(n));
			
			System.out.println( "Number of Elements: " + no);
			System.out.println("Elements: " + numbers.toString());
			
			BinarySearchTree bst = new BinarySearchTree(numbers);
			System.out.println("Height: " + bst.getHeight() +" Size: " + bst.getSize());
			System.out.println();
			bst.printTree();
			
			Collections.shuffle(numbers);
			for (Integer i : numbers) {
				System.out.print("Removing: " + i +"	");
				bst.removeItem(i);
				bst.inOrder(bst.getRoot());
				System.out.println();
			}
			System.out.println("Height: " + bst.getHeight() +" Size: " + bst.getSize());
		}

	}

}
