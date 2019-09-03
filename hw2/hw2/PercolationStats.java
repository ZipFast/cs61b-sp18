package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private double nums[];
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
    private Percolation percolation;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        nums = new double[T];
        for (int i = 0; i < T; i++) {
            int sum = 0;
            percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                percolation.open(row, col);
                sum += 1;
            }
            double x = (double) sum / (N * N);
            nums[i] = x;
        }
        mean = StdStats.mean(nums);
        stddev = StdStats.stddev(nums);
        confidenceLow = mean - (1.96 * Math.sqrt(stddev) / Math.sqrt(T));
        confidenceHigh = mean + (1.96 * Math.sqrt(stddev) / Math.sqrt(T));
    }

    //sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    //sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    //low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceLow;
    }

    //high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceHigh;
    }

}
