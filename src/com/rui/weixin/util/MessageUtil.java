package com.rui.weixin.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rui.weixin.bean.TextMessage;
import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author ruihu
 *
 */
public class MessageUtil {
	//微信消息类型
	//文本
	private static final String MESSAGE_TEXT = "text";
	//图片
	private static final String MESSAGE_IMAGE = "image";
	//	语音
	private static final String MESSAGE_VOICE = "voice";
	//	视频
	private static final String MESSAGE_VIDEO = "video";
	// 连接
	private static final String MESSAGE_LINK = "link";
	// 地理位置
	private static final String MESSAGE_LOCATION = "location";
	// 事件
	private static final String MESSAGE_EVENT = "event";
	// 关注
	private static final String MESSAGE_SUBSCRIBE = "subscribe";
	// 取消关注
	private static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	// 菜单点击
	private static final String MESSAGE_CLICK = "CLICK";
	// 菜单点击
	private static final String MESSAGE_VIEW = "VIEW";
	
	/**
	 * @title 根据消息类型的不同，返回不同类型的消息
	 * @param str
	 */
	public static String message(HttpServletRequest request){
		Map<String, String > messageMap = getWeixinMessage(request);
		String resultMessage = "你说什么？";
		switch(messageMap.get("MsgType")){
		case MESSAGE_TEXT:
			resultMessage = textMessage(messageMap);
			break;
		case MESSAGE_IMAGE:
			
			break;
		case MESSAGE_VOICE:
			
			break;
		case MESSAGE_VIDEO:
			
			break;
		case MESSAGE_LINK:
			
			break;
		case MESSAGE_LOCATION:
			
			break;
		case MESSAGE_EVENT:
			//事件处理
			switch(messageMap.get("Event")){
			case MESSAGE_SUBSCRIBE:
				//用户关注事件
				resultMessage = subscribeMessage(messageMap);
				break;
			case MESSAGE_UNSUBSCRIBE:
				
				break;
			case MESSAGE_CLICK:
				
				break;
			case MESSAGE_VIEW:
				
				break;
			}
			break;
		
		default:
			
		
		}
		return resultMessage;
	}
	
	/**
	 * @Title: TODO(关注事件处理)    
	 * @param: @param messageMap
	 * @author 胡锐
	 * @date:   2018年6月30日 上午12:10:48
	 * @return: String      
	 * @throws
	 */
	private static String subscribeMessage(Map<String, String> messageMap){
		TextMessage textMessage = takeUserInfo(messageMap);
		textMessage.setContent(getDefaultResultMessage());
		textMessage.setMsgType("text");
		return MessageUtil.textToXML(textMessage);
	}
	
	/**
	 * @Title: TODO(文本消息的处理)    
	 * @param: @param messageMap
	 * @author 胡锐
	 * @date:   2018年6月29日 下午11:59:55
	 * @return: String      
	 * @throws
	 */
	private static String textMessage(Map<String, String> messageMap){
		TextMessage textMessage = takeUserInfo(messageMap);
		textMessage.setMsgType("text");
		String content = messageMap.get("Content");
		String result = "";
		if("1".equals(content.trim())){
			result = "课程介绍：我给你说哈慕课网是很牛逼的哦";
		}
		else if("2".equals(content.trim())){
			result = "慕课网介绍：这个就是慕课网的介绍";
		}
		else if("3".equals(content.trim())){
			//textMessage.setMsgType("news");
			//直接返回图文消息内容，且不需要其他拼接
			return result = ResultMessageUtil.initNewsMessage(messageMap.get("ToUserName"), messageMap.get("FromUserName"));
		} else if("4".equals(content.trim())){
			//textMessage.setMsgType("image");
			//直接返回图文消息内容，且不需要其他拼接
			return result = ResultMessageUtil.initImageMessage(messageMap.get("ToUserName"), messageMap.get("FromUserName"));
		} else if("5".equals(content.trim())){
			return result = ResultMessageUtil.initMusicMessage(messageMap.get("ToUserName"), messageMap.get("FromUserName"));
		} else if("6".equals(content.trim())){
			return result = ResultMessageUtil.initVideoMessage(messageMap.get("ToUserName"), messageMap.get("FromUserName"));
		} else{
			
			result = getDefaultResultMessage();
		}
		//获取要返回的文本消息
		textMessage.setContent(textMessageDetail(textMessage,messageMap.get("Content"),messageMap.get("ToUserName"),messageMap.get("FromUserName")));
		return MessageUtil.textToXML(textMessage);
	}
	
	private static String getDefaultResultMessage(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("欢迎关注，请按照菜单提示操作:\r\n");
		buffer.append("1：课程介绍\r\n");
		buffer.append("2：慕课网介绍\r\n");
		buffer.append("3：让你看看图文消息\r\n");
		buffer.append("4：让你看图片消息\r\n");
		buffer.append("5：让你听音乐\r\n");
		buffer.append("6：让你好看\r\n");
		buffer.append("回复？调出此菜单");
		return buffer.toString();
		
	}
	
	/**
	 * @Title: TODO(根据消息内容，返回不同的消息)    
	 * @param: @param content
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年6月30日 上午12:37:15
	 * @return: String      
	 * @throws
	 */
	private static String textMessageDetail(TextMessage textMessage, String content, String toUserName, String fromUserName){
		String result = "";
		if("1".equals(content.trim())){
			result = "课程介绍：我给你说哈慕课网是很牛逼的哦";
		}
		else if("2".equals(content.trim())){
			result = "慕课网介绍：这个就是慕课网的介绍";
		} else{
			result = getDefaultResultMessage();
		}
		return result;
	}
	
	
	/**
	 * @Title: TODO(设置消息回发路径)    
	 * @param: @param messageMap
	 * @author 胡锐
	 * @date:   2018年6月30日 上午12:05:19
	 * @return: TextMessage      
	 * @throws
	 */
	private static TextMessage takeUserInfo(Map<String, String> messageMap){
		TextMessage textMes = new TextMessage();
		textMes.setFromUserName(messageMap.get("ToUserName"));
		textMes.setToUserName(messageMap.get("FromUserName"));
		//textMes.setMsgType(messageMap.get("MsgType"));
		textMes.setCreateTime(new Date().getTime());
		return textMes;
	}
	
	/**
	 * @title 微信消息入参转到map集合中
	 * @param request
	 * @return
	 */
	private static Map<String, String> getWeixinMessage(HttpServletRequest request){
		Map<String, String> map = new HashMap<>();
		try {
			map = xml2Map(request);
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @title xml转map集合
	 * @param req
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public static Map<String, String> xml2Map(HttpServletRequest req) throws IOException, DocumentException{
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		InputStream ins = req.getInputStream();
		Document doc = reader.read(ins);
		
		Element el = doc.getRootElement();
		List<Element> list = el.elements();
		if(null != list && list.size() > 0){
			for(Element e:list){
				map.put(e.getName(), e.getText());
			}
		}
		ins.close();
		return map;
		
	}
	
	public static String textToXML(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	

}
