package com.qjx.sample.classloader.code;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/9
 * @author <others>
 */
public class Rob2 {

    public static void main(String[] args) {
        Rob2 r = new Rob2();
        System.out.println(r.rob(new int[]{1,2,3}));
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // db方程 转换成 max([0,length-1] , [1,nums.length])
        return Math.max(rob(nums, 0, nums.length - 2), rob(nums, 1, nums.length - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int first = nums[start];
        int second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int tmp = second;
            second = Math.max(second, first + nums[i]);
            first = tmp;
        }
        return second;
    }

}
