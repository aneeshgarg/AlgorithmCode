__author__ = 'Aneesh Garg'

import math

class Heap:

    def __init__(self, data=None):
        self.heap = [None]
        self.last = 0
        self.size = 0
        if data is not None:
            self.createHeap(data)

    def createHeap(self, data):
        print("Creating heap of size "+ str(len(data)))
        for key in data:
            self.insertItem(key)
        print("Created heap of size "+ str(len(data)))

    def isEmpty(self):
        return self.size == 0

    def min(self):
        return self.heap[1]

    def insertItem(self, key):
        self.last += 1
        self.heap.append(key)
        self.upheap()
        self.size += 1

    def removeMin(self):
        if self.size == 0:
            return None
        else:
            minimum = self.min()
            self.heap[1] = self.heap[self.last]
            self.last -= 1
            self.size -= 1
            self.downheap()
            return minimum

    def downheap(self):
        """
        This method will restore heap order after and element is inserted
        """
        currentIndex = 1
        while True:
            childIndex = currentIndex * 2
            if childIndex > self.size:
                break
            if (childIndex + 1) < self.size:
                #This means there are two children. Take smaller or the left if they are eqaul
                if self.heap[childIndex] > self.heap[childIndex + 1]:
                    childIndex += 1
            if self.heap[currentIndex] <= self.heap[childIndex]:
                break
            #swap the two element if parent is greater than child
            self.heap[currentIndex], self.heap[childIndex] = self.heap[childIndex], self.heap[currentIndex]
            currentIndex = childIndex

    def upheap(self):
        """
        This method will restore heap order after and element is removed
        """
        index = self.last
        while index > 1:
            parent = math.floor(index/2)
            #break if parent is
            if self.heap[parent] <= self.heap[index]:
                break
            self.heap[parent], self.heap[index] = self.heap[index], self.heap[parent]
            index = parent

    def printHeap(self):
        if self.size == 0:
            return 'Heap is Empty !!!'
        else:
            return str(self.heap)