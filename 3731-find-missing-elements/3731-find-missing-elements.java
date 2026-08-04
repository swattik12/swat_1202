class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> ans  = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int prev = nums[0];

        for(int i = 1 ; i < n ; i++){
            while(nums[i] != prev+1){
                ans.add(++prev);
            }
            prev = nums[i];
        }
        return ans;
    }
}