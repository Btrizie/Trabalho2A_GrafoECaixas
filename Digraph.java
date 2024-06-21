
/******************************************************************************
 *  Compilation:  javac Digraph.java
 *  Execution:    java Digraph filename.txt
 *  Dependencies: Bag.java In.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/42digraph/tinyDG.txt
 *                http://algs4.cs.princeton.edu/42digraph/mediumDG.txt
 *                http://algs4.cs.princeton.edu/42digraph/largeDG.txt  
 ******************************************************************************/

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V; // number of vertices in this digraph
	private int E; // number of edges in this digraph
	private Bag<Integer>[] adj; // adj[v] = adjacency list for vertex v
	private int[] indegree; // indegree[v] = indegree of vertex v


	public Digraph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}


	public int V() {
		return V;
	}


	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		indegree[w]++;
		E++;
	}

	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	public String toDot() {
		StringBuilder s = new StringBuilder();
		s.append("digraph {" + NEWLINE);
		s.append("rankdir = LR;"+NEWLINE);
		s.append("node [shape = circle];"+NEWLINE);
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				s.append(v + " -> " + w + ";" + NEWLINE);
			}
		}
		s.append("}");
		return s.toString();
	}



}
