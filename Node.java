package col106.assignment4.WeakAVLMap;

public class Node<K extends Comparable,V>{
	public Node left;
	public Node right;
	public Node parent_of_ex;
	public K key;
	public V value;
	public Integer rank;

	public Node()
	{
		rank = -1;
		left = null;
		right = null;
		key = null;
		value = null;
		parent_of_ex = null;
	}
}