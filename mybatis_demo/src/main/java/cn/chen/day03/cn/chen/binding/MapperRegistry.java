package cn.chen.day03.cn.chen.binding;

import cn.chen.day03.cn.chen.session.Configuration;
import cn.chen.day03.cn.chen.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * mapper注册机 存储被代理的对象
 */
@SuppressWarnings("all")
public class MapperRegistry {
    private Configuration configuration;
    
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();
    
    public MapperRegistry(Configuration configuration){
        this.configuration = configuration;
    }
    
    //获取被代理的mapper
    public <T> T getMapper(Class<?> type, SqlSession sqlSession) {
        //从注册中获取mapper工厂对象
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);

        if (mapperProxyFactory == null) {
            //如果没有注册过 抛出异常
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }

        try {
            //获取代理对象
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> boolean hasMapper(Class<T> type) {
        if (type == null) {
            return false;
        }
        return knownMappers.get(type) != null;
    }

    //添加mapper对象
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            //必须是接口
            if (hasMapper(type)) {
                //重复添加的话报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }

            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public void addMappers(String packageName){
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);

        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

}
