package com.qjx.sample.classloader.code;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/9
 * @author <others>
 */
public class Rob {

    public static void main(String[] args) {
        Rob rob = new Rob();
        System.out.println(rob.rob(new int[]{1, 2, 3, 1}));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        return dp[nums.length];
    }

    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 上一个
        int pre = 0;
        // 当前
        int cur = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            int temp = Math.max(cur, nums[i - 1] + pre);
            // 设置当前的为前一个
            pre = cur;
            // 设置当前的为当前的
            cur = temp;
        }
        return cur;
    }


}
