import java.util.*;

class Solution {
    public int[] separateDigits(int[] nums) {
        
        ArrayList<Integer> list = new ArrayList<>();

        for (int num : nums) {

            // Convert number to string
            String str = Integer.toString(num);

            // Extract each digit
            for (char ch : str.toCharArray()) {
                list.add(ch - '0');
            }
        }

        // Convert ArrayList to int[]
        int[] result = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}