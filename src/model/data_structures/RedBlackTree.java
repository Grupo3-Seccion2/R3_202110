package model.data_structures;


public class RedBlackTree <K extends Comparable<K>, V extends Comparable<V>> implements ITablaSimbolosOrdenada<K,V>
{
	private static final boolean RED = true;

	private static final boolean BLACK = false;

	public NodoRBT <K, V> root;

	@Override
	public int size() 
	{
		if(isEmpty())
			return 0;
		else
			return root.size;
	}

	public int size(NodoRBT<K, V> nodo) 
	{
		if (nodo == null) {
			return 0;
		}
		return nodo.size;
	}

	@Override
	public boolean isEmpty()
	{
		return root.size == 0;
	}

	@Override
	public V get(K key) {
		NodoRBT<K, V> nodo = get(root, key);
		if (nodo != null) 
			return nodo.value;
		else
			return null;
	}

	public  NodoRBT<K, V> get(NodoRBT<K, V> actual, K key) {
		if (actual == null)
			return null;

		int comp = actual.key.compareTo(key);
		if (comp < 0)
			return get(actual.right, key);
		else if (comp > 0)
			return get(actual.left, key);
		else
			return actual;

	}

	@Override
	public int getHeight(K key) {
		return getHeight(get(root, key));
	}

	private int getHeight(NodoRBT<K, V> actual) {
		if (actual == null) 
			return 0;
		else if (actual.left == null && actual.right == null)
			return 0;
		else
			return 1 + Math.max(getHeight(actual.left), getHeight(actual.right));
	}

	@Override
	public boolean contains(K key) {
		NodoRBT<K, V> nodo = get(root, key);
		return !(nodo == null);
	}

	@Override
	public void put(K key, V val) {
		root = put(root, key, val);
		root.color = BLACK;
	}

	private NodoRBT<K, V> put(NodoRBT<K, V> nodo, K key, V value) 
	{
		if (nodo == null) {
			return new NodoRBT<K, V>(key, value, RED);
		}

		int comp = key.compareTo(nodo.getKey());

		if (comp == 0)
			nodo.setValue(value);
		else if (comp < 0)
			nodo.setLeft(put(nodo.getLeft(), key, value));
		else
			nodo.setRight(put(nodo.getRight(), key, value));

		return balance(nodo);
	}

	// Metodos propios
	public NodoRBT<K, V> balance(NodoRBT<K, V> nodo) {
		if (isRed(nodo.getRight()) && !isRed(nodo.getLeft()))
			nodo = rotateLeft(nodo);
		if (isRed(nodo.getLeft()) && isRed(nodo.getLeft().getLeft()))
			nodo = rotateRight(nodo);
		if (isRed(nodo.getLeft()) && isRed(nodo.getRight()))
			flipColors(nodo);
		nodo.size = size(nodo.getLeft()) + size(nodo.getRight()) + 1;

		return nodo;
	}

	public boolean isBalanced(NodoRBT<K,V> nodo){
		int arbolIzq;
		int arbolDer;

		if(nodo == null)
			return true;

		arbolIzq = (nodo.getLeft() == null) ? 0: getHeight(nodo.getLeft().getKey());
		arbolDer = (nodo.getRight() == null) ? 0: getHeight(nodo.getRight().getKey());

		if(Math.abs(arbolIzq - arbolDer) <= 1 && isBalanced(nodo.getLeft()) && isBalanced(nodo.getRight()))
			return true;
		else
			return false;
	}

	public boolean isRed(NodoRBT<K, V> nodo) {
		if (nodo == null) return false;
		return nodo.color == RED;
	}

	public boolean isBlack(NodoRBT<K, V> nodo) {
		if (nodo == null) return false;
		return nodo.color == BLACK;
	}

	public NodoRBT<K, V> rotateLeft(NodoRBT<K, V> nodo) {
		NodoRBT<K, V> der = nodo.getRight();
		nodo.setRight(der.getLeft());
		der.setLeft(nodo);
		der.setColor(nodo.getColor());
		nodo.setColor(RED);
		der.size = nodo.size;
		nodo.size = 1 + size(nodo.getLeft()) + size(nodo.getRight());

		return der;
	}

	public NodoRBT<K, V> rotateRight(NodoRBT<K, V> nodo) {
		NodoRBT<K, V> izq = nodo.getLeft();
		nodo.setLeft(izq.getRight());
		izq.setRight(nodo);
		izq.setColor(nodo.getColor());
		nodo.setColor(RED);

		izq.size = nodo.size;
		nodo.size = 1 + size(nodo.getLeft()) + size(nodo.getRight());

		return izq;

	}

	public void flipColors(NodoRBT<K, V> nodo) {

		nodo.color = RED;
		nodo.left.color = BLACK;
		nodo.right.color = BLACK;

	}

	public int getSize(NodoRBT<K, V> node) {
		return node.size;
	}

	//Continuacion metodos heredados

	@Override
	public int height() 
	{
		return getHeight(root);
	}

	@Override
	public K min() {
		if (root == null) 
			return null;
		NodoRBT<K, V> actual = root;
		while (actual.left !=null)
			actual = actual.left;
		return actual.key;
	}

	@Override
	public K max() {
		if (root == null) 
			return null;
		NodoRBT<K, V> actual = root;
		while (actual.right !=null)
			actual = actual.right;
		return actual.key;
	}

	@Override
	public ILista<K> keySet() {
		ILista<K> k = new ArregloDinamico<K>(100);
		if( root !=  null)
			keySet(root, k);

		return k;
	}

	public void keySet(NodoRBT<K, V> nodo, ILista<K> k)
	{
		if (nodo.getLeft() != null)
			keySet(nodo.getLeft(), k);

		k.addLast(nodo.getKey());

		if (nodo.getRight() != null)
			keySet(nodo.getRight(), k);
	}

	@Override
	public ILista<V> valueSet() {
		ILista<V> v = new ArregloDinamico<V>(100);
		if( root !=  null)
			valueSet(root, v);

		return v;
	}

	public void valueSet(NodoRBT<K, V> nodo, ILista<V> v)
	{
		if (nodo.getLeft() != null)
			valueSet(nodo.getLeft(), v);

		v.addLast(nodo.getValue());

		if (nodo.getRight() != null)
			valueSet(nodo.getRight(), v);
	}


	@Override
	public ILista<V> valuesInRange(K init, K end) {
		ILista<V> lista = new ArregloDinamico<>(1000);
		values((ArregloDinamico<V>) lista, root, init, end);

		return lista;
	}

	private void values(ArregloDinamico<V> lista, NodoRBT<K, V> nodo, K lo, K hi) {
		if (nodo == null) return;
		int cmplo = lo.compareTo(nodo.getKey());
		int cmphi = hi.compareTo(nodo.getKey());
		if (cmplo < 0) values(lista, nodo.left, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) lista.addLast(nodo.value);
		if (cmphi > 0) values(lista, nodo.right, lo, hi);

	}

	@Override
	public ILista<K> keysInRange(K init, K end) {
		ArregloDinamico<K> lista = new ArregloDinamico<>(1000);
		keys(lista, root, init, end);
		return lista;
	}


	private void keys(ArregloDinamico<K> lista, NodoRBT<K, V> nodo, K lo, K hi) {
		if (nodo == null) return;
		int cmplo = lo.compareTo(nodo.getKey());
		int cmphi = hi.compareTo(nodo.getKey());
		if (cmplo < 0) keys(lista, nodo.left, lo, hi);
		if (cmplo <= 0 && cmphi >= 0) lista.addLast(nodo.key);
		if (cmphi > 0) keys(lista, nodo.right, lo, hi);
	}



}
