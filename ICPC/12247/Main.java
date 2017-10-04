import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int c1, c2, c3, c4, c5;

        do {
            
            c1 = sc.nextInt();
            c2 = sc.nextInt();
            c3 = sc.nextInt();
            c4 = sc.nextInt();
            c5 = sc.nextInt();
            if (c1 + c2 + c3 + c4 + c5 == 0) break;

            if (c1 > c2) { int aux = c1; c1 = c2; c2 = aux; }
            if (c2 > c3) { int aux = c2; c2 = c3; c3 = aux; }
            if (c1 > c2) { int aux = c1; c1 = c2; c2 = aux; }

            int fourth = 0, fifth = 0, start = 53;

            if (c4 > c1) fourth++;
            if (c4 > c2) fourth++;
            if (c4 > c3) fourth++;

            if (c5 > c1) fifth++;
            if (c5 > c2) fifth++;
            if (c5 > c3) fifth++;

            if (fourth + fifth == 6) start = 1;
            else if (fourth >= 2 && fifth >= 2) start = c2 + 1;
            else if (fourth == 3 || fifth == 3) start = c3 + 1;

            for (; start <= 52; start++) {
                if (start == c1) continue;
                if (start == c2) continue;
                if (start == c3) continue;
                if (start == c4) continue;
                if (start == c5) continue;

                System.out.println(start);
                break;
            }

            if (start > 52) System.out.println("-1");

        } while (c1 + c2 + c3 + c4 + c5 != 0);

    }

}