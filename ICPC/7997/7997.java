import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class P {

  public static void main(String[] args) {

    int casos = sc.nextInt();

    for (int c = 0; c < casos; c++) {

      int g = sc.nextInt();
      char[] e = sc.next().toCharArray();

      //

      for (int i = 3; i >= 1; i--) {

        int quant = e.length / i;
        if (quant < g) continue;

        for (int k = 1; k < quant; k++) {

          int index = -1;
          int maior = -1;

          for (int j = 0; j <= e.length - i; j++) {
            int soma = 0;
            boolean flag = false;
            for (int l = 0; l < i; l++) { if (e[j + l] == 'x') { flag = true; break; } soma *= 10; soma += e[j + l] - '0'; }
            if (!flag && soma <= 100 && soma > maior) { maior = soma; index = j; }
          }

          for (int l = 0; l < i; l++) { e[j + l] = 'x'; }

        }

      }

    }

  }

}
