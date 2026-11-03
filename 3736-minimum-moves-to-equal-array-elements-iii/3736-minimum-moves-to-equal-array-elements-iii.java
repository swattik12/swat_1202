class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        int sum = 0;

        for (int x : nums) {
            if (x > maxVal) {
                maxVal = x;
            }
            sum += x;
        }

        return (nums.length * maxVal) - sum;
    }
}