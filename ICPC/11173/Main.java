import java.util.*;

/*

This problem can be solved in two ways:

 - Checking if the number is larger than or equal to the half of the corresponding power of 2
 - "Adding" the power of two that corresponds to the current number of bits

*/

public class Main {

    // Using halves

    public static void mainHalves(String[] args) {

        Scanner sc = new Scanner(System.in);

        int casos = sc.nextInt();
        for (int c = 0; c < casos; c++) {

            int n = sc.nextInt();
            int k = sc.nextInt();

            int result = 0;
            while (n > 0) {

                result = result << 1;
                int metade = (int) Math.pow(2, n) / 2;

                if (k >= metade) {

                    result = result | 1;
                    k = metade - (k - metade) - 1;

                }

                n--;

            }

            System.out.println(result);

        }

        sc.close();

    }

    // Using powers (not working)

    public static void mainPowers(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Calculate all powers of 2

        int[] powers = new int[32];
        for (int i = 0; i < 31; i++) powers[i+1] = 1 << i;

        // Read input

        int casos = sc.nextInt();
        while (casos > 0) {

            int result = 0;
            int n = sc.nextInt();
            int k = sc.nextInt();
            while (n > 0) { result += k >= powers[n-1] / 2 ? powers[n-1] : 0; n--; }
            System.out.println(result);
            casos--;

        }

    }

    // Main

    public static void main(String[] args) {

        mainPowers(args);

    }

}