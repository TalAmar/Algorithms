package util;

import java.util.ArrayList;
import java.util.List;

public class Graph<T, E> {
	private List<List<Vertex<T>>> vertex_list;
	private List<Edge<T, E>> edge_list;
	private is_directed direction;
	protected static int key = 0; // key for vertices

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

	/* adds a vertex to the graph */
	public boolean addVertex(Vertex<T> v) {
		/* checks if the vertex is in the graph already */
		/*-----------------------------------------------------------*/
		for (int i = 0; i < vertex_list.size(); i++)
			if (vertex_list.get(i).get(0).equals(v))
				return false;
		/*-----------------------------------------------------------*/

		List<Vertex<T>> temp = new ArrayList<>();
		temp.add(v);
		vertex_list.add(temp);
		return true;
	}

	/* adds an edge to the graph and connects the vertices */
	public boolean addEdge(Edge<T, E> e) {

		/* checks if the edge is in the graph already */
		/*-----------------------------------------------------------*/
		if (edge_list.contains(e))
			return false;
		if (direction.equals(is_directed.NOT_DIRECTED)) { // checks if the edge is in the non-directed graph
			for (int i = 0; i < vertex_list.size(); i++)
				if (vertex_list.get(i).get(0).equals(e.getV1())) {
					if (vertex_list.get(i).contains(e.getV2()))
						return false;
				} else if (vertex_list.get(i).get(0).equals(e.getV2()))
					if (vertex_list.get(i).contains(e.getV1()))
						return false;
		} else // checks if the edge is in the directed graph
			for (int i = 0; i < vertex_list.size(); i++)
				if (vertex_list.get(i).get(0).equals(e.getV1()))
					if (vertex_list.get(i).contains(e.getV2()))
						return false;
		/*-----------------------------------------------------------*/

		if (direction.equals(is_directed.NOT_DIRECTED)) // adds a non-directed edge to the graph
			for (int i = 0; i < vertex_list.size(); i++) {
				if (vertex_list.get(i).get(0).equals(e.getV1()))
					vertex_list.get(i).add(e.getV2());
				if (vertex_list.get(i).get(0).equals(e.getV2()))
					vertex_list.get(i).add(e.getV1());
			}
		else
			for (int i = 0; i < vertex_list.size(); i++) // adds a directed edge to the graph
				if (vertex_list.get(i).get(0).equals(e.getV1()))
					vertex_list.get(i).add(e.getV2());
		return true;
	}

	public static void main(String[] args) {
		Graph<Integer, Integer> g = new Graph<Integer, Integer>(is_directed.DIRECTED);
		Vertex<Integer> v1 = new Vertex<Integer>(1);
		Vertex<Integer> v2 = new Vertex<Integer>(1);
		System.out.println(g.addVertex(v1));
		System.out.println(g.addVertex(v1));
		System.out.println(g.addVertex(v2));
		System.out.println(g.addEdge(new Edge<Integer, Integer>(v1, v2, 1)));
		System.out.println(g.addEdge(new Edge<Integer, Integer>(v2, v1, 1)));
		System.out.println(g.addVertex(v1));
	}
}
