
// Java program to solve fractional Knapsack Problem
// import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.time.LocalTime;

// Greedy approach
class FractionalKnapSack {
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

        int capacity = 80;

        double maxValue = getMaxValue(arr, capacity);

        // Function call
        System.out.println(maxValue);

        LocalTime endObj = LocalTime.now();
        System.out.println(endObj);
        int ET = endObj.getNano() - myObj.getNano();
        System.out.println(ET);
    }
}
