package com.rui.weixin.menu;

import java.io.Serializable;

/**
 * @Title: 点击菜单    
 * @author 胡锐
 * @date:   2018年7月13日 下午11:09:46
 */
public class ClientButton extends Button implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//菜单KEY值，用于消息接口推送，不超过128字节,点击菜单必选
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	

}
