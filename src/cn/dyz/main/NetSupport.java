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

import java.net.SocketAddress;




import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import cn.dyz.codec.GameProtocolcodecFactory;
import cn.dyz.conservation.base.ConversationChain;
import cn.dyz.msg.base.Request;

/**
 * 
 *
 * @author  daiyongzhi
 * @date 2015年1月29日 上午9:35:53
 * @version V1.0
 */
public class NetSupport {
    private IoHandler handler;

    private ConversationChain chain;
    private IoSession session;

    private boolean isSetChain=false;
    
    public void setConversationChain(ConversationChain chain) {
    	this.chain = chain;
        this.handler = new ClientHandler(chain.getHandleMap());
        isSetChain = true;
	}

    public boolean connect(NioSocketConnector connector, SocketAddress address) {
    	if(!isSetChain){
    		throw new IllegalStateException(
                    "please set ConservationChain first !");
    	}
        if (session != null && session.isConnected()) {
            throw new IllegalStateException(
                    "Already connected. Disconnect first.");
        }

        try {

            IoFilter CODEC_FILTER = new ProtocolCodecFilter(
                    new GameProtocolcodecFactory());
            
            connector.getFilterChain().addLast("codec", CODEC_FILTER);

            connector.setHandler(handler);
            ConnectFuture future1 = connector.connect(address);
            future1.awaitUninterruptibly();
            if (!future1.isConnected()) {
                return false;
            }
            session = future1.getSession();
           
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void startConversation(){
    	chain.startConversation();
    }
    
    public void sendMsg(Request request){
    	if (session != null) {
            if (session.isConnected()) {
               session.write(request);
            }
    	}
    }


    public void quit() {
        if (session != null) {
            if (session.isConnected()) {
                // Wait until the chat ends.
                session.getCloseFuture().awaitUninterruptibly();
            }
            session.close(true);
        }
    }



	

}
