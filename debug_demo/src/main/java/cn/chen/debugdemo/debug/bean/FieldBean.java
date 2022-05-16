package cn.chen.debugdemo.debug.bean;

public class FieldBean {

    private String name;

    public void modifyName(){
        name = "张三";
    }

    @Override
    public String toString() {
        return "FieldBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
