package com.rui.weixin.bean;

import java.util.List;

/**
 * @title 回复消息类型
 * @author ruihu
 * @date 2018年7月3日
 */
public class NEWSMessage<T> extends BaseMessage{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 图文消息个数，限制为8条以内 
	 */
	private Integer ArticleCount = 1;
	
	/**
	 * 消息内容
	 */
	private List<T> Articles;

	public Integer getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(Integer articleCount) {
		ArticleCount = articleCount;
	}

	public List<T> getArticles() {
		return Articles;
	}

	public void setArticles(List<T> articles) {
		Articles = articles;
	}

}
