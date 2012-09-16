package com.iatb.pojo;

/**
 * Admin entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginName;
	private String loginPwd;
	private String privileges;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** full constructor */
	public Admin(String loginName, String loginPwd, String privileges) {
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.privileges = privileges;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getPrivileges() {
		return this.privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

}