package cn.dyz.conservation.base;


import java.util.concurrent.locks.LockSupport;

import cn.dyz.main.NetSupport;
import cn.dyz.msg.base.Request;
import cn.dyz.processor.base.MsgProcessor;

public abstract class Conversation extends MsgProcessor{

	protected Conversation next;
	
	protected int waitTime;
	
	protected Request request;
	
	protected int handleMsgCode;
	
	protected NetSupport netSupport;
	
	public Conversation(int waitTime,Request request){
		this.waitTime=waitTime;
		this.request=request;
		this.handleMsgCode=request.getReponseCode();
	}
	
	public void setNetSupport(NetSupport netSupport){
		this.netSupport = netSupport;
	}
	
	public void addNext(Conversation conversation){
		this.next = conversation;
	}
	
	public void startNext(){
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
	
}

	
