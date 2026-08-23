class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int cnt1 = 0;
        int cnt2 = 0;

        int sum1 = 0;
        int sum2 = 0;

        // First half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                cnt1++;
            } else {
                sum1 += num.charAt(i) - '0';
            }
        }

        // Second half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                cnt2++;
            } else {
                sum2 += num.charAt(i) - '0';
            }
        }

        // Alice wins
        return (cnt1 + cnt2) % 2 == 1
                || sum1 - sum2 != 9 * (cnt2 - cnt1) / 2;
    }
}