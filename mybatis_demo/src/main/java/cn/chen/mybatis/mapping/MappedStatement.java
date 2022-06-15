package cn.chen.mybatis.mapping;

import cn.chen.mybatis.session.Configuration;

import java.util.Map;

/**
 * 映射申明
 * @author chen
 */
public class MappedStatement {
    /**
     * 配置对象
     */
    private Configuration configuration;

    /**
     * 唯一id
     */
    private String id;

    /**
     * sql类型 insert update
     */
    private SqlCommandType sqlCommandType;

    /**
     * 入参类型
     */
    private String parameterType;
    /**
     * 出参类型
     */
    private String resultType;

    /**
     * sql语句
     */
    private String sql;

    /**
     * 参数
     */
    private Map<Integer, String> parameter;

    MappedStatement() {

    }

    /**
     * 构建 映射申明
     */
    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, String parameterType, String resultType, String sql, Map<Integer, String> parameter) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlCommandType = sqlCommandType;
            mappedStatement.parameterType = parameterType;
            mappedStatement.resultType = resultType;
            mappedStatement.sql = sql;
            mappedStatement.parameter = parameter;
        }

        public MappedStatement build(){
            return mappedStatement;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public String getSql() {
        return sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }
}
