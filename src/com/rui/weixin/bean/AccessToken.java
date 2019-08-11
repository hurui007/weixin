package com.rui.weixin.bean;

import java.io.Serializable;
/**
 * @title token 出参
 * @author ruihu
 * @date 2018年07月4日
 */
public class AccessToken implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//获取到的凭证
	private String accessToken;
	//凭证有效时间，单位：秒
	private int expiresIn;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + "]";
	}
	
	
}
