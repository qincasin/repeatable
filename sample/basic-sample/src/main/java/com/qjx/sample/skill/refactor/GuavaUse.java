package com.qjx.sample.skill.refactor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.google.common.collect.Tables;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 参考 https://mp.weixin.qq.com/s/Yb4UuUxcDBkDVYQo1mMBKg
 *
 * @author qinjiaxing on 2023/9/11
 * @author <others>
 */
public class GuavaUse {

    public static void main(String[] args) {
        // 双键
        table();
    }

    /**
     * 1 key 对应 俩value ，比如 记录张三多个学科的学科成绩
     */
    public static void table() {
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();
        table.put("张三", "语文", 80);
        table.put("张三", "数学", 90);
        table.put("张三", "英语", 70);
        table.put("李四", "政治", 70);
        // 1. 常规打印输出
        Integer value = table.get("张三", "语文");
        System.out.println(value);
        Set<String> rowKey = table.rowKeySet();
        System.out.println("所有的row：" + rowKey);
        Set<String> columnKey = table.columnKeySet();
        System.out.println("所有的columnKey：" + columnKey);
        Collection<Integer> values = table.values();
        System.out.println("所有的value：" + values);
        // 2. 计算key对应的所有value的和
        for (String key : table.rowKeySet()) {
            int sum = table.row(key).values().stream().mapToInt(Integer::intValue).sum();
            System.out.println(key + "的总成绩：" + sum);
        }
        // 3. 转换rowKey和columnKey
        //{语文=张三, 数学=张三, 英语=张三, 政治=李四}
        Table<String, String, Integer> transpose = Tables.transpose(table);
        Set<Cell<String, String, Integer>> cells = transpose.cellSet();
        cells.forEach(cell -> {
            System.out.println(cell.getRowKey() + ":" + cell.getColumnKey() + ":" + cell.getValue());
        });
        // 4. 转为嵌套的map
        //{张三={语文=80, 数学=90, 英语=70}, 李四={政治=70}}
        Map<String, Map<String, Integer>> rowMap = table.rowMap();
        System.out.println("按照rowMap:" + rowMap);
        //{语文={张三=80}, 数学={张三=90}, 英语={张三=70}, 政治={李四=70}}
        Map<String, Map<String, Integer>> columnMap = table.columnMap();
        System.out.println("按照columnMap:" + columnMap);

    }

    /**
     * multimap ，一个key 可以映射多个value
     * 类似于：Map<String,List<Integer>> map = new HashMap<>();
     */
    public static void multiMap() {
    }

}
