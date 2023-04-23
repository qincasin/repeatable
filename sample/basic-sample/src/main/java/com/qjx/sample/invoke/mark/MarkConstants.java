package com.qjx.sample.invoke.mark;

import com.google.common.base.Splitter;
import com.qjx.sample.util.Pair;
import java.security.Key;
import java.util.List;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/4/23
 * @author <others>
 */
public class MarkConstants {

    public static final String join = "_";
    public static final String multi = ";";
    public static final String kv_assi = "=";
    public static final String head_assi = ":";

    public static final String from = "x-yxt-traffic-from";

    public static final String orig = "x-yxt-traffic-orig";

    public static String appendKv(String key, String value) {
        return key = kv_assi + value;
    }

    public static Pair<String, String> parseKv(String context) {
        List<String> kv = Splitter.on(kv_assi).trimResults().splitToList(context);
        if (kv.size() > 1) {
            return new Pair<>(kv.get(0).toLowerCase(), kv.get(1));
        }
        return new Pair<>(kv.get(0).toLowerCase(), null);
    }

    public static void main(String[] args) {
        Pair<String, String> stringStringPair = parseKv("abc key1=value1, key2=value2, key3=value3");
        System.out.println(stringStringPair);
    }

}
