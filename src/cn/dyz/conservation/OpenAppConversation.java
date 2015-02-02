package cn.dyz.conservation;

import org.apache.mina.core.session.IoSession;

import cn.dyz.conservation.base.Conversation;
import cn.dyz.msg.base.Request;
import cn.dyz.msg.base.Response;

public class OpenAppConversation extends Conversation{

	public OpenAppConversation(int waitTime, Request request) {
		super(waitTime, request);
		
	}

	@Override
	public void doHandle(IoSession session, Response response) throws Exception {
		String str = response.getString();
		System.out.println("open APP,server response: "+str);
	}

}
