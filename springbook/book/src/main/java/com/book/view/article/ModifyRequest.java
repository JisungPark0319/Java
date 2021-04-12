package com.book.view.article;

public class ModifyRequest {
	private String userId;
	private int articleNumber;
	private String title;
	private String content;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getArticleNumber() {
		return articleNumber;
	}

	public void setArticleNumber(int articleNumber) {
		this.articleNumber = articleNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", articleNumber=" + articleNumber + ", title=" + title
				+ ", content=" + content + "]";
	}

}
