package util;

public class Vertex<T> {
	private static int key = 0;
	private T value;

	public Vertex(T value) {
		key = key++;
		this.value = value;
	}

	/* Getters and Setters */
	/*-----------------------------------------------------------*/
	public T getValue() {
		return value;
	}

	public int getKey() {
		return key;
	}

	public void setValue(T value) {
		this.value = value;
	}
	/*-----------------------------------------------------------*/

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertex)
			return false;
		if(obj == null)
			return false;
		@SuppressWarnings("unchecked")
		Vertex<T> v = (Vertex<T>) obj;
		if (Vertex.key == v.getKey())
			return true;
		return false;
	}
}
