package cn.dyz.conservation;

import org.apache.mina.core.session.IoSession;

import cn.dyz.conservation.base.Conversation;
import cn.dyz.msg.base.Request;
import cn.dyz.msg.base.Response;

public class DisconnectConversation extends Conversation{

	public DisconnectConversation(int waitTime, Request request) {
		super(waitTime, request);
	}

	@Override
	public void doHandle(IoSession session, Response response) throws Exception {
		
	}

}
