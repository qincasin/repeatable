package com.qjx.sample.scrach;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qjx.sample.util.BeanHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class YmcasApi {
    public static void main(String[] args) {
        List<ContentDTO> datas = getList2("nacos.json");
        for (ContentDTO  data: datas) {
            StringBuilder sb = new StringBuilder();
            sb.append(data.getAppName());
            sb.append("\t");
            String lastContent = data.getLastContent();
            String[] split = lastContent.split("\n");
            for (String s : split) {
                if (!StringUtils.isBlank(s)){
                    String[] split1 = s.split("=", 2);
                    boolean b = split1.length >= 2;
                    if (b){
                        String target = StringUtils.trim(split1[0]);
                        if (target.contains("ribbon.ReadTimeout") && !target.contains("#")){
                            String s1 = StringUtils.trim(split1[1]);
                            sb.append(s1).append("\t").append(StringUtils.trim(s)).append("\t")
                                    .append(StringUtils.trim(data.getLastModifyUser()));
                            System.out.println(sb.toString());
                        }
                    }
                }
            }
        }
    }
    protected static List<ContentDTO> getList2(String fileName) {
        String path = YmcasApi.class.getClassLoader().getResource(fileName).getPath();
        String json = readJsonFile(path);
        Type listType = new TypeReference<List<ContentDTO>>() {
        }.getType();
        List<ContentDTO> list = JSON.parseObject(json, listType);
        return list;
    }

    protected static List<NacosData> getList(String fileName) {
        String path = YmcasApi.class.getClassLoader().getResource(fileName).getPath();
        String json = readJsonFile(path);
        Type listType = new TypeReference<List<NacosData>>() {
        }.getType();
        List<NacosData> list = JSON.parseObject(json, listType);
        System.out.println(BeanHelper.bean2Json(list));
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
