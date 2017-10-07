import java.util.*;

public class Main{

  static boolean debug = false;
  static void d(String s) {
    if (debug) System.out.println(s);
  }

  public static void main(String[] args){

    if (args.length > 0) debug = true;

    Scanner sc = new Scanner(System.in);

    int count = sc.nextInt();
    sc.nextLine();

    int[] hash = {1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,4,1,2,3,1,2,3,4};
    for(int i=0;i<count;i++){
      char[] str = sc.nextLine().toCharArray();

      d("Banana");

      int clicks = 0;
      for(int j=0;j<str.length;j++){

        if(str[j] == ' '){
            clicks += 1;
        }else{
            clicks += hash[str[j]-'a'];
        }
      }
      System.out.printf("Case #%d: %d\n", i+1, clicks);
    }

  }
}