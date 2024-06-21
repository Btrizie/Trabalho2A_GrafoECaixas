import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        // pegar a file com as dimensões
        String fileName = "testeG.txt";
        // Cataloga as dimensões em uma lista
        List<int[]> boxes = readBoxes(fileName);
        // acha a maior sequência
        int depthGraph = depthGraph(boxes);
        System.out.printf("A maior sequência de caixas é: %d \n\n",depthGraph );

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
        myReader.close();
        return boxes;
    }

    // Esse método compara todas as medidas já em ordem
    public static boolean ifFits(int[] box1, int[] box2) {
        return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2];
    }

    // Acha a maior sequência
    public static int depthGraph(List<int[]> boxes) {
        int n = boxes.size();

        // Inicializa o grafo
        Digraph graph = new Digraph(n);

        // Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            System.out.println("A caixa "+i+" de dimensões: " + Arrays.toString(boxes.get(i)));
            for (int j = 0; j < n; j++) {
                if (i != j && ifFits(boxes.get(i), boxes.get(j))) {
                    System.out.println("Cabe dentro da caixa "+j+": " + Arrays.toString(boxes.get(j)));
                    graph.addEdge(i, j);
                }
            }
            System.out.println("------");
        }

        // Encontra o caminho mais longo no grafo utilizando a classe DephtFirstSearch
        int maxSequenceL = 0;
        for (int i = 0; i < n; i++) {
            DepthFirstSearch dfs = new DepthFirstSearch(graph, i);
            for (int j = 0; j < n; j++) {
                if (dfs.hasPathTo(j)) {
                    ArrayList<Integer> path = dfs.pathTo(j);
                    if (path != null && path.size() > maxSequenceL) {
                        maxSequenceL = path.size();
                    }
                }
            }
        }
        return maxSequenceL;
    }


    public static void showGraph(List<int[]> boxes) {
        int n = boxes.size();
        Digraph graph = new Digraph(n);

        // Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && ifFits(boxes.get(i), boxes.get(j))) {
                    graph.addEdge(i, j);
                }
            }
        }

        // Exibir o grafo no formato DOT
        //Acessar o site http://www.webgraphviz.com/
        String dotRepresentation = graph.toDot();
        System.out.println(dotRepresentation);
    }
}
