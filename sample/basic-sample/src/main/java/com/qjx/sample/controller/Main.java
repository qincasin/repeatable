package com.qjx.sample.controller;

import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/18
 * @author <others>
 */
public class Main {

    /**
     * string[] split(string src, string sub)
     * 例子：split(“abcdeefgefh”,”ef”).       [“abcde”,”g”,”h”]
     *
     * @param args
     */
    public static void main(String[] args) {
        Main m = new Main();
        String[] split = m.split("abcdeefgefh", "ef");
        System.out.println(Arrays.toString(split));
    }

    /**
     * string[] split(string src, string sub)
     * 例子：split(“abcde ef g ef h”,”ef”).       [“abcde”,”g”,”h”]
     *
     * @param src
     * @param sub
     * @return
     */
    public static String[] split(String src, String sub) {
        // rst
        List<String> res = new ArrayList<>();
        int startIndex = 0;
        while (startIndex < src.length()) {
            // 找最后一个index
            int endIndex = findIndexOf(src, sub, startIndex);
            if (endIndex == -1) {
                endIndex = src.length();
            }
            startIndex++;
            // 截取前面的值
            // TODO
            int start = 0;
            String split = subStr(src, start, endIndex);
            if (split.length() > 0) {
                res.add(split);
            }
        }
        return res.toArray(new String[0]);
    }

    private static String subStr(String src, int startIndex, int endIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            stringBuilder.append(src.charAt(i));
        }
        return stringBuilder.toString();
    }

    // 查找子串最后的一个char 在str中的下标
    private static int findIndexOf(String str, String sub, int startIndex) {
        int subIndex = 0;
        int srcLen = str.length();
        int subLen = sub.length();
        for (int i = startIndex; i < srcLen; i++) {
            if (str.charAt(i) == sub.charAt(subIndex)) {
                subIndex++;
                // 求最后一个值的下标位置
                if (subIndex == subLen) {
                    return i - subLen + 1;
                }
            } else {
                // 重置 为0
                subIndex = 0;
            }
        }
        return -1;
    }


}
