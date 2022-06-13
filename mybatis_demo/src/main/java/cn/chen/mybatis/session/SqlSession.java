package cn.chen.mybatis.session;

public interface SqlSession {
    <T> T selectOne(String statement);
    <T> T selectOne(String statement,Object parameter);
    <T> T getMapper(Class<T> type);

}
