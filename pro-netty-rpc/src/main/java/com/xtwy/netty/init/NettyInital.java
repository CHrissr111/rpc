package com.xtwy.netty.init;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xtwy.netty.constant.Constants;
import com.xtwy.netty.factory.ZookeeperFactory;
import com.xtwy.netty.handler.ServerHandler;
import com.xtwy.netty.handler.SimpleServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

@Component
public class NettyInital implements ApplicationListener<ContextRefreshedEvent>{
	public static void start() {
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootStrap = new ServerBootstrap();

			bootStrap.group(parentGroup, childGroup);
			//允许128个通道在此排队
			bootStrap.option(ChannelOption.SO_BACKLOG, 128) //允许128个通道在此排队
					.childOption(ChannelOption.SO_KEEPALIVE, false) //设置心跳包
					.channel(NioServerSocketChannel.class) //绑定通道
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
//							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535, Delimiters.lineDelimiter()[0]));
							ch.pipeline().addLast(new StringDecoder());
							ch.pipeline().addLast(new IdleStateHandler(60,45,20,TimeUnit.SECONDS));
							ch.pipeline().addLast(new ServerHandler());
							ch.pipeline().addLast(new StringEncoder());

						}
					});
			int port=8080;
//			int weight = 2;
			ChannelFuture f = bootStrap.bind(8080).sync();
			
			CuratorFramework client = ZookeeperFactory.create();
			InetAddress netAddress = InetAddress.getLocalHost();
			client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(Constants.SERVER_PATH+"/"+netAddress.getHostAddress()+"#"+port+"#");

//			client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(Constants.SERVER_PATH+"/"+netAddress.getHostAddress()+"#"+port+"#"+weight+"#");
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		this.start();
	}
}
