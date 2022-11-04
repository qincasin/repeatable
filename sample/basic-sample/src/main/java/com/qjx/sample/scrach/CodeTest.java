package com.qjx.sample.scrach;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/27 15:52
 * @Description:
 */
public class CodeTest {
    public static void main(String[] args) {
        String msg = "apis.evaluation.export.header.evalName";
        String s = msg.split("\\.")[1];
        System.out.println(s);
        test();
    }

    private static void test() {
        Set<String> set = new HashSet<>();
        List<String> duplicateList = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        String path = "/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/resources/code.txt";
        try (Stream<String> lines = Files.lines(Paths.get(path))){
                lines.filter(a->a.trim().length()!=0)
                    .filter(a->a.split(";").length>2)
                        .forEach(a->{
                            String[] split = a.split(";");
                            if (!set.add(split[0])){
                                duplicateList.add(split[0]);
                            }
                            count.getAndIncrement();
                        });

            System.out.println(count.get() +"   "+ set.size() +" " +duplicateList.size());
            System.out.println(duplicateList);
        }catch (Exception e){
            System.out.println(111);
        }
    }
}
