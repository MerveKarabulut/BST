package Lab5;


import java.util.Comparator;

// A class that defines a comparator to compare any 2 objects of the generic type 
// E based on their natural ordering. The compareTo method of the type E is used 
// for comparison assuming that the type E implements the Comparable interface. 
public class DefaultComparator<E> implements Comparator<E> {
   // implementing the compare method of the Comparator interface
   @SuppressWarnings("unchecked") // to suppress the unchecked cast warning
   // the method throws a ClassCastException if the type E does not implement
   // the Comparable interface
   @Override // implements the compare method in the Comparator interface
   public int compare(E a, E b) throws ClassCastException {
      // return the result of the compareTo method of the Comparable type E
      return ((Comparable<E>) a).compareTo(b);
   }
}