package cn.dyz.conservation.base;


import java.util.concurrent.locks.LockSupport;

import org.apache.mina.core.session.IoSession;

import cn.dyz.main.NetSupport;
import cn.dyz.msg.base.Request;
import cn.dyz.msg.base.Response;

/**
 * 对话抽象类，所有具体对话对象 都要继承此类
 * 具体对话对象 只要实现doHandle方法，处理服务器返回的消息即可
 * @author  daiyongzhi
 * @date 2015年2月2日 上午10:20:29
 * @version V1.0
 */
public abstract class Conversation{

	protected Conversation next;
	
	protected int waitTime;
	
	protected Request request;
	
	protected int handleMsgCode;
	
	protected NetSupport netSupport;
	
	/**
	 * 
	 * @param waitTime 等待 waitTime秒之后在发起对话
	 * @param request  向服务器发起对话的内容对象
	 */
	public Conversation(int waitTime,Request request){
		this.waitTime=waitTime;
		this.request=request;
		this.handleMsgCode=request.getReponseCode();
	}
	
	public void setNetSupport(NetSupport netSupport){
		this.netSupport = netSupport;
	}
	
	/**
	 * 追加下一个对话
	 * @param conversation
	 */
	public void appendNext(Conversation conversation){
		this.next = conversation;
	}
	
	private void startNext(){
		if(next!=null){
			next.start();
		}
	}
	
	public void start(){
		LockSupport.parkNanos(waitTime*1000);
		if(netSupport!=null){
			netSupport.sendMsg(request);
		}
	}
	
	public int getHandleMsgCode(){
		return this.handleMsgCode;
	}
	
	public void process(IoSession session,Response response){
		try {
			doHandle(session, response);
			startNext();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 处理返回消息
	 */
	public abstract void doHandle(IoSession session,Response response) throws Exception;
	
}

	
