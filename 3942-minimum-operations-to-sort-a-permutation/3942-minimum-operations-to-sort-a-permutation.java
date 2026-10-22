class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        int pos = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                pos = i;
                break;
            }
        }

        // Check if cyclically increasing
        boolean isInc = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                isInc = false;
                break;
            }
        }

        if (isInc) {
            int cost1 = pos;
            int cost2 = 2 + (n - pos) % n;
            return Math.min(cost1, cost2);
        }

        // Check if cyclically decreasing
        boolean isDec = true;
        for (int i = 0; i < n; i++) {
            if (nums[(pos - i + n) % n] != i) {
                isDec = false;
                break;
            }
        }

        if (isDec) {
            // Option 1: Reverse first, then rotate left
            int cost1 = 1 + (n - 1 - pos);
            // Option 2: Rotate left first, then reverse
            int cost2 = 1 + (pos + 1) % n;
            return Math.min(cost1, cost2);
        }

        return -1;
    }
}