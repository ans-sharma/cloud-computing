import java.util.concurrent.ThreadLocalRandom;

import Main.Task;

import java.util.Scanner;

class Task {
    String taskId;
    int lMin, lMax, pMax, pMin, lengthOfTask, price;

    Task(String taskId, int lMin, int lMax, int pMin, int pMax) {
        this.taskId = taskId;
        this.lMin = lMin;
        this.lMax = lMax;
        // this.deadLine = deadLine;
        this.pMin = pMin;
        this.pMax = pMax;

        // Calculating random value for LengthOfTask and Price
        this.lengthOfTask = ThreadLocalRandom.current().nextInt(lMin, lMax + 1);
        this.price = ThreadLocalRandom.current().nextInt(pMin, pMax + 1);

        //
    }
    public void printAll() {
        System.out.println("Task Id: " + this.taskId);
        System.out.println("LMin: " + this.lMin + " LMax: " + this.lMax + " Length of Task: " + this.lengthOfTask);
        // System.out.println("Dead Line: " + this.deadLine);
        System.out.println("PMin: " + this.pMin + " PMax: " + this.pMax + " Price: " + this.price);
    }

    public int getLengthOfTask() {
        return lengthOfTask;
    }

    public int getPrice() {
        return price;
    }

    public String getTaskId() {
        return taskId;
    }
}

class Knapsack {
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public void printknapSack(int W, int wt[], int val[], String processNameList[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = Math.max(val[i - 1] +
                            K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        // stores the result of Knapsack
        int res = K[n][W];
        System.out.println("Max. Profit: " + res);
        System.out.println("Process Selected");
        w = W;
        for (i = n; i > 0 && res > 0; i--) {

            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if (res == K[i - 1][w])
                continue;
            else {

                // This item is included.
                System.out.println("Task ID: " + processNameList[i-1] +" BurstTime: " + wt[i - 1] + " Price: " + val[i - 1]);

                // Since this weight is included its
                // value is deducted
                res = res - val[i - 1];
                w = w - wt[i - 1];
            }
        }
    }
}

class S {

    public static void displayAll(String processNameList[], int burstTimeList[], int priceList[], int noOfTasks) {
        System.out.println("All tasks list: ");
        for(int i =0; i < noOfTasks; i++){
            System.out.println("Task ID: " + processNameList[i] +" BurstTime: " + burstTimeList[i] + " Price: " + priceList[i]);
        }
    }
    public static void main(String[] args) {
        // Task a = new Task("h01", 1, 10, 2, 20, 50);
        // a.printAll();
        int noOfTasks = 500000, deadLine = 50, burstTime = 0;
        int compilationTime = 0;
        int turnAroundTime = 0;
        int waitingTime = 0;
        int responseTime = 0; // responce time is equal to wating time as fcfs is non-primitive sheduling.

        // Creating Arrays
        int priceList[] = new int[noOfTasks];
        int burstTimeList[] = new int[noOfTasks];
        String processNameList[] = new String[noOfTasks];

        //Knapsack Obj
        Knapsack k = new Knapsack();

        //  // Inputs
        // Scanner sc = new Scanner(System.in);
        // System.out.println("No of Tasks to be Scheduled: ");
        // noOfTasks = sc.nextInt();
        // System.out.println("Enter the Dead Line: ");
        // deadLine = sc.nextInt();

        // Array of class Task Created
        // int taskLength = 50;
        Task a[] = new Task[noOfTasks];
        // Manually Adding the Task Value in Array
        // a[0] = new Task("p01", 2, 20, 10, 40);
        // a[1] = new Task("p02", 2, 20, 10, 40);
        // a[2] = new Task("p03", 2, 20, 10, 40);
        // a[3] = new Task("p04", 2, 20, 10, 40);
        // a[4] = new Task("p05", 2, 20, 10, 40);

        //Adding random values
        for(int i=0; i<noOfTasks; i++){
            a[i] = new Task(Integer.toString(i), 2, 2000, 1, 4000);
        }

        for(int i=0; i<noOfTasks; i++){
            burstTimeList[i] = a[i].getLengthOfTask();
            priceList[i] = a[i].getPrice();
            processNameList[i] = a[i].getTaskId();
        }
        
        displayAll(processNameList, burstTimeList, priceList, noOfTasks);
        k.printknapSack(deadLine, burstTimeList, priceList, processNameList, noOfTasks);

        // sc.close();
    }
}