/**
 * Copyright (c) 2011-2014, hubin (243194995@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
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
		me.add("/logout", LogoutController.class);
		me.add("/verify", VerifyCodeController.class);
	}

}
