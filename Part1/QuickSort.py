__author__ = 'Aneesh Garg'

import random

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
        size = right-left
        if (size > 10):
            print("InPlace Quick sort on input size = " + str(size))
            if left > right:
                return data
            pivotIndex = self.getPivotRank(data, left, right)
            pivot = data[pivotIndex]
            print(pivot)
            h, k = self.inPlacePartition(data, pivot, left, right)
            #print(data)
            data = self.inPlaceQuickSort(data, left, h-1)
            data = self.inPlaceQuickSort(data, k + 1, right)
        else:
            data[left:right] = self.insertionSort(data[left:right])
        return data

    def insertionSort(self,data):
        size = len(data)
        print("Insertion sort on input size = " + str(size))
        for j in range(1,size):
            #print(str(j)+"  "+str(data[j]))
            key = data[j]
            i = j-1
            while i>=0 and data[i] > key:
                data[i+1] = data[i]
                i -= 1
                data[i+1] = key
        return data

    def inPlacePartition(self, data, pivot, minimum, maximum):
        j = minimum
        k = maximum
        size = len(data)
        while j < k:
            while data[j] < pivot and j < size:
                j += 1

            while data[k] > pivot and k >= 0:
                k -= 1

            data[j], data[k] = data[k], data[j]
            print(str(minimum) +"-"+str(maximum) + str(data))
        return j, k

    def getPivotRank(self, data, minimum, maximum):
        rank = random.randint(minimum, maximum)
        if self.pivotMode == FIRST_PIVOT:
            rank = minimum
        elif self.pivotMode == LAST_PIVOT:
            rank = maximum
        return rank

