import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class App2 {

        // método que ordena e cataloga as dimensões
        public static Map<Integer, int[]> readBoxes(String fileName) throws FileNotFoundException {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);

            // Dcionário que guarda as dimensões das caixas
            Map<Integer,int[]> boxes = new HashMap<>();
            int id = 0;
            while (myReader.hasNextLine()) {
                String[] parts = myReader.nextLine().split(" ");
                int[] dimensions = new int[3];
                for (int i = 0; i < 3; i++) {
                    dimensions[i] = Integer.parseInt(parts[i]);
                }
                // ordena as dimensões da caixa
                Arrays.sort(dimensions);
                // adiciona na lista
                boxes.put(id++, dimensions);
            }
            return boxes;
        }

        // Esse método compara todas as medidas, já ordenadas
        public static boolean fitsBox (int[] box1, int[] box2) {
            return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2];
        }

        // Acha a maior sequência, assim como adiciona as caixas no grafo e faz sua representação.
        public static int depht(Map<Integer, int[]> boxes) {
            int n = boxes.size();

            // Inicializa o grafo
            Digraph graph = new Digraph(n);

            // Adiciona as caixas no grafo
            for (int i = 0; i < n; i++) {
                System.out.println("A caixa de dimensões: " + Arrays.toString(boxes.get(i)));
                for (int j = 0; j < n; j++) {
                    if (i != j && fitsBox(boxes.get(i), boxes.get(j))) {
                        System.out.println("Cabe dentro da caixa "+j+": " + Arrays.toString(boxes.get(j)));
                        graph.addEdge(i, j);
                    }
                }
                System.out.println("------");
            }
            //Apresenta o grafo e suas relações
            printGraph(graph);

            // Encontra o caminho mais longo no grafo utilizando a classe DephtFirstSearch
            int maxSequenceLength = 0;
            for (int i = 0; i < n; i++) {
                DepthFirstSearch16 dfs = new DepthFirstSearch16(graph, i);
                for (int j = 0; j < n; j++) {
                    if (dfs.hasPathTo(j)) {
                        ArrayList<Integer> path = dfs.pathTo(j);
                        if (path != null && path.size() > maxSequenceLength) {
                            maxSequenceLength = path.size();
                        }
                    }
                }
            }
            return maxSequenceLength;
        }

       // Método para exibir o grafo no formato DOT
        //Acessar o site http://www.webgraphviz.com/
        public static void printGraph(Digraph digraph) {
            String dotRepresentation = digraph.toDot();
            System.out.println(dotRepresentation);
        }
    }


