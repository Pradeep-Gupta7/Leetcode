class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) return "";
        StringBuilder ans = new StringBuilder();
        while (num-- > 0) {
            int d = Math.min(9, sum);
            ans.append(d);
            sum -= d;
        }
        return ans.toString();
    }
}