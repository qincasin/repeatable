package com.qjx.sample.test;

import groovy.lang.GroovyShell;
import java.io.File;
import java.io.IOException;

/**
 * @author qinjiaxing on 2022/12/21
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
        GroovyShell shell = new GroovyShell();
        Object evaluate = shell.evaluate(new File("/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/java/com/qjx/sample/test/Test.groovy"));
        // System.out.println(evaluate.toString());
        System.out.println(111);
    }
}
