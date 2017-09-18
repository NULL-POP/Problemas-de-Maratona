import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for(int nn = 1; nn <= n; nn++)
        {
            int k = input.nextInt();
            int num = input.nextInt();
            int p = 1, q = 1;
            String bin_num = Integer.toString(num, 2);
            //bin_num = new StringBuffer(bin_num).reverse().toString();
            //System.out.println(bin_num);
            for (int i = 1; i < bin_num.length(); i++)
            {
                p = p + (bin_num.charAt(i) - '0')*q;
                q = q + (1 - (bin_num.charAt(i) - '0'))*p;
            }
            System.out.println(k+" "+p+"/"+q);
        }
    }
}