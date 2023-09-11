package com.qjx.sample.classloader;

import java.util.concurrent.Callable;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/8/7
 * @author <others>
 */
public class Main2 {

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        main2.reverseString("hello".toCharArray());
    }

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            swap(s, i++, j--);
        }
        System.out.println(new String(s));
    }

    private void swap(char[] chars, int l, int r) {
        char temp = chars[l];
        chars[l] = chars[r];
        chars[r] = temp;
    }

}
