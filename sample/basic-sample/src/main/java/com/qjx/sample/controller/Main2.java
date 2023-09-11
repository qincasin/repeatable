package com.qjx.sample.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/28
 * @author <others>
 */
public class Main2 {

    public static void main(String[] args) {
        //[aab]  ==> "[a a b],[aa b]"
        //
        String s = "aab";
        List<String> list = new ArrayList<>();
        List<String> solution = solution(s, list);
        System.out.println(solution);
    }

    private static List<String> solution(String s, List<String> list) {
        int l = 0;
        int r = l + 1;
        for (int i = l; i < r; i++) {
            String substring = s.substring(l, i);
            if (isPara(substring)) {
                list.add(substring);
            } else {
                //TODO
                l++;
                r++;
            }
        }
        return list;
    }

    // 判断回文串
    private static boolean isPara(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }


}
