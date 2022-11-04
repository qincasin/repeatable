package com.qjx.sample.scrach;

/**
 * @author: qinjiaxing
 * @Date: 2022/9/30 09:45
 * @Description:
 */
public class EventAPi {
    private static String ali_format = "阿里环境\\t%s\\t%s\\t负责人\\t%s\\t原因\\t%s\\t原因\\t%s\\t原因\\t";
    private static String tc_format = "腾讯环境\\t%s\\t%s\\t负责人\\t%s\\t原因\\t%s\\t原因\\t%s\\t原因\\t";

    public static void main(String[] args) {
        String startTime = "";
        String endTime = "";
        
    }

    private static String buildALiFormat(String time, String svc, String oomNum, String restartNum, String unhealthNum) {
        return String.format(ali_format, time, svc, oomNum, restartNum, unhealthNum);
    }

    private static String buildTcFormat(String time, String svc, String oomNum, String restartNum, String unhealthNum) {
        return String.format(tc_format, time, svc, oomNum, restartNum, unhealthNum);
    }


}
