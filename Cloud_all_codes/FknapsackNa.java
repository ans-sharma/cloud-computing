
//import java.io.*;
import java.util.*;

//import java.util.Collections;;
class FknapsackNa {
    public static void main(String args[]) throws Exception {

        long startTime = System.nanoTime();

        String st1 = "";
        int For_Array = 0;
        double Test_Max_P = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter profit array size n");
        int n = sc.nextInt();

        // values
        int[] profits = new int[n];
        System.out.println("enter the profits");
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }
        int weights[] = new int[n];
        System.out.println("enter the weights");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }
        System.out.println("enter the capacity");
        int capacity = sc.nextInt();
        double size = Math.pow(2, n);
        String[] Test_Max_P1 = new String[(int) size];

        double maxp_arr[] = new double[(int) size];
        sc.close();
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            double r = (double) profits[i] / weights[i];
            Item item = new Item(profits[i], weights[i], r);
            // System.out.println(item);
            items[i] = item;
        }

        Arrays.sort(items);
        System.out.println("profits:" + Arrays.toString(profits));
        System.out.println("Weights:" + Arrays.toString(weights));
        double[] ratio = new double[n];
        for (int i = 0; i < n; i++) {
            ratio[i] = items[i].r;
        }

        System.out.println("Ratios" + Arrays.toString(ratio));

        // main traversal
        // System.out.print("selected:");
        for (int k = 0; k < size; ++k) {
            double max_profit = 0;
            int r_cap = capacity;

            st1 = (String.format("%3s", Integer.toBinaryString(k)).replace(' ', '0')); // here "%--s" should be value of
                                                                                       // 'n'
            System.out.println(st1);
            // s2[k]=st1;

            for (int i = st1.length() - 1; i >= 0; i--) {

                if (st1.charAt(i) == '1') {

                    // for(int i=n-1;i>=0;i--){
                    if (r_cap - items[i].weights >= 0) {
                        if (r_cap == 0) {
                            break;
                        }
                        max_profit += items[i].profits;
                        // System.out.println("->"+i);
                        r_cap -= items[i].weights;
                    }
                }
            }
            maxp_arr[k] = max_profit;
            if (max_profit > Test_Max_P) {
                Test_Max_P1[For_Array] = st1;
                Test_Max_P = max_profit;
                For_Array += 1;

            }

        }

        long endTime = System.nanoTime();
        long ET = endTime - startTime;

        System.out.println("Profits of Strings  " + Arrays.toString(maxp_arr));
        Arrays.sort(maxp_arr);
        System.out.println("maximum profit  " + maxp_arr[(int) size - 1]);
        // System.out.println("Testing max pro:"+Test_Max_P);
        System.out.println("maximum profit giving string:" + Test_Max_P1[For_Array - 1]);
        System.out.println("Executation Time : " + ET);

        // System.out.println("max profit is:"+max_profit);
    }

    public static class Item implements Comparable<Item> {
        int profits;
        int weights;
        double r;

        Item(int profits, int weights, double r) {
            this.profits = profits;
            this.weights = weights;
            this.r = r;
        }

        @Override
        public String toString() {
            return "profits:" + profits + "  weights:" + weights + "  ratio:" + r;
        }

        public int compareTo(Item o) {
            // System.out.println("this r "+this.r+" o_r "+o.r);
            if (this.r > o.r) {
                return 1;
            } else if (this.r < o.r) {
                return -1;
            } else {
                return 0;
            }

        }
    }

}

// enter profit array size n
// 5
// enter the profits
// 40 20 10 30 62
// enter the weights
// 10 20 10 20 10
// enter the capacity
// 50
// profits:[40, 20, 10, 30, 62]
// Weights:[10, 20, 10, 20, 10]
// Ratios[1.0, 1.0, 1.5, 4.0, 6.2]
// 000
// 001
// 010
// 011
// 100
// 101
// 110
// 111
// 1000
// 1001
// 1010
// 1011
// 1100
// 1101
// 1110
// 1111
// 10000
// 10001
// 10010
// 10011
// 10100
// 10101
// 10110
// 10111
// 11000
// 11001
// 11010
// 11011
// 11100
// 11101
// 11110
// 11111
// Profits of Strings [0.0, 30.0, 10.0, 40.0, 20.0, 50.0, 30.0, 60.0, 20.0,
// 60.0, 50.0, 90.0, 30.0, 70.0, 60.0, 80.0, 20.0, 82.0, 60.0, 122.0, 50.0,
// 112.0, 90.0, 132.0, 30.0, 92.0, 70.0, 132.0, 60.0, 102.0, 80.0, 142.0]
// maximum profit 142.0
// maximum profit giving string:11111
// Executation Time : 41828245000