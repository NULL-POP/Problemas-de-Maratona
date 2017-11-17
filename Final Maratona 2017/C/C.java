import java.util.*;

public class C {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int l = sc.nextInt();

        int quant = (int) Math.floor((float)(l + 1) / (float) n);

        HashSet<Integer> toGrow = new HashSet<>();
        HashSet<Integer> toFall = new HashSet<>();

        int[] freqs = new int[n+1];

        for (int i = 0; i < l; ++i) freqs[sc.nextInt()]++;
        for (int i = 1; i < n+1; ++i) {

            if (freqs[i] > quant) {

                toFall.add(i);
                if (Math.abs(freqs[i] - quant) > 1) { System.out.println("*"); return; }

            }
            else if (freqs[i] < quant) {
                
                toGrow.add(i);
                if (Math.abs(freqs[i] - quant) > 1) { System.out.println("*"); return; }

            }

        }

        if (toGrow.size() > 1 || toFall.size() > 1) { System.out.println("*"); return; }

        if (toGrow.size() == 1 && toFall.size() == 1) {

            int grow = toGrow.iterator().next();
            int fall = toFall.iterator().next();

            System.out.println("-" + fall + " +" + grow);
            return;

        }
        else if (toGrow.size() == 1) {

            int grow = toGrow.iterator().next();
            System.out.println("+" + grow);
            return;

        }
        else if (toFall.size() == 1) {

            int fall = toFall.iterator().next();
            System.out.println("-" + fall);
            return;

        }
        else {

            System.out.println("*");

        }

        sc.close();

    }

}