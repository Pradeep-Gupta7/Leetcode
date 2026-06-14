class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
            int sum = 0;

            // Calculate total weight of the word
            for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];
            }

            // Take modulo 26
            int rem = sum % 26;

            // Reverse mapping:
            // 0 -> 'z', 1 -> 'y', ..., 25 -> 'a'
            char mapped = (char) ('z' - rem);

            ans.append(mapped);
        }

        return ans.toString();
    }
}