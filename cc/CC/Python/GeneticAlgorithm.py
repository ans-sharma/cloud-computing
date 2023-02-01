import random


def createGene(geneSize):
    chromoArr = []
    for _ in range(geneSize):
        if random.random() > 0.5:
            chromoArr.append(0)
        else:
            chromoArr.append(1)
    chromoArr.append(0)  # adding extra bit to store fitness value
    return chromoArr


def createPopulation(populationSize, geneSize):
    populationArr = []
    for _ in range(populationSize):
        populationArr.append(createGene(geneSize))
    return populationArr


def calculateFitness(population, executionTimeArr, profitArr, maxCapacity):  # profit in our case
    for gene in population:
        profit = 0
        capacity = 0
        # beacause we are using the last bit for string the fitness value ie profit
        for i in range(len(gene) - 1):
            if gene[i] == 1:
                profit += profitArr[i]
                capacity += executionTimeArr[i]
        if capacity > maxCapacity:
            gene[-1] = 0  # -1 last element
        else:
            gene[-1] = profit


def sortWrtFitnessValue(population):
    return sorted(population, key=lambda x: x[-1], reverse=True)


def selection(population):
    tempLen = len(population)-1
    for i in range(1, len(population)//2):
        if random.random() < 0.2:
            try:
                temp = crossOver(population[i], population[i+1])
                population[tempLen] = temp
                tempLen -= 1
            except:
                pass


def crossOver(geneA, geneB):  # mix and match # yaha hoga fusion
    temp = random.randint(0, len(geneA)-1)
    tempArr = geneA[:temp] + geneB[temp:]
    return tempArr


def mutation(population): # captain america
    for i in range(len(population)):
        if random.random() < 0.05: # bahut kam
            temp = random.randint(0, len(population[i])-1)
            if population[i][temp] == 1:
                population[i][temp] = 0
            else:
                population[i][temp] = 1

def printResults(population, generation):
    print("--------------------------")
    print(f"Generation: {generation}")
    for gene in population:
        print(f"{gene[:-1]} Fitness: {gene[-1]}")
    print("--------------------------")

def geneticAlgo():
    executionTimeArr = [
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
            169684]
    profitArr = [
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
             369261
    ]
    maxCapacity = 6404180 # opt soln - 110111000110100100000111 - 13549094
    # executionTimeArr = [10, 20, 30, 40, 50]
    # profitArr = [60, 100, 120, 150, 200]
    # maxCapacity = 100
    generation = 0
    population = createPopulation(10, len(profitArr)) # random seed
    calculateFitness(population, executionTimeArr, profitArr, maxCapacity)
    population = sortWrtFitnessValue(population)
    # printResults(population, generation)

    i = 0
    while(i < 10):
        selection(population)
        mutation(population)
        calculateFitness(population, executionTimeArr, profitArr, maxCapacity)
        population = sortWrtFitnessValue(population)
        generation += 1
        # printResults(population, generation)
        i += 1
    return(population[0][-1])

print(geneticAlgo())