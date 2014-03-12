__author__ = 'Aneesh Garg'

import random
import math

FIRST_PIVOT = "First"
LAST_PIVOT = "Last"
RANDOM_PIVOT = "Random"
MEDIAN_PIVOT = "Median"

class QuickSort:

    def __init__(self, pivotMode = RANDOM_PIVOT, modifiedSort = False):
        self.pivotMode = pivotMode
        self.modifiedSort = modifiedSort

    def sort(self, data):
        return self.inPlaceQuickSort(data,0,len(data)-1)

    def inPlaceQuickSort(self, data, left, right):
        size = right - left + 1
        #print("InPlace Quick sort on input size = " + str(size))
        if left >= right:
            return data
        if not self.modifiedSort or (size > 10 and self.modifiedSort):
            pivotIndex, data = self.getPivotRank(data, left, right)
            #print(str(data) + " pivotIndex=" + str(pivotIndex))
            newPivotIndex, data = self.inPlacePartition(data, left, right, pivotIndex)
            #print(str(data) + " newPivotIndex=" + str(newPivotIndex))
            data = self.inPlaceQuickSort(data, left, newPivotIndex-1)
            data = self.inPlaceQuickSort(data, newPivotIndex + 1, right)
        else:
            data = self.insertionSort(data, left, right)
        return data

    def insertionSort(self, data, left, right):
        size = right - left + 1
        #print("Insertion sort on input size = " + str(size))
        for j in range(left, right + 1):
            #print(str(j)+"  "+str(data[j]))
            key = data[j]
            i = j - 1
            while i >= 0 and data[i] > key:
                data[i+1] = data[i]
                i -= 1
                data[i+1] = key
        return data

    def inPlacePartition(self, data, left, right, pivotIndex):
        pivotValue = data[pivotIndex]
        data[pivotIndex], data[right] = data[right], data[pivotIndex] #Move pivot to the end
        storeIndex = left
        for i in range(left, right):
            if data[i] <= pivotValue:
                data[i], data[storeIndex] = data[storeIndex], data[i]
                storeIndex += 1
        data[storeIndex], data[right] = data[right], data[storeIndex]
        return storeIndex, data

    def getPivotRank(self, data, minimum, maximum):
        rank = random.randint(minimum, maximum)
        if self.pivotMode == FIRST_PIVOT:
            rank = minimum
        elif self.pivotMode == LAST_PIVOT:
            rank = maximum
        elif self.pivotMode == MEDIAN_PIVOT:
            center = math.floor((minimum + maximum)/2)
            if data[center] < data[minimum]:
                data[center], data[minimum] = data[minimum], data[center]
            if data[maximum] < data[minimum]:
                data[maximum], data[minimum] = data[minimum], data[maximum]
            if data[maximum] < data[center]:
                data[maximum], data[center] = data[center], data[maximum]
            rank = center
        return rank, data

