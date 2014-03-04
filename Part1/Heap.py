__author__ = 'Aneesh Garg'


from Node import Node


class Heap:

    def __init__(self,data=None):
        self.root = None
        self.lastNode = None
        self.size = 0
        if data is not None:
            self.createHeap(data)


    def createHeap(self,data):
        pass

