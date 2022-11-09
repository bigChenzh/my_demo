package cn.chen.day03.cn.chen.session;


import cn.chen.day03.cn.chen.binding.MapperRegistry;
import cn.chen.day03.cn.chen.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * 贯穿了mybatis整体的生命周期 所有的成果存储在mappedStatements中
 */
public class Configuration {
    //Mapper注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    //mapper映射对象所有的都存放在Configuration对象中
    //String就是命名空间.方法名 MappedStatement就是这个方法存储的参数
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public MappedStatement getMappedStatement(String statement) {
        return mappedStatements.get(statement);
    }
}
