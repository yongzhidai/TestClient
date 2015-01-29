/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package cn.dyz.main;

import java.util.Map;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import cn.dyz.conservation.base.Conversation;
import cn.dyz.msg.base.Response;

/**
 * 
 *
 * @author  daiyongzhi
 * @date 2015年1月28日 下午3:51:28
 * @version V1.0
 */
public class ClientHandler extends IoHandlerAdapter {

	private Map<Integer, Conversation> handleMap;
	
	public ClientHandler(Map<Integer, Conversation> handleMap){
		this.handleMap = handleMap;
	}
	
	public void setHandleMap(Map<Integer, Conversation> handleMap){
		this.handleMap = handleMap;
	}
	
    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
    	Response response = (Response) message;
    	if(handleMap!=null){
    		Conversation conversation = handleMap.get(response.getMsgCode());
    		if(conversation!=null){
    			conversation.process(session, response);
    		}
    	}
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    	
    }

}
