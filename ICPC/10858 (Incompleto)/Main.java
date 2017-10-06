import java.util.Scanner;
import java.util.Stack;

public class Main {

    static int maxPrime = 1500;
    static boolean[] primes = new boolean[maxPrime];

    static int size = 0;
    static int start = 0;
    static StringBuilder result;
    static Scanner sc = new Scanner(System.in);

    static void print(Stack<Integer> from) {

        if (from.isEmpty()) return;

        int n = from.pop();
        result.append(n);
        result.append(' ');
        print(from);
        from.push(n);

    }

    static void reverse(Stack<Integer> from, Stack<Integer> to) {

        if (from.isEmpty()) return;
        Integer n = from.pop();
        to.push(n);
        reverse(from, to);
        from.push(n);

    }

    static void iterate(Stack<Integer> stack) {

        Stack<Integer> istack = new Stack<>();
        reverse(stack, istack);

        while (istack.size() >= 2) {
            
            size++;
            print(istack);
            result.append('\n');
            istack.push(istack.pop() * istack.pop());

        }

    }

    static int find(int max) {

        for (int i = start; i < max; i++) {
            if (!primes[i] && max % i == 0) return i;
        }
        return -1;

    }

    public static void main(String[] args) {

        // Calculate Sieve

        for (int i = 2; i < maxPrime; i++) {
            if (primes[i]) continue;
            for (int j = i+i; j < maxPrime; j+=i) {
                primes[j] = true;
            }
        }

        // End

        int number;
        while ((number = sc.nextInt()) != 0) {

            size = 0;
            start = 2;
            result = new StringBuilder();

            Stack<Integer> stack = new Stack<Integer>();
            stack.push(number);

            int p;
            while ((p = find(stack.peek())) != -1) {
                
                number = stack.pop();

                stack.push(p);
                stack.push(number / p);

                iterate(stack);
                start = p;

            }
            //iterate(stack);

            System.out.println(size);
            if (size > 0) System.out.print(result.toString());

        }

    }

}