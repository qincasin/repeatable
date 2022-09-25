package com.qjx.pattern.trash;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 14:52
 * @Description: 信使对象 或者说叫做 dto 数据传输对象
 */
public class TrashInfo {
    public final String type;
    public final double data;

    public TrashInfo(String type, double data) {
        this.type = type;
        this.data = data;
    }

    @Override
    public String toString() {
        return "TrashInfo{" + "type='" + type + '\'' + ", data=" + data + '}';
    }
}
