import java.util.Arrays;

class VM {
    private String userId;
    private int vmRequired, qLoc;
    private double bidPrice, ratio;

    VM(String userId, int vmRequired, double bidPrice, int qLoc) {
        this.userId = userId;
        this.vmRequired = vmRequired;
        this.bidPrice = bidPrice;
        this.qLoc = qLoc;
        this.ratio = bidPrice / (double) vmRequired;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getRatio() {
        return ratio;
    }

    public String getUserId() {
        return userId;
    }

    public int getVmRequired() {
        return vmRequired;
    }

    public int getqLoc() {
        return qLoc;
    }

    public void display() {
        System.out.println("User Id: " + userId);
        System.out.println("VM Required " + vmRequired);
        System.out.println("Bid Price: " + bidPrice);
        System.out.println("Ratio: " + ratio);
        System.out.println("--------------------");

    }
}

public class ResourceAllocation {

    public static int[] schedule(VM[] vm, int noOfTasks, int maxVm) {
        int result[] = new int[noOfTasks];
        for (int i = 0; i < noOfTasks; i++)
            result[i] = 0;
        int count = 0;
        double val = 0.0;
        for (int i = 0; i < noOfTasks; i++) {
            if (count + vm[i].getVmRequired() <= maxVm) {
                result[vm[i].getqLoc()] = 1;
                count = count + vm[i].getVmRequired();
                val = val + vm[i].getBidPrice();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int noOfTasks = 5;
        int maxVm = 8; // taking static value
        VM vm[] = new VM[5];
        // Taking static values for testing
        vm[0] = new VM("u1", 3, 4, 0);
        vm[1] = new VM("u2", 4, 5, 1);
        vm[2] = new VM("u3", 1, 1, 2);
        vm[3] = new VM("u4", 2, 1, 3);
        vm[4] = new VM("u5", 5, 7, 4);

        // Sorting wrt ratio
        for (int i = 0; i < noOfTasks; i++) {
            for (int j = 0; j < noOfTasks - i - 1; j++) {
                if (vm[j].getRatio() < vm[j + 1].getRatio()) {
                    VM temp = vm[j];
                    vm[j] = vm[j + 1];
                    vm[j + 1] = temp;
                }
            }
        }

        // Printing Elements
        // for(int i=0; i<noOfTasks; i++){
        // vm[i].display();
        // }

        int tempGreedy[];
        tempGreedy = schedule(vm, noOfTasks, maxVm);
        System.out.println(Arrays.toString(tempGreedy));
    }
}
