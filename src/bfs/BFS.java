package bfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import util.Graph;
import util.Vertex;
import util.isDirected;

/*Color code:
 * WHITE - not yet addressed
 * RED - in progress
 * BLACK - finished processing*/

public class BFS<T, E> {
	private VertexBFS<T> s; // the source of the BFS
	private List<VertexBFS<T>> vertices; // list of all the vertices in the form of vertexBFS
	private Queue<VertexBFS<T>> Q; // queue for the vertices to check
	private List<VertexBFS<T>> path = new ArrayList<>(); // contains the path to a specific vertex
	private boolean flag = false;// flag to clean the path after usage

	/* initialize the BFS and runs it on a given vertex */
	public BFS(Graph<T, E> g, Vertex<T> s) {
		List<VertexBFS<T>> temp = new ArrayList<>();
		Vertex<T> tempVer = new Vertex<>();
		List<VertexBFS<T>> tempNeighbors = new ArrayList<>();
		for (int i = 0; i < g.getVertices().size(); i++) {
			tempVer = g.getVertices().get(i);
			for (int j = 0; j < g.getNeighbors(tempVer).size(); j++)
				tempNeighbors.add((VertexBFS<T>) g.getNeighbors(tempVer).get(j)); // ***** need to fix casting
			if (tempVer.equals(s)) {
				this.s = (VertexBFS<T>) tempVer;
				this.s.setColor(Color.RED);
				this.s.setDistance(0);
				this.s.setPrevious(null);
				this.s.setNeighbors(tempNeighbors);
			} else {
				temp.add((VertexBFS<T>) tempVer);
				temp.get(i).setColor(Color.WHITE);
				temp.get(i).setDistance(Integer.MAX_VALUE);
				temp.get(i).setPrevious(null);
				temp.get(i).setNeighbors(tempNeighbors);
			}
		}
		vertices = temp;
		vertices.add(this.s);
		Q.add(this.s);
		run();
	}

	/* initialize the BFS and runs it on a given vertex's name */
	public BFS(Graph<T, E> g, String name) {
		for (int i = 0; i < g.getVertices().size(); i++)
			if (g.getVertices().get(i).getValue().equals(name)) {
				new BFS<T, E>(g, g.getVertices().get(i));
				break;
			}
	}

	private void run() {
		VertexBFS<T> temp;
		while (!Q.isEmpty()) {
			temp = Q.remove();
			for (VertexBFS<T> ver : temp.getNeighbors())
				if (ver.getColor().equals(Color.WHITE)) {
					ver.setColor(Color.RED);
					ver.setDistance(temp.getDistance() + 1);
					ver.setPrevious(temp);
					Q.add(ver);
				}
			temp.setColor(Color.BLACK);
		}
	}

	/* returns distance from a specific vertex */
	public int getDistance(VertexBFS<T> s) {
		return s.getDistance();
	}

	/* returns the path to a specific vertex */
	public List<VertexBFS<T>> getPath(VertexBFS<T> s) {
		if (flag) {
			path.removeAll(vertices);
			flag = false;
		}
		if (s.getDistance() == Integer.MAX_VALUE)
			return null;
		path.add(s);
		if (s.equals(this.s)) {
			flag = true;
			return path;
		}
		return getPath(s.getPrevious());
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (VertexBFS<T> ver : vertices)
			str.append("vertex " + ver.getValue() + "has distance of: " + ver.getDistance() + "\n");
		return str.toString();
	}

	public static void main(String[] args) {
		Graph<String, Integer> g = new Graph<String, Integer>(isDirected.NOT_DIRECTED);
		g.addVertex(new Vertex<String>("v"));
		g.addVertex(new Vertex<String>("r"));
		g.addVertex(new Vertex<String>("s"));
		g.addVertex(new Vertex<String>("w"));
		g.addVertex(new Vertex<String>("t"));
		g.addVertex(new Vertex<String>("x"));
		g.addVertex(new Vertex<String>("u"));
		g.addVertex(new Vertex<String>("y"));
		g.connectByName("v", "r");
		g.connectByName("r", "s");
		g.connectByName("s", "w");
		g.connectByName("w", "t");
		g.connectByName("w", "x");
		g.connectByName("t", "u");
		g.connectByName("x", "t");
		g.connectByName("x", "y");
		g.connectByName("u", "y");
		System.out.println(new BFS<String, Integer>(g, "s"));
	}
}
