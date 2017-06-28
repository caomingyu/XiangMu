package com.po;
/**
 * 评论类
 * 
 *
 */
public class Comment {
	private int commentId;
	private String content;
	//评论者
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//评论时间
	private String PTime;
	private String newsId;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPTime() {
		return PTime;
	}
	public void setPTime(String pTime) {
		PTime = pTime;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
}
