package com.rui.weixin.menu;

import java.io.Serializable;
/**
 * @Title: 连接按钮     
 * @author 胡锐
 * @date:   2018年7月13日 下午11:12:58
 */
public class ViewButton extends Button implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//跳转连接
	private String url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
