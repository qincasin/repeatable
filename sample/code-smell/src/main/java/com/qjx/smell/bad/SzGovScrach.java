package com.qjx.smell.bad;

import com.qjx.smell.bad.util.BeanHelper;
import com.qjx.smell.bad.util.HttpUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/5/21
 * @author <others>
 */
public class SzGovScrach {

    private static String child_Url = "http://spf.szfcweb.com/szfcweb/(S(dzcoq2h5jj1s4s3yoz2ynx0k))/DataSerach/SaleInfoHouseView.aspx?SHSE_ID=%s";

    private static String fileName = "/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/java/com/qjx/sample/scrach/sz.html";
    private static String fileName2 = "/Users/qinjiaxing/code/casin/repeatable/sample/basic-sample/src/main/java/com/qjx/sample/scrach/sz-child.html";
    private static String num = "1";

    public static void main(String[] args) {
        String date = "2023-05-21 17:00:00";
        List<House> list1 = new ArrayList<>();
        // System.out.println(date);
        doPrint(date, list1);


    }

    private static void doPrint(String date, List<House> list1) {
        try {
            int page = 1;
            for (int i = 0; i < page; i++) {
                String file1 = rpcParent(i);
                try {
                    Document d1 = readFilesBefore(file1);
                    List<House> list = parseDocumentBefore(d1);
                    for (House house : list) {
                        String child = rpcChild(house);
                        Document document = readFiles(child);
                        House h = null;
                        try {
                            h = parseDocumentAfter(document, house);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (h == null) {
                            continue;
                        }
                        h.setCreateTime(date);
                        // 转json
                        list1.add(h);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("第 " + i + "页 数据有问题");
                    continue;
                }
                System.out.println("输出页码 【" + i + "】， 数据 : " + BeanHelper.bean2Json(list1));
            }
            System.out.println("最终的结果输出: " + BeanHelper.bean2Json(list1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String rpcParent(int i) throws Exception {
        return HttpUtil.httpPost(HttpUtil.Referer, i, (i == 0));
    }

    private static Document readFilesBefore(String fileName1) throws Exception {
        // File file = new File(fileName);
        return Jsoup.parse(fileName1, "utf-8");
    }

    private static List<House> parseDocumentBefore(Document d1) {
        Element mainContentPageGridView1 = d1.getElementById("MainContent_PageGridView1");
        Elements tr = mainContentPageGridView1.select("tr");
        List<House> list = new ArrayList<>();
        for (int i = 1; i < tr.size(); i++) {
            House h = new House();
            Elements td = tr.get(i).select("td");
            h.setId(td.get(0).text());
            h.setLocation(td.get(0).text());
            h.setType(td.get(3).text());
            h.setAllArea(td.get(4).text());
            h.setPerPrice(td.get(5).text());
            String id = parseId(td.get(0));
            h.setId(id);
            list.add(h);
        }
        return list;
    }

    private static String parseId(Element element) {
        // ((Attributes)element).attributes.get("onclick").trim().substring(58+9,78);
        String onclick = element.attributes().get("onclick").trim();
        int i = onclick.lastIndexOf("SHSE_ID=");
        return onclick.substring(i + 8, i + 8 + 12);
    }

    private static String rpcChild(House house) throws Exception {
        String format = String.format(child_Url, house.getId());
        return HttpUtil.httpGet(format.trim());
    }

    private static House parseDocumentAfter(Document document, House house) {
        Element table = document.select("table").get(1);
        Elements tr = table.select("tr");
        // 总价格
        String allPrice = tr.get(10).select("td").get(1).select("input").get(0).attributes().get("value");
        // 套内
        String in = tr.get(9).select("td").get(1).select("input").get(0).attributes().get("value");
        // 公摊
        String out = tr.get(9).select("td").get(3).select("input").get(0).attributes().get("value");
        // 单元号
        String unit = tr.get(1).select("td").get(5).select("input").get(0).attributes().get("value");
        // 楼层
        String floor = tr.get(1).select("td").get(1).select("input").get(0).attributes().get("value");
        // 抵押起始日期
        String createdTime = tr.get(8).select("td").get(1).select("span").text();
        // set
        house.setAllPrice(allPrice);
        house.setInArea(in);
        house.setOutArea(out);
        house.setFloor(floor);
        house.setUnit(unit);
        house.setCreatedTime(createdTime);
        return house;

    }

    private static Document readFiles(String fileName) throws Exception {
        // File file = new File(fileName2);
        return Jsoup.parse(fileName, "utf-8");
    }

    @Data
    public static class House {

        private String id;
        private String location;
        private String type;
        private String allArea;

        /**
         * 公摊面积
         */
        private String outArea;

        /**
         * 套内面积
         */
        private String inArea;
        private String perPrice;
        /**
         * 但愿
         */
        private String unit;

        /**
         * 建成日期
         */
        private String createdTime;


        private String allPrice;
        private String floor;

        private String createTime;
    }
}
