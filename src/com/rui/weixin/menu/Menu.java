package com.rui.weixin.menu;

import java.io.Serializable;
/**
 * @Title: 菜单对象       
 * @author 胡锐
 * @date:   2018年7月13日 下午11:15:22
 */
public class Menu implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}
	

}
