package com.rui.weixin.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rui.weixin.bean.Image;
import com.rui.weixin.bean.ImageMessage;
import com.rui.weixin.bean.Music;
import com.rui.weixin.bean.MusicMessage;
import com.rui.weixin.bean.NEWSMessage;
import com.rui.weixin.bean.News;
import com.rui.weixin.bean.VideoMessage;
import com.rui.weixin.bean.Videos;
import com.thoughtworks.xstream.XStream;

/**
 * @title 回复消息工具类
 * @author ruihu
 * @date 2018年7月3日
 */
public class ResultMessageUtil {
	//回复消息为文本消息
		private static String RESULT_MESSAGE_TYPE_TEXT = "text";
	//回复消息为图文消息
	private static String RESULT_MESSAGE_TYPE_NEWS = "news";
	//回复消息为图文消息
	private static String RESULT_MESSAGE_TYPE_IMAGE = "image";
	//音乐消息
	private static String RESULT_MESSAGE_TYPE_MUSIC = "music";
	
	private static String LOCAL_PATH = "http://b19d09d3.ngrok.io/weixin/";
	
	
	public static String initVideoMessage(String toUserName, String fromUserName){
		String result = "";
		VideoMessage message = new VideoMessage();
		Videos video = new Videos();
		
		//消息从哪里来的
		message.setFromUserName(toUserName);
		//消息发给谁
		message.setToUserName(fromUserName);
		Date date = new Date();
		message.setCreateTime(date.getTime());
		message.setMsgType("video");
		
		video.setTitle("要你好看");
		video.setDescription("幸福的日子");
		video.setMediaId("FPd7o0Ky-88_jHjdwjXBKnpdDUW8tH61p7Sz_zwBenEkZh_5AR1YrqKtmDJaKRVO");
		message.setVideo(video);
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		xstream.alias("Video", new Music().getClass());
		result = xstream.toXML(message); 
		
		return result;
	}
	
	
	public static String initMusicMessage(String toUserName, String fromUserName){
		String result = "";
		MusicMessage message = new MusicMessage();
		Music music = new Music();
		
		//消息从哪里来的
		message.setFromUserName(toUserName);
		//消息发给谁
		message.setToUserName(fromUserName);
		Date date = new Date();
		message.setCreateTime(date.getTime());
		message.setMsgType("music");
		
		music.setDescription("这个是一首网红歌");
		music.setTitle("THAT GIRL");
		music.setMusicUrl(LOCAL_PATH + "music/That-Girl.mp3");
		music.setHQMusicUrl(LOCAL_PATH + "music/That-Girl.mp3");
		music.setThumbMediaId("eTxbIMzLuGpylyPdITdShckxgGWr3wZYvqn2o-M-hJlWKP1NcHqDQhSgHddergy7");
		message.setMusic(music);
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		xstream.alias("Music", new Music().getClass());
		result = xstream.toXML(message); 
		
		return result;
	}
	
	
	/**
	 * @Title: 图片消息初始化    
	 * @param: @param toUserName
	 * @param: @param fromUserName
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年7月4日 下午10:57:55
	 * @return: String      
	 * @throws
	 */
	public static String initImageMessage(String toUserName, String fromUserName){
		String result = "";
		ImageMessage imageMessage = new ImageMessage();
		Image photo = new Image();
		photo.setMediaId("1uCk24azOFgOgwJd9pwMVkmhGjlRfJIbLdYWJoq8qZR0wvCt-hU1GCnqhieA3K02");
		
		//消息从哪里来的
		imageMessage.setFromUserName(toUserName);
		//消息发给谁
		imageMessage.setToUserName(fromUserName);
		Date date = new Date();
		imageMessage.setCreateTime(date.getTime());
		imageMessage.setMsgType(RESULT_MESSAGE_TYPE_IMAGE);
		imageMessage.setImage(photo);
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		xstream.alias("Image", new Image().getClass());
		result = xstream.toXML(imageMessage); 
		
		return result;
	}

	/**
	 * @Title: 拼接图文消息字符串
	 * @param: @param toUserName
	 * @param: @param fromUserName
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年7月3日 下午11:20:00
	 * @return: String      
	 * @throws
	 */
	public static String initNewsMessage(String toUserName, String fromUserName){
		List<News> newsList = new ArrayList<>();
		
		NEWSMessage<News> newsMessage = new NEWSMessage<>();
		News phoa = new News();
		phoa.setTitle("小小梦阿胶膏");
		phoa.setDescription("这个是一个关于慕课网描述的内容，应该是会有很多内容的");
		//图片链接
		phoa.setPicUrl(LOCAL_PATH + "image/ejiao.jpg");
		//跳转的路径
		phoa.setUrl("www.imooc.com");
		
		News phob = new News();
		phob.setTitle("慕课网介绍第二条");
		phob.setDescription("这个是第二条关于慕课网描述的内容，应该是会有很多内容的");
		//图片链接
		phob.setPicUrl(LOCAL_PATH + "image/ejiao.jpg");
		//跳转的路径
		phob.setUrl("www.zbj.com");
		
		News phoc = new News();
		phoc.setTitle("慕课网介绍第三条");
		phoc.setDescription("这个是第二条关于慕课网描述的内容，应该是会有很多内容的");
		//图片链接
		phoc.setPicUrl(LOCAL_PATH + "image/ejiao.jpg");
		//跳转的路径
		phoc.setUrl(LOCAL_PATH + "index.jsp");
		
		newsList.add(phoa);
		newsList.add(phob);
		newsList.add(phoc);
		//设置图文消息个数
		newsMessage.setArticleCount(newsList.size() );
		//设置消息体
		newsMessage.setArticles(newsList);
		//消息从哪里来的
		newsMessage.setFromUserName(toUserName);
		//消息发给谁
		newsMessage.setToUserName(fromUserName);
		Date date = new Date();
		newsMessage.setCreateTime(date.getTime());
		newsMessage.setMsgType(RESULT_MESSAGE_TYPE_NEWS);
		
		return photoMessToXML(newsMessage);
	}
	
	/**
	 * @Title: 图文消息转为xml字符串   
	 * @param: @param message
	 * @param: @return      
	 * @author 胡锐
	 * @date:   2018年7月3日 下午11:02:34
	 * @return: String      
	 * @throws
	 */
	public static String photoMessToXML(NEWSMessage message){
		XStream xstream = new XStream();
		xstream.alias("xml", message.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(message);
	}
	
	public static void main(String[] args) {
		//System.out.println(initImageMessage("xx","yy"));
		System.out.println(initMusicMessage("xx", "yyy"));
	}
	
}
