package cn.chen.day03;


import cn.chen.day03.cn.chen.session.SqlSession;
import cn.chen.day03.cn.chen.session.SqlSessionFactory;
import cn.chen.day03.cn.chen.session.SqlSessionFactoryBuilder;
import cn.chen.day03.dao.IUserDao;
import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

@Slf4j
public class TestBean {
    @Test
    public void test_SqlSessionFactory() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //创建SqlSession工厂 获取一个配置xml
        //将xml解析成一个configuration对象
        //把这个configuration对象传递个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(ResourceUtil.getReader("day03/IUserDao.xml", Charset.defaultCharset()));

        //通过工厂获取sqlSession对象
        //SqlSessionFactory将configuration传递给SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //通过sqlSession获取集体实现代理类
        //sqlSession通过配置类就可以产生代理对象了
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        String s = userDao.queryUserInfoById("123");


        log.info(s);
    }
}
