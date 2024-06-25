import java.util.ArrayList;
import java.util.Collections;

public class DepthFirstSearch2 {
    private  boolean[] marked;
    private int[] edgeTo;
    private int[] distanceTo;

    public DepthFirstSearch(Digraph g){
        //inicializar os vetores
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distanceTo = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distanceTo[v] = -1;
        }
    }

    //Métodos dfs e hasPathTo não foram necessarios

    public int PathTo (Digraph g, int v){
        if (distanceTo[v] != -1) 
            return distanceTo[v];
        //marcar como visitado
        marked[v] = true;
        int maxLength = 0;
        // para cada vertice : pegue os adjacentes
        for (int w : g.adj(v)) {
            int length = PathTo(g, w) + 1;
            if (length > maxLength) {
                maxLength = length;
                //nao esta marcado, pode ser visitado
                if(!marked[w]){
                    edgeTo[v] = w;
                }
            }
        }
        //adiciona a lista a maior distancia detectada
        distanceTo[v] = maxLength;
        return maxLength;
    //PathTo não recursivo, pois só precisamos da distancia
    }



    public ArrayList<Integer> findLongestPath(Digraph g) {
        int maxLength = 0;
        int endV = 0;

        for (int v = 0; v < g.V(); v++) {
            int length = PathTo(g, v);
            if (length > maxLength) {
                maxLength = length;
                endV = v;
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        for (int x = endV; distanceTo[x] != 0; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(0);
        Collections.reverse(path);

        return path;
    }

}



