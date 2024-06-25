import java.util.ArrayList;
import java.util.List;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // number of vertices in this digraph
	private int E; // number of edges in this digraph
	private List<Integer>[] adj; // adj[v] = adjacency list for vertex v

	/**
	 * Initializes an empty digraph with <em>V</em> vertices.
	 *
	 * @param V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public Digraph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (List<Integer>[]) new ArrayList[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new ArrayList<Integer>();
		}
	}

	/**
	 * Returns the number of vertices in this digraph.
	 *
	 * @return the number of vertices in this digraph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this digraph.
	 *
	 * @return the number of edges in this digraph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Adds the directed edge v→w to this digraph.
	 *
	 * @param v the tail vertex
	 * @param w the head vertex
	 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		E++;
	}

	/**
	 * Returns the vertices adjacent from vertex {@code v} in this digraph.
	 *
	 * @param v the vertex
	 * @return the vertices adjacent from vertex {@code v} in this digraph, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns this graph as an input for GraphViz (dot format).
	 *
	 * @return dot graph representation
	 */
	public String toDot() {
		StringBuilder s = new StringBuilder();
		s.append("digraph {" + NEWLINE);
		s.append("rankdir = LR;" + NEWLINE);
		s.append("node [shape = circle];" + NEWLINE);
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				s.append(v + " -> " + w + ";" + NEWLINE);
			}
		}
		s.append("}");
		return s.toString();
	}
}