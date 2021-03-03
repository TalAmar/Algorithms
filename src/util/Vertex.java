package util;

public class Vertex<T> {
	private int vertexKey;
	private T value;

	/* builder for vertex with value */
	public Vertex(T value) {
		vertexKey = Graph.key++;
		this.value = value;
	}

	/* builder for vertex without value */
	public Vertex() {
		vertexKey = Graph.key++;
	}

	/* Getters and Setters */
	/* v-----------------------------------------------------------v */
	public T getValue() {
		return value;
	}

	public int getKey() {
		return vertexKey;
	}

	public void setValue(T value) {
		this.value = value;
	}
	/* ^-----------------------------------------------------------^ */

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Vertex))
			return false;
		@SuppressWarnings("unchecked")
		Vertex<T> v = (Vertex<T>) obj;
		if (vertexKey == v.getKey())
			return true;
		return false;
	}
}
