import java.io.*;
import java.util.*;

public class App{
    public static void main(String[] args) throws FileNotFoundException {
        //pegar a file com as dimensões
        String fileName = "catalogo.txt"; 
        //dicionario com as dimensões
        List<int[]> boxes = readBoxes(fileName);
        //acha a maior sequência
        int longestSequence = findLongestNestingSequence(boxes);
        System.out.println("The longest nesting sequence length is: " + longestSequence);
    }
    
    //método que ordena e cataloga as dimensões
    public static List<int[]> readBoxes(String fileName) throws FileNotFoundException {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);

        //lista que quarda as dimensões das caixas
        List<int[]> boxes = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String[] parts = myReader.nextLine().split(" ");
            int[] dimensions = new int[3];
            for (int i = 0; i < 3; i++) {
                dimensions[i] = Integer.parseInt(parts[i]);
            }
            //ordena as dimensões da caixa
            Arrays.sort(dimensions);
            //adiciona no dicionário
            boxes.add(dimensions);
        }
        return boxes;
    }

    //Esse método compara todas as medidas já em ordem
    public static boolean canNest(int[] box1, int[] box2) {
        return box1[0] < box2[0] && box1[1] < box2[1] && box1[2] < box2[2];
    }

    //Acha a maior sequência
    public static int findLongestNestingSequence(List<int[]> boxes) {
        int n = boxes.size();
        
        //Inicializa o grafo
        //ARRUMAR!! --> mudar para a classe Graph
        //Graph G = new Graph(4) -> descobrir como importa
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        //Adiciona as caixas no grafo
        for (int i = 0; i < n; i++) {
            int contador = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && canNest(boxes.get(i), boxes.get(j))) {
                    //falta uma caixa, por algum motivo
                    System.out.println("A caixa de dimensões: " + Arrays.toString(boxes.get(i)));
                    System.out.println("Cabe dentro da caixa"+j+": " + Arrays.toString(boxes.get(j)));
                    
                    graph.get(i).add(j);
                    contador++;
                }
            }
            System.out.println(contador);
            System.out.println("---------");
        }

        //Encontra o caminho mais longo no grafo
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int maxSequenceLength = 0;

        for (int i = 0; i < n; i++) {
            maxSequenceLength = Math.max(maxSequenceLength, dfs(graph, i, memo));
        }

        return maxSequenceLength;
    }

    //ARRUMAR!! --> Não é esse método que temos que usar
    public static int dfs(Map<Integer, List<Integer>> graph, int node, int[] memo) {
        if (memo[node] != -1) {
            return memo[node];
        }
        
        // O comprimento mínimo é 1 (a própria caixa)
        int maxLength = 1;
        for (int neighbor : graph.get(node)) {
            maxLength = Math.max(maxLength, 1 + dfs(graph, neighbor, memo));
        }
        
        memo[node] = maxLength;
        return maxLength;
    }
}
