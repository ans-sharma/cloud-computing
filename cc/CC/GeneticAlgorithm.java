import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
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
        for (int i = 0; i < population.length-1; i++) {
            if(Math.random() < 0.1){
                int[] temp = crossOver(population[i].chromo, population[i + 1].chromo);
                population[i + 1].chromo = temp;
                ++crossOverCount;
                // revLen--;
                ++i;
            }

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
        Random random = new Random();
        for (int i = 0; i < population.length; i++) {
            if (Math.random() < 0.05) {
                mutationCount++;
                int[] temp = population[i].chromo;
                if (temp[random.nextInt(temp.length - 1)] == 1) {
                    temp[random.nextInt(temp.length - 1)] = 0;
                } else {
                    temp[random.nextInt(temp.length - 1)] = 1;
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

public class GeneticAlgorithm {
    public static void main(String[] args) {
        int[] executionTimeArr = { 
            382745,
            799601,
            909247,
            729069,
            467902,
             44328,
             34610,
            698150,
            823460,
            903959,
            853665,
            551830,
            610856,
            670702,
            488960,
            951111,
            323046,
            446298,
            931161,
             31385,
            496951,
            264724,
            224916,
            169684};
        int[] priceArr = { 
            825594
,            1677009,
            1676628,
            1523970,
             943972,
              97426,
              69666,
            1296457,
            1679693,
            1902996,
            1844992,
            1049289,
            1252836,
            1319836,
             953277,
            2067538,
             675367,
             853655,
            1826027,
              65731,
             901489,
             577243,
             466257,
             369261,
        };
        int capacity = 6404180; // opt soln - 110111000110100100000111 - 13549094
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
        while (i < 100) {
            System.out.println("Gen: " + p.generation);
            p.generation++;
            p.printPopulation();
            p.selection();
            p.mutation();
            p.calculateFitness(t);
            p.sortGenes();
            System.out.println("CrossOver Count: " + p.crossOverCount);
            System.out.println("Mutation Count: " + p.mutationCount);
            p.crossOverCount = 0;
            p.mutationCount = 0;
            System.out.println("-----------------------------------------------");
            i++;
        }
    }
}
