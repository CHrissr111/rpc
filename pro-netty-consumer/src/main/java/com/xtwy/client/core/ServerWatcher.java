package com.xtwy.client.core;

import java.util.HashSet;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

import com.xtwy.client.zk.ZookeeperFactory;

import io.netty.channel.ChannelFuture;

public class ServerWatcher implements CuratorWatcher {

	public void process(WatchedEvent event) throws Exception {
		CuratorFramework client = ZookeeperFactory.create();
		String path = event.getPath();
		client.getChildren().usingWatcher(this).forPath(path);//设置多次监听
		List<String> serverPaths = client.getChildren().forPath(path);//变化后的路径
		ChannelManager.realServerPath.clear();
		for(String serverPath:serverPaths) {
			String[] str = serverPath.split("#");
			
			ChannelManager.realServerPath.add(str[0]+"#"+str[1]);

		}
		ChannelManager.clear();
		for(String realServer:ChannelManager.realServerPath) {
			String[] str = realServer.split("#");
			try {
				ChannelFuture channelFuture = TcpClient.b.connect(str[0],Integer.valueOf(str[1]));
				ChannelManager.add(channelFuture);	
//				int weight = Integer.valueOf(str[2]);
//				if(weight>0) {
//					for(int w=0;w<=weight;w++) { //权重为几加几次
//						ChannelFuture channelFuture = TcpClient.b.connect(str[0],Integer.valueOf(str[1]));
//						ChannelManager.add(channelFuture);					}
//				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
