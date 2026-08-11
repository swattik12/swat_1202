class Solution {
    public int missingInteger(int[] nums) {
        int n = nums.length;
        
        int sum = nums[0];
        for(int i = 1 ; i < n ; i++){
            if(nums[i] == nums[i - 1] +1){
                sum += nums[i];
            }
            else{
                break;
            }
        }

        while(true){
            boolean found = false;
            for(int num : nums){
                if(sum == num){
                    found = true; 
                    break;
                }
            }
            if(!found){
                return sum;
            }
            sum++;
        }
    }
}