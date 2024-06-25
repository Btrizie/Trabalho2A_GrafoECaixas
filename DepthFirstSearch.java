import java.util.ArrayList;
import java.util.Collections;

public class DepthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstSearch(Digraph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        ArrayList<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }
}