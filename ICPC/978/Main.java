import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> greenTeam = new PriorityQueue<Integer>(new Comparator<Integer>() {

            public int compare(Integer a, Integer b) {

                return -1 * (a - b);

            }

        });
        PriorityQueue<Integer> blueTeam = new PriorityQueue<Integer>(new Comparator<Integer>() {
            
            public int compare(Integer a, Integer b) {

                return -1 * (a - b);

            }

        });

        int casos = sc.nextInt();
        for (int c = 0; c < casos; c++) {

            if (c > 0) System.out.println();

            int campos = sc.nextInt();
            int numGreen = sc.nextInt();
            int numBlue = sc.nextInt();

            greenTeam.clear();
            blueTeam.clear();

            for (int i = 0; i < numGreen; i++) { int aux = sc.nextInt(); greenTeam.offer(aux); } 
            for (int i = 0; i < numBlue; i++) { int aux = sc.nextInt(); blueTeam.offer(aux); } 

            int[] campoVerde = new int[campos];
            int[] camposAzul = new int[campos];

            while (greenTeam.size() > 0 && blueTeam.size() > 0) {

                for (int i = 0; i < campos; i++) {
                    if (greenTeam.size() > 0) { campoVerde[i] = greenTeam.poll(); }
                    if (blueTeam.size() > 0) { camposAzul[i] = blueTeam.poll(); }
                }

                for (int i = 0; i < campos; i++) {

                    int verde = Math.max(campoVerde[i] - camposAzul[i], 0);
                    int azul = Math.max(camposAzul[i] - campoVerde[i], 0);

                    if (verde > 0) greenTeam.offer(verde);
                    if (azul > 0) blueTeam.offer(azul);

                    campoVerde[i] = 0;
                    camposAzul[i] = 0;
                }

            }

            if (greenTeam.size() == 0 && blueTeam.size() == 0) System.out.println("green and blue died");
            else if (greenTeam.size() == 0) {

                System.out.println("blue wins");
                while (blueTeam.size() > 0) System.out.println(blueTeam.poll());

            }
            else {

                System.out.println("green wins");
                while (greenTeam.size() > 0) System.out.println(greenTeam.poll());

            }

        }

    }

}