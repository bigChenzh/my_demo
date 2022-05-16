### 写在前面
log4j2的包一定要使用2.16.0之后的版本
会有攻击问题 注意!!!

### 如何尝试?
由于日志门面技术只要导入对应的包，日志门面框架自己就可以完成代理
所以想要试那个实现框架 只要修改门面框架的依赖即可
配置文件在具体日志实现框架内的resource文件下 可自己修改体验
测试类使用门面框架的测试类就可以
### 文件名含义
#### log_class_xxx
表明是一个日志实现框架
#### log_interface_xxx
表明是一个日志门面框架
#### log_interClass_xxx
表明是一个日志实现框架又是一个日志门面框架

## 建议玩的路线
jul->jcl->log4j->log4j2->slf4j->logback->springboot

## tips
log4j2的小脾气
想要使用log4j2的异步logger 需要如下jar包
```xml
<!--异步的logger依赖的高性能队列-->
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.4.2</version>
</dependency>
```

slf4j+log4j2 需要的jar包
```xml
<!--log4j2门面-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.12.1</version>
</dependency>
<!--log4j2核心-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.12.1</version>
</dependency>
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-jcl</artifactId>
    <version>2.12.1</version>
</dependency>
<!--异步的logger依赖的高性能队列-->
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.4.2</version>
</dependency>
<!--slf4j链接log4j2-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.12.1</version>
</dependency>

```

springboot原生支持logback 想要换log4j2
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!--去除logback支持-->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!--log4j2支持-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
