package com.wuwq.yygh.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.test
 * @ClassName:MyTest
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-14 21:42
 * @Version: 1.0
 */
public class MyTest {

    @Test
    public void test(){
        Map map = new HashMap();
        map.put("aa",1);
        map.put("bb",2);
        map.put("cc","cc");
        map.put("dd","");
        String string = JSON.toJSONString(map);
        System.out.println(string);
    }

}
