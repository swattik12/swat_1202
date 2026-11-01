class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];
        long sum = 0;

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }

        // Each move decreases the relative distance to min by 1
        return (int) (sum - (long) min * nums.length);
    }
}