package cn.chen.mybatis.builder.xml;

import cn.chen.mybatis.builder.BaseBuilder;
import cn.chen.mybatis.mapping.MappedStatement;
import cn.chen.mybatis.mapping.SqlCommandType;
import cn.chen.mybatis.session.Configuration;
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

public class XMLConfigBuilder extends BaseBuilder {
    private Element root;

    public XMLConfigBuilder(Reader reader) {
        //初始化config
        super(new Configuration());
        //dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            root = document.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Configuration parse(){
        try {
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return configuration;
    }

    public void mapperElement(Element mappers) throws Exception{
        List<Element> mapperList = mappers.elements("mapper");

        for (Element element : mapperList) {
            Map<Integer, String> integerStringMap = new HashMap<>();

            MappedStatement.Builder builder = new MappedStatement.Builder(
                    configuration,
                    "",
                    SqlCommandType.INSERT,
                    "",
                    "",
                    "",
                    integerStringMap
            );

            configuration.addMappedStatement(builder.build());

        }


    }
}
