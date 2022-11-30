package com.qjx.sample._17;

/**
 * @author: qinjiaxing
 * @Date: 2022/11/30 12:33
 * @Description:
 */
public class ClassA {
    public static void main(String[] args) {
        System.out.println("hello world ");

        String text = """
                                         No man is an island,
                                           Entire of itself,
                                Every man is a piece of the continent,
                                         A part of the main.
                """;
        System.out.println(text);

        String html = """
                <html>
                 <head></head>
                 <body>
                  <div align="center">
                   No man is an island,<br>
                   Entire of itself,<br>
                   Every man is a piece of the continent,<br>
                   A part of the main.
                  </div>
                 </body>
                </html>
                """;
        System.out.println(html);
    }
}
