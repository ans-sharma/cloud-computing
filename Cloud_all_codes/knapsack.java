class knapsack {
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack(int W, int burstT[], int profit[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;
        if (burstT[n - 1] > W)
            return knapSack(W, burstT, profit, n - 1);
        else
            return max(profit[n - 1]
                    + knapSack(W - burstT[n - 1], burstT,
                            profit, n - 1),
                    knapSack(W, burstT, profit, n - 1));
    }

    // Driver code
    public static void main(String args[]) {
        int profit[] = new int[] { 10, 15, 20, 25, 40 };
        int burstT[] = new int[] { 10, 20, 40, 20, 10 };
        int W = 70;
        int n = profit.length;
        System.out.println(knapSack(W, burstT, profit, n));
    }
}
