import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainApp2 {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner in = new Scanner(System.in);
        String fileName, loop = " ";
        int count = 1;

        while (!loop.equals("a")) {
            System.out.printf("---Leitura[%d] de casos---\n", count);
            System.out.println("Insira qual caso deseja verificar (ex: nome.txt): ");
            fileName = in.nextLine();

            // Chama os métodos da classe App2
            long startTime = System.nanoTime();
            TimeUnit.SECONDS.sleep(5);
            Map<Integer, int[]> boxes = App2.readBoxes(fileName);
            int longestS = App2.depthGraph(boxes);
            System.out.println("A maior sequência de caixas é: " + longestS);
            //App2.showGraph(boxes);

            long endTime = System.nanoTime();
            double timeElapsed = endTime - startTime;

            System.out.println("Execução do programa em milissegundos: " + timeElapsed / 1000000);

            System.out.printf("\nO que deseja fazer a seguir?\n  (a)sair\n  (b)verificar novamente\n");
            loop = in.nextLine();
            count++;
        }
        in.close();
        System.out.println("Teste, vou apagar!");
    }
}



