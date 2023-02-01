// multiple knapsack/processors with limits  --------- m no. of knapsack
// multiple jobs/processes with profits and burst time --------- n no. of processes
// maximum profit with Approximation algorithm
// greedy method   ---------------------time complexity O(2^n*m) 

public class MultipleKnapsack {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack(int capacity, int burstT[], int profit[], int n) {
        // Base Case
        if (n == 0 || capacity == 0)
            return 0;
        if (burstT[n - 1] > capacity)
            return knapSack(capacity, burstT, profit, n - 1);
        else
            return max(profit[n - 1]
                    + knapSack(capacity - burstT[n - 1], burstT,
                            profit, n - 1),
                    knapSack(capacity, burstT, profit, n - 1));
    }

    // Driver code
    public static void main(String args[]) {
        int profit[] = new int[] { 60, 100, 120 };
        int burstT[] = new int[] { 20, 10, 20 };
        int capacity = 50;
        int n = profit.length;
        System.out.println(knapSack(capacity, burstT, profit, n));
    }

}
