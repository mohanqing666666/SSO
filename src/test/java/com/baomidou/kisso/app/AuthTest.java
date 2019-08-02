package com.baomidou.kisso.app;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Ignore;

/**
 * <p>
 * APP 授权测试
 * </p>
 * 
 * @author 成都瘦人  lendo.du@gmail.com
 * 
 */
public class AuthTest extends TestCase {

	CookieStore httpCookieStore = new BasicCookieStore();

	HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);

	CloseableHttpClient client = builder.build();


	@Ignore
	public void testNotLogin() {
		HttpGet httpRequest = new HttpGet("http://sso.test.com:8080/index");
		httpRequest.setHeader("PLATFORM", "APP");
		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(httpRequest);
		} catch ( Throwable error ) {
			throw new RuntimeException(error);
		}

		/* check cookies */
		System.out.println(httpResponse);
	}


	/**
	 * 测试后台web登录，期待跳转至登录页面。
	 * 状态200 OK
	 */
	@Ignore
	public void testLogin() {
		HttpGet httpRequest = new HttpGet("http://sso.test.com:8080/login");
		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(httpRequest);
		} catch ( Throwable error ) {
			throw new RuntimeException(error);
		}

		/* check cookies */
		System.out.println(httpResponse);
	}


	/**
	 * 测试APP登录，期待返回JSON格式响应
	 * 状态200 ERROR
	 */
	@Ignore
	public void testAppLogin() {
		HttpPost httpRequest = new HttpPost("http://sso.test.com:8080/login/auth");
		httpRequest.setHeader("PLATFORM", "APP");

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("username", "admin"));
		params.add(new BasicNameValuePair("password", "admin"));

		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}

		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(httpRequest);
			HttpEntity entity = httpResponse.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(httpResponse.getStatusLine().getStatusCode());
			System.out.println(content);
		} catch ( Throwable error ) {
			throw new RuntimeException(error);
		}
	}


	/**
	 * 测试APP请求业务，期待返回业务响应。
	 * 状态200
	 */
	@Ignore
	public void testOperation() {
		testAppLogin();
		HttpGet httpRequest = new HttpGet("http://sso.test.com:8080/appTest");
		httpRequest.setHeader("PLATFORM", "APP");
		HttpResponse httpResponse = null;
		try {
			httpResponse = client.execute(httpRequest);
			HttpEntity entity = httpResponse.getEntity();
			String content = EntityUtils.toString(entity);
			System.out.println(httpResponse.getStatusLine());
			System.out.println(content);
		} catch ( Throwable error ) {
			throw new RuntimeException(error);
		}
	}
}
