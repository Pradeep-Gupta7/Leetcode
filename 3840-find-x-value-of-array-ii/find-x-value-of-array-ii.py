class Solution(object):
    def resultArray(self, nums, k, queries):
        """
        :type nums: List[int]
        :type k: int
        :type queries: List[List[int]]
        :rtype: List[int]
        """
        n = len(nums)

        def merge(a, b):
            if a is None: return b
            if b is None: return a

            prod = a[1] * b[1] % k
            cnt = a[0][:]

            for r in range(k):
                cnt[a[1] * r % k] += b[0][r]

            return cnt, prod

        tree = [None] * (4 * n)

        def build(node, l, r):
            if l == r:
                rem = nums[l] % k
                cnt = [0] * k
                cnt[rem] = 1
                tree[node] = (cnt, rem)
                return

            mid = (l + r) // 2
            build(node * 2, l, mid)
            build(node * 2 + 1, mid + 1, r)
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1])

        def update(node, l, r, idx, val):
            if l == r:
                rem = val % k
                cnt = [0] * k
                cnt[rem] = 1
                tree[node] = (cnt, rem)
                return

            mid = (l + r) // 2
            if idx <= mid:
                update(node * 2, l, mid, idx, val)
            else:
                update(node * 2 + 1, mid + 1, r, idx, val)

            tree[node] = merge(tree[node * 2], tree[node * 2 + 1])

        def query(node, l, r, ql, qr):
            if qr < l or r < ql:
                return None
            if ql <= l and r <= qr:
                return tree[node]

            mid = (l + r) // 2
            return merge(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            )

        build(1, 0, n - 1)
        ans = []

        for idx, val, start, x in queries:
            update(1, 0, n - 1, idx, val)
            ans.append(query(1, 0, n - 1, start, n - 1)[0][x])

        return ans