package com.lh.spring.jmx;

import com.lh.spring.jmx.test.JmxTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author haol
 * @Date 2019/4/2 16:51
 * @Version 1.0
 * @Desciption 主程序入口
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
        context.start();

        JmxTest jmxTest = (JmxTest) context.getBean("jmxTest");
        System.out.println(jmxTest.textJmxMethod());

        while (true) {
            try {
                Thread.sleep(24 * 3600 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                context.close();
            }
        }
    }
}
