package com.qjx.sample.scrach;

import com.qjx.sample.util.BeanHelper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/27 15:52
 * @Description:
 */
public class CodeTest2 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        List<String> duplicateList = new ArrayList<>();
        List<CodeClass> dupClass = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        Map<String,Set<String>> map = new HashMap<>();
        String path = "/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/resources/code.txt";
        try (Stream<String> lines = Files.lines(Paths.get(path))){
            List<CodeClass> classList = lines.filter(a -> a.trim().length() != 0)
                    .filter(a -> a.split("\\t").length >= 2 && a.split("\\t")[1].trim().length() != 0
                            && a.split("\\t")[1].split(";").length > 2).map(a -> {
                        CodeClass code = new CodeClass(a.split("\\t")[0], a.split("\\t")[1].split(";")[0],
                                a.split("\\t")[1]);
                        code.setSvc(code.getKey().split("\\.")[1]);
                        return code;
                    }).collect(Collectors.toList());
            //6422
            System.out.println(classList.size());
            int size = classList.stream().map(a -> a.getCode() + a.getSvc()).collect(Collectors.toSet()).size();
            System.out.println(size);
            for (CodeClass codeClass : classList) {
                if (!set.add(codeClass.getCode() + codeClass.getSvc())) {
                    dupClass.add(codeClass);
                }
            }

            System.out.println(BeanHelper.bean2Json(dupClass));
            for (CodeClass aClass : classList) {
                String svc = aClass.getSvc();
                Set<String> strings = map.get(svc);
                if (!CollectionUtils.isEmpty(strings)){
                    strings.add(aClass.getCode());
                }else {
                    map.put(svc,new HashSet<>());
                }
            }
            System.out.println(BeanHelper.bean2Json(map));


        } catch (Exception e) {
            System.out.println(111);
        }
    }
}

@Data
class CodeClass {
    private String key;
    private String code;
    private String msg;

    private String svc;

    public CodeClass(String key, String msg) {
        this.key = key;
        this.msg = msg;
    }

    public CodeClass(String key, String code, String msg) {
        this.key = key;
        this.code = code;
        this.msg = msg;
    }


}
