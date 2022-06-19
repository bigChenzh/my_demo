package cn.chen.mybatis.binding;

import cn.chen.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * mapper代理的工厂 提供生产 被代理的对象 T
 * @param <T>
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    //返回被代理的类
    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession){
        MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession,mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }

}
