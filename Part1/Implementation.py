__author__ = 'Aneesh Garg'

import sys
import time
import random
import MergeSort
import QuickSort
import HeapSort
import matplotlib.pyplot as plt

maxSize = 100000
repetition = 10
if len(sys.argv) > 1:
    maxSize = int(float(sys.argv[1]))
    if len(sys.argv) > 2:
        repetition = int(float(sys.argv[2]))
print("Maximum input size: " + str(maxSize))
print("Repetition " + str(repetition))

# Creating various testing lengths less than maxSize
N = []
currentSize = 10
while currentSize <= maxSize:
    N.append(currentSize)
    if currentSize < 100:
        currentSize += 10
    elif currentSize < 1000:
        currentSize += 100
    elif currentSize < 10000:
        currentSize += 1000
    elif currentSize < 100000:
        currentSize += 10000
    else:
        currentSize += 25000
print(N)
outputString = ""
#{'N':(random,sorted,reverse)}
mergeSortTime = {}
heapSortTime = {}
inPlaceQuickSortTime = {}
modifiedQuickSortTime = {}

for n in N:
    data = []
    for i in range(n):
        data.append(random.randint(0, n*10))

    temp = "*"*25 + "Input Size: " + str(n) + "*"*25
    print(temp)
    outputString += temp + "\n\n"

    print("Merge Sort: ")
    print("Random Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        mergeSort = MergeSort.MergeSort(MergeSort.ASC)
        sortedData = mergeSort.mergeSort(data[:])
        if n < 100:
            print(data)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    randomSortTime = runtime/repetition
    print("Average Time Taken: "+str(randomSortTime))

    print("Sorted Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        if n < 100:
            print(sortedData)
        mergeSort = MergeSort.MergeSort(MergeSort.ASC)
        sortedData = mergeSort.mergeSort(sortedData[:])
        if n < 100:
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    sortedSortTime = runtime/repetition
    print("Time Taken: "+str(sortedSortTime))

    reverseSortedData = sortedData[::-1]
    print("Reverse Sorted Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        mergeSort = MergeSort.MergeSort(MergeSort.ASC)
        sortedData = mergeSort.mergeSort(reverseSortedData[:])
        if n < 100:
            print(reverseSortedData)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    reverseSortedSortTime = runtime/repetition
    print("Time Taken: "+str(reverseSortedSortTime))

    outputString += "Merge Sort:\n"
    outputString += "Random Order: " + str(randomSortTime) + "\n"
    outputString += "Sorted Order: " + str(sortedSortTime) + "\n"
    outputString += "Reverse Sorted Order: " + str(reverseSortedSortTime) + "\n"
    mergeSortTime[n] = (randomSortTime, sortedSortTime, reverseSortedSortTime)



    print("\nHeap Sort: ")
    print("Random Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        heapSort = HeapSort.HeapSort()
        sortedData = heapSort.sort(data[:])
        if n < 100:
            print(data)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    randomSortTime = runtime/repetition
    print("Time Taken: "+str(randomSortTime))

    print("Sorted Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        heapSort = HeapSort.HeapSort()
        if n < 100:
            print(sortedData)
        sortedData = heapSort.sort(sortedData[:])
        if n < 100:
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    sortedSortTime = runtime/repetition
    print("Time Taken: "+str(sortedSortTime))

    print("Reverse Sorted Order")
    reverseSortedData = sortedData[::-1]
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        heapSort = HeapSort.HeapSort()
        sortedData = heapSort.sort(reverseSortedData[:])
        if n < 100:
            print(reverseSortedData)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    reverseSortedSortTime = runtime/repetition
    print("Time Taken: "+str(reverseSortedSortTime))

    outputString += "\nHeap Sort:\n"
    outputString += "Random Order: " + str(randomSortTime) + "\n"
    outputString += "Sorted Order: " + str(sortedSortTime) + "\n"
    outputString += "Reverse Sorted Order: " + str(reverseSortedSortTime) + "\n"
    heapSortTime[n] = (randomSortTime, sortedSortTime, reverseSortedSortTime)



    print("\nIn-place Quick Sort with Random Pivot: ")
    print("Random Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        quickSort = QuickSort.QuickSort(QuickSort.RANDOM_PIVOT, False)
        sortedData = quickSort.sort(data[:])
        if n < 100:
            print(data)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    randomSortTime = runtime/repetition
    print("Time Taken: "+str(randomSortTime))

    print("Sorted Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        if n < 100:
            print(sortedData)
        quickSort = QuickSort.QuickSort(QuickSort.RANDOM_PIVOT, False)
        sortedData = quickSort.sort(sortedData[:])
        if n < 100:
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    sortedSortTime = runtime/repetition
    print("Time Taken: "+str(sortedSortTime))

    print("Reverse Sorted Order")
    reverseSortedData = sortedData[::-1]
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        quickSort = QuickSort.QuickSort(QuickSort.RANDOM_PIVOT, False)
        sortedData = quickSort.sort(reverseSortedData[:])
        if n < 100:
            print(reverseSortedData)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    reverseSortedSortTime = runtime/repetition
    print("Time Taken: "+str(reverseSortedSortTime))

    outputString += "\nIn-place Quick Sort with Random Pivot: \n"
    outputString += "Random Order: " + str(randomSortTime) + "\n"
    outputString += "Sorted Order: " + str(sortedSortTime) + "\n"
    outputString += "Reverse Sorted Order: " + str(reverseSortedSortTime) + "\n"
    inPlaceQuickSortTime[n] = (randomSortTime, sortedSortTime, reverseSortedSortTime)



    print("\nModified Quick Sort with Median of Three Pivot: ")
    print("Random Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        quickSort = QuickSort.QuickSort(QuickSort.MEDIAN_PIVOT, True)
        sortedData = quickSort.sort(data[:])
        if n < 100:
            print(data)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    randomSortTime = runtime/repetition
    print("Time Taken: "+str(randomSortTime))

    print("Sorted Order")
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        if n < 100:
            print(sortedData)
        quickSort = QuickSort.QuickSort(QuickSort.MEDIAN_PIVOT, True)
        sortedData = quickSort.sort(sortedData[:])
        if n < 100:
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    sortedSortTime = runtime/repetition
    print("Time Taken: "+str(sortedSortTime))

    print("Reverse Sorted Order")
    reverseSortedData = sortedData[::-1]
    runtime = 0
    for i in range(repetition):
        startTime = time.time()
        print("Start: "+str(startTime))
        quickSort = QuickSort.QuickSort(QuickSort.MEDIAN_PIVOT, True)
        sortedData = quickSort.sort(reverseSortedData[:])
        if n < 100:
            print(reverseSortedData)
            print(sortedData)
        tempTime = time.time() - startTime
        print("Iteration "+str(i)+" Runtime: "+str(tempTime))
        runtime += tempTime
        print("End: "+str(time.time()))
    reverseSortedSortTime = runtime/repetition
    print("Time Taken: "+str(reverseSortedSortTime))

    outputString += "\nModified Quick Sort with Median of Three Pivot: \n"
    outputString += "Random Order: " + str(randomSortTime) + "\n"
    outputString += "Sorted Order: " + str(sortedSortTime) + "\n"
    outputString += "Reverse Sorted Order: " + str(reverseSortedSortTime) + "\n\n\n"
    modifiedQuickSortTime[n] = (randomSortTime, sortedSortTime, reverseSortedSortTime)

#Writing output into a text file
file = open("output\Result.txt",'w')
file.write(outputString)
file.close()


#Creating plots
x_axis = [0] + N
merge_random = [0]
merge_sorted = [0]
merge_reverse = [0]
heap_random = [0]
heap_sorted = [0]
heap_reverse = [0]
inplace_random = [0]
inplace_sorted = [0]
inplace_reverse = [0]
modified_random = [0]
modified_sorted = [0]
modified_reverse = [0]
for n in N:
    merge = mergeSortTime[n]
    merge_random.append(merge[0])
    merge_sorted.append(merge[1])
    merge_reverse.append(merge[2])

    heap = heapSortTime[n]
    heap_random.append(heap[0])
    heap_sorted.append(heap[1])
    heap_reverse.append(heap[2])

    inplace = inPlaceQuickSortTime[n]
    inplace_random.append(inplace[0])
    inplace_sorted.append(inplace[1])
    inplace_reverse.append(inplace[2])

    modified = modifiedQuickSortTime[n]
    modified_random.append(modified[0])
    modified_sorted.append(modified[1])
    modified_reverse.append(modified[2])

print("\n\nDrawing Merge Sort Plots")
print("x-axis : "+str(x_axis))
print("y-axis (random) : "+str(merge_random))
print("y-axis (sorted) : "+str(merge_sorted))
print("y-axis (reverse) : "+str(merge_reverse))
plt.plot(x_axis, merge_random, marker='o', label='Random Case',  color='b')
plt.plot(x_axis, merge_sorted, marker='o', label='Sorted Case',  color='g')
plt.plot(x_axis, merge_reverse, marker='o', label='Reverse Case',  color='r')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Merge Sort')
plt.legend(loc=2)
plt.savefig("output\\1_merge_sort.png")
plt.clf()
print("Merge sort graph drawn\n")

print("\n\nDrawing Heap Sort Plots")
print("x-axis : "+str(x_axis))
print("y-axis (random) : "+str(heap_random))
print("y-axis (sorted) : "+str(heap_sorted))
print("y-axis (reverse) : "+str(heap_reverse))
plt.plot(x_axis, heap_random, marker='o', label='Random Case',  color='b')
plt.plot(x_axis, heap_sorted, marker='o', label='Sorted Case',  color='g')
plt.plot(x_axis, heap_reverse, marker='o', label='Reverse Case',  color='r')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Heap Sort')
plt.legend(loc=2)
plt.savefig("output\\2_heap_sort.png")
plt.clf()
print("heap sort graph drawn\n")

print("\n\nDrawing In-Place QuickSort Plots")
print("x-axis : "+str(x_axis))
print("y-axis (random) : "+str(inplace_random))
print("y-axis (sorted) : "+str(inplace_sorted))
print("y-axis (reverse) : "+str(inplace_reverse))
plt.plot(x_axis, inplace_random, marker='o', label='Random Case',  color='b')
plt.plot(x_axis, inplace_sorted, marker='o', label='Sorted Case',  color='g')
plt.plot(x_axis, inplace_reverse, marker='o', label='Reverse Case',  color='r')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('In-Place QuickSort')
plt.legend(loc=2)
plt.savefig("output\\3_inplace_quicksort.png")
plt.clf()
print("Inplace Quick Sort graph drawn\n")

print("\n\nDrawing Modified QuickSort Plots")
print("x-axis : "+str(x_axis))
print("y-axis (random) : "+str(modified_random))
print("y-axis (sorted) : "+str(modified_sorted))
print("y-axis (reverse) : "+str(modified_reverse))
plt.plot(x_axis, modified_random, marker='o', label='Random Case',  color='b')
plt.plot(x_axis, modified_sorted, marker='o', label='Sorted Case',  color='g')
plt.plot(x_axis, modified_reverse, marker='o', label='Reverse Case',  color='r')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Modified QuickSort')
plt.legend(loc=2)
plt.savefig("output\\4_Modified_quicksort.png")
plt.clf()
print("Modified Quick Sort graph drawn\n")

print("\n\nDrawing Random Order Plots")
print("x-axis : "+str(x_axis))
print("y-axis (Merge Sort) : "+str(merge_random))
print("y-axis (Heap Sort) : "+str(heap_random))
print("y-axis (Inplace QuickSort) : "+str(inplace_random))
print("y-axis (Modified QuickSort) : "+str(modified_random))
plt.plot(x_axis, merge_random, marker='o', label='Merge Sort',  color='b')
plt.plot(x_axis, heap_random, marker='o', label='Heap Sort',  color='g')
plt.plot(x_axis, inplace_random, marker='o', label='In-Place QuickSort',  color='r')
plt.plot(x_axis, modified_random, marker='o', label='Modified QuickSort',  color='y')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Random Order Sorting')
plt.legend(loc=2)
plt.savefig("output\\5_random_order_sort.png")
plt.clf()
print("Random Order Sort graph drawn\n")

print("\n\nDrawing Sorted Order Plots")
print("x-axis : "+str(x_axis))
print("y-axis (Merge Sort) : "+str(merge_sorted))
print("y-axis (Heap Sort) : "+str(heap_sorted))
print("y-axis (Inplace QuickSort) : "+str(inplace_sorted))
print("y-axis (Modified QuickSort) : "+str(modified_sorted))
plt.plot(x_axis, merge_sorted, marker='o', label='Merge Sort',  color='b')
plt.plot(x_axis, heap_sorted, marker='o', label='Heap Sort',  color='g')
plt.plot(x_axis, inplace_sorted, marker='o', label='In-Place QuickSort',  color='r')
plt.plot(x_axis, modified_sorted, marker='o', label='Modified QuickSort',  color='y')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Sorted Order Sorting')
plt.legend(loc=2)
plt.savefig("output\\6_sorted_order_sort.png")
plt.clf()
print("Sorted Order Sort graph drawn\n")

print("\n\nDrawing Reverse Sorted Order Plots")
print("x-axis : "+str(x_axis))
print("y-axis (Merge Sort) : "+str(merge_reverse))
print("y-axis (Heap Sort) : "+str(heap_reverse))
print("y-axis (Inplace QuickSort) : "+str(inplace_reverse))
print("y-axis (Modified QuickSort) : "+str(modified_reverse))
plt.plot(x_axis, merge_reverse, marker='o', label='Merge Sort',  color='b')
plt.plot(x_axis, heap_reverse, marker='o', label='Heap Sort',  color='g')
plt.plot(x_axis, inplace_reverse, marker='o', label='In-Place QuickSort',  color='r')
plt.plot(x_axis, modified_reverse, marker='o', label='Modified QuickSort',  color='y')
plt.xlabel('Input Size (N)')
plt.ylabel('Time Taken (s)')
plt.title('Reverse Sorted Order Sorting')
plt.legend(loc=2)
plt.savefig("output\\7_reverse_sorted_order_sort.png")
plt.clf()
print("Reverse Sorted Order Sort graph drawn\n")