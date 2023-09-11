package com.qjx.sample.classloader;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/4
 * @author <others>
 * <p>
 * 题目：给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 :
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 */
public class Main {

    List<List<String>> result = new ArrayList<>();

    public static void main(String[] args) {
        Main main = new Main();
        List<List<String>> result = main.partition("aab");
        System.out.println(result);
    }

    public List<List<String>> partition(String s) {
        backtrack(s, 0, new ArrayList<>());
        return result;
    }

    void backtrack(String s, int start, List<String> path) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        //穷举
        for (int i = start; i < s.length(); i++) {
            if (find(s, start, i)) {
                path.add(s.substring(start, i + 1));
                //递归
                backtrack(s, i + 1, path);
                //
                path.remove(path.size() - 1);
            }
        }
    }

    boolean find(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

