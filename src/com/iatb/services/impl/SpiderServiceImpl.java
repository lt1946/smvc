package com.iatb.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.bean.Webbean;
import com.iatb.dao.ContentDao;
import com.iatb.dao.GroupurlnowDao;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.pojo.Content;
import com.iatb.pojo.Groupurlnow;
import com.iatb.pojo.Groupurlrole;
import com.iatb.services.SpiderService;
import com.iatb.util.SpiderUtil;
import com.iatb.util.http.MyHtmlCleaner;

@Service("spiderService")
//@RequestMapping("/spider.do")
public class SpiderServiceImpl implements SpiderService {

	protected static Logger logger = Logger.getLogger(SpiderServiceImpl.class);
	@Autowired
	private GroupurlroleDao groupurlroleDao;
	@Autowired
	private ContentDao contentDao;
	@Autowired
	GroupurlnowDao groupurlnowDao;
	private int count;
	private boolean isLimit=false;
	private int nowtype=-1;
	
	public void spiderLimit() {
		System.out.println("开始抓取限制规则");
		List<Groupurlrole> list=groupurlroleDao.getLimitGur();
		count=10;isLimit=true;
		run(list);
	}
	public void spiderNormal() {
		System.out.println("开始抓取普通规则");
		List<Groupurlrole> list=groupurlroleDao.getNormalGur();
		run(list);
	}
	public void run(List<Groupurlrole> list) {
		for (Groupurlrole gur : list) {
			if(isLimit&&count<=0){
				logger.info("达到10次限制抓取个数，停止抓取");
				return;
			}
			String url[]=((String) gur.getUrl()).split(",");
			String u = "";
			int end=Integer.parseInt(url[3]);
			int next=Integer.parseInt(url[2]);
			//从上次暂停处抓取
			Groupurlnow gun= groupurlnowDao.getfromgurid(gur.getId());
			if(gun!=null){
				if(gun.getEndurlnum()!=0){
					nowtype=1;
					boolean bn=true;
					logger.info("开始从上次暂停处抓取分页：" + url[0].replace("(*)",gun.getEndurlnum()+"" ));
					for(int ei=gun.getEndurlnum();ei<end;ei+=next,bn=groupurlnowDao.updateGunEndNum(gun.getId(),ei)){
						if(!bn){
							logger.info("更新上次抓取页码数字失败！停止！");
							return;
						}
						String uend = url[0].replace("(*)",ei+"" );
						getUrl(gur,uend); 		// 获取url
						if(isLimit&&count<=0){
							logger.info("达到10次限制抓取个数，停止抓取");
							return;
						}
					}
				}
			}else{
				gun=new Groupurlnow();
			}
			nowtype=-1;
			//从最新抓取
			for (int i = Integer.parseInt(url[1]); i <= end; i += next) {
				u = url[0].replace("(*)", String.valueOf(i));
				logger.info("开始抓取分页：" + u);
				getUrl(gur,u); 		// 获取url
				if(isLimit&&count<=0){
					logger.info("达到10次限制抓取个数，停止抓取");
					return;
				}
			}
		}
		System.out.println("结束获取抓取规则");
	}
	/**
	 * 获取分页里的链接
	 * @param url
	 */
	public  void getUrl(Groupurlrole gur,String url) {
		try {
			Map<String, String> m=new ConcurrentHashMap<String, String>();
			boolean b=false;
			String urlWebs[]=new String[]{};
			if(gur.getUrlWebs()!=null&&gur.getUrlWebs().trim().length()!=0){
				urlWebs=gur.getUrlWebs().split("[|]");
				if(urlWebs.length==2){
					b=true;
				}
				m = MyHtmlCleaner.getAllLink(url, gur.getEncode(),urlWebs[0]);
			}else{
				m = MyHtmlCleaner.getAllLink(url, gur.getEncode());
			}
			if(m==null||m.size()==0)return;
			if(b){	//两层urlwebs
				for (Map.Entry<String, String> s : m.entrySet()) {
					Map<String, String> m2 = MyHtmlCleaner.getAllLink(s.getValue(), gur.getEncode(),urlWebs[1]);
					checkSaveContent(gur, m2);
				}
			}else 
				checkSaveContent(gur, m);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 按gur类型区分保存方法
	 * @param gur
	 * @param m
	 */
	private void checkSaveContent(Groupurlrole gur,Map<String,String> m ){
		for (Map.Entry<String, String> s : m.entrySet()) {
			Webbean wb=groupurlroleDao.checkUrl(gur,s.getKey(), s.getValue());  	//过滤webbean
			if(wb!=null)
				if(!contentDao.checkWebbean(wb)){	//检查webben是否重复		//TODO优化返回值
					boolean b=true;
					if(gur.getInnerWebs()!=null&&MyHtmlCleaner.isInnerPage(s.getValue().trim(),gur.getEncode(),gur.getInnerWebs())){
						//获取所有内页链接
						List<String> list=MyHtmlCleaner.getLinksByWebs(s.getValue().trim(),gur.getEncode(),gur.getInnerWebs() );
						count--;
						b=saveContent(gur,list,wb.getTitle());
					}else if(gur.getIsPic()==1){
						count--;
						b=saveContentPic(gur,wb);
					}else{
						try {
							String c =getContent(gur,s.getKey().trim(), s.getValue().trim());
							if(c!=null){
								count--;
								b=saveContent(gur,wb,c);
							}else b=false;
						} catch (Exception e) {
							b=false;
							continue;
						}
					}
					//保存更新的title,url
					if(b&&nowtype==1){
						boolean wbb=groupurlnowDao.saveWebBean(gur.getId(),wb);
						logger.info("保存更新的webbean："+(wbb?"成功":"失败"));
					}
				}
			}
	}
	/**	
	 * 保存有图片的内容
	 * @param wb
	 */
	private boolean saveContentPic(Groupurlrole gur, Webbean wb){
		try {
			if(gur.getSiteid()==11){	//扩展
				List<String> list=new ArrayList<String>();
				HtmlCleaner cleaner = new HtmlCleaner();
				TagNode node=null;
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(wb.getUrl().trim()); 
				HttpResponse response;
				String  r = null;
				response = client.execute(httpget);
				if(null != response){
					HttpEntity entity = response.getEntity();
					r  = EntityUtils.toString(entity ,gur.getEncode());
					httpget.abort();
					client.getConnectionManager().shutdown(); 
				}
				node=cleaner.clean(r);
				Object[] ns = node.evaluateXPath("//div[@class=\"title fz18 white line24\"]");
				if(ns.length==0)return  false;
				String title=((TagNode)ns[0]).getText().toString();
				int i=title.indexOf("(1/");
				if(i!=-1){
					String num=title.substring(i+3,title.length()-1);
					int end=Integer.parseInt( num);
					if(end>1){
						String c=wb.getUrl().substring(wb.getUrl().lastIndexOf("."));
						for (int j = 0; j < end; j++) {
							list.add(wb.getUrl().substring(0,wb.getUrl().lastIndexOf("_")+1)+""+j+c);
						}
					}else{
						list.add(wb.getUrl());
					}
				}
				for (int j = 0; j <list.size(); j++) {
					String c =getContent(gur,wb.getTitle()+"("+j+1+")",list.get(j)); //获取内容
					if(c==null||c.trim().equals("")){
						logger.info("saveContentPic():		content is :null");return false;
					}
					saveContent(gur,new Webbean(j==0?wb.getTitle():wb.getTitle()+"("+(j+1)+")",list.get(j)),c);// 保存
				}
			}else{
				String c =getContent(gur,wb.getTitle().trim(),wb.getUrl().trim());
				if(c==null)return false;
				saveContent(gur,wb,c);// 保存
			}
			count--;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存内页内容
	 * @param list
	 * @param title
	 */
	private boolean saveContent(Groupurlrole gur,List<String> list, String title) {
		for (int i = 0; i <list.size(); i++) {
			String c =getContent(gur,title+"("+i+1+")",list.get(i)); //获取内容
			if(c==null){
				logger.info("saveContent():	content is :null");return false;
			}
			int j=c.indexOf(gur.getInnerEnd().replace("{0}",""+list.size()));
			if(j>0)c=c.substring(0,j);
			if(c.trim().equals(""))return false;
			saveContent(gur,new Webbean(i==0?title:title+"("+(i+1)+")",list.get(i)),c);// 保存
		}
		return true;
	}
	/**
	 * 保存内容
	 * @param wb
	 * @param c
	 */
	private boolean saveContent(Groupurlrole gur,Webbean wb, String c) {
		try {
			if(c==null||c.trim().equals(""))return false;
			Content cc=new Content();
			cc.setTitle(wb.getTitle());
			cc.setContent(c);
			cc.setCreateTime();
			cc.setGroupurlid(gur.getId());
			cc.setUrl(wb.getUrl());
			cc.setStatus(0);
			cc.setSiteid(gur.getSiteid());
			String s=contentDao.add(cc);
			logger.info("添加content:《"+wb.getTitle()+(s.indexOf("成功")>=0?"》\t成功":"》\t失败"));
		} catch (Exception e) {
			logger.info("saveContent():\t"+e.getMessage());
			return false;
		}
		return true;
	}
	/**
	 * 获取内容
	 * @param title
	 * @param url
	 * @return
	 */
	private String getContent(Groupurlrole gur,String title, String url) {
		try {
			String encode=gur.getEncode();
			String webs=gur.getWebs();
//			String contentUnLike=gur.getContentUnLike();
			String content=null;
			if(webs!=null){
				try {
					content=SpiderUtil.getContentParser(url,encode,webs,gur.getHasCode(),gur.getIsPic()==1).toString();
				} catch (Exception e) {
					System.out.println(e.getMessage()+":"+url);
					return null;
				}
			}else{
				content=SpiderUtil.getContentParser(url,encode,gur.getHasCode(),gur.getIsPic()==1).toString();
//				content=MyHtmlCleaner.getCode(url, encode);
			}
			if(content==null)return null;
			String contentPre=gur.getContentPre();
			String contentEnd=gur.getContentEnd();
			int first=0;
			int end=content.length();
			//修改默认值为null
			if(contentPre!=null&&contentEnd!=null&&!contentPre.trim().equals("")&&!contentEnd.trim().equals("")){
				String c[] = contentPre.split("[|]");
				String c2[] = contentEnd.split("[|]");
				int s=-1;
				for (int i = 0; i < c.length; i++) {
					int j=-1;
					if(s<(j=content.indexOf(c[i],s>-1?s:0))){
						s=j+c[i].length();
					}
				}
				if (s != -1) first = s ;
				s=-1;
				for (int i = 0; i < c2.length; i++) {
					int j=content.indexOf(c2[i]);
					s=(j>s)?(s!=-1?s:j):(j!=-1?j:s);
				}
				if(s!=-1)end=s;
				content= content.substring(first,end);
			}else if((contentPre==null||contentPre.trim().equals(""))&&contentEnd!=null&&!contentEnd.trim().equals("")){
				String c2[] = contentEnd.split("[|]");
				int s=-1;
				for (int i = 0; i < c2.length; i++) {
					int j=content.indexOf(c2[i]);
					s=(j>s)?(s!=-1?s:j):(j==-1?s:j);
				}
				if(s!=-1)end=s;
				content= content.substring(first,end);
			}else if(contentPre!=null&&!contentPre.trim().equals("")&&(contentEnd==null||contentEnd.trim().equals(""))){
				String c[] = contentPre.split("[|]");
				int s=-1;
				int len=0;
				for (int i = 0; i < c.length; i++) {
					int j=-1;
					if(s<(j=content.indexOf(c[i],s>-1?s:0))){
						s=j;
						len=c[i].length();
					}
				}
				if (s != -1) first = s + len;
				content= content.substring(first,end);
			}
//			System.out.println(MyString.utf8ToGbk(content.trim()));
//			System.out.println(MyString.UTF8ToGB(content.trim()).replace("?", ""));
			if(gur.getContentDel()!=null){
				String del[]=gur.getContentDel().split("[|]");
				for (int i = 0; i < del.length; i++) {
					String dd[]=del[i].split("~");
					if(dd.length==1){
							content=content.replace(del[i], "");
					}else{
						int j=-1;
						while((j=content.indexOf(dd[0]))>=0){
							int jj=-1;
							if((jj=content.indexOf(dd[1], j))<0)break;
							content=content.substring(0,j)+content.substring(jj+dd[1].length(),content.length());
						}
					}
				}
			}
			return content.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
