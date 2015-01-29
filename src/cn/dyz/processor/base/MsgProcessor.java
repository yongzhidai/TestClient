package cn.dyz.processor.base;

import org.apache.mina.core.session.IoSession;

import cn.dyz.msg.base.Response;


public abstract class MsgProcessor {

	public void handle(IoSession session,Response response){
		try {
			process(session, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	
	public abstract void process(IoSession session,Response response) throws Exception;
}
