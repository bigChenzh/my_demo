package cn.chen.mybatis.session;

import cn.chen.mybatis.builder.xml.XMLConfigBuilder;
import cn.chen.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {
    /**
     * 创建一个SqlSessionFactory
     * @param reader
     * @return
     */
    public SqlSessionFactory build(Reader reader){
        //创建配置文件 通过读入的流 一个mapper文件
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);
        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config){
        return new DefaultSqlSessionFactory(config);
    }
}
