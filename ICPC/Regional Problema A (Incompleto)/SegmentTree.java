// Sum Segment Tree Implementation

import java.util.Scanner;

public class SegmentTree {

    int n;               // Array size
    public int[] items;  // The array itself

    // Build the initial segment tree

    public void build() {

        for (int i = n - 1; i > 0; --i) {

            // T[i] = t[2 * i] + t[2 * i + 1]

            items[i] = items[i << 1] + items[i << 1 | 1];

        }

    }

    // Modify the value at position

    public void modify(int p, int value) {

        // Set the value at the leaf
        // Return to each parent and update it's value

        for (items[p += n] = value; p > 1; p >>= 1) {

            // T[p / 2] = T[p] + The Sibling
            // p ^ 1 turns 2 * i into 2 * i + 1 and vice-versa

            items[p >> 1] = items[p] + items[p ^ 1];

        }

    }

    // Query for the sum

    public int query(int l, int r) {

        int res = 0;

        // Make l and r start at the leaves
        
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {

            // If l is odd, sum it to the result and move to next interval
            // If r is odd, sum the previous inteval

            if ((l & 1) != 0) res += items[l++];
            if ((r & 1) != 0) res += items[--r];

        }

        return res;

    }

    // Constructor

    public SegmentTree(int size) {

        n = size;
        items = new int[2 * n];

    }

}