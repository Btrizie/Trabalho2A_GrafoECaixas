import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        // pegar a file com as dimensões
        String fileName = "catalogo.txt";
        // dicionario com as dimensões
        List<int[]> boxes = readBoxes(fileName);
        // acha a maior sequência
        int longestSequence = findLongestNestingSequence(boxes);
        System.out.println("The longest nesting sequence length is: " + longestSequence);

        // Exibir o grafo no formato DOT
        showGraph(boxes);
    }

    // método que ordena e cataloga as dimensões
    public static List<int[]> readBoxes(String fileName) throws FileNotFoundException {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);

        // lista que guarda as dimensões das caixas
        List<int[]> boxes = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String[] parts = myReader.nextLine().split(" ");
            int[] dimensions = new int[3];
            for (int i = 0; i < 3; i++) {
                dimensions[i] = Integer.parseInt(parts[i]);
            }
            // ordena as dimensões da caixa
            Arrays.sort(dimensions);
            // adiciona na lista
            boxes.add(dimensions);
        }
        return boxes;
    }

    // Esse método compara todas as medidas já em ordem
    public static boolean canNest(int[] box1, int[] box2) {
        return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2];
    }

    // Acha a maior sequência
    public static int findLongestNestingSequence(List<int[]> boxes) {
        int n = boxes.size();

        // Inicializa o grafo
        Digraph graph = new Digraph(n);

        // Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            System.out.println("A caixa de dimensões: " + Arrays.toString(boxes.get(i)));
            for (int j = 0; j < n; j++) {
                if (i != j && canNest(boxes.get(i), boxes.get(j))) {
                    System.out.println("Cabe dentro da caixa"+j+": " + Arrays.toString(boxes.get(j)));
                    graph.addEdge(i, j);
                }
            }
            System.out.println("------");
        }

        // Encontra o caminho mais longo no grafo
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int maxSequenceLength = 0;

        for (int i = 0; i < n; i++) {
            maxSequenceLength = Math.max(maxSequenceLength, dfs(graph, i, memo));
        }
        return maxSequenceLength;
    }

    // Método de busca em profundidade (DFS)
    public static int dfs(Digraph graph, int node, int[] memo) {
        if (memo[node] != -1) {
            return memo[node];
        }

        // O comprimento mínimo é 1 (a própria caixa)
        int maxLength = 1;
        for (int neighbor : graph.adj(node)) {
            maxLength = Math.max(maxLength, 1 + dfs(graph, neighbor, memo));
        }

        memo[node] = maxLength;
        return maxLength;
    }

    // Método para exibir o grafo no formato DOT
    public static void showGraph(List<int[]> boxes) {
        int n = boxes.size();
        Digraph graph = new Digraph(n);

        // Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && canNest(boxes.get(i), boxes.get(j))) {
                    graph.addEdge(i, j);
                }
            }
        }

        // Exibir o grafo no formato DOT
        String dotRepresentation = graph.toDot();
        System.out.println(dotRepresentation);
    }
}
