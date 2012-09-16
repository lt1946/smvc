package com.iatb.pojo;

import com.iatb.util.DateUtil;
import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2012-01-23 00:45:11
 */
public class Groupurlrole  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	int	id;
	private	String	name;
	private	String	url;
	private	String	titleLike;
	private	String	titleUnLike;
	private	String	urlWebs;
	private	String	urlLike;
	private	String	urlUnLike;
	private	String	contentPre;
	private	String	contentEnd;
	private	String	createTime;
	private	int	status;
	private	int	isNew;
	private	String	contentUnLike;
	private	int	siteid;
	private	String	encode;
	private	String	webs;
	private	String	contentDel;
	private	int	isInner;
	private	String	innerWebs;
	private	String	innerEnd;
	private	int	hasCode;
	private	int	isPic;

	@Override
	public String toString() {
		return "|id:"+id+"|name:"+name+"|url:"+url+"|titleLike:"+titleLike+"|titleUnLike:"+titleUnLike+"|urlWebs:"+urlWebs+"|urlLike:"+urlLike+"|urlUnLike:"+urlUnLike+"|contentPre:"+contentPre+"|contentEnd:"+contentEnd+"|createTime:"+createTime+"|status:"+status+"|isNew:"+isNew+"|contentUnLike:"+contentUnLike+"|siteid:"+siteid+"|encode:"+encode+"|webs:"+webs+"|contentDel:"+contentDel+"|isInner:"+isInner+"|innerWebs:"+innerWebs+"|innerEnd:"+innerEnd+"|hasCode:"+hasCode+"|isPic:"+isPic;
	}
	public Groupurlrole() {
		super();
	}
	public Groupurlrole(String name,String url,String titleLike,String titleUnLike,String urlWebs,String urlLike,String urlUnLike,String contentPre,String contentEnd,int isNew,String contentUnLike,int siteid,String encode,String webs,String contentDel,int isInner,String innerWebs,String innerEnd,int hasCode,int isPic)
	{
		this.name = name;
		this.url = url;
		this.titleLike = titleLike;
		this.titleUnLike = titleUnLike;
		this.urlWebs = urlWebs;
		this.urlLike = urlLike;
		this.urlUnLike = urlUnLike;
		this.contentPre = contentPre;
		this.contentEnd = contentEnd;
		this.createTime = DateUtil.getPlusTimeDate();
		this.status = 0;
		this.isNew = isNew;
		this.contentUnLike = contentUnLike;
		this.siteid = siteid;
		this.encode = encode;
		this.webs = webs;
		this.contentDel = contentDel;
		this.isInner = isInner;
		this.innerWebs = innerWebs;
		this.innerEnd = innerEnd;
		this.hasCode = hasCode;
		this.isPic = isPic;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getTitleLike(){
		return titleLike;
	}
	public void setTitleLike(String titleLike){
		this.titleLike = titleLike;
	}
	public String getTitleUnLike(){
		return titleUnLike;
	}
	public void setTitleUnLike(String titleUnLike){
		this.titleUnLike = titleUnLike;
	}
	public String getUrlWebs(){
		return urlWebs;
	}
	public void setUrlWebs(String urlWebs){
		this.urlWebs = urlWebs;
	}
	public String getUrlLike(){
		return urlLike;
	}
	public void setUrlLike(String urlLike){
		this.urlLike = urlLike;
	}
	public String getUrlUnLike(){
		return urlUnLike;
	}
	public void setUrlUnLike(String urlUnLike){
		this.urlUnLike = urlUnLike;
	}
	public String getContentPre(){
		return contentPre;
	}
	public void setContentPre(String contentPre){
		this.contentPre = contentPre;
	}
	public String getContentEnd(){
		return contentEnd;
	}
	public void setContentEnd(String contentEnd){
		this.contentEnd = contentEnd;
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
	public int getIsNew(){
		return isNew;
	}
	public void setIsNew(int isNew){
		this.isNew = isNew;
	}
	public String getContentUnLike(){
		return contentUnLike;
	}
	public void setContentUnLike(String contentUnLike){
		this.contentUnLike = contentUnLike;
	}
	public int getSiteid(){
		return siteid;
	}
	public void setSiteid(int siteid){
		this.siteid = siteid;
	}
	public String getEncode(){
		return encode;
	}
	public void setEncode(String encode){
		this.encode = encode;
	}
	public String getWebs(){
		return webs;
	}
	public void setWebs(String webs){
		this.webs = webs;
	}
	public String getContentDel(){
		return contentDel;
	}
	public void setContentDel(String contentDel){
		this.contentDel = contentDel;
	}
	public int getIsInner(){
		return isInner;
	}
	public void setIsInner(int isInner){
		this.isInner = isInner;
	}
	public String getInnerWebs(){
		return innerWebs;
	}
	public void setInnerWebs(String innerWebs){
		this.innerWebs = innerWebs;
	}
	public String getInnerEnd(){
		return innerEnd;
	}
	public void setInnerEnd(String innerEnd){
		this.innerEnd = innerEnd;
	}
	public int getHasCode(){
		return hasCode;
	}
	public void setHasCode(int hasCode){
		this.hasCode = hasCode;
	}
	public int getIsPic(){
		return isPic;
	}
	public void setIsPic(int isPic){
		this.isPic = isPic;
	}

}