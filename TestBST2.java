package Lab5;

import java.util.Arrays;
import java.util.Iterator;

// Program 2 for testing the BST class.
public class TestBST2 {
   public static void main(String[] args) {
      // create a BST for the type Integer from an array of some integer values
      Integer[] numbers = { 12, 14, 13, 11, 18, 15, 16, 17 };
      BST<Integer> bst = new BST<>(numbers);
      // invoke the printTree method defined below to print the elements in bst
      // in a way that shows the hierarchical structure of the binary search tree,
      // the number of the elements in bst and each different traversal of bst
      System.out.println("bst after inserting " + Arrays.toString(numbers));
      printTree(bst, true);

      // search for some elements in bst and print the results
      System.out.println("\n\nIs 10 in bst?: " + bst.search(10));
      System.out.println("Is 7 in bst?: " + bst.search(7));
      System.out.println("Is 12 in bst?: " + bst.search(12));

      // the iterator method of the BST class returns an inorder iterator that
      // provides access to all the elements in bst as a sorted sequence
      System.out.print("\nbst sorted: ");
      Iterator<Integer> bstInorderIterator = bst.iterator();
      Integer currentElement, deletedElement = null;
      int count = 0;
      // as long as there is a next element in the sequence
      while (bstInorderIterator.hasNext()) {
         // get that element and print it on the console
         currentElement = bstInorderIterator.next();
         System.out.print(currentElement + " ");
         count++; // increase the count by 1 (the number of the printed elements)
         // remove the fifth element in bst
         if (count == 5) {
            deletedElement = currentElement;
            bstInorderIterator.remove();
         }
      }

      // print the final state of the binary search tree
      System.out.println("\n\nbst after deleting " + deletedElement);
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