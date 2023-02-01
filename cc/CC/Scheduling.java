class Task {
    private String taskId;
    private int arrivalTime, burstTime;

    Task(String taskId, int arrivalTime, int burstTime) {
        this.taskId = taskId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
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
}

public class Scheduling {
    public static double[] fcfs(int noOfTasks, Task[] tasks, boolean debug) {
        double result[] = new double[3];
        double compilationTime = 0;
        double turnAroundTime = 0;
        double waitingTime = 0;
        double responseTime = 0;

        for (int i = 0; i < noOfTasks; i++) {
            compilationTime = compilationTime + tasks[i].getBurstTime();
            turnAroundTime = compilationTime - tasks[i].getArrivalTime(); // ArrivalTime = 0
            waitingTime = turnAroundTime - tasks[i].getBurstTime();
            responseTime = waitingTime;
            // responseTime = tasks[i].getBurstTime() - tasks[i].getArrivalTime(); //
            // ArrivalTime = 0
            if (debug == true)
                System.out.println("ProcessID: " + tasks[i].getTaskId() + ", Compilation Time: " + compilationTime
                        + ", Turn Around Time: " + turnAroundTime + ", Waiting Time: " + waitingTime
                        + ", responseTime: " + responseTime);
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

    public static void main(String[] args) {
        // System.out.println("null");
        Task task[] = new Task[5];
        task[0] = new Task("p1", 0, 2);
        task[1] = new Task("p2", 0, 1);
        task[2] = new Task("p3", 0, 6);
        // task[3] = new Task("p4", 5, 1);
        // task[4] = new Task("p5", 4, 3);

        fcfs(3, task, true);
    }
}
