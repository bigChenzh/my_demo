package cn.chen.day01;

import cn.chen.day01.cn.chen.binding.MapperProxyFactory;
import cn.chen.day01.dao.IUserDao;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestBean {
    @Test
    public void test_MapperProxyFactory() {
        //通过接口类型创建代理实现工厂
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);
        //key存储的是 接口名.方法名，value存储的是参数
        Map<String, String> sqlSession = new HashMap<>();

        sqlSession.put("cn.chen.day01.dao.IUserDao.queryUserName", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户姓名");
        sqlSession.put("cn.chen.day01.dao.IUserDao.queryUserAge", "模拟执行 Mapper.xml 中 SQL 语句的操作：查询用户年龄");
        //这个工厂就可以生产 userDao了
        IUserDao userDao = factory.newInstance(sqlSession);

        System.out.println(userDao.queryUserName("10001"));
        System.out.println(userDao.queryUserAge("10001"));

    }
}
