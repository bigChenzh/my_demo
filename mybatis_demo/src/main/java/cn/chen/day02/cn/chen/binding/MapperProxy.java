package cn.chen.day02.cn.chen.binding;

import cn.chen.day02.cn.chen.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private SqlSession sqlSession;
    private final Class<T> mapperInterface;


    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }


    /**
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //所有的方法的代理都在这里

        System.out.println("当前方法：" + method.getName());

        if (Object.class.equals(method.getDeclaringClass())) {
            //toString 和 hashcode 不需要被代理
            return method.invoke(this, args);
        } else if (Integer.class.equals(method.getReturnType())) {
            return 0;
        } else {
            //其实没有具体实现代理逻辑
            return "你被代理了!!!" + sqlSession;
        }
    }
}
