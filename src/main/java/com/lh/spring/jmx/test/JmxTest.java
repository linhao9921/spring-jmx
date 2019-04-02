package com.lh.spring.jmx.test;

import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author haol
 * @Date 2019/4/2 16:54
 * @Version 1.0
 * @Desciption 测试jmx
 */
@Component
@ManagedResource
public class JmxTest {

    /**
     * 测试jmx接口
     * @return
     */
    @ManagedOperation(description = "jmxTest")
    public Map<String, Object> textJmxMethod(){
        System.out.println("测试jmx接口成功！");

        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 0);
        map.put("message", "测试jmx接口成功！");

        return map;
    }
}
