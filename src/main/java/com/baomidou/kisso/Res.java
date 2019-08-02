package com.baomidou.kisso;

/**
 * <p>
 * APP 返回请求
 * </p>
 * 
 * @author 成都瘦人  lendo.du@gmail.com
 * 
 */
public class Res {

	private String code;

	private String note;

	private Object data;


	public Res() {
		this.code = "1";
		this.note = "请求成功";
	}


	public String getCode() {
		return code;
	}


	public void setCode( String code ) {
		this.code = code;
	}


	public String getNote() {
		return note;
	}


	public void setNote( String note ) {
		this.note = note;
	}


	public Object getData() {
		return data;
	}


	public void setData( Object data ) {
		this.data = data;
	}
}
