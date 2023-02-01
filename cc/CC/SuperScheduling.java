import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Genes {
    int fitnessValue = 0;
    int[] chromo;

    Genes(int size) {
        chromo = new int[size];
        createChromoString();
    }

    void createChromoString() {
        for (int i = 0; i < chromo.length; i++) {
            if (Math.random() > 0.5) {
                chromo[i] = 1;
            } else {
                chromo[i] = 0;
            }
        }
    }

    void printGene() {
        System.out.println(Arrays.toString(chromo) + " Fitness: " + fitnessValue);
    }
}

class Population {
    Genes[] population;
    int generation = 0;
    int geneSize;
    int bestGenes = 0;
    int maxCrossOver = 2;
    int crossOverCount = 0;
    int mutationCount = 0;

    Population(int populationSize, int geneSize) {
        population = new Genes[populationSize];
        this.geneSize = geneSize;
        createPopulation();
    }

    void createPopulation() {
        for (int i = 0; i < population.length; i++) {
            population[i] = new Genes(geneSize);
        }
    }

    void printPopulation() {
        for (int i = 0; i < population.length; i++) {
            // System.out.println("---------------------------------------------");
            // System.out.println("Generation: " + generation);
            population[i].printGene();
            // System.out.println("---------------------------------------------");
        }
    }

    void calculateFitness(Tasks t) {
        for (int i = 0; i < population.length; i++) {
            int temp = 0;
            int tempPrice = 0;
            for (int j = 0; j < population[i].chromo.length; j++) {
                if (population[i].chromo[j] == 1) {
                    temp = temp + t.executionTimeArr[j];
                    tempPrice = tempPrice + t.priceArr[j];
                }
            }
            if (temp > t.capacity) {
                population[i].fitnessValue = 0;
            } else {
                population[i].fitnessValue = tempPrice;
            }
        }
    }

    void sortGenes() {
        Arrays.sort(population, new SortByFitness());
    }

    void selection() {
        // int[] temp = crossOver(population[1].chromo, population[2].chromo);
        // population[population.length-1].chromo = temp;
        // int revLen = population.length-1;
        try {
            for (int i = 0; i < population.length - 1; i++) {
                if (Math.random() < 0.1) {
                    int[] temp = crossOver(population[i].chromo, population[i + 1].chromo);
                    population[i + 1].chromo = temp;
                    ++crossOverCount;
                    // revLen--;
                    ++i;
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    int[] crossOver(int[] a, int[] b) {
        Random random = new Random();
        int[] temp = new int[a.length];
        int crossOverPoint = random.nextInt(a.length);
        for (int i = 0; i < a.length; i++) {
            if (crossOverPoint < i) {
                temp[i] = a[i];
            } else {
                temp[i] = b[i];
            }
        }
        return temp;
    }

    void mutation() {
        for (int i = 0; i < population.length; i++) {
            if (Math.random() < 0.05) {
                mutationCount++;
                try {
                    int[] temp = population[i].chromo;
                    if (temp[ThreadLocalRandom.current().nextInt(temp.length - 1)] == 1) {
                        temp[ThreadLocalRandom.current().nextInt(temp.length - 1)] = 0;
                    } else {
                        temp[ThreadLocalRandom.current().nextInt(temp.length - 1)] = 1;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }
}

class Tasks {
    int[] executionTimeArr;
    int[] priceArr;
    int capacity;

    Tasks(int[] executionTimeArr, int[] priceArr, int capacity) {
        this.executionTimeArr = executionTimeArr;
        this.priceArr = priceArr;
        this.capacity = capacity;
    }
}

class SortByFitness implements Comparator<Genes> {
    public int compare(Genes a, Genes b) {
        return b.fitnessValue - a.fitnessValue;
    }
}

class GeneticAlgorithm {
    public long[] geneticAlgorithm(int[] executionTimeArr, int[] priceArr, int capacity) {
        long start = System.nanoTime();
        // int[] executionTimeArr = { 3, 4, 6, 5};
        // int[] priceArr = { 2, 3, 1, 4};
        // int capacity = 8;
        // int[] executionTimeArr = { 10, 20, 30};
        // int[] priceArr = { 60, 100, 120};
        // int capacity = 50;
        Tasks t = new Tasks(executionTimeArr, priceArr, capacity);
        Population p = new Population(10, executionTimeArr.length);
        p.calculateFitness(t);
        p.sortGenes();
        int i = 0;
        int check = 0;
        int counter = 0;
        while (i < 100) {
            if(true){
                if(check == 0){
                    check = p.population[0].fitnessValue;
                }
                if(check == p.population[0].fitnessValue){
                    counter += 1;
                }
                else{
                    counter = 0;
                }
                if(counter == 5){
                    break;
                }
            }
            // System.out.println("Gen: " + p.generation);
            p.generation++;
            // p.printPopulation();
            p.selection();
            p.mutation();
            p.calculateFitness(t);
            p.sortGenes();
            // System.out.println("CrossOver Count: " + p.crossOverCount);
            // System.out.println("Mutation Count: " + p.mutationCount);
            // p.crossOverCount = 0;
            // p.mutationCount = 0;
            // System.out.println("-----------------------------------------------");
            i++;
        }
        long end = System.nanoTime();
        long geneticExecutionTime = end - start;
        long[] returnGenetic = {p.population[0].fitnessValue, geneticExecutionTime};
        return returnGenetic;
    }
}

class Optimal {
    public String padLeftZeros(String inputString, int length) {
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

    public int[] returnBitArray(int num, int len) {
        String strNum = Integer.toBinaryString(num);
        strNum = padLeftZeros(strNum, len);
        int[] result = new int[len];
        // System.out.println(strNum);
        for (int i = 0; i < len; i++) {
            result[i] = Integer.parseInt(String.valueOf(strNum.charAt(i)));
        }
        return result;
    }

    @SuppressWarnings("unused")
    public long[] optimal(int[] executionTimeArr, int[] priceArr, int capacity) {
        long start = System.nanoTime();
        int maxProfit = 0;
        int[] maxProfitCombination;
        int noOfTasks = executionTimeArr.length;
        int totalNoOfCombinations = (int) Math.pow(2, noOfTasks);
        for (int i = 0; i < totalNoOfCombinations; i++) {
            int[] temp = returnBitArray(i, noOfTasks);
            int tempProfit = 0;
            int tempCapacity = 0;
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] == 1) {
                    tempProfit = tempProfit + priceArr[j];
                    tempCapacity = tempCapacity + executionTimeArr[j];
                }
            }
            if (tempCapacity <= capacity) {
                if (maxProfit < tempProfit) {
                    maxProfit = tempProfit;
                    maxProfitCombination = returnBitArray(i, noOfTasks);
                }
            }
        }
        long end = System.nanoTime();
        long optimalExecutionTime = end - start;
        long[] returnOptimalValue = {maxProfit, optimalExecutionTime};
        return returnOptimalValue;
    }
}

class SortByRatio implements Comparator<ItemValue> {
    public int compare(ItemValue item1, ItemValue item2) {
        double cpr1 = item1.executionTime / item1.price;
        double cpr2 = item2.executionTime / item2.price;
        if (cpr1 < cpr2)
            return 1;
        else
            return -1;
    }

}

class ItemValue {
    int executionTime, price;

    public ItemValue(int executionTime, int price) {
        this.executionTime = executionTime;
        this.price = price;
    }
}

class Greedy {
    public long[] greedy(int[] executionTimeArr, int[] priceArr, int capacity) {
        long start = System.nanoTime();
        ItemValue[] itemValue = new ItemValue[executionTimeArr.length];
        for (int i = 0; i < itemValue.length; i++) {
            itemValue[i] = new ItemValue(executionTimeArr[i], priceArr[i]);
        }
        Arrays.sort(itemValue, new SortByRatio());
        int maxValue = 0;
        int currentCapacity = 0;
        for (int i = 0; i < itemValue.length; i++) {
            if (capacity - (currentCapacity + itemValue[i].executionTime) >= 0) {
                maxValue = maxValue + itemValue[i].price;
                currentCapacity = currentCapacity + itemValue[i].executionTime;
            }
        }
        long end = System.nanoTime();
        long greedyExecutionTime = end - start;
        long[] returnGreedyValue = {maxValue, greedyExecutionTime}; 
        return returnGreedyValue;
    }
}

class WriteToCsv {
    public WriteToCsv() {
        File file = new File("/home/ans/code playground/CC/Data/scheduling.txt");
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.newLine();
            String header = "S.no, Optimal, OptimalExecutionTime, Greedy, GreedyExecutionTime, Genetic, GeneticExecutionTime";
            writer.write(header);
            writer.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToCsv(String data) {
        File file = new File("/home/ans/code playground/CC/Data/scheduling.txt");
        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fr);
            writer.newLine();
            writer.write(data);
            writer.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class PoissonDistribution {

    public int getPoissonRandom(double mean) {
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
}

class DriverMain {
    public void driver(int size, int loop) {
        GeneticAlgorithm genetic = new GeneticAlgorithm();
        Optimal optimal = new Optimal();
        Greedy greedy = new Greedy();

        WriteToCsv wtcsv = new WriteToCsv();
        PoissonDistribution pd = new PoissonDistribution();
        final int SIZE = size;
        final int LOOP = loop;
        final int RANDOM_BURST_TIME_MAX = 200;
        final int RANDOM_BURST_TIME_MIN = 100;
        final int RANDOM_PRICE_MAX = 200;
        final int RANDOM_PRICE_MIN = 100;

        for (int i = 0; i < LOOP; i++) {
            int temp = pd.getPoissonRandom(SIZE);
            int[] executionTimeArr = new int[temp];
            int[] priceArr = new int[temp];
            int capacity = ThreadLocalRandom.current().nextInt(1000, 5000);
            for (int j = 0; j < temp; j++) {
                executionTimeArr[j] = ThreadLocalRandom.current().nextInt(RANDOM_BURST_TIME_MIN, RANDOM_BURST_TIME_MAX);
                priceArr[j] = ThreadLocalRandom.current().nextInt(RANDOM_PRICE_MIN, RANDOM_PRICE_MAX);
            }
            long[] a = optimal.optimal(executionTimeArr, priceArr, capacity);
            long[] b = greedy.greedy(executionTimeArr, priceArr, capacity);
            long[] c = genetic.geneticAlgorithm(executionTimeArr, priceArr, capacity);
            String data = i + ", " + a[0] + ", " + a[1] + ", " + b[0] + ", " + b[1] + ", " + c[0] + ", " + c[1];
            wtcsv.writeToCsv(data);
            System.out.println(data);
        }
    }
}

public class SuperScheduling {
    public static void main(String[] args) {
        DriverMain dm = new DriverMain();
        final int SIZE = 30;
        for(int i = 5; i <= SIZE; i+=5){
            dm.driver(i, 10000);
        }
    }
}
