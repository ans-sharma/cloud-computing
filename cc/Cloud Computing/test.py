from typing import OrderedDict


b ={}

# This part is for taking user input

# for i in range(0, 5):
#     tempName = input("Enter the Name: ")
#     tempScore = int(input("Enter the Score: "))
#     b[tempName] = tempScore
# print(b)

b = {'yogi': 5, 'rahul': 10, 'ans': 1, 'pra': 5, 'ashutosh': 5}

dict1 = OrderedDict(sorted(b.items()))
print(dict1)