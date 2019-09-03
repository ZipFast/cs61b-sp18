package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
// create N-by-N grid. with all sites initially blocked
public class Percolation {
    private boolean[][] grid;
    private int numOfOpen;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf_without_tail;
    private int head;
    private int tail;
    private int N;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        uf_without_tail = new WeightedQuickUnionUF(N * N + 1);
        head = N * N;
        tail = N * N + 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
        }
        numOfOpen = 0;
    }
// open the site (row, col) if it is not open
    public void open(int row, int col) {
        if (invalidIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        int[] nextR = {1, -1, 0, 0};
        int[] nextC = {0, 0, 1, -1};
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOfOpen += 1;
            int newRow, newCol;
            if (row == 0) {
                uf.union(getIndex(row, col), head);
                uf_without_tail.union(getIndex(row, col), head);
            }
            if (row == N - 1) {
                uf.union(getIndex(row, col), tail);
            }
            for (int k = 0; k < 4; k++) {
                newRow = row + nextR[k];
                newCol = col + nextC[k];
                if (!invalidIndex(newRow, newCol) && isOpen(newRow, newCol)) {
                    uf.union(getIndex(newRow, newCol), getIndex(row, col));
                    uf_without_tail.union(getIndex(newRow, newCol), getIndex(row, col));
                }
            }
        }
    }

   // is the site (row, col) open ?
    public boolean isOpen(int row, int col) {
        if (invalidIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }

    // is the site (row, col) full ?
    public boolean isFull(int row, int col) {
        if (invalidIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }
       return  uf_without_tail.connected(getIndex(row, col), head);
    }

    //numbers of open sites
    public int numberOfOpenSites() {
        return numOfOpen;
    }

    // does the system percolates?
    public boolean percolates() {
        return uf.connected(head, tail);
    }

    //help function
    private int getIndex(int row, int col) {
        return row * N + col;
    }

    private boolean invalidIndex(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return true;
        }
        return false;
    }
}
