import java.util.*;

public class Main {

  static boolean debug = false;
  static void debug(String s) { if (debug) System.out.println(s); }

  public static void main(String[] args) {

    if (args.length > 0) debug = true;

    //

    Scanner sc = new Scanner(System.in);

    int linhas;
    while ((linhas = sc.nextInt()) != 0) {
      sc.nextLine();

      int min = -1;
      int sum = 0;

      for (int i = 0; i < linhas; i++) {

        int l = 0;
        char[] s = sc.nextLine().toCharArray();

        for (int j = 0; j < 25; j++) {

          if (s[j] == ' ') l++;

        }

        if (l < min || min == -1) min = l;
        sum += l;

      }

      System.out.println(sum - (linhas * min));

    }

  }

}