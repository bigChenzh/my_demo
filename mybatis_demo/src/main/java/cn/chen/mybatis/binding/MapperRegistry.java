package cn.chen.mybatis.binding;

import cn.chen.mybatis.session.Configuration;
import cn.chen.mybatis.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扫描注册Mapper
 */
public class MapperRegistry {
    private Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    //注册的mapper key是mapper类型 value是我们代理实现类
    private final Map<Class<?>, MapperProxyFactory<?>> knowMappers = new HashMap<>();

    /**
     * 通过类型 返回该类型被动态代理的类
     *
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        //从注册表获取 Mapper实现类
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knowMappers.get(type);


        if (mapperProxyFactory == null) {
            //如果没有 报错
            throw new RuntimeException("type" + type + "is not known to hte MapperRegistry");
        }

        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance.Cause:" + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hashMapper(type)) {
                throw new RuntimeException("Type" + type + "is already known to the MapperRegistry.");
            }
            knowMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public <T> boolean hashMapper(Class<T> type) {
        return knowMappers.containsKey(type);
    }

    public void addMappers(String packages){
        Set<Class<?>> classes = ClassScanner.scanPackage(packages);
        for (Class<?> clazz : classes) {
           addMapper(clazz);
        }
    }
}
