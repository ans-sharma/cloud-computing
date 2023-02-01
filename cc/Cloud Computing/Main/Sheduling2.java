package Main;
// import Main.Task;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;



// class Task{
//     private String taskId;
//     private int arrivalTime, burstTime, price;
//     double ratio;
//     Task(String taskId, int arrivalTime, int burstTime, int price){
//         this.taskId = taskId;
//         this.arrivalTime = arrivalTime;
//         this.burstTime = burstTime;
//         this.price = price;
//         this.ratio = burstTime / price;
//     }
//     public int getBurstTime() {
//         return burstTime;
//     }
//     public int getArrivalTime() {
//         return arrivalTime;
//     }
//     public String getTaskId() {
//         return taskId;
//     }
//     public double getPrice() {
//         return price;
//     }
// }

public class Sheduling2 {
    public static void main(String[] args) {
        int noOfTasks = 5; // taking static value for test
        Task task[] = new Task[2];
        int lMin, lMax, pMin, pMax; 
        // we can take user input
        // taking static values for testing
        lMin = 5;
        lMax = 10;
        pMin = 5;
        pMax = 20;

        // for(int i=0; i<noOfTasks; i++){
        //     String temp = "p" + Integer.toString(i);
        //     int lengthOfTask = ThreadLocalRandom.current().nextInt(lMin, lMax + 1);
        //     int price = ThreadLocalRandom.current().nextInt(pMin, pMax + 1);
        //     System.out.println(temp);
        //     task[i] = new Task(temp, 0, lengthOfTask, price);
        // }

        task[0] = new Task("p1", 0, 2);
        task[1] = new Task("p2", 0, 1);
        // Arrays.sort(task);
        // Arrays.toString(task);
        System.out.println("null");
    }
}
