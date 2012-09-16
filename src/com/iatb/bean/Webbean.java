package com.iatb.bean;

public class Webbean {

	public Webbean() {
		super();
	}
	private String url;
	private String title;
	public Webbean(String title,String url) {
		super();
		this.url = url;
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
