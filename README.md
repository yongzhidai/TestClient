# TestClient
客户端模拟器，模拟向GameServer发起会话。
目前只支持一次发起一串会话


使用步骤：
	一、编写请求对象
		在cn.dyz.msg.request包中写一个请求对象，继承Request类，例如LoginRequest1003
	二、编写对话对象
		在cn.dyz.conservation包中写对话对象，继承Conversation类，例如LoginConversation
	三、添加会话对象
		在Main的buildConversation方法中添加会话

