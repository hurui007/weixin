package com.rui.weixin.bean;

import java.io.Serializable;

public class TextMessage extends BaseMessage implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String Content;
	
	private String MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	
	
	/*<xml>
	<ToUserName><![CDATA[公众号]]></ToUserName>
	 <FromUserName><![CDATA[粉丝号]]></FromUserName>
	 <CreateTime>1460537339</CreateTime>
	 <MsgType><![CDATA[text]]></MsgType>
	 <Content><![CDATA[欢迎开启公众号开发者模式]]></Content>
	 <MsgId>6272960105994287618</MsgId>
	 </xml>*/
	
//	<xml>
//	 <ToUserName><![CDATA[粉丝号]]></ToUserName>
//	 <FromUserName><![CDATA[公众号]]></FromUserName>
//	 <CreateTime>1460541339</CreateTime>
//	 <MsgType><![CDATA[text]]></MsgType>
//	 <Content><![CDATA[test]]></Content>
//	 </xml>
	
	
	
}
