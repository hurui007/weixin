package com.rui.weixin.bean;
/**
 * @title 音乐消息出参
 * @author ruihu
 * @date 2018年7月5日
 */
public class MusicMessage extends BaseMessage {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
	
}
