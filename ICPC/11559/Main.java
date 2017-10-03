import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int minPrice = -1;

            int members = sc.nextInt();
            int budget = sc.nextInt();
            int hotels = sc.nextInt();
            int weeks = sc.nextInt();

            for (int h = 0; h < hotels; h++) {

                boolean canAccomodate = false;
                int price = sc.nextInt();
                
                for (int w = 0; w < weeks; w++) {

                    int beds = sc.nextInt();
                    if (beds >= members) { canAccomodate = true; }

                }

                if (canAccomodate) {

                    int totalPrice = price * members;
                    if (totalPrice < minPrice || minPrice == -1) minPrice = totalPrice;

                }

            }

            if (minPrice == -1 || minPrice > budget) System.out.println("stay home");
            else System.out.println(minPrice);

        }

    }

}