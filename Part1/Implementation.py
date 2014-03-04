__author__ = 'Aneesh Garg'


import random
from List import List
import MergeSort


N = 15


data = []
for i in range(N):
    data.append(random.randint(0, N*10))
print(data)

"""
list = List(data)
mergeSort = MergeSort.MergeSort(MergeSort.ASC)
sortedList = mergeSort.sort(list)
print(sortedList.printList())
"""














