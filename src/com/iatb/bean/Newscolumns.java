package com.iatb.bean;

/** 新闻栏目的持久化类 */
public class Newscolumns implements java.io.Serializable {
	private Integer id;//ID号
	private Newscolumns newscolumns;//父栏目
	private String columnCode;//新闻栏目编号
	private String columnName;//新闻栏目名称

	/** 构造方法 */
	public Newscolumns() {}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Newscolumns getNewscolumns() {
		return this.newscolumns;
	}

	public void setNewscolumns(Newscolumns newscolumns) {
		this.newscolumns = newscolumns;
	}

	public String getColumnCode() {
		return this.columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
}