import java.util.Scanner;

public class Main {

    public static class FreqTable {

        public int[] v = new int[9];

        FreqTable update(int offset) {

            int[] vv = new int[9];
            for (int i = 0; i < 9; i++) vv[(i + offset) % 9] = v[i];
            v = vv;

            return this;

        }

        FreqTable sum(FreqTable t) {

            FreqTable result = new FreqTable();

            for (int i = 0; i < 9; i++) result.v[i] = t.v[i] + this.v[i];
            
            return result;

        }

        public FreqTable() {}
        public FreqTable(int val) {

            v[val]++;

        }

        public String toString() {

            for (int i = 0; i < 9; i++) {

                if (v[i] > 0) return i + "";

            }

            return null;

        }

    }

    public static class SegmentTree {

        int n;               // Array size
        public FreqTable[] items;  // The array itself

        // Build the initial segment tree

        public void build() {

            for (int i = n - 1; i > 0; --i) {

                // T[i] = t[2 * i] + t[2 * i + 1]

                items[i] = items[i << 1].sum(items[i << 1 | 1]);

            }

        }

        // Modify the value at position

        public void modify(int p, FreqTable value) {

            // Set the value at the leaf
            // Return to each parent and update it's value

            for (items[p += n] = value; p > 1; p >>= 1) {

                // T[p / 2] = T[p] + The Sibling
                // p ^ 1 turns 2 * i into 2 * i + 1 and vice-versa

                items[p >> 1] = items[p].sum(items[p ^ 1]);

            }

        }

        public void modify(int l, int r, int value) {
            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) != 0) { items[l] = items[l].update(value); ++l; }
                if ((r & 1) != 0) { items[r-1] = items[r-1].update(value); --r; }
            }
        }

        public void push() {
            for (int i = 1; i < n; ++i) {
                items[i<<1] = items[i<<1].sum(items[i]);
                items[i<<1|1] = items[i<<1].sum(items[i]);
                items[i] = new FreqTable();
            }
        }

        // Query for the sum

        public FreqTable query(int l, int r) {

            FreqTable res = new FreqTable();

            // Make l and r start at the leaves
            
            for (l += n, r += n; l < r; l >>= 1, r >>= 1) {

                // If l is odd, sum it to the result and move to next interval
                // If r is odd, sum the previous inteval

                if ((l & 1) != 0) res = res.sum(items[l++]);
                if ((r & 1) != 0) res = res.sum(items[--r]);

            }

            return res;

        }

        FreqTable query(int p) {
            FreqTable res = new FreqTable();
            for (p += n; p > 0; p >>= 1) res = res.sum(items[p]);
            return res;
        }
        
          // Constructor

        public SegmentTree(int size) {

            n = size;
            items = new FreqTable[2 * n];

        }

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int size = sc.nextInt();

        SegmentTree s = new SegmentTree(size);
        for (int i = 0; i < size; i++) s.items[i + size] = new FreqTable(1);
        s.build();

        int acordes = sc.nextInt();

        for (int i = 0; i < acordes; i++) {

            int l = sc.nextInt();
            int r = sc.nextInt() + 1;

            int mf = -1;
            int quant = 0;
            FreqTable maisFreq = s.query(l, r);

            for (int j = 0; j < 9; j++) if (maisFreq.v[j] >= quant) { mf = j; quant = maisFreq.v[j]; }
            s.modify(l, r - 1, mf);

        }

        s.push();
        for (int i = size; i < 2 * size; i++) System.out.println(s.items[i]);

    }

}