class Solution {
    public int scoreOfParentheses(String s) {
         int n = s.length();
        Deque<Integer> stack = new ArrayDeque<>();

        int score = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(score);
                score = 0;
            } else {
                if (s.charAt(i - 1) == '(') { // we found innermost "()" -> +1 point
                    score = stack.peek() + 1;
                } else {
                    // had content inside -> double it
                    score = stack.peek() + (2 * score);
                }
                stack.pop();
            }
        }
        return score;
    }
}