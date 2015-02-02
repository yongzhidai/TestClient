package cn.dyz.main;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.transport.socket.nio.NioSocketConnector;

import cn.dyz.conservation.DisconnectConversation;
import cn.dyz.conservation.LoginConversation;
import cn.dyz.conservation.OpenAppConversation;
import cn.dyz.conservation.base.ConversationChain;
import cn.dyz.msg.request.DisconnectRequest1000;
import cn.dyz.msg.request.LoginRequest1003;
import cn.dyz.msg.request.OpenAppRequest1001;
import cn.dyz.msg.request.SignUpRequest1005;



public class Main{

	public static void main(String[] args) throws Exception {
		//网络链接工具
		NetSupport support = new NetSupport();
		
		//创建回话链
		ConversationChain chain = new ConversationChain(support);
		buildConversation(chain);
		
		//设置会话链
		support.setConversationChain(chain);
		
		NioSocketConnector connector = new NioSocketConnector();
		SocketAddress address = new InetSocketAddress("localhost", 1101);
		
		boolean isConnected = support.connect(connector, address);
		
		if(isConnected){
			support.startConversation();
		}
		support.quit();
		connector.dispose();
		
	}
	
	public static void buildConversation(ConversationChain chain){
		chain.addConversation(new OpenAppConversation(0, new OpenAppRequest1001()));
		//chain.addConversation(new SignUpConversation(10, new SignUpRequest1005("刘德华", "110", "dyz@foxmail.com", "1234")));
		chain.addConversation(new LoginConversation(0, new LoginRequest1003("1100", "1234")));
		
		chain.addConversation(new DisconnectConversation(0, new DisconnectRequest1000()));//断开链接请求
	}
	

}
