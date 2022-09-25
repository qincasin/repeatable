package com.qjx.pattern.trash;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/24 14:54
 * @Description: 工厂通用化
 */
public class DynaFactory {
    private Map<String, Constructor> constructorMap = new HashMap<>();
    private String packageName;

    public DynaFactory(String packageName) {
        this.packageName = packageName;
    }

    @SuppressWarnings("unchecked")
    public <T extends Trash> T create(TrashInfo info) {
        String typename = "com.qjx.pattern." + packageName + "." + info.type;
        try {
            //newInstance(info.data) info.data 会调用Constructor，向其传入重量 weight
            return (T) constructorMap.computeIfAbsent(typename, this::findConstructor).newInstance(info.data);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 生成对应的typename 对应的 Constructor
     * @param typename
     * @return
     */
    private Constructor findConstructor(String typename) {
        System.out.println("Loading " + typename);
        try {
            return Class.forName(typename).getConstructor(double.class);
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
