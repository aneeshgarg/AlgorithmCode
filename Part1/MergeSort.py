__author__ = 'Aneesh Garg'

ASC = "Ascending"
DESC = "Descending"

class MergeSort:

    def __init__(self, mode = ASC):
        self.mode = mode


    def mergeSort(self, data):
        if len(data) <= 1:
            return data
        else:
            center = int(len(data)/2)
            left, right = data[:center], data[center:]
            left = self.mergeSort(left)
            right = self.mergeSort(right)
            if left[-1] <= right[0]:
                return left + right
            else:
                return self.merge(left, right)

    def merge(self, left, right):
        result = []
        while len(left) > 0 and len(right) > 0:
            if left[0] <= right[0]:
                result.append(left[0])
                left = left[1:]
            else:
                result.append(right[0])
                right = right[1:]
        if len(left) > 0:
            result += left
        if len(right) > 0:
            result += right
        return result