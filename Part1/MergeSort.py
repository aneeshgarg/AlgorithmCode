__author__ = 'Aneesh Garg'


from List import List

ASC = "Ascending"
DESC = "Descending"

class MergeSort:

    def __init__(self, mode):
        self.mode = mode

    def sort(self, list):
        #print(list.size)
        if list.size > 1:
            list1, list2 = self.partition(list)
            #print(list1.printList() + "     " + list2.printList())
            list1 = self.sort(list1)
            list2 = self.sort(list2)
            return self.merge(list1, list2)
        else:
            return List([list.removeFirst()])

    def partition(self, list):
        list1 = List()
        list2 = List()
        current = list.first
        while current is not None:
            list1.insertLast(current.key)
            current = current.next
            if current is None:
                break
            list2.insertLast(current.key)
            current = current.next
        return list1, list2


    def merge(self, list1, list2):
        mergedList = List()
        while not list1.isEmpty() and not list2.isEmpty():
            condition = None
            if self.mode == ASC:
                condition = list1.first.key < list2.first.key
            else:
                condition = list1.first.key > list2.first.key
            #print(str(list1.first.key) + "   " + str(list2.first.key)+"   "+str(condition))
            if condition:
                mergedList.insertLast(list1.removeFirst())
            else:
                mergedList.insertLast(list2.removeFirst())

        while not list1.isEmpty():
            mergedList.insertLast(list1.removeFirst())

        while not list2.isEmpty():
            mergedList.insertLast(list2.removeFirst())

        return mergedList