from math import inf

n=int(input())
a = []
for i in range(0, n):
    name= str(input())
    score=float(input())
    a.append([name, score])

# a = [['ans', 50.0], ['tamru', -50.0], ['yahi', -50.0], ['yohi', -50.0]]
a.sort()  # alphabetically sorted

arr2 = []
for i in range(0, len(a)):
    arr2.append(a[i][1])
arr2.sort(reverse=True)
# print(arr2)
# print(a)

first = second = inf
for i in range(0, len(a)):

    # If current element is smaller than first then
    # update both first and second
    if arr2[i] < first:
        second = first
        first = arr2[i]
    # If arr[i] is in between first and second then
    # update second
    elif (arr2[i] < second and arr2[i] != first):
        second = arr2[i]
# print(second)

#printing the array
for i in range(0, len(a)):
    if second == a[i][1]:
        print(a[i][0])

# for i in range(0, len(a)):
#     if arr2[-1]==arr2[-2]:
#         if a[i][1] == arr2[-3]:
#             print(a[i][0])
#     elif a[i][1] == arr2[-2]:
#         print(a[i][0])

# -50 -50 -50 51

#modifying it to test the env
