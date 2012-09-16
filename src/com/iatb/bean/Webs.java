package com.iatb.bean;

public class Webs {

	private String tag;
	private String attribute;
	private String value;
	private int num;
	private String clearAttr;
	private String like;
	private String noLike;
	private String wtitle;
	private String wtime;
	
	public String getLike() {
		return like;
	}
	public String getNoLike() {
		return noLike;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public void setNoLike(String noLike) {
		this.noLike = noLike;
	}
	public Webs(String tag, String attribute, String value, String clearAttr) {
		super();
		this.tag = tag;
		this.attribute = attribute;
		this.value = value;
		this.clearAttr = clearAttr;
	}
	public String getClearAttr() {
		return clearAttr;
	}
	public void setClearAttr(String clearAttr) {
		this.clearAttr = clearAttr;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTag() {
		return tag;
	}
	public Webs(String tag, String attribute, String value, int num) {
		super();
		this.tag = tag;
		this.attribute = attribute;
		this.value = value;
		this.num = num;
	}
	public String getAttribute() {
		return attribute;
	}
	public String getValue() {
		return value;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Webs(String tag, String attribute, String value) {
		super();
		this.tag = tag;
		this.attribute = attribute;
		this.value = value;
	}
	public String getWtitle() {
		return wtitle;
	}
	public void setWtitle(String wtitle) {
		this.wtitle = wtitle;
	}
	public String getWtime() {
		return wtime;
	}
	public void setWtime(String wtime) {
		this.wtime = wtime;
	}

}
