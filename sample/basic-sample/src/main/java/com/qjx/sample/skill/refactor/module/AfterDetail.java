package com.qjx.sample.skill.refactor.module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
@Slf4j
public class AfterDetail {
    // 对比明细

    // 比对两个对象的差异
    private static List<String> compareObject(Object obj1, Object obj2) {
        List<String> rst = new ArrayList<>();
        // 通过反射获取类的所有属性
        Class<?> aClass = obj1.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            field.setAccessible(true);
            try {
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);
                if (value1 == null && value2 == null) {
                    continue;
                }
                if (value1 == null || value2 == null) {
                    rst.add(name + "字段不一致");
                    continue;
                }
                if (!value1.equals(value2)) {
                    rst.add(name + "字段不一致");
                }
            } catch (IllegalAccessException e) {
                log.error("compareObject error", e);
            }
        }
        return rst;
    }

    /**
     * 明细和余额的对比类似,代码整体流程:
     * <p>
     * -  读取A、B端文件到内存的两个list
     * -  两个list通过某个唯一key转化为map
     * -  两个map字段逐个对比
     *
     * @param detailPathOfA
     * @param detailPathOfB
     * @throws IOException
     */
    private void checkDetail(String detailPathOfA, String detailPathOfB) throws IOException {
        // 读取A端的文件
        List<DetailDTO> resultListOfA = readFile(detailPathOfA);
        // 读取B端的文件
        List<DetailDTO> resultListOfB = readFile(detailPathOfB);
        // A列表转化为Map
        Map<String, DetailDTO> resultMapOfA = convertListToMap(resultListOfB);
        // B列表转化为Map
        Map<String, DetailDTO> resultMapOfB = convertListToMap(resultListOfB);
        // 明细逐个对比
        for (Map.Entry<String, DetailDTO> temp : resultMapOfA.entrySet()) {
            if (resultMapOfB.containsKey(temp.getKey())) {
                DetailDTO detailOfA = temp.getValue();
                DetailDTO detailOfB = resultMapOfB.get(temp.getKey());
                List<String> list = compareObject(detailOfA, detailOfB);
                for (String s : list) {
                    log.warn("{} is different,key:{}", s, detailOfA.getKey());
                }
            }
        }
    }

    // 抽取公用方法
    private List<DetailDTO> readFile(String detailPath) throws IOException {
        List<DetailDTO> resultList = new ArrayList<>();
        try (BufferedReader reader1 = new BufferedReader(new FileReader(detailPath))) {
            String line;
            while ((line = reader1.readLine()) != null) {
                resultList.add(DetailDTO.convert(line));
            }
        }
        return resultList;
    }

    private Map<String, DetailDTO> convertListToMap(List<DetailDTO> detailList) {
        Map<String, DetailDTO> resultMap = new HashMap<>();
        for (DetailDTO detail : detailList) {
            resultMap.put(detail.getBizSeq(), detail);
        }
        return resultMap;
    }


}
