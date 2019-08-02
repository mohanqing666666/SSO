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

import com.baomidou.kisso.SSOHelper;
import com.jfinal.core.Controller;

/**
 * 登录
 */
public class LogoutController extends Controller {

	/**
	 * 退出登录
	 */
	public void index() {
		/**
		 * <p>
		 * SSO 退出，清空退出状态即可
		 * </p>
		 * 
		 * <p>
		 * 子系统退出 SSOHelper.logout(request, response); 注意 sso.properties 包含 退出到
		 * SSO 的地址 ， 属性 sso.logout.url 的配置
		 * </p>
		 */
		SSOHelper.clearLogin(getRequest(), getResponse());
		redirect("login");
	}
}
