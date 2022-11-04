package cn.chen.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class ServiceDemo {
    public static void main(String[] args) {
        //核心链接
        NioEventLoopGroup bootNio = null;
        //任务处理
        NioEventLoopGroup supNio = null;

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        ChannelFuture channelFuture = null;
        try {
            bootNio = new NioEventLoopGroup(2);
            supNio = new NioEventLoopGroup(4);

            channelFuture = serverBootstrap
                    .group(bootNio,supNio)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//创建通道初始化对象，设置初始化参数，在 SocketChannel 建立起来之前执行

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //对workerGroup的SocketChannel设置处理器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    })
                    .bind(9000)
                    .sync();

            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (bootNio != null){
                bootNio.shutdownGracefully();
            }

            if (supNio != null){
                supNio.shutdownGracefully();
            }
        }

    }
}
