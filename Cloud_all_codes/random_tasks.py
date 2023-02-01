import numpy as np


# price_paid = [np.random.randint(100, 200, lamda[0])]

# arival_time = [np.random.randint(1, 10, lamda[0])]

#  no. of task
#  profits
#  weights


def VMsize(n):
    return np.random.randint(20, 60, n)


def Profits(n):
    return np.random.randint(100, 200, n)


def weights(n):
    # if n > 5:
    #     return np.random.randint(1000, 2000)
    # else:
    #     return np.random.randint(500, 1000)
    return np.random.randint(200, 500)


if __name__ == '__main__':
    lamda = [5, 10, 15, 20]
    vmSize = []
    profits = []
    weight = []
    for i in range(len(lamda)):
        vmSize.append(VMsize(lamda[i]))
        profits.append(Profits(lamda[i]))
        weight.append(weights(lamda[i]))
    # print(vmSize)
    print(vmSize, profits, weight)


# **********VMSIZE****************
# [array([36, 47, 46, 48, 55]),
#  array([52, 27, 38, 49, 45, 59, 44, 25, 53, 38]),
#  array([35, 24, 27, 47, 24, 35, 59, 43, 57, 51, 28, 21, 21, 57, 52]),
#  array([58, 30, 28, 55, 20, 26, 38, 40, 47, 49, 39, 35, 39, 45, 41, 24, 34, 51, 51, 36])]
#
#
# ***********PROFITS*************
# [array([154, 131, 108, 166, 184]),
# array([193, 182, 157, 192, 186, 123, 154, 158, 180, 191]),
# array([128, 158, 169, 100, 147, 156, 142, 138, 103, 102, 141, 158, 102, 145, 168]),
# array([183, 176, 107, 185, 150, 180, 147, 148, 146, 151, 157, 108, 107, 137, 177, 179, 157, 197, 183, 132])]
#
#
# ***********Weghts**************
#  [460, 291, 428, 348]


#                                   Greedy Knapsack
# 1. len 5
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 10
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 15
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 20
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000


#                                   Genetic Algo
# 1. len 5
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 10
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 15
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 20
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000
#                                   Brute force
# 1. len 5
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 10
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 15
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000

# 1. len 20
# 108
# 131
# 184
# max profit : 148.0
# ET : 9000000
