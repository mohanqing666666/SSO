package com.baomidou.kisso.jfinal;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.kisso.AuthToken;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.SSOToken;
import com.baomidou.kisso.Token;
import com.baomidou.kisso.common.SSOProperties;
import com.baomidou.kisso.common.util.HttpUtil;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;


/**
 * 登录
 */
public class LoginController extends Controller {
	/**
	 * 
	
	* <p>Title: LoginController.java</p>  
	
	* <p>Description:认证中心登录验证 </p>  
	
	
	* @author moshuai
	
	* @date 2019年8月11日
	 */
	public void index() {
		String returnUrl=this.getPara(SSOConfig.getInstance().getParamReturl());//因为XXXXX处记录了，所以此处可以取到。
		Token token = SSOHelper.getToken(getRequest());
		if (token == null) {
			/**
			 * 正常登录 需要过滤sql及脚本注入
			 */
			WafRequestWrapper wr = new WafRequestWrapper(getRequest());
			String name = wr.getParameter("name");
			String password = wr.getParameter("password");
			if ("moshuai".equals(name) && "123456".equals(password)) {
				/*
				 * 设置登录 Cookie
				 * 最后一个参数 true 时添加 cookie 同时销毁当前 JSESSIONID 创建信任的 JSESSIONID
				 */
				SSOToken st = new SSOToken(getRequest(), name);
				SSOHelper.setSSOCookie(getRequest(), getResponse(), st, true);
				// 重定向到指定地址 returnUrl
				if (StringUtils.isEmpty(returnUrl)) {
					returnUrl = "/demo/index.html";
				} else {
					returnUrl = HttpUtil.decodeURL(returnUrl);
				}
				redirect(returnUrl);
				 return;
			}else {
					if (StringUtils.isNotEmpty(returnUrl)) {
					//	model.addAttribute("ReturnURL", returnUrl);
						setAttr("ReturnURL", returnUrl);
					}
					render("login.html");
					 return;
			}
		}else {
			if (StringUtils.isEmpty(returnUrl)) {
				returnUrl = "/demo/index.html";
//				setAttr("userId", token.getUid());
			}
			 redirect(returnUrl);
			 return;
		}
		
	//	render("login.html");
	}
	
	


	/**
	 * 
	
	* <p>Title: LoginController.java</p>  
	
	* <p>Description: token消息分发验证</p>  
	
	
	* @author moshuai
	
	* @date 2019年8月11日
	 */
	public void replylogin() {
		StringBuffer replyData = new StringBuffer();
	//	replyData.append(getRequest().getParameter("callback")).append("({\"msg\":\"");
		Token token = SSOHelper.getToken(getRequest());
		String callback = getRequest().getParameter("callback"); 
		Map json=new HashMap();
		if (token != null) {
			String askData = getRequest().getParameter("askData");
			if (askData != null && !"".equals(askData)) {
				/**
				 * 
				 * 用户自定义配置获取
				 * 
				 * <p>
				 * 由于不确定性，kisso 提倡，用户自己定义配置。
				 * </p>
				 * 
				 */
				SSOProperties prop = SSOConfig.getSSOProperties();
				
				//下面开始验证票据，签名新的票据每一步都必须有。
				AuthToken at = SSOHelper.replyCiphertext(getRequest(), askData);
				if (at != null) {
					//1、业务系统公钥验证签名合法性（此处要支持多个跨域端，取 authToken 的 app 名找到对应系统公钥验证签名）
					at = at.verify(prop.get("sso.defined." + at.getApp() + "_public_key"));
					if (at != null) {
						
						//at.getUuid() 作为 key 设置 authToken 至分布式缓存中，然后 sso 系统二次验证
						//at.setData(data); 设置自定义信息，当然你也可以直接 at.setData(token.jsonToken()); 把当前 SSOToken 传过去。
						
						at.setUid(token.getUid());//设置绑定用户ID
						at.setTime(token.getTime());//设置登录时间
						
						//2、SSO 的私钥签名
						at.sign(prop.get("sso.defined.sso_private_key"));
						
						//3、生成回复密文票据
//						replyData.append(at.encryptAuthToken());
						 json.put("msg",at.encryptAuthToken());
					} else {
						//非法签名, 可以重定向至无权限界面，自己处理
						replyData.append("-2");
					}
				} else {
					//非法签名, 可以重定向至无权限界面，自己处理
					replyData.append("-2");
				}
			}else {
				// 未登录
				replyData.append("-1");
			}
          String jsonp = callback+"("+ JsonKit.toJson(json)+")";//返回的json 格式要加callback()
          renderJson(jsonp); 
			
		}
		
	}
	

}
