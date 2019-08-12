
package com.baomidou.kisso.jfinal;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;

/**
 * 验证码
 */
public class VerifyCodeController extends Controller {


	/**
	 * 
	
	* <p>Title: VerifyCodeController.java</p>  
	
	* <p>Description:验证码 </p>  
	
	
	* @author moshuai
	
	* @date 2019年8月11日
	 */
	public void index() {
		HttpServletResponse response = getResponse();
		try {
			String verifyCode = CaptchaUtil.outputImage(response.getOutputStream());
			System.out.println("验证码:" + verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		renderNull();
	}
}
