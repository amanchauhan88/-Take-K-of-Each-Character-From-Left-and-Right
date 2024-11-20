import java.util.*;

class Solution {
    public int takeCharacters(String s, int k) {
        // Step 1: Count occurrences of each character
        int[] count = new int[3]; // count[0] for 'a', count[1] for 'b', count[2] for 'c'
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        
        // Step 2: Check if it's possible to take k of each character
        for (int i = 0; i < 3; i++) {
            if (count[i] < k) {
                return -1;
            }
        }
        
        // Step 3: Sliding window to find the largest valid window
        int n = s.length();
        int[] total = Arrays.copyOf(count, 3); // Total counts to check outside the window
        int maxWindowSize = 0;
        int[] windowCount = new int[3];
        int left = 0;

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            windowCount[ch - 'a']++;

            // Shrink window if it invalidates the condition
            while (windowCount[ch - 'a'] > total[ch - 'a'] - k) {
                windowCount[s.charAt(left) - 'a']--;
                left++;
            }

            // Update maximum valid window size
            maxWindowSize = Math.max(maxWindowSize, right - left + 1);
        }

        // Step 4: Minimum minutes required
        return n - maxWindowSize;
    }
}
