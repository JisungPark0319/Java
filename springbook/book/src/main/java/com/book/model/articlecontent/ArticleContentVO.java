package com.book.model.articlecontent;

public class ArticleContentVO {
	private Integer number;
	private String content;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleContent [number=" + number + ", content=" + content + "]";
	}

}
