import java.util.Scanner;

class Task{
    
}


class Sheduling {
    public static void fcfs(int noOfTasks, int[] burstTime) {
        // System.out.println(noOfTasks);
        // System.out.println(burstTime);
        int compilationTime = 0;
        int turnAroundTime = 0;
        int waitingTime = 0;
        int responseTime = 0;
        for (int i = 0; i < noOfTasks; i++) {
            compilationTime = compilationTime + burstTime[i];
            turnAroundTime = compilationTime - 0; // ArrivalTime = 0
            waitingTime = turnAroundTime - burstTime[i];
            responseTime = burstTime[i] - 0; // ArrivalTime = 0
            System.out.println("ProcessID: " + i + ", Compilation Time: " + compilationTime + ", Turn Around Time: " + turnAroundTime + ", Waiting Time: " + waitingTime + ", responseTime: " + responseTime);
        }

        System.out.println("--------------------------------------------");
        System.out.println("Avg. Turn Around Time: " + turnAroundTime/noOfTasks);
        System.out.println("Avg. Waiting Time: " + waitingTime/noOfTasks);
        System.out.println("Avg. Response Time: " + responseTime/noOfTasks);

    }

    public static void main(String[] args) {
        int noOfTasks; // No of tasks
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the No of Tasks to pe Sheduled: ");
        noOfTasks = sc.nextInt();

        int burstTime[] = new int[noOfTasks]; // Array decleration
        System.out.println("Enter the burst time of tasks: ");
        for (int i = 0; i < noOfTasks; i++) {
            burstTime[i] = sc.nextInt(); // taking all the burst time
        }

        fcfs(noOfTasks, burstTime);

        sc.close();
    }
}