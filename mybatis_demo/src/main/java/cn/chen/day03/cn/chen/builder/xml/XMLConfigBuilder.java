package cn.chen.day03.cn.chen.builder.xml;


import cn.chen.day03.cn.chen.builder.BaseBuilder;
import cn.chen.day03.cn.chen.mapping.MappedStatement;
import cn.chen.day03.cn.chen.mapping.SqlCommandType;
import cn.chen.day03.cn.chen.session.Configuration;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;
import sun.security.krb5.internal.KrbErrException;


import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * xml配置类创建
 *
 * @author chen
 */
public class XMLConfigBuilder extends BaseBuilder {
    /**
     * xml配置项
     */
    private Element root;

    /**
     * 构造函数 读取这个文档到这个对象中
     *
     * @param reader
     */
    public XMLConfigBuilder(Reader reader) {
        //初始化config 产生唯一的一个Configuration对象
        super(new Configuration());
        //dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            //将文档赋予变量root
            Document document = saxReader.read(reader);
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 存储的这个文档
     *
     * @return Configuration 配置对象
     */
    public Configuration parse() {
        try {
            mapperElement(root);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configuration;
    }

    /**
     * 解析调用的实际方法
     * 实际就是将xml解析 然后将结果存储到Configuration中
     *
     * @param mappers
     * @throws Exception
     */
    public void mapperElement(Element mappers) throws Exception {

        List<Element> mapperList = mappers.elements();

        String namespace = mappers.attributeValue("namespace");

        for (Element element : mapperList) {
            Map<Integer, String> integerStringMap = new HashMap<>();
            String name = element.getName().toLowerCase();


            //设置sql类型
            SqlCommandType sqlCommandType = SqlCommandType.UNKNOWN;
            for (SqlCommandType enumConstant : SqlCommandType.class.getEnumConstants()) {
                if (enumConstant.name().equals(name.toUpperCase())) {
                    sqlCommandType = enumConstant;
                    break;
                }
            }

            //设置id
            String id = namespace + "." + element.attributeValue("id");

            //入参类型
            String parameterType = element.attributeValue("parameterType");

            //出参类型
            String resultType = element.attributeValue("resultType");

            //sql语句
            String sql = element.getStringValue().replaceAll("#\\{\\w+\\}", "?");


            MappedStatement.Builder builder = new MappedStatement.Builder(
                    configuration,
                    id,
                    sqlCommandType,
                    parameterType,
                    resultType,
                    sql,
                    integerStringMap
            );

            configuration.addMappedStatement(builder.build());

        }

        configuration.addMapper(Class.forName(namespace));


    }

}
