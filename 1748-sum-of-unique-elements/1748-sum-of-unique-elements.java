class Solution {
    public int sumOfUnique(int[] nums) {
        int[] freq = new int[101];

        for (int num : nums) {
            freq[num]++;
        }

        int sum = 0;

        for (int i = 1; i <= 100; i++) {
            if (freq[i] == 1) {
                sum += i;
            }
        }

        return sum;
    }
}