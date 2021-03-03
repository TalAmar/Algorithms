package bfs;

import java.util.ArrayList;
import java.util.List;

import util.Vertex;

public class VertexBFS<T> extends Vertex<T> {
	private int distance;
	private VertexBFS<T> previous;
	private Color color;
	private List<VertexBFS<T>> neighbors = new ArrayList<>();
	private int originKey;

	public VertexBFS(Integer originKey) {
		super();
		this.originKey = originKey;
	}

	/* Getters and Setters */
	/* v-----------------------------------------------------------v */
	protected int getDistance() {
		return distance;
	}

	protected void setDistance(int distance) {
		this.distance = distance;
	}

	protected VertexBFS<T> getPrevious() {
		return previous;
	}

	protected void setPrevious(VertexBFS<T> previous) {
		this.previous = previous;
	}

	protected Color getColor() {
		return color;
	}

	protected void setColor(Color color) {
		this.color = color;
	}

	protected List<VertexBFS<T>> getNeighbors() {
		return neighbors;
	}

	protected void setNeighbors(List<VertexBFS<T>> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public int getKey() {
		return originKey;
	}
	/* ^-----------------------------------------------------------^ */
}
