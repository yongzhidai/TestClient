package cn.dyz.conservation;

import org.apache.mina.core.session.IoSession;

import cn.dyz.conservation.base.Conversation;
import cn.dyz.msg.base.Request;
import cn.dyz.msg.base.Response;

public class SignUpConversation extends Conversation{

	public SignUpConversation(int waitTime, Request request) {
		super(waitTime, request);
	}


	@Override
	public void process(IoSession session, Response response) throws Exception {
		boolean b = response.getBoolean();
		if(b){
			System.out.println("注册成功");
			startNext();
		}else{
			System.out.println("注册失败");
		}
	}

}
