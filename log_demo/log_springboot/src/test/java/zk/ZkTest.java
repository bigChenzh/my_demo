package zk;

import org.junit.jupiter.api.Test;

public class ZkTest {
    @Test
    public void t1(){
        //String s ="unikwon201131313213";
        String s1 ="CHSG123.c.0200_123_888_666_unikwon201131313213_wocao.xls";
        //s1.indexOf()
        //String substring = s1.substring();
        String replaceAll = s1.replaceAll("unikwon",s1);
        String s2 = s1.replaceFirst("unikwon",s1);
        System.out.println(replaceAll);
        System.out.println(s2);


    }
}
