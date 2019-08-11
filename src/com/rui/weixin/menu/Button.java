package com.rui.weixin.menu;

import java.io.Serializable;
import java.util.List;
/**
 * @Title: 一级菜单       
 * @author 胡锐
 * @date:   2018年7月13日 下午11:05:50
 */
public class Button implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
	 private String type;
	 //菜单标题，不超过16个字节，子菜单不超过60个字节
	 private String name;
	 //二级菜单数组，个数应为1~5个
	 private Button[] sub_button;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	 
}
