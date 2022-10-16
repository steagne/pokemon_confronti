public class MagicList<E>
{
	//VARIABLES
	private MagicNode<E> root;
	
	//CONSTRUCTORS
	public MagicList(MagicNode<E> root) { this.root = root; }
	public MagicList() { this((MagicNode<E>)null); }
	public MagicList(E[] array) { generateByArray(array); }
	
	//PUBLIC METHODS
	public int getSize()
	{
		if (isEmpty())
			return 0;
		
		return getSize(root);
	}
	public boolean isEmpty() { return (root == null); }
	public void generateByArray(E[] array) { root = generateByArray(array, 0); }
	public void clear() { root = null; }
	public void remove(int index)
	{
		if (index == 0)
			root = root.getNext();
		else
			remove(root, index);
	}
	public void removeEqualsOf(E value)
	{
		while (root.getValue() == value && !isEmpty())
			root = root.getNext();
		if (!isEmpty())
			removeEqualsOf(root, value);
	}
	public E get(int index) { return get(root, index); }
	public int indexOf(E value) { return indexOf(root, value, 0); }
	public Object[] toArray()
	{
		if (isEmpty())
			return null;
		
		Object[] array;
		
		array = new Object[getSize()];
		toArray(array, root, 0);
		
		return array;
	}
	public boolean contains(E value)
	{
		if (isEmpty())
			return false;
		
		return contains(root, value);
	}
	public void add(int index, E value)
	{
		if (index == 0 || isEmpty())
			root = new MagicNode<E>(value, root);
		else
			add(root, index, value);
	}
	public void add(E value) { add(getSize(), value); }
	
	//PRIVATE METHODS
	private int getSize(MagicNode<E> node)
	{
		if (node.getNext() == null)
			return 1;
		
		return (1 + getSize(node.getNext()));
	}
	private MagicNode<E> generateByArray(E[] array, int index)
	{
		if ((index + 1) == array.length)
			return new MagicNode<E>(array[index]);
		
		return new MagicNode<E>(array[index], generateByArray(array, index + 1));
	}
	private void remove(MagicNode<E> node, int index)
	{
		if (index == 1)
			node.setNext(node.getNext().getNext());
		else
			remove(node.getNext(), (index - 1));
	}
	private void removeEqualsOf(MagicNode<E> node, E value)
	{
		if (node.getValue() == value)
			node.setValue(node.getNext().getValue());
		if (node.getNext() != null)
			removeEqualsOf(node.getNext(), value);
	}
	private E get(MagicNode<E> node, int index)
	{
		if (index == 0)
			return node.getValue();
		
		return get(node.getNext(), index - 1);
	}
	private int indexOf(MagicNode<E> node, E value, int index)
	{
		if (node.getValue() == value)
			return index;
		if (node.getNext() == null)
			return -1;
		
		return indexOf(node.getNext(), value, index + 1);
	}
	private void toArray(Object[] array, MagicNode<E> node, int index)
	{
		array[index] = node.getValue();
		if (node.getNext() != null)
			toArray(array, node.getNext(), index + 1);
	}
	private boolean contains(MagicNode<E> node, E value)
	{
		if (node.getValue().equals(value))
			return true;
		if (node.getNext() == null)
			return false;
		
		return contains(node.getNext(), value);
	}
	private void add(MagicNode<E> node, int index, E value)
	{
		if (node.getNext() == null)
			node.setNext(new MagicNode<E>(value));
		else if (index == 1)
			node.setNext(new MagicNode<E>(value, node.getNext()));
		else
			add(node.getNext(), (index - 1), value);
	}
}
