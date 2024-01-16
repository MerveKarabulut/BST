package Lab5;

import java.util.Arrays;

public class TestBST1 {
	   public static void main(String[] args) {
	      // create a BST for the type String and insert some elements
	      BST<String> bst = new BST<>();
	      String[] names = { "George", "Mike", "Tom", "Adam", "Joe", "Peter", "Dany" };
	      for (String s : names)
	         bst.insert(s);
	      
	      System.out.println("bst after inserting " + Arrays.toString(names));
	      printTree(bst, true);

	      // search for some elements in bst and print the results
	      System.out.println("\n\nIs Peter in bst?: " + bst.search("Peter"));
	      System.out.println("Is Max in bst?: " + bst.search("Max"));
	      System.out.println("Is Dany in bst?: " + bst.search("Dany"));
	      
	      // delete some elements from bst and print bst after each deletion
	      bst.delete("George");
	      System.out.println("\n\nbst after deleting George");
	      printTree(bst, false);
	      bst.delete("Adam");
	      System.out.println("\nbst after deleting Adam");
	      printTree(bst, false);
	      bst.delete("Mike");
	      System.out.println("\nbst after deleting Mike");
	      printTree(bst, false);
	   }
	   // prints all the elements in a given BST in a way that shows the hierarchical
	   // structure of the BST and the number of the elements in the BST (also prints
	   // each different traversal of the given BST if showTraversals is true)
	   @SuppressWarnings("rawtypes")
	   private static void printTree(BST tree, boolean showTraversals) {
	      // print the given BST on the console
	      System.out.print(tree); // uses the toString method of the BST class
	      // print the number of the elements in the BST
	      System.out.println("The number of the elements is " + tree.getSize() + ".");
	      // print each traversal of the given BST if showTraversals is given as true
	      if (showTraversals) {
	         System.out.print("Inorder (sorted): ");
	         tree.inorder();
	         System.out.print("\nPostorder: ");
	         tree.postorder();
	         System.out.print("\nPreorder: ");
	         tree.preorder();
	      }
	   }
	}