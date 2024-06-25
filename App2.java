import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class App2 {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        // pegar a file com as dimensões
        String fileName = "CatalogoGG.txt";
        // Cataloga as dimensões em uma lista
        long startTime = System.nanoTime();
        TimeUnit.SECONDS.sleep(5);
        Map<Integer, int[]> boxes = readBoxes(fileName);
        // acha a maior sequência
        int depthGraph = depthGraph(boxes);
        long endTime = System.nanoTime();
        System.out.printf("A maior sequência de caixas é: %d \n\n",depthGraph );

        double timeElapsed = endTime - startTime;
        System.out.println("Execução do programa em milissegundos: " + timeElapsed / 1000000);

    }

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
            //Apresenta o grafo e suas relações
            printGraph(graph);

            // Encontra o caminho mais longo no grafo utilizando a classe DephtGdepthGraphFirstSearch
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

        // Método para exibir o grafo no formato DOT
        //Acessar o site http://www.webgraphviz.com/
        public static void printGraph(Digraph digraph) {
            String dotRepresentation = digraph.toDot();
            System.out.println(dotRepresentation);
        }
    }



