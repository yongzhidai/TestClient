package cn.dyz.conservation.base;

import java.util.HashMap;
import java.util.Map;

import cn.dyz.main.NetSupport;

public class ConversationChain {

	private Conversation head;
	
	private Conversation current;
	
	private NetSupport netSupport;
	
	private Map<Integer, Conversation> handleMap = new HashMap<Integer, Conversation>();
	
	public ConversationChain(NetSupport netSupport){
		this.netSupport = netSupport;
	}
	
	
	public void addConversation(Conversation converstation){
		if(converstation==null){
			return;
		}
		if(head==null){
			head = converstation;
			current = converstation;
		}else{
			current.addNext(converstation);
			current=converstation;
		}
		converstation.setNetSupport(netSupport);
		handleMap.put(converstation.getHandleMsgCode(), converstation);
	}
	
	public void startConversation(){
		if(head!=null){
			head.start();
		}
	}
	
	public Map<Integer, Conversation> getHandleMap(){
		return this.handleMap;
	}
}
