package util;

import java.util.ArrayList;
import java.util.List;

public class Graph<T, E> {
	private List<List<Vertex<T>>> graph;
	private List<Vertex<T>> vertex_list;
	private List<Edge<T, E>> edge_list;
	private isDirected direction;

	protected static int key = 0; // key for vertices

	/* builder for new graph */
	public Graph(isDirected direction) {
		graph = new ArrayList<>();
		vertex_list = new ArrayList<>();
		edge_list = new ArrayList<>();
		this.direction = direction;
	}

	/* builder for graph with data */
	public Graph(List<Vertex<T>> vertex_list, List<Edge<T, E>> edge_list, isDirected direction) {
		this.vertex_list = vertex_list;
		this.edge_list = edge_list;
		this.direction = direction;
		List<List<Vertex<T>>> tempGraph = new ArrayList<>();
		List<Edge<T, E>> tempEdges = new ArrayList<>();
		tempGraph.add(vertex_list);
		tempEdges = edge_list;
		if (direction.equals(isDirected.NOT_DIRECTED)) // builds not directed graph
			for (int i = 0; i < vertex_list.size(); i++) {
				for (int j = 0; j < tempEdges.size(); j++) {
					if (tempEdges.get(j).getV1().equals(tempGraph.get(i).get(0)))
						tempGraph.get(i).add(tempEdges.get(j).getV2());
					for (int k = 0; k < vertex_list.size(); k++)
						if (tempEdges.get(j).getV2().equals(tempGraph.get(k).get(0)))
							tempGraph.get(k).add(tempEdges.get(j).getV1());
					tempEdges.remove(j);
					continue;
				}
			}
		else// builds directed graph
			for (int i = 0; i < vertex_list.size(); i++)
				for (int j = 0; j < tempEdges.size(); j++)
					if (tempEdges.get(j).getV1().equals(tempGraph.get(i).get(0))) {
						tempGraph.get(i).add(tempEdges.get(j).getV2());
						tempEdges.remove(j);
						continue;
					}
		graph = tempGraph;
	}

	/* Getters and Setters */
	/* v-----------------------------------------------------------v */
	public void setDirection(isDirected direction) {
		this.direction = direction;
	}

	public isDirected getDirection() {
		return direction;
	}

	public List<List<Vertex<T>>> getGraph() {
		return graph;
	}

	public List<Vertex<T>> getVertices() {
		return vertex_list;
	}

	public List<Edge<T, E>> getEdges() {
		return edge_list;
	}

	public List<Vertex<T>> getNeighbors(Vertex<T> v) {
		List<Vertex<T>> temp = new ArrayList<>();
		for (int i = 0; i < vertex_list.size(); i++)
			if (vertex_list.get(i).equals(v)) {
				temp.addAll(graph.get(i));
				temp.remove(0);
				break;
			}
		return temp;
	}
	/* ^-----------------------------------------------------------^ */

	/* adds a vertex to the graph */
	public boolean addVertex(Vertex<T> vertex) {
		/* checks if the vertex is in the graph already */
		/* v-----------------------------------------------------------v */
		for (int i = 0; i < graph.size(); i++)
			if (graph.get(i).get(0).equals(vertex))
				return false;
		/* ^-----------------------------------------------------------^ */

		List<Vertex<T>> temp = new ArrayList<>();
		temp.add(vertex);
		graph.add(temp);
		vertex_list.add(vertex);
		return true;
	}

	/* adds an edge to the graph and connects the vertices */
	public boolean addEdge(Edge<T, E> e) {

		/* checks if the edge is in the graph already */
		/* v-----------------------------------------------------------v */
		if (edge_list.contains(e))
			return false;
		if (direction.equals(isDirected.NOT_DIRECTED)) { // checks if the edge is in the non-directed graph
			for (int i = 0; i < graph.size(); i++)
				if (graph.get(i).get(0).equals(e.getV1())) {
					if (graph.get(i).contains(e.getV2()))
						return false;
				} else if (graph.get(i).get(0).equals(e.getV2()))
					if (graph.get(i).contains(e.getV1()))
						return false;
		} else // checks if the edge is in the directed graph
			for (int i = 0; i < graph.size(); i++)
				if (graph.get(i).get(0).equals(e.getV1()))
					if (graph.get(i).contains(e.getV2()))
						return false;
		/* ^-----------------------------------------------------------^ */

		if (direction.equals(isDirected.NOT_DIRECTED)) // adds a non-directed edge to the graph
			for (int i = 0; i < graph.size(); i++) {
				if (graph.get(i).get(0).equals(e.getV1()))
					graph.get(i).add(e.getV2());
				if (graph.get(i).get(0).equals(e.getV2()))
					graph.get(i).add(e.getV1());
			}
		else
			for (int i = 0; i < graph.size(); i++) // adds a directed edge to the graph
				if (graph.get(i).get(0).equals(e.getV1()))
					graph.get(i).add(e.getV2());
		return true;
	}

	/*
	 * connects two vertices by their given value only if this value is a string the
	 * user's responsibility to give different names to the vertices to avoid errors
	 */
	public boolean connectByName(String v1, String v2) {
		int count = 0;
		int index1 = -1;
		int index2 = -1;
		Vertex<T> tempVer = new Vertex<T>();
		for (int i = 0; i < vertex_list.size(); i++) {
			tempVer = vertex_list.get(i);
			if (!tempVer.getValue().getClass().equals(v1.getClass()))
				return false;
			if (tempVer.getValue().equals(v1)) {
				if (index1 == -1) {
					count++;
					index1 = i;
				}
			} else if (tempVer.getValue().equals(v2))
				if (index2 == -1) {
					count++;
					index2 = i;
				}
			if (count == 2)
				break;
		}
		if (count != 2)
			return false;
		Edge<T, E> tempEdge = (Edge<T, E>) new Edge<T, E>(vertex_list.get(index1), vertex_list.get(index2));
		addEdge(tempEdge);
		return true;
	}
}
