package com.baomidou.kisso;

/**
 * 
 * 
 * 这个是测试自定义 Token
 * <p>
 * 线上可以直接使用 SSOToken 去掉 sso.properties 中配置
 * sso.token.class=com.baomidou.kisso.MyToken
 * </p>
 * 
 * @author hubin
 *
 */
public class MyToken extends SSOToken {
	private static final long serialVersionUID = 1L;

	private String abc;

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

}
