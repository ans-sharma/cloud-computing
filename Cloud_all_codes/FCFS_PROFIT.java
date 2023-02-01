import java.util.*;

public class FCFS_PROFIT {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.println("enter no of process: ");

        int n = sc.nextInt();

        int pid[] = new int[n]; // process ids
        int profit[] = new int[n]; // profit
        int ar[] = new int[n]; // arrival times
        int bt[] = new int[n]; // burst or execution times
        int ct[] = new int[n]; // completion times
        int ta[] = new int[n]; // turn around times
        int wt[] = new int[n]; // waiting times
        int totalprofit = 0;
        int temp;

        float avgwt = 0, avgta = 0;

        for (int i = 0; i < n; i++)

        {

            System.out.println("enter process " + (i + 1) + " arrival time: ");

            ar[i] = sc.nextInt();

            System.out.println("enter process " + (i + 1) + " brust time: ");
            bt[i] = sc.nextInt();

            System.out.println("enter process " + (i + 1) + " profit: ");
            profit[i] = sc.nextInt();

            pid[i] = i + 1;

        }

        // sorting according to arrival times
        for (int i = 0; i < n; i++)

        {

            for (int j = 0; j < n - (i + 1); j++)

            {

                if (ar[j] > ar[j + 1])

                {

                    temp = ar[j];

                    ar[j] = ar[j + 1];

                    ar[j + 1] = temp;

                    temp = bt[j];

                    bt[j] = bt[j + 1];

                    bt[j + 1] = temp;

                    temp = pid[j];

                    pid[j] = pid[j + 1];

                    pid[j + 1] = temp;

                    temp = profit[j];

                    profit[j] = profit[j + 1];

                    profit[j + 1] = temp;

                }

            }

        }

        // finding completion times
        for (int i = 0; i < n; i++)

        {

            if (i == 0)

            {
                totalprofit += profit[i];

                ct[i] = ar[i] + bt[i];

            }

            else

            {
                totalprofit += profit[i];

                if (ar[i] > ct[i - 1])

                {

                    ct[i] = ar[i] + bt[i];

                }

                else

                    ct[i] = ct[i - 1] + bt[i];

            }

            ta[i] = ct[i] - ar[i]; // turnaround time= completion time- arrival time
            wt[i] = ta[i] - bt[i]; // waiting time= turnaround time- burst time
            avgwt += wt[i]; // total waiting time
            avgta += ta[i]; // total turnaround time
        }

        System.out.println("\npid  arrival  brust  complete turn waiting profit");

        for (int i = 0; i < n; i++)

        {

            System.out.println(pid[i] + "  \t " + ar[i] + "\t" + bt[i] + "\t" +
                    ct[i] + "\t" + ta[i] + "\t" + wt[i] + "\t" + profit[i]);

        }
        sc.close();

        System.out.println("\naverage waiting time: " + (avgwt / n)); // printing average waiting time.
        System.out.println("average turnaround time:" + (avgta / n)); // printing average turnaround time.
        System.out.println("total profit:" + (totalprofit)); // printing average turnaround time.
    }
}
