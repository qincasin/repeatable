package com.qjx.sample.scrach;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qjx.sample.util.BeanHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/3/16
 * @author <others>
 */
public class CodeTest4 {

    public static void main(String[] args) {
        List<PageDTO> datas = getList2("cc.json");
        System.out.println(JSON.toJSON(datas));
        System.out.println(datas);
    }
    protected static List<PageDTO> getList2(String fileName) {
        String path = YmcasApi.class.getClassLoader().getResource(fileName).getPath();
        String json = readJsonFile(path);
        Type listType = new TypeReference<List<PageDTO>>() {
        }.getType();
        List<PageDTO> list = JSON.parseObject(json, listType);
        return list;
    }


    //读取json文件
    private static  String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
