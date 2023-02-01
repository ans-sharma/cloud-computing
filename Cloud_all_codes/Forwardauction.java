// single service provider -----> size of physical VMs
// multiple user client/process/jobs ----> 1. requirement of VMs
//                                         2. bid price 
//  

public class Forwardauction {

    static int objective(int S[], int Profit[], int n) {
        int max = 0;
        for (int i = 0; i <= n; i++) {
            max += S[i] * Profit[i];
        }
        return max;
    }

    static int incentive() {
        return 0;
    }

    static int finalPrice(int S[], int Profit[], int n) {
        int fPrice = 0;
        for (int i = 0; i <= n; i++) {
            fPrice += S[i] * Profit[i] - incentive();
        }
        return fPrice;
    }

    public static void main(String args[]) {
        System.out.println("Forward Auction");
    }
}
