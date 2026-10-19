class Solution(object):
    def targetIndices(self, nums, target):
        nums.sort()
        output = []
        for i in range(len(nums)):
            if nums[i] == target:
                output.append(i)
        return output