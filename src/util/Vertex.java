package util;

public class Vertex<T> {
	private int vertexKey;
	private T value;

	public Vertex(T value) {
		vertexKey = Graph.key++;
		this.value = value;
	}

	/* Getters and Setters */
	/*-----------------------------------------------------------*/
	public T getValue() {
		return value;
	}

	public int getKey() {
		return vertexKey;
	}

	public void setValue(T value) {
		this.value = value;
	}
	/*-----------------------------------------------------------*/
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Vertex))
			return false;
		@SuppressWarnings("unchecked")
		Vertex<T> v = (Vertex<T>) obj;
		if (vertexKey == v.getKey())
			return true;
		return false;
	}
}
