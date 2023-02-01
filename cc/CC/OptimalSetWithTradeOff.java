import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class Task {
    private String taskId;
    private int arrivalTime, burstTime, price, qLoc;

    Task(String taskId, int arrivalTime, int burstTime, int price, int qLoc) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.price = price;
        this.qLoc = qLoc;
    }

    public void display() {
        System.out.println("Id: " + taskId);
        System.out.println("Burst Time: " + burstTime);
        System.out.println("Price: " + price);
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public double getPrice() {
        return price;
    }

    public int getqLoc() {
        return qLoc;
    }
}

class Scheduling {
    public double[] fcfs(int noOfTasks, Task[] tasks, int[] requestSet, boolean debug) {
        double result[] = new double[3];
        double compilationTime = 0;
        double turnAroundTime = 0;
        double waitingTime = 0;
        double responseTime = 0;

        for (int i = 0; i < noOfTasks; i++) {
            if (requestSet[i] == 1) {
                compilationTime = compilationTime + tasks[i].getBurstTime();
                turnAroundTime = compilationTime - tasks[i].getArrivalTime(); // ArrivalTime = 0
                waitingTime = turnAroundTime - tasks[i].getBurstTime();
                responseTime = waitingTime;
                if (debug == true)
                    System.out.println("ProcessID: " + tasks[i].getTaskId() + ", Compilation Time: " + compilationTime
                            + ", Turn Around Time: " + turnAroundTime + ", Waiting Time: " + waitingTime
                            + ", responseTime: " + responseTime);
            }
        }
        if (debug == true) {
            System.out.println("--------------------------------------------");
            System.out.println("Avg. Turn Around Time: " + turnAroundTime / noOfTasks);
            System.out.println("Avg. Waiting Time: " + waitingTime / noOfTasks);
            System.out.println("Avg. Response Time: " + responseTime / noOfTasks);
        }
        result[0] = turnAroundTime / noOfTasks;
        result[1] = waitingTime / noOfTasks;
        result[2] = responseTime / noOfTasks;
        return result;
    }
}

class RequestSet {
    private int set[];
    private int totalValue = 0;
    private double avgResponceTime = 0;
    private int totalBurstTime = 0;
    private int flag = 0;
    private double w1, w2;
    private double objFunctionResult = 0;

    RequestSet(int set[], double w1, double w2) {
        this.set = set;
        if (w1 + w2 != 1)
            System.out.println("Input Error, w1 and w2");
        else {
            this.w1 = w1;
            this.w2 = w2;
        }
    }

    public void calObjFunction() {
        objFunctionResult = (w1 * (double) totalValue) + (w2 * (1 / avgResponceTime));
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setTotalBurstTime(int totalBurstTime) {
        this.totalBurstTime = totalBurstTime;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public void setAvgResponceTime(double avgResponceTime) {
        this.avgResponceTime = avgResponceTime;
    }

    public int[] getSet() {
        return set;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public int getFlag() {
        return flag;
    }

    public int getTotalBurstTime() {
        return totalBurstTime;
    }

    public double getAvgResponceTime() {
        return avgResponceTime;
    }

    public double getObjFunctionResult() {
        return objFunctionResult;
    }

    void display() {
        System.out.println("Set: " + Arrays.toString(set));
        System.out.println("Total Value: " + totalValue);
        System.out.println("Total Burst Time: " + totalBurstTime);
        System.out.println("Flag: " + flag);
        System.out.println("Avg. Responce Time: " + avgResponceTime);
        System.out.println("Objective Function Value: " + objFunctionResult);
    }
}

public class OptimalSetWithTradeOff {
    static void calculateSets(Task[] task, RequestSet[] rs, int noOfTasks, int deadLine) {
        int totalNoOfCombinations = (int) Math.pow(2, noOfTasks);
        double max = Double.MIN_VALUE;
        // System.out.println(deadLine + "\n");
        for (int i = 0; i < totalNoOfCombinations; i++) {

            // System.out.print(rs[i].getObjFunctionResult() + " ");
            // System.out.print(rs[i].getTotalBurstTime() + "\n");

            if (rs[i].getTotalBurstTime() <= deadLine && max < rs[i].getObjFunctionResult()
                    && !Double.isNaN(rs[i].getObjFunctionResult()) && !Double.isInfinite(rs[i].getObjFunctionResult()))
                max = rs[i].getObjFunctionResult();
        }
        // System.out.println("\n" + min);
        for (int i = 0; i < totalNoOfCombinations; i++) {
            if (rs[i].getObjFunctionResult() == max && rs[i].getTotalBurstTime() <= deadLine) {
                rs[i].setFlag(1);
            }
        }
    }

    static void calculateAvgResponceTime(Task[] task, RequestSet[] rs, int noOfTasks) {
        Scheduling sch = new Scheduling();
        int totalNoOfCombinations = (int) Math.pow(2, noOfTasks);
        for (int i = 0; i < totalNoOfCombinations; i++) {
            double[] temp;
            temp = sch.fcfs(noOfTasks, task, rs[i].getSet(), false);
            rs[i].setAvgResponceTime(temp[2]);
            rs[i].calObjFunction();
        }
    }

    static void calculatePrice(Task[] task, RequestSet[] rs, int noOfTasks) {
        int totalNoOfCombinations = (int) Math.pow(2, noOfTasks);
        // int maximum = Integer.MIN_VALUE;
        for (int i = 0; i < totalNoOfCombinations; i++) { // calculate total no of items
            int[] temp = rs[i].getSet();
            // System.out.println(Arrays.toString(rs[i].getSet()));
            for (int j = 0; j < noOfTasks; j++) {
                if (temp[j] == 1) {
                    rs[i].setTotalValue(rs[i].getTotalValue() + (int) task[j].getPrice());
                    rs[i].setTotalBurstTime(rs[i].getTotalBurstTime() + task[j].getBurstTime());
                    // System.out.println(rs[i].getTotalValue());
                }
            }
            // if (rs[i].getTotalBurstTime() <= deadLine && maximum < rs[i].getTotalValue())
            // maximum = rs[i].getTotalValue();

        }
        // for (int i = 0; i < totalNoOfCombinations; i++) {
        // if (rs[i].getTotalValue() == maximum && rs[i].getTotalBurstTime() <=
        // deadLine)
        // rs[i].setFlag(1);

        // }
    }

    static String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);
        return sb.toString();
    }

    static int[] returnBitArray(int num, int len) {
        String strNum = Integer.toBinaryString(num);
        strNum = padLeftZeros(strNum, len);
        int[] result = new int[len];
        // System.out.println(strNum);
        for (int i = 0; i < len; i++) {
            result[i] = Integer.parseInt(String.valueOf(strNum.charAt(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        int noOfTasks = 5; // for no. of tasks = 25, it take a lot of time and we have to inc the heap size
                           // with -Xmx in terminal
        double w1 = 0.5;
        double w2 = 1 - w1;
        double totalNoOfCombinations = Math.pow(2, noOfTasks);
        RequestSet[] rs = new RequestSet[(int) totalNoOfCombinations];
        Task task[] = new Task[noOfTasks];

        int lMin, lMax, pMin, pMax;
        int deadLine = 30;
        // we can take user input
        // taking static values for testing
        lMin = 5;
        lMax = 10;
        pMin = 5;
        pMax = 20;

        for (int i = 0; i < noOfTasks; i++) { // generating random tasks with random values
            String temp = "p" + Integer.toString(i);
            int lengthOfTask = ThreadLocalRandom.current().nextInt(lMin, lMax + 1);
            int price = ThreadLocalRandom.current().nextInt(pMin, pMax + 1);
            // System.out.println(temp);
            task[i] = new Task(temp, 0, lengthOfTask, price, i);
        }

        // for (int i = 0; i < noOfTasks; i++) { // Display all tasks
        // task[i].display();
        // }

        for (int i = 0; i < (int) totalNoOfCombinations; i++) { // Making the total no of sets possible
            rs[i] = new RequestSet(returnBitArray(i, noOfTasks), w1, w2);
        }

        calculatePrice(task, rs, noOfTasks);
        calculateAvgResponceTime(task, rs, noOfTasks);
        calculateSets(task, rs, noOfTasks, deadLine);

        // for (int i = 0; i < (int) totalNoOfCombinations; i++) {
        //     rs[i].display();
        // }

        // System.out.print("\n ----------------------------------- \n");

        for (int i = 0; i < (int) totalNoOfCombinations; i++) {
            if (rs[i].getFlag() == 1)
                rs[i].display();
        }
    }
}
