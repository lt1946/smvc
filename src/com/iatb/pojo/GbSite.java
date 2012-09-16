package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-03-07 18:59:17
 */
public class GbSite  implements Serializable {
	
	private	int	id;
	private	String	siteName;
	private	String	siteUrl;
	private	String	loginUrl;
	private	String	signupUrl;
	private	String	inviteURl;
	private	int	isReg;
	private	int	isDownLine;
	private	int	isInvite;
	private	int	isCheck;
	private	int	level;
	private	String	createTime;
	private	int	status;
	private	String	encode;
	private	String	updateTime;
	

	@Override
	public String toString() {
		return "|id:"+id+"|siteName:"+siteName+"|siteUrl:"+siteUrl+"|loginUrl:"+loginUrl+"|signupUrl:"+signupUrl+"|inviteURl:"+inviteURl+"|isReg:"+isReg+"|isDownLine:"+isDownLine+"|isInvite:"+isInvite+"|isCheck:"+isCheck+"|level:"+level+"|createTime:"+createTime+"|status:"+status+"|encode:"+encode+"|updateTime:"+updateTime;
	}
	public GbSite() {
		super();
	}
	public GbSite(String siteName,String siteUrl,String loginUrl,String signupUrl,String inviteURl,int isReg,int isDownLine,int isInvite,int isCheck,int level,String encode,String updateTime)
	{
		this.siteName = siteName;
		this.siteUrl = siteUrl;
		this.loginUrl = loginUrl;
		this.signupUrl = signupUrl;
		this.inviteURl = inviteURl;
		this.isReg = isReg;
		this.isDownLine = isDownLine;
		this.isInvite = isInvite;
		this.isCheck = isCheck;
		this.level = level;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
		this.encode = encode;
		this.updateTime = updateTime;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getSiteName(){
		return siteName;
	}
	public void setSiteName(String siteName){
		this.siteName = siteName;
	}
	public String getSiteUrl(){
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl){
		this.siteUrl = siteUrl;
	}
	public String getLoginUrl(){
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl){
		this.loginUrl = loginUrl;
	}
	public String getSignupUrl(){
		return signupUrl;
	}
	public void setSignupUrl(String signupUrl){
		this.signupUrl = signupUrl;
	}
	public String getInviteURl(){
		return inviteURl;
	}
	public void setInviteURl(String inviteURl){
		this.inviteURl = inviteURl;
	}
	public int getIsReg(){
		return isReg;
	}
	public void setIsReg(int isReg){
		this.isReg = isReg;
	}
	public int getIsDownLine(){
		return isDownLine;
	}
	public void setIsDownLine(int isDownLine){
		this.isDownLine = isDownLine;
	}
	public int getIsInvite(){
		return isInvite;
	}
	public void setIsInvite(int isInvite){
		this.isInvite = isInvite;
	}
	public int getIsCheck(){
		return isCheck;
	}
	public void setIsCheck(int isCheck){
		this.isCheck = isCheck;
	}
	public int getLevel(){
		return level;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public String getCreateTime(){
		return DateUtil.getPlusTimeDate();
	}
	public void setCreateTime(){
		this.createTime = DateUtil.getPlusTimeDate();
	}
	public int getStatus(){
		return status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	public String getEncode(){
		return encode;
	}
	public void setEncode(String encode){
		this.encode = encode;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime;
	}

}