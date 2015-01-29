package cn.dyz.msg.request;

import java.io.IOException;

import cn.dyz.msg.base.Request;

public class SignUpRequest1005 extends Request{

	public SignUpRequest1005(String name,String phone,String email,String passwd) {
		super(1005);
		try {
			output.writeUTF(name);
			output.writeUTF(phone);
			output.writeUTF(email);
			output.writeUTF(passwd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
