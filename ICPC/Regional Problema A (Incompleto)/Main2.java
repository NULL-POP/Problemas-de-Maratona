import java.util.Scanner;

public class Main {

    static void createTree(int[] piano, int[][] freqTree) {

        createTree(piano, freqTree, 0, 0, piano.length - 1);

    }
    static void createTree(int[] piano, int[][] freqTree, int index, int left, int right) {

        if (left > right) return;

        for (int i = left; i <= right; i++) {
            freqTree[index][piano[i]]++;
        }

        /* Print

        System.out.println("Index is " + index);
        System.out.print("De " + left + " até " + right + " ");
        for (int i = 0; i < 9; i++) System.out.print(freqTree[index][i]);
        System.out.println();

        */

        if (left == right) return;
        createTree(piano, freqTree, 2 * index + 1, left, (left + right) / 2);
        createTree(piano, freqTree, 2 * index + 2, (left + right) / 2 + 1, right);

    }

    static void updateTree(int[] piano, int[][] freqTree, int from, int to, int offset) {

        updateTree(piano, freqTree, from, to, offset, 0, 0, piano.length - 1);

    }
    static int[] updateTree(int[] piano, int[][] freqTree, int from, int to, int offset, int index, int left, int right) {

        if (left > right) return null;
        if (left > to || right < from) return null;

        if (left == right) {

            freqTree[index][piano[left]]--;
            piano[left] = (piano[left] + offset) % 9;
            freqTree[index][piano[left]]++;

            return freqTree[index];

        }
        else {

            int[] result = new int[9];
            int[] r1 = updateTree(piano, freqTree, from, to, offset, 2 * index + 1, left, (left + right) / 2);
            int[] r2 = updateTree(piano, freqTree, from, to, offset, 2 * index + 2, (left + right) / 2 + 1, right);

            if (r1 != null) for (int i = 0; i < 9; i++) { freqTree[index][i] += r1[i]; result[i] += r1[i]; }
            if (r2 != null) for (int i = 0; i < 9; i++) { freqTree[index][i] += r2[i]; result[i] += r2[i]; }

            return result;

        }

    }

    static int mostFrequent(int[] piano, int[][] freqTree, int from, int to) {

        int[] result = mostFrequent(piano, freqTree, from, to, 0, 0, piano.length - 1);

        int top = 0, quant = -1;
        for (int i = 0; i < 9; i++) if (result[i] >= quant) { top = i > top || result[i] > quant ? i : top; quant = result[i]; }

        System.out.println(String.format("From %d to %d, the most frequent is %d", from, to, top));
        return top;

    }
    static int[] mostFrequent(int[] piano, int[][] freqTree, int from, int to, int index, int left, int right) {

        if (left > right) return null;
        if (left > to || right < from) return null;

        if (left == right) return freqTree[index];

        if (left == from && right < to || left < from && right == to) {

            int l = Math.max(from, left);
            int r = Math.min(to, right);

            int[] result = new int[9];
            for (int i = l; i < r; i++) result[piano[i]]++;

            return result;

        }
        else {

            int[] result = new int[9];
            int[] r1 = mostFrequent(piano, freqTree, from, to, 2 * index + 1, left, (left + right) / 2);
            int[] r2 = mostFrequent(piano, freqTree, from, to, 2 * index + 2, (left + right) / 2 + 1, right);

            if (r1 != null) for (int i = 0; i < 9; i++) { result[i] += r1[i]; }
            if (r2 != null) for (int i = 0; i < 9; i++) { result[i] += r2[i]; }

            return result;

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Create the piano

        int tamanhoPiano = sc.nextInt();
        int[] piano = new int[tamanhoPiano];
        for (int i = 0; i < tamanhoPiano; i++) piano[i] = 1;

        // Create the frequency tree

        int[][] freqTree = new int[tamanhoPiano * tamanhoPiano][9];
        createTree(piano, freqTree);

        // Read chords

        System.out.println("The piano is: ");
        for (int j = 0; j < tamanhoPiano; j++) System.out.print(String.format("%3d", piano[j]));
        System.out.println();
        for (int j = 0; j < tamanhoPiano; j++) System.out.print(String.format("%3d", j));
        System.out.println();
        System.out.println();

        int quantAcordes = sc.nextInt();

        for (int i = 0; i < quantAcordes; i++) {

            int from = sc.nextInt();
            int to = sc.nextInt();

            int mf = mostFrequent(piano, freqTree, from, to);
            updateTree(piano, freqTree, from, to, mf);

            System.out.println("The piano is: ");
            for (int j = 0; j < tamanhoPiano; j++) System.out.print(String.format("%3d", piano[j]));
            System.out.println();
            for (int j = 0; j < tamanhoPiano; j++) System.out.print(String.format("%3d", j));
            System.out.println();
            System.out.println();

        }

        for (int i = 0; i < tamanhoPiano; i++) System.out.println(piano[i]);

    }

}