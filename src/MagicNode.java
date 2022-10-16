public class MagicNode<E>
{
	private E value;
	private MagicNode<E> next;
	
	public MagicNode(E value, MagicNode<E> next)
	{
		this.value = value;
		this.next = next;
	}
	public MagicNode(E value) { this(value, null); }
	public MagicNode() { this(null, null); }
	
	public E getValue() { return value; }
	public MagicNode<E> getNext() { return next; }
	public void setValue(E value) { this.value = value; }
	public void setNext(MagicNode<E> next) { this.next = next; }
}
