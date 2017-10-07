import java.util.*;

public class Main {

  public static void main(String[] args){
    Scanner s = new Scanner(System.in);

    int test = s.nextInt();

    for(int i = 0;i<test;i++){
      int t1 = s.nextInt();
      int t2 = s.nextInt();
      int f = s.nextInt();
      int a = s.nextInt();
      int c1 = s.nextInt();
      int c2 = s.nextInt();
      int c3 = s.nextInt();
      int c = 0;

      int m1 = c1 + c2;
      int m2 = c1 + c3;
      int m3 = c2 + c3;
      c = m1;
      if(m2 > c){
        c = m2;
      }
      if(m3 > c){
        c = m3;
      }

      int v = t1+t2+f+a+(c/2);
      char r = 'F';
      if(v >= 90){
        r = 'A';
      }else if(v >= 80){
        r = 'B';
      }else if(v >= 70){
        r = 'C';
      }else if(v >= 60){
        r = 'D';
      }

      System.out.printf("Case %d: %c\n", i+1, r);
    }
  }
}