package com.iatb.tld;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.eportal.DAO.BaseDAO;
import com.eportal.DAO.BaseDAOImpl;
import com.eportal.ORM.Category;
import com.eportal.util.Tools;

/** 商品搜索分页控制自定义标签类 */
public class SearchMerPageTag extends SimpleTagSupport{
	BaseDAO dao = null; //数据库DAO接口
	String hql= null;
	int listtype = 1;	//1-最新商品列表,2-促销商品列表,默认值为1
	String url = "";	//分页调用的URL,此项为必填项
	int pageNo = 1;		//页号
	int pageSize=6;     //每页商品条数,默认值6
	String keyword ="";	//搜索关键字	
	int pageTotal=1;    //总页数
	int prePageNo = 1;	//上一页页号	
	int nextPageNo = 1;	//下一页页号
	List<Category> catelist;
	Iterator<Category> cateit;
	Category cate;
	String ids=null;

	/** 标签体处理 */
    public void doTag() throws JspException, IOException{
    	//使用WebApplicationContextUtils工具类获取Spring IOC容器中的dao实例
    	dao = (BaseDAOImpl)WebApplicationContextUtils.getRequiredWebApplicationContext(((PageContext)getJspContext()).getServletContext()).getBean("dao");    	
    	if (listtype==1){//最新商品列表
    		hql = "select count(*) from Merchandise as a where a.status=1 and (a.merName like '%"+keyword+"%' or a.merModel like '%"+keyword+"%' or a.merDesc like '%"+Tools.escape(keyword)+"%')";
    	}else{//促销商品列表
    		hql = "select count(*) from Merchandise as a where a.status=1 and a.special=1 and (a.merName like '%"+keyword+"%' or a.merModel like '%"+keyword+"%' or a.merDesc like '%"+Tools.escape(keyword)+"%')";
    	}
		StringBuffer sb=new StringBuffer();
		//统计满足条件的商品条数
		int total=dao.countQuery(hql);
		//计算总页数
		if (total>0){
			pageTotal = total / pageSize;
			if ((total%pageSize)>0)pageTotal++;
		}
		//计算上一页
		if (pageNo>1){
			prePageNo = pageNo-1;
		}else{
			prePageNo = 1;
		}
		//计算下一页
		if (pageNo<pageTotal){
			nextPageNo = pageNo+1;
		}else{
			nextPageNo = pageTotal;
		}
		//构造出url
		if (url.indexOf("?")!=-1){
			url = url+"&";
		}else{
			url = url+"?";
		}
		sb.append("<div>当前第["+pageNo+"/"+pageTotal+"]页 [<a target='_self' href='"+url+"pageNo=1'>首页</a>] ");
		//第一页时,"上一页"链接无效
		if (pageNo==1){
			sb.append("[上一页] ");
		}else{
			sb.append("[<a target='_self' href='"+url+"pageNo="+prePageNo+"'>上一页</a>] ");
		}
		//最末页时,"下一页"链接无效
		if (pageNo==pageTotal){
			sb.append("[下一页] ");
		}else{
			sb.append("[<a target='_self' href='"+url+"pageNo="+nextPageNo+"'>下一页</a>] ");
		}
		sb.append("[<a target='_self' href='"+url+"pageNo="+pageTotal+"'>尾页</a>]</div>");
    	//输出处理结果到页面上
    	getJspContext().getOut().println(sb); 
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getListtype() {
		return listtype;
	}

	public void setListtype(int listtype) {
		this.listtype = listtype;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
