package cn.chen.mybatis.binding;

import cn.chen.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 将 Mapper类代理并返回一个被代理的类
 * Mapper中的方法 和sql语句对应 用来执行sql语句
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {
    //代理的主要方法
    private static final Long serialVersionUID = -4545454512125465L;

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    /**
     * 这里执行被代理的逻辑
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this,args);
        }else {
//            return "你被代理了！"+sqlSession.get(mapperInterface.getName()+"."+method.getName());
//            Object invoke = method.invoke(this, args);
            //这里处理逻辑就好了 可以获取到方法名 还可以获取到参数  那不是可以为所以为了
            return sqlSession.selectOne(method.getName(),args);
        }
    }


}
