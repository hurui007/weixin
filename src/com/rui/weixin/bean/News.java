package com.rui.weixin.bean;

import java.io.Serializable;

/**
 * @title 图文消息出参
 * @author ruihu
 * @date 2018年7月3日
 */
public class News implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图文消息标题
	 */
	 private  String Title;
	 /**
	  * 图文消息描述
	  */
	 private String Description;
	 
	 //图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 private String PicUrl;
	 //点击图文消息跳转链接
	 private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	 
	 

}
