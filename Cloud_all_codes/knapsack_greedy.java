
// Java program to solve fractional Knapsack Problem
// import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.time.LocalTime;

// Greedy approach
class Knapsack_greedy {
    // Function to get maximum value
    private static double getMaxValue(ItemValue[] arr,
            int capacity) {
        // Sorting items by value/weight ratio;
        // int outStr[] = new int[arr.length];
        Arrays.sort(arr, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue item1,
                    ItemValue item2) {
                double cpr1 = new Double((double) item1.value
                        / (double) item1.weight);
                double cpr2 = new Double((double) item2.value
                        / (double) item2.weight);

                if (cpr1 < cpr2)
                    return 1;
                else
                    return -1;
            }
        });

        double totalValue = 0d;

        for (ItemValue i : arr) {

            int curWt = (int) i.weight;
            int curPro = (int) i.value;
            // int cur = (int) i.value;

            if (capacity - curWt >= 0) {

                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curPro;
                // outStr[i] = 1;
                System.out.println(curWt);
            }
        }

        return totalValue;
    }

    // Item value class
    static class ItemValue {

        int value, weight;

        // Item value function
        public ItemValue(int val, int wt) {
            this.weight = wt;
            this.value = val;
        }
    }

    // Driver code
    public static void main(String[] args) {

        LocalTime myObj = LocalTime.now();
        System.out.println(myObj);

        ItemValue[] arr = { new ItemValue(60, 10),
                new ItemValue(100, 10),
                new ItemValue(120, 30),
                new ItemValue(100, 10),
                new ItemValue(120, 30) };
        // 36, 47, 46, 48, 55 -> 154, 131, 108, 166, 184

        // 52, 27, 38, 49, 45, 59, 44, 25, 53, 38 ->
        // 193, 182, 157, 192, 186, 123, 154, 158, 180, 191
        // int vmsize[] = { 52, 27, 38, 49, 45, 59, 44, 25, 53, 38 };
        // int profits[] = { 193, 182, 157, 192, 186, 123, 154, 158, 180, 191 };

        // ItemValue[] arr[];
        // for (int i = 0; i < vmsize.length; i++) {
        // arr[i] = { new ItemValue(vmsize[i], profits[i]) };
        // }

        // [460, 291, 428, 348]
        int capacity = 291;

        double maxValue = getMaxValue(arr, capacity);

        // Function call
        System.out.println("max profit : " + maxValue);

        LocalTime endObj = LocalTime.now();
        System.out.println("ET : ");
        System.out.println(endObj.getNano() - myObj.getNano());
    }
}
