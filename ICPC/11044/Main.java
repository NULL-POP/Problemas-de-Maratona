import java.util.*;

public class Main{
    public static void main (String [] args){ 
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        for(int i=0;i<n;i++){
            int l = s.nextInt();
            int c = s.nextInt();
            System.out.println((int)(Math.ceil((l-2)/3.0)) * (int)(Math.ceil((c-2)/3.0)));
        }
    }
}