
package com.baomidou.kisso.jfinal;

import com.baomidou.kisso.MyToken;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.plugin.SSOJfinalInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * 首页
 * <p>
 * SSOJfinalInterceptor 登录权限拦截, 你也可以自己实现
 * </p>
 */
@Before(SSOJfinalInterceptor.class)
public class IndexController extends Controller {

	/**
	 * 
	
	* <p>Title: IndexController.java</p>  
	
	* <p>Description: </p>  
	
	
	* @author moshuai
	
	* @date 2019年8月11日
	 */
	public void index() {
		/*
		 * 退出登录 SSOHelper.logout(request, response);
		 */
		/*
		 * <p>
		 * SSOHelper.getToken(request)
		 * 
		 * 从 Cookie 解密 token 使用场景，拦截器
		 * </p>
		 * 
		 * <p>
		 * SSOHelper.attrToken(request)
		 * 
		 * 非拦截器使用减少二次解密
		 * </p>
		 */
		MyToken mt = SSOHelper.attrToken(getRequest());
		if ( mt != null ) {
			System.err.println(" Long 用户ID :" + mt.getId());
			System.err.println(" 登录用户ID : " + mt.getUid());
			System.err.println(" 自定义属性测试 : " + mt.getAbc());
		}
		
		System.err.println(" 当前注入运行模式是：" + SSOConfig.getInstance().getRunMode());
		render("index.html");
	}


}
