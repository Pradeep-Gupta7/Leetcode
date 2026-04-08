/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] a) {
        if (a == null || a.length == 0) return null;
        PriorityQueue<ListNode> q = new PriorityQueue<>((x,y)->x.val-y.val);
        for (ListNode n : a) if (n != null) q.add(n);
        ListNode d = new ListNode(0), t = d;
        while (!q.isEmpty()) {
            t = t.next = q.poll();
            if (t.next != null) q.add(t.next);
        }
        return d.next;
    }
}