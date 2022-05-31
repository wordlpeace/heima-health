package com.itheima.health.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.util.DigestUtils;

/**
 * @author ：sean
 * @date ：Created in 2022/5/31
 * @description ：
 * @version: 1.0
 */
public class PasswordUtils {
	public static boolean checkPassword(String password,String dbPassword){
		if(dbPassword.startsWith("{md5}")){
			// 如果是Md5密码
			String mdPassword = DigestUtils.md5DigestAsHex(password.getBytes());
			return mdPassword.equals(dbPassword.replace("{md5}",""));
		} else if(dbPassword.startsWith("{bcrypt}")){
			// 如果是bcrypt加密
			return BCrypt.checkpw(password,dbPassword.replace("{bcrypt}",""));//解密
		}
		// 未加密
		return password.equals(dbPassword);
	}

	public static void main(String args[]){
		String password = "1234";//$2a$10$ofPkBDUezOJp6Sik63Q/0.QlU8a1itEyzldjSXqfn2nDPqXjN0Ljm
		String pwt = BCrypt.hashpw(password, BCrypt.gensalt()); // 加密
		System.out.println(pwt);
		boolean checkpw = BCrypt.checkpw(password, pwt);  // 解密
		System.out.println(checkpw);

	}
}
