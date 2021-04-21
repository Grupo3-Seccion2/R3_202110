package model.data_structures;

public class NodoRBT<K extends Comparable<K>, V extends Comparable<V>> 
{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	boolean color;
	protected K key;
	public V value;
	protected NodoRBT<K, V> left, right;
	protected int size;

	public NodoRBT(K key, V value) 
	{
		this.key = key;
		this.value = value;
		this.size = 1;
		this.color = false;
	}

	public NodoRBT(K key, V value, boolean color) 
	{
		this.key = key;
		this.value = value;
		this.size = 1;
		this.color = color;
	}

	public NodoRBT<K, V> getLeft() 
	{
		return this.left;
	}

	public void setLeft(NodoRBT<K, V> left)
	{
		this.left = left;
	}

	public NodoRBT<K, V> getRight() 
	{
		return this.right;
	}

	public void setRight(NodoRBT<K, V> right) 
	{
		this.right = right;
	}

	public K getKey() 
	{
		return this.key;
	}

	public V getValue() 
	{
		return this.value;
	}

	public void setValue(V value) 
	{
		this.value = value;
	}

	public boolean getColor() 
	{
		return isRed(this);
	}

	public void setColor(boolean newColor) 
	{
		this.color = (newColor == RED) ? RED : BLACK;
	}

	private boolean isRed(NodoRBT<K, V> node) 
	{
		if (node == null) return false;
		return node.color == RED;
	}

	private boolean isBlack(NodoRBT<K, V> node)
	{
		if (node == null) return false;
		return node.color == BLACK;
	}


}
