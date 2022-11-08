package cn.chen.day02;

import cn.chen.day02.cn.chen.binding.MapperRegistry;
import cn.chen.day02.cn.chen.session.SqlSession;
import cn.chen.day02.cn.chen.session.SqlSessionFactory;
import cn.chen.day02.cn.chen.session.defaults.DefaultSqlSessionFactory;
import cn.chen.day02.dao.ISchoolDao;
import cn.chen.day02.dao.IUserDao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestBean {
    public static void main(String[] args) {
        //新建注册类
        MapperRegistry registry = new MapperRegistry();
        //扫描包 并注册获取对应的bean
        registry.addMappers("cn.chen.day02.dao");

        //创建sqlSession工厂对象
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //这里会生成被代理的类 返回增强类
        ISchoolDao schoolDao = sqlSession.getMapper(ISchoolDao.class);
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);


        schoolDao.querySchoolName("123");

        userDao.queryUserAge("123");
        String res = userDao.queryUserName("123");
        log.info("测试结果：{}", res);


    }
}
