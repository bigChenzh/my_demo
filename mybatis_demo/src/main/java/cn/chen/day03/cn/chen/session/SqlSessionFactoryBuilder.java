package cn.chen.day03.cn.chen.session;

import cn.chen.day03.cn.chen.builder.xml.XMLConfigBuilder;
import cn.chen.day03.cn.chen.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * 整个mybatis项目的入口
 */
public class SqlSessionFactoryBuilder {

    //给一个输入流 返回一个SqlSession对象
    public SqlSessionFactory build(Reader reader){
        //给一个输入流 解析成Configuration对象
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration configuration){
        //返回一个默认的SqlSession工厂对象
        return new DefaultSqlSessionFactory(configuration);
    }
}

