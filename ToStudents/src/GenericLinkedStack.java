public class GenericLinkedStack<E> implements Stack<E>{
	RuntimeException emptyStack = new EmptyStackException();
	private static class Elem<I>
	{
		private I value;
		private Elem<I> next;
		private Elem(I value, Elem<I> next)
		{
			this.value = value;
			this.next = next;
		}
	}
	private Elem<E> first;
	public boolean isEmpty()
	{
		return first==null;
	}
	public void push(E value)
	{
		if(value==null)
		{
			throw new NullPointerException();
		}
		first = new Elem<E>(value, first);
	}
	public E peek()
	{
		if(isEmpty())
		{
			throw emptyStack;
		}
		return first.value;

	}
	public E pop()
	{
		if(isEmpty())
		{
			throw emptyStack;
		}
		E returnable = first.value;
		first = first.next;
		return returnable;
	}
	public static void main(String [] args)
	{

	}
}