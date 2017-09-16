import java.util.Arrays;
import java.util.Scanner;

/*
1
1
1 48 -1 -1 33 -1 63 18
30 51 -1 3 -1 -1 -1 -1
-1 -1 -1 -1 15 -1 -1 -1
-1 -1 -1 45 -1 -1 36 -1
-1 -1 25 -1 9 -1 21 60
-1 -1 -1 -1 24 57 12 -1
-1 6 -1 -1 39 -1 -1 -1
54 -1 42 -1 -1 -1 -1 -1
*/

public class Main {

    static int[][] m;
    static int[] rowSum;
    static int[] colSum;

    static void set(int x, int y, int val) {

        int sum = m[y][x] == -1 ? 0 : m[y][x];

        rowSum[y] -= sum;
        rowSum[y] += val;

        colSum[x] -= sum;
        colSum[x] += val;

    }

    static boolean check(int x, int y, int val) {

        if (rowSum[y] + val > 260) return false;
        if (colSum[x] + val > 260) return false;
        return true;

    }

    static boolean rec(int x, int y, int val, int dir) {

        // System.out.println(Arrays.toString(rowSum));

        if (val < 1 || val > 64) return true;
        if (x < 0 || x > 7 || y < 0 || y > 7) return false;
        if (m[y][x] > 0 && m[y][x] != val) return false;

        boolean rightPosition = m[y][x] == val;

        if (!rightPosition) {
            if (!check(y, x, val)) return false;
            set(y, x, val);
        }

        boolean flag = false;

        for (int xx = x-2; xx <= x+2 && !flag; xx += 4) {
            for (int yy = y-1; yy <= y+1 && !flag; yy += 2) {

                boolean a = rec(xx, yy, val + dir, dir);
                if (a) flag = true;

            }
        }

        for (int xx = x-1; xx <= x+1 && !flag; xx += 2) {
            for (int yy = y-2; yy <= y+2 && !flag; yy += 4) {

                boolean a = rec(xx, yy, val + dir, dir);
                if (a) flag = true;

            }
        }

        if (!flag && !rightPosition) set(y, x, -1);
        return flag;

    }

    static void clear() {

        for (int i = 0; i < 8; i++) {

            rowSum[i] = 0;
            colSum[i] = 0;

            for (int j = 0; j < 8; j++) {

                m[i][j] = -1;

            }

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int casos = sc.nextInt();
        m = new int[8][8];
        rowSum = new int[8];
        colSum = new int[8];

        for (int i = 0; i < casos; i++) {

            int dataSet = sc.nextInt();
            clear();

            // Ler Entradas

            int posX = -1;
            int posY = -1;

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {

                    int val = sc.nextInt();
                    if (val == 1) { posX = x; posY = y; }
                    set(y, x, val);

                }
            }

            // Tentar preencher

            if (posX >= 0) {

                rec(posX, posY, 1, 1);

            }
            else {

                boolean flag = false;
                for (int k = 0; k < 8 && !flag; k++) {
                    for (int l = 0; l < 8 && !flag; l++) {
                        if (m[k][l] == -1) {
                            if (rec(k, l, 1, 1)) flag = true;
                        }
                    }
                }

            }

            System.out.println(dataSet);
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l < 8; l++) {
                    System.out.print(m[k][l]);
                }
                if (k != 7) System.out.println();
            }

        }

    }

}