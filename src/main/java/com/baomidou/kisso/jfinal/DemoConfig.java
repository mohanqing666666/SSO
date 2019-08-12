
package com.baomidou.kisso.jfinal;

import com.baomidou.kisso.plugin.KissoJfinalPlugin;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;

/**
 * 配置
 */
public class DemoConfig extends JFinalConfig {

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants arg0) {

	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers arg0) {

	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors arg0) {

	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {
		
		//kisso 初始化
		plugins.add(new KissoJfinalPlugin());
		
	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/demo");
		me.add("/login", LoginController.class, "/demo");
		me.add("/logout", LogoutController.class,"/demo");
		me.add("/verify", VerifyCodeController.class);
	}

}
