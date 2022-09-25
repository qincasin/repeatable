package com.qjx.pattern.trash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/25 09:45
 * @Description: 从文件中解析Trash
 */
public class ParseTrash {
    public static String source = "Trash.dat";

    public static <T extends Trash> void fillBin(String packageName, Fillable<T> bin) {
        DynaFactory factory = new DynaFactory(packageName);
        try (Stream<String> lines = Files.lines(Paths.get("/Users/qinjiaxing/code/casin/repeatable/sample/pattern-sample/src/main/java/com/qjx/pattern/trash", source))) {
            lines
                    //移除注释和空行
                    .filter(line->line.trim().length()!=0)
                    .filter(line->!line.startsWith("//"))
                    .forEach(line->{
                        String type = line.substring(0, line.indexOf(":")).trim();
                        double weight = Double.parseDouble(line.substring(line.indexOf(":") + 1).trim());
                        bin.addTrash(factory.create(new TrashInfo(type,weight)));
                    });
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends Trash> void fillBin(String packageName, List<T> bin) {
        fillBin(packageName, new FillableList<>(bin));
    }

    public static void main(String[] args) {
        List<Trash> bin = new ArrayList<>();
        fillBin("trash",bin);
        for (Trash trash : bin) {
            System.out.println(trash);
        }
    }
}
