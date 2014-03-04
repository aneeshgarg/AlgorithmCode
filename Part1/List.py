__author__ = 'Aneesh Garg'

from Node import Node


class List:

    def __init__(self,data = None):
        self.first = None
        self.last = None
        self.size = 0
        if data is not None:
            self.createList(data)

    def createList(self,data):
        for key in data:
            self.insertLast(key)

    def removeFirst(self):
        key = self.first.key
        self.first = self.first.next
        self.size -= 1
        if self.size == 0:
            self.first = self.last = None
        return key


    def insertFirst(self,key):
        node = Node(key)
        node.next = self.first
        self.first = node
        self.size += 1
        if self.size == 1:
            self.last = self.first

    def insertLast(self,key):
        node = Node(key)
        if self.size == 0:
            self.last = self.first = node
        else:
            self.last.next = node
            self.last = node
        self.size += 1

    def first(self):
        return self.first

    def last(self):
        return self.last

    def isFirst(self, node):
        return self.first == node

    def isLast(self,node):
        return self.last == node

    def isEmpty(self):
        return self.first is None

    def printList(self):
        if self.size == 0:
            return 'List is Empty !!!'
        list = '['
        current = self.first
        while current is not None:
            list += str(current.key) + ""
            current = current.next
            if current is not None:
                list += ', '
        list += ']'
        return list