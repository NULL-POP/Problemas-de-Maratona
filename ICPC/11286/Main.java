import java.util.*;

public class Main{
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);


    int n = s.nextInt();
    while(n!=0){
      HashMap<String, Integer> hash = new HashMap<>();
      int maior = 0;
      for(int i=0;i<n;i++){
        int[] c = {s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt()};
        Arrays.sort(c);
        if(!hash.containsKey(Arrays.toString(c)))
          hash.put(Arrays.toString(c), 0);

        int cur = hash.get(Arrays.toString(c))+1;
        hash.put(Arrays.toString(c), cur);
        if(cur > maior){
          maior = cur;
        }
      }

      int sum = 0;
      for(String str : hash.keySet()){
        if(hash.get(str) == maior){
          sum += maior;
        }
      }
      System.out.println(sum);

      n = s.nextInt();
    }
  }
}