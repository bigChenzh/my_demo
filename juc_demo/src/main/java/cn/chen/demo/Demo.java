package cn.chen.demo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chen
 * @date 2022/8/23 9:33
 */
public class Demo {
    private static final List<NetMall> MALLS = Arrays.asList(
            new NetMall("tm"),
            new NetMall("tb"),
            new NetMall("jd")
    );


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //List<BigDecimal> price = normalMethod();
        List<BigDecimal> price = completableFutureMethod();
        long end = System.currentTimeMillis();

        System.out.println(price);
        System.out.println(String.format("耗时: %s", end - start));

    }

    private static List<BigDecimal> normalMethod() {
        List<BigDecimal> price = MALLS.stream().map(netMall -> {
            return netMall.getPrice("mysql");
        }).collect(Collectors.toList());
        return price;
    }

    private static List<BigDecimal> completableFutureMethod() {
        return MALLS.stream().map(netMall ->
                CompletableFuture.supplyAsync(() ->
                        netMall.getPrice("mysql")
                )
        ).collect(Collectors.toList()).stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}

class NetMall {
    private String mallName;

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public BigDecimal getPrice(String goodName) {
        int price = Math.round(1) * 100 + goodName.charAt(0);
        try {
            //等待3s
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return BigDecimal.valueOf(price);
    }


    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }
}
