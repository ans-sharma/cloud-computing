
// import java.util.Arrays;
// import java.util.Collections;
// import java.util.Comparator;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class Task {
    private String taskId;
    private int arrivalTime, burstTime, price, qLoc;
    private double ratio;

    Task(String taskId, int arrivalTime, int burstTime, int price, int qLoc) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.price = price;
        this.ratio = burstTime / (double) price;
        this.qLoc = qLoc;
    }

    public void display() {
        System.out.println("Id: " + taskId);
        System.out.println("Ratio: " + ratio);
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

    public double getRatio() {
        return ratio;
    }

    public int getqLoc() {
        return qLoc;
    }
}

public class Scheduling2 {

    public static int[] schedule(Task[] tasks, int noOfTasks, int deadLine) {
        int result[] = new int[noOfTasks];
        for (int i = 0; i < noOfTasks; i++)
            result[i] = 0;
        int count = 0;
        double val = 0.0;
        for (int i = 0; i < noOfTasks; i++) {
            if (count + tasks[i].getBurstTime() <= deadLine) {
                result[tasks[i].getqLoc()] = 1;
                count = count + tasks[i].getBurstTime();
                val = val + tasks[i].getPrice();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int noOfTasks = 10; // taking static value for test
        Task task[] = new Task[noOfTasks];
        int lMin, lMax, pMin, pMax;
        int deadLine = 20;
        // we can take user input
        // taking static values for testing
        lMin = 5;
        lMax = 10;
        pMin = 5;
        pMax = 20;

        for (int i = 0; i < noOfTasks; i++) {
            String temp = "p" + Integer.toString(i);
            int lengthOfTask = ThreadLocalRandom.current().nextInt(lMin, lMax + 1);
            int price = ThreadLocalRandom.current().nextInt(pMin, pMax + 1);
            // System.out.println(temp);
            task[i] = new Task(temp, 0, lengthOfTask, price, i);
        }

        // for(int i=0; i<noOfTasks; i++){
        // task[i].display();
        // }

        // Sorting wrt ratio
        for (int i = 0; i < noOfTasks; i++) {
            for (int j = 0; j < noOfTasks - i - 1; j++) {
                if (task[j].getRatio() < task[j + 1].getRatio()) {
                    Task temp = task[j];
                    task[j] = task[j + 1];
                    task[j + 1] = temp;
                }
            }
        }

        // Printing Elements
        // for(int i=0; i<noOfTasks; i++){
        // task[i].display();
        // }

        // Arrays.toString(task);
        // System.out.println("null");
        int temp[];
        temp = schedule(task, noOfTasks, deadLine);
        System.out.println(Arrays.toString(temp));
    }
}
