package cn.dyz.msg.request;

import java.io.IOException;

import cn.dyz.msg.base.Request;

public class LoginRequest1003 extends Request{

	public LoginRequest1003(String phone,String passwd) {
		super(1003);
		try {
			output.writeUTF(phone);
			output.writeUTF(passwd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
