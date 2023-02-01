import java.util.Random;

public class PoissonDistribution {

    private static int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
    
    public static void main(String[] args) {
        int range = 999999;
        // int[] arr =  new int[range];
        double sum = 0;
        // System.out.println(getPoissonRandom(5));
        for(int i=0; i<range; i++){
            int temp = getPoissonRandom(5);
            System.out.println("value of PD(" + i + ") is : " + temp);
            // arr[i] = temp; 
            sum += temp;
        }
        System.out.println("Average: " + sum/range);
    }
}
