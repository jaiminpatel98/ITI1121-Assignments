//Code inspired by the ArrayStack implementation of Professor Guy Vincent Jordan of University of Ottawa

public class ArrayStack<E> implements Stack<E> {

    private E[] elems;
    private int top;
    @SuppressWarnings("unchecked")
    public ArrayStack( int capacity ) {
        elems = (E[]) new Object[ capacity ];
        top = 0;
    }
    
    public boolean isEmpty() {
        return ( top == 0 );
    }

    
    public E peek() {
        return elems[ top-1 ];
    }

    public E pop() {
        E saved = elems[ --top ];
        elems[ top ] = null; 
        return saved;
    }
    @SuppressWarnings("unchecked")
    public void push( E element ) {

      if (top == elems.length) { 
        E[] newArray;
        newArray = (E[]) new Object[ elems.length * 2 ];
        for (int i=0; i<top; i++) {
          newArray[i] = elems[i];
        }
        elems = newArray;
      }
 
        elems[ top++ ] = element;
    }
}