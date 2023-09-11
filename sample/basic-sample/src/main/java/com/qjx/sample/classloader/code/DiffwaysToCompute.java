package com.qjx.sample.classloader.code;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description>
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 *
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 *
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 *
 * 提示：
 *
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 *
 * @author qinjiaxing on 2023/8/9
 * @author <others>
 */
public class DiffwaysToCompute {

    public static void main(String[] args) {
        DiffwaysToCompute c = new DiffwaysToCompute();
        System.out.println(c.diffWaysToCompute("2-1-1"));
        System.out.println(c.diffWaysToCompute("2*3-4*5"));
    }

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (c) {
                            case '+':
                                rst.add(l + r);
                                break;
                            case '-':
                                rst.add(l - r);
                                break;
                            case '*':
                                rst.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (rst.size() == 0) {
            rst.add(Integer.valueOf(expression));
        }
        return rst;
    }


}
