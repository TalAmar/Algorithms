package util;

import java.util.ArrayList;
import java.util.List;

public class Graph<T, E> {
	private List<List<Vertex<T>>> vertex_list;
	private List<Edge<T, E>> edge_list;
	private is_directed direction;

	private enum is_directed {
		DIRECTED, NOT_DIRECTED;
	}

	/* builder for new graph */
	public Graph(is_directed direction) {
		vertex_list = new ArrayList<>();
		edge_list = new ArrayList<>();
		this.direction = direction;
	}

	/* builder for graph with data */
	public Graph(List<List<Vertex<T>>> vertex_list, List<Edge<T, E>> edge_list, is_directed direction) {
		this.vertex_list = vertex_list;
		this.edge_list = edge_list;
		this.direction = direction;
	}

	public boolean addVertex(Vertex<T> v) {
		List<Vertex<T>> temp = new ArrayList<>();
		temp.add(v);
		for (List<Vertex<T>> ver : vertex_list)
			if (ver.get(0).equals(v))
				return false;
		vertex_list.add(temp);
		return true;
	}

	public static void main(String[] args) {
		Graph<Integer, Integer> g = new Graph<Integer, Integer>(is_directed.NOT_DIRECTED);
		Vertex<Integer> v = new Vertex<Integer>(1);
		System.out.println(g.addVertex(v));
		System.out.println(g.addVertex(v));
	}
}
