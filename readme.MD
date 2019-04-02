# 基于spring-jmx的使用

## jmx-web

### 本例采用基于spring的jmx

#### 1. spring 集成jmx方式

- **在spring-jmx.xml中配置spring jmx如下：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--jmx相关配置-->
    <bean id="annotationMBeanExporter" class="org.springframework.jmx.export.annotation.AnnotationMBeanExporter"
          p:autodetect="true"
          p:assembler-ref="assembler"
          p:namingStrategy-ref="namingStrategy" />

    <bean id="assembler" class="org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler"
        p:attributeSource-ref="attributeSource"/>

    <bean id="namingStrategy" class="org.springframework.jmx.export.naming.MetadataNamingStrategy"
          p:attributeSource-ref="attributeSource"/>

    <bean id="attributeSource" class="org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource"/>
</beans>
```

- **使用注解@ManagedResource和 @ManagedOperation(description = "")加入jmx**
```java
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
```

上面的代码将textJmxMethod方法加入jmx监控。


#### 2. 加入jminix实现web端控制

##### 2.1 集成jminix

- **pom.xml中加入jminix**

```xml
<!-- jmx web monitor-->
<dependency>
  <groupId>org.jminix</groupId>
  <artifactId>jminix</artifactId>
  <version>1.1.0</version>
</dependency>
```   

- **web.xml中加入jminix-console的web接口**    
```xml
<servlet>
    <servlet-name>JmxMiniConsoleServlet</servlet-name>
    <servlet-class>org.jminix.console.servlet.MiniConsoleServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>JmxMiniConsoleServlet</servlet-name>
    <url-pattern>/jmx/*</url-pattern>
</servlet-mapping>
```

从上面配置可以看出,可以通过访问：http://{host}:{port}/jmx/ 地址进行查看。


##### 2.1 通过jconsole.exe查看

如果本地是win系统，并安装了java,可以通过cmd后输入jconsole.exe回车，弹出【java监控和管理控制台】，连接后，点击MBean可以看到对应的jmx接口。

