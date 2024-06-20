import java.util.ArrayList;

public class DepthFirstSearch16 {
    private int s;
    private  boolean[] marked;
    private int[] edgeTo;

    public DepthFirstSearch16(Digraph g, int s){
        this.s = s;
        //inicializar os vetores
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        //comeca caminhamento recursivo - vertice inicial - s
        dfs(g,s);
    }

    //boolean hasPathTo(v) Há um caminho de s a v?
    public boolean hasPathTo(int v){
        return marked[v];
    }
    public ArrayList<Integer> pathTo (int v) {
        // ver se tem caminho
        if (!hasPathTo(v))
            return null;
        //vamos criar a lista para preencher
        ArrayList<Integer> path = new ArrayList<>();
        //enquanto nao cheguei no vertice inicial S, sigo fazendo o caminho
        while (v != s) {
            // adicionar v no caminho
            path.add(0, v); //insere no inicio
            // queremos a posicao anterior, onde veio
            v = edgeTo[v];
            //vai chegar no zero e vai funcionar
        }
        path.add(0, s); //vertice inicial, sempre o mesmo
        return path;
    }
    public void dfs (Digraph g, int v){
        // algoritmo
        // para cada vertice :
        // 1. marcar o vertice como visitado
        // 2. RECURSIVAMENTE: visita todos os adj[v] que n estao marcados

        //System.out.println("Entrei: " + v);
        //marcar como visitado
        marked[v] = true;
        //para teste inicial - dos adjacentes
        for (int w : g.adj(v)){
            // para cada vertice : pegue os adjacentes
           // System.out.println("Adjacente: "+ w);
            //temos os adj . agora: quem nao esta marcado?
            if(!marked[w]){
                //nao esta marcado, pode ser visitado

                edgeTo[w] = v;
                //----------------------------
                dfs(g, w);
            }
        }

    }


    }



