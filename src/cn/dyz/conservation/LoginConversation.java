package cn.dyz.conservation;

import org.apache.mina.core.session.IoSession;

import cn.dyz.conservation.base.Conversation;
import cn.dyz.msg.base.Request;
import cn.dyz.msg.base.Response;

public class LoginConversation extends Conversation{

	public LoginConversation(int waitTime, Request request) {
		super(waitTime, request);
		
	}

	@Override
	public void process(IoSession session, Response response) throws Exception {
		boolean b = response.getBoolean();
		if(b){
			System.out.println("登陆成功");
			startNext();
		}else{
			System.out.println("登陆失败");
		}
	}

}
