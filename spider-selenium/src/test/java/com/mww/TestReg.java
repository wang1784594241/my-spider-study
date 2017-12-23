package com.mww;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/12/23.
 */
public class TestReg {
    @org.junit.Test
    public void name() throws Exception {
        String url = "http://www.jb51.net/article/35482.htm";
        String encode = URLEncoder.encode(url, "UTF-8");
        System.out.println(encode);
        String a = "asdasd'name=%s'asdazxasd";

//        (?<=exp)	匹配exp后面的位置
//        (?=exp)	匹配exp前面的位置
        Pattern pattern = Pattern.compile("(?<=\'name=)[\\s\\S]*(?=\')");
        Matcher matcher = pattern.matcher(String.format(a, encode));
        while (matcher.find()) {
            System.out.println(URLDecoder.decode(matcher.group(), "UTF-8"));
        }
    }
}
