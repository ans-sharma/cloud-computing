// Abhi nahi karna hai

import java.util.Arrays;

class RequestSet{
    private int set[][];
    private int totalValue = 0;
    private int totalBurstTime = 0;
    private int flag = 0;

    RequestSet(int set[][]){
        this.set = set;
    }

    void display(){
        System.out.println("Set: " + Arrays.deepToString(set));
        System.out.println("Value: " + totalValue);
        System.out.println("Burst Time: " + totalBurstTime);
        System.out.println("Flag: " + flag);
    }
}

public class OptimalMulipleCpu {
    public static void main(String[] args) {
        ;
    }
}
