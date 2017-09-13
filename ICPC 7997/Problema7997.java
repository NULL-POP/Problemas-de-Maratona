import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class Problema7997 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int testes = sc.nextInt();

    // Ler todas as entradas

    for (int caso = 0; caso < testes; caso++) {

      int g = sc.nextInt();
      String entrada = sc.next();
      ArrayList<Integer> notas = new ArrayList<>();
      PriorityQueue<Integer> menoresNotas = new PriorityQueue<>();

      // Ler entradas

      int i = 0;
      int soma = 0;

      while (i < entrada.length()) {

        char c = entrada.charAt(i);

        if (c == '0') {

          notas.add(0);
          menoresNotas.offer(0);
          i++;

        }
        else if (i < entrada.length()-2 && c == '1' && entrada.charAt(i+1) == '0' && entrada.charAt(i+2) == '0') {

          notas.add(100);
          menoresNotas.offer(100);
          soma += 100;
          i += 3;

        }
        else if (i < entrada.length() -1 && c > entrada.charAt(i+1)) {

          Integer nota = new Integer(c + entrada.charAt(i+1));
          notas.add(nota);
          menoresNotas.offer(nota);
          soma += nota;

          i += 2;

        }
        else {

          Integer nota = new Integer(c + "");
          notas.add(nota);
          menoresNotas.offer(nota);
          soma += nota;
          i++;

        }

      }

      System.out.println("Soma: " + soma);

      // Quebrar notas

      int tamanho = notas.size();

      while (tamanho < g) {

        Integer menor = menoresNotas.poll();
        if (menor >= 10) {

          soma -= menor;
          soma += menor / 10;
          soma += menor % 10;
          tamanho++;

        }

      }

      // Merge notas

      while (tamanho > g) {

        int maior = -1;
        int index = -1;

        for (int j = 0; j < notas.size(); j++) {
          if (j < notas.size() - 1 && notas.get(j) < 10 && notas.get(j+1) < 10) {
            if (notas.get(j) > maior) {
              maior = notas.get(j);
              index = j;
            }
          }
        }

        soma -= notas.get(index);
        soma -= notas.get(index-1);
        soma += notas.get(index) * 10 + notas.get(index+1);

        tamanho--;

      }

      // Print

      System.out.println(soma / tamanho);

    }

  }

}
