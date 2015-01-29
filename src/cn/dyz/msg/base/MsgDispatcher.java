package cn.dyz.msg.base;

import java.util.HashMap;

import org.apache.mina.core.session.IoSession;

import cn.dyz.processor.base.MsgProcessor;


public class MsgDispatcher {

	private HashMap<Integer, MsgProcessor> processors = new HashMap<Integer, MsgProcessor>();
	
	public void dispatcher(IoSession session,Response response){
		MsgProcessor processor = processors.get(response.getMsgCode());
		processor.handle(session,response);
	}
	
	public void addProcessor(int msgCode ,MsgProcessor processor){
		processors.put(msgCode, processor);
	}
}
