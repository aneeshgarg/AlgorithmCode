__author__ = 'Aneesh Garg'


class Node:
    """
    This Node will be used to construct trees or lists.
    Attributes Used:
        Tree -> key, right, left
        List -> key, next
    """

    def __init__(self,key):
        self.key = key
        self.next = None
        self.right = None
        self.left = None


    def __eq__(self, other):
        if other is None:
            return False
        return (self.key == other.key) and (self.next == other.next) and (self.left == other.left) and (self.right == other.right)