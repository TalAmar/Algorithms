package util;

public class Edge<T, E> {
	private Vertex<T> v1;
	private Vertex<T> v2;
	private E value;

	/* builder for edge with value */
	public Edge(Vertex<T> v1, Vertex<T> v2, E value) {
		this.v1 = v1;
		this.v2 = v2;
		this.value = value;
	}

	/* builder for edge without value */
	public Edge(Vertex<T> v1, Vertex<T> v2) {
		this.v1 = v1;
		this.v2 = v2;
	}

	/* Getters and Setters */
	/* v-----------------------------------------------------------v */
	public Vertex<T> getV1() {
		return v1;
	}

	public void setV1(Vertex<T> v1) {
		this.v1 = v1;
	}

	public Vertex<T> getV2() {
		return v2;
	}

	public void setV2(Vertex<T> v2) {
		this.v2 = v2;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}
	/* ^-----------------------------------------------------------^ */

	public boolean equals(Object obj) {
		if (!(obj instanceof Edge))
			return false;
		@SuppressWarnings("unchecked")
		Edge<T, E> e = (Edge<T, E>) obj;
		if (v1.equals(e.v1) && v2.equals(e.v2))
			return true;
		return false;
	}
}
