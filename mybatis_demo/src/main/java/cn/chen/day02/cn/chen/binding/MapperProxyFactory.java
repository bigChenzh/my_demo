package cn.chen.day02.cn.chen.binding;

import cn.chen.day02.cn.chen.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.Map;

@SuppressWarnings("all")
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        //new 工厂提供一个需要被代理的mapper
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        //调取的时候 提供一个sqlsession对象

        //传递个mapperProxy
        MapperProxy<T> mapperProxy = new MapperProxy(sqlSession, mapperInterface);

        //jdk自己的代理实现
        /**
         * 1    当前对象的加载器
         * 2    被代理的接口
         * 3    处理方式
         */
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
