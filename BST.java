package Lab5;

import java.util.Comparator; // used for comparing the elements in the BST
import java.util.Iterator; // used for implementing an iterator for the BST
import java.util.ArrayList;

// The BST class defines a data structure for modeling a binary search tree.
// It implements the Iterable interface by defining an iterator() method which 
// returns an inorder (sorted) iterator to traverse the elements in the tree.
public class BST<E> implements Iterable<E> { // E is declared as a generic type.
   // A private inner class for modeling each node in the binary search tree.
   // It uses the generic type E that is declared for its outer class BST<E>.
   // --------------------------------------------------------------------------
   private class TreeNode {
      // data fields
      E element; // the stored element in this node
      TreeNode left; // a reference to the left child of this node
      TreeNode right; // a reference to the right child of this node

      // a constructor that creates an unconnected node with the given element e
      TreeNode(E e) {
         element = e;
      }
   }

   // Data fields of the BST<E> class
   // --------------------------------------------------------------------------
   private TreeNode root; // the root (top) node of the tree
   private int size = 0; // the number of the elements in the tree (initially 0)
   private Comparator<E> c; // a comparator for comparing the elements

   // Constructors of the BST<E> class
   // --------------------------------------------------------------------------
   // creates a default binary search tree using a natural order for comparisons
   // assuming that the type E implements the Comparable interface
   public BST() {
      c = new DefaultComparator<E>();
   }

   // creates a binary search tree from a given array of objects
   public BST(E[] objects) {
      // invoke the default constructor
      this();
      // insert all the elements in the given array to the tree
      for (int i = 0; i < objects.length; i++)
         insert(objects[i]);
   }

   // Methods of the BST<E> class
   // --------------------------------------------------------------------------
   // returns the number of the elements in the binary search tree
   public int getSize() {
      return size;
   }

   // returns true if the binary search tree is empty and false otherwise
   public boolean isEmpty() {
      return size == 0;
   }

   // returns true if the given element e is in the tree and false otherwise
   public boolean search(E e) {
      // start searching from the root node
      TreeNode current = root;
      // continue as long as the current node is not null
      while (current != null) {
         // if the given element e is smaller than the current element
         if (c.compare(e, current.element) < 0)
            // continue searching from the left subtree
            current = current.left;
         // if the given element e is bigger than the current element
         else if (c.compare(e, current.element) > 0)
            // continue searching from the right subtree
            current = current.right;
         // if the given element e matches the current element
         else
            // return true as the given element e is found
            return true;
      }
      // return false as the given element e is not found
      return false;
   }

   // inserts a given element e into the binary search tree and returns true if
   // the element is inserted successfully and false otherwise
   // (note: the same element cannot be inserted multiple times)
   public boolean insert(E e) {
      // if the tree is empty
      if (root == null)
         // insert the given element e as the root node of the tree
         root = new TreeNode(e);
      // otherwise
      else {
         // locate the parent node for insertion
         TreeNode parent = null;
         // start from the root node
         TreeNode current = root;
         // continue as long as the current node is not null
         while (current != null) {
            // if the given element e is smaller than the current element
            if (c.compare(e, current.element) < 0) {
               // continue from the left subtree
               parent = current;
               current = current.left;
            }
            // if the given element e is bigger than the current element
            else if (c.compare(e, current.element) > 0) {
               // continue from the right subtree
               parent = current;
               current = current.right;
            }
            // if the given element e matches the current element
            else
               // return false as a duplicate node is not inserted
               return false;
         }
         // create the new node and attach it to its parent node
         if (c.compare(e, parent.element) < 0)
            // e is smaller than its parent -> attach it as the left child
            parent.left = new TreeNode(e);
         else
            // otherwise -> attach the new node as the right child
            parent.right = new TreeNode(e);
      }
      // increase the size of the tree (the number of the elements) by 1
      size++;
      // return true as the element e is inserted successfully
      return true;
   }

   // deletes a given element e from the tree and returns true if the element is
   // deleted successfully and false otherwise
   public boolean delete(E e) {
      // locate the node to be deleted (current) as well as its parent node
      TreeNode parent = null;
      TreeNode current = root; // start searching from the root node
      // continue as long as the current node is not null
      while (current != null) {
         // if the given element e is smaller than the current element
         if (c.compare(e, current.element) < 0) {
            // continue searching from the left subtree
            parent = current;
            current = current.left;
         }
         // if the given element e is bigger than the current element
         else if (c.compare(e, current.element) > 0) {
            // continue searching from the right subtree
            parent = current;
            current = current.right;
         }
         // if the given element e matches the current element
         else
            // end searching as the given element is found in the current node
            break;
      }
      // return false if the element is not found in the tree
      if (current == null)
         return false;
      // case 1: the current node has no left child -> simply connect the right
      // child of the current node to the parent of the current node (the leaf
      // nodes also fall into this case)
      if (current.left == null) {
         // if the current node is the root node (no parent node)
         if (parent == null)
            // the right child of the current node becomes the new root node
            root = current.right;
         // otherwise
         else {
            // e is smaller than its parent -> the right child of the current node
            // becomes the left child of the parent node
            if (c.compare(e, parent.element) < 0)
               parent.left = current.right;
            // otherwise -> the right child of the current node becomes the right
            // child of the parent node
            else
               parent.right = current.right;
         }
      }
      // case 2: the current node has a left child
      else {
         // locate the rightmost node in the left subtree of the current node as
         // well as its parent node (the rightmost node is the node that contains
         // the largest element in the left subtree of the current node)
         TreeNode parentOfRightMost = current;
         TreeNode rightMost = current.left;
         // keep going to the right as long as there is a right child
         while (rightMost.right != null) {
            parentOfRightMost = rightMost;
            rightMost = rightMost.right;
         }
         // move the element in the rightmost node to the current node
         current.element = rightMost.element;
         // delete the rightmost node by connecting its left child to its parent
         // (it may only have a left child as it is the rightmost node)
         if (parentOfRightMost.right == rightMost)
            parentOfRightMost.right = rightMost.left;
         else
            // special case: parentOfRightMost == current
            parentOfRightMost.left = rightMost.left;
      }
      // decrease the size of the tree (the number of the elements) by 1
      size--;
      // return true as the element e is deleted successfully
      return true;
   }

   // removes all the nodes from the tree with the help of the garbage collector
   public void clear() {
      root = null;
      size = 0;
   }
   
   // performs a preorder traversal of the tree and prints each traversed element
   public void preorder() {
      // using the recursive preorder method defined below starting from the root
      preorder(root);
   }
   
   // performs a preorder traversal of the tree recursively
   private void preorder(TreeNode n) {
      // base case
      if (n == null)
         return;
      // print the current element followed by a space
      System.out.print(n.element + " ");
      // perform a preorder traversal of the left subtree
      preorder(n.left);
      // perform a preorder traversal of the right subtree
      preorder(n.right);
   }

   // performs an inorder traversal of the tree and prints each traversed element
   public void inorder() {
      // using the recursive inorder method defined below starting from the root
      inorder(root);
   }

   // performs an inorder (sorted) traversal of the tree recursively
   private void inorder(TreeNode n) {
      // base case
      if (n == null)
         return;
      // perform an inorder traversal of the left subtree
      inorder(n.left);
      // print the current element followed by a space
      System.out.print(n.element + " ");
      // perform an inorder traversal of the right subtree
      inorder(n.right);
   }

   // performs a postorder traversal of the tree and prints each traversed element
   public void postorder() {
      // using the recursive postorder method defined below starting from the root
      postorder(root);
   }

   // performs a postorder traversal of the tree recursively
   private void postorder(TreeNode n) {
      // base case
      if (n == null)
         return;
      // perform a postorder traversal of the left subtree
      postorder(n.left);
      // perform a postorder traversal of the right subtree
      postorder(n.right);
      // print the current element followed by a space
      System.out.print(n.element + " ");
   }

   // returns a string that is used for printing a BST instance on the console
   // in a way that shows the hierarchical structure of the binary search tree
   @Override // overriding the toString method of the Object class
   public String toString() {
      // using the recursive toString method defined below starting from the root
      return toString(root, "");
   }

   // recursively creates a string by adding each element in the BST in a way that
   // shows the hierarchical structure of the BST and returns the resulting string
   private String toString(TreeNode n, String indent) {
      // initialize the result string as the element stored in the given node n
      // followed by a new line
      String result = n.element + "\n";
      // add "Root: " to the beginning of result if n is the root node
      if (indent.equals("")) {
         result = "Root: " + result;
         indent = "  "; // set the indent as the half the length of "Root"
      }
      // if the given node n does not have any child
      if (n.left == null && n.right == null)
         // return result as initialized above
         return result;
      // if the given node n has only a left child
      else if (n.right == null)
         // add the current value of indent to result followed by "â””â”€ L: " and
         // the output of the recursive toString method for the left subtree
         // with a new value for indent that has 3 additional spaces
         result += indent + "â””â”€ L: " + toString(n.left, indent + "   ");
      // if the given node n has only a right child
      else if (n.left == null)
         // add the current value of indent to result followed by "â””â”€ R: " and
         // the output of the recursive toString method for the right subtree
         // with a new value for indent that has 3 additional spaces
         result += indent + "â””â”€ R: " + toString(n.right, indent + "   ");
      // otherwise (if the given node n has both child nodes)
      else {
         // add the current value of indent to result followed by "â”œâ”€ L: " and
         // the output of the recursive toString method for the left subtree
         // with a new value for indent that additionally has a "|" and 2 spaces
         result += indent + "â”œâ”€ L: " + toString(n.left, indent + "|  ");
         // add the current value of indent to result followed by "â””â”€ R: " and
         // the output of the recursive toString method for the right subtree
         // with a new value for indent that has 3 additional spaces
         result += indent + "â””â”€ R: " + toString(n.right, indent + "   ");
      }
      // return result as the resulting string
      return result;
   }

   // returns an iterator that provides access to all the elements in the binary
   // search tree as an inorder (sorted) sequence
   @Override // implements the iterator method in the Iterable interface
   public Iterator<E> iterator() {
      // creates and returns an InorderIterator instance
      return new InorderIterator();
   }

   // A private inner class that implements an inorder (sorted) BST iterator.
   // It uses the generic type E that is declared for its outer class BST<E>.
   // --------------------------------------------------------------------------
   private class InorderIterator implements Iterator<E> {
      // the inorder (sorted) sequence of elements are stored in an array list
      ArrayList<E> list = new ArrayList<>();
      // the index of the current list element that is referenced by the iterator
      int current = 0;

      // a constructor that creates a default InorderIterator instance
      InorderIterator() {
         // use the recursive inorder method defined below to traverse the binary
         // search tree and add each traversed element to the list
         inorder(root); // starting from the root node
      }

      // performs an inorder (sorted) traversal of the tree recursively
      void inorder(TreeNode n) {
         // base case
         if (n == null)
            return;
         // perform an inorder traversal on the left subtree
         inorder(n.left);
         // add the current element to the list
         list.add(n.element);
         // perform an inorder traversal on the right subtree
         inorder(n.right);
      }

      // returns true if the iteration has more elements and false otherwise
      @Override // implements the hasNext method in the Iterator interface
      public boolean hasNext() {
         if (current < list.size())
            return true;
         return false;
      }

      // returns the current element then moves to the next element
      @Override // implements the next method in the Iterator interface
      public E next() {
         return list.get(current++);
      }

      // removes the element returned by the last call of the next method
      @Override // implements the remove method in the Iterator interface
      public void remove() {
         // if the next method has not been called yet
         if (current == 0)
            // throw an illegal state exception
            throw new IllegalStateException();
         // decrease the index of the current element by 1 to address the element
         // returned by the last call of the next method then delete the element
         // from the binary search tree
         delete(list.get(--current));
         // clear and rebuild the list for the new state of the BST (note that
         // removing an element this way is inefficient as it requires the list
         // to be rebuilt)
         list.clear();
         inorder(root);
      }
   }
}
