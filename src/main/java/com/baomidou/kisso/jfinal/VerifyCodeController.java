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

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.core.Controller;

/**
 * 验证码
 */
public class VerifyCodeController extends Controller {

	/**
	 * 验证码
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
