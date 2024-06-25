import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App3 {

    // Método que ordena e cataloga as dimensões
    public static Map<Integer, int[]> readBoxes(String fileName) throws FileNotFoundException {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);

        // Dicionário que guarda as dimensões das caixas
        Map<Integer, int[]> boxes = new HashMap<>();
        int id = 0;
        while (myReader.hasNextLine()) {
            String[] parts = myReader.nextLine().split(" ");
            int[] dimensions = new int[3];
            for (int i = 0; i < 3; i++) {
                dimensions[i] = Integer.parseInt(parts[i]);
            }
            // Ordena as dimensões da caixa
            Arrays.sort(dimensions);
            // Adiciona na lista
            boxes.put(id++, dimensions);
        }
        myReader.close();
        return boxes;
    }

    // Esse método compara todas as medidas, já ordenadas
    public static boolean ifFits(int[] box1, int[] box2) {
        return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2];
    }

    // Acha a maior sequência, assim como adiciona as caixas no grafo e faz sua representação.
    public static int depthGraph(Map<Integer, int[]> boxes) {
        int n = boxes.size();

        // Inicializa o grafo
        Digraph graph = new Digraph(n);

        // Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && ifFits(boxes.get(i), boxes.get(j))) {
                    graph.addEdge(i, j);
                }
            }
        }
        // Apresenta o grafo e suas relações
        printGraph(graph);

        // Encontra o caminho mais longo no grafo utilizando a classe DepthFirstSearch
        int[] longestPathLength = new int[n];
        Arrays.fill(longestPathLength, -1);

        int maxSequenceLength = 0;
        for (int v = 0; v < n; v++) {
            int length = findLongestPath(graph, v, longestPathLength);
            if (length > maxSequenceLength) {
                maxSequenceLength = length;
            }
        }
        return maxSequenceLength + 1; // O comprimento do caminho é o número de caixas menos 1
    }

    private static int findLongestPath(Digraph graph, int v, int[] longestPathLength) {
        if (longestPathLength[v] != -1) {
            return longestPathLength[v];
        }
        int maxLength = 0;
        for (int w : graph.adj(v)) {
            int length = findLongestPath(graph, w, longestPathLength);
            if (length + 1 > maxLength) {
                maxLength = length + 1;
            }
        }
        longestPathLength[v] = maxLength;
        return maxLength;
    }

    // Método para exibir o grafo no formato DOT
    // Acessar o site http://www.webgraphviz.com/
    public static void printGraph(Digraph digraph) {
        String dotRepresentation = digraph.toDot();
        System.out.println(dotRepresentation);
    }
}