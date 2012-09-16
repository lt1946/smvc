package com.iatb.services.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.iatb.dao.GroupurlroleDao;
import com.iatb.dao.WbcontentDao;
import com.iatb.dao.WebbeanDao;
import com.iatb.pojo.Groupurlrole;
import com.iatb.pojo.Wbcontent;
import com.iatb.pojo.Webbean;
import com.iatb.services.SpiderConentService;
import com.iatb.util.SpiderUtil;
import com.iatb.util.http.MyHtmlCleaner;

@Service("spiderContentService")
public class SpiderConentServiceImpl implements SpiderConentService {

	protected static Logger logger = Logger.getLogger(SpiderConentServiceImpl.class);

	@Autowired
	private GroupurlroleDao groupurlroleDao;
	@Autowired
	private WebbeanDao webbeanDao;
	@Autowired
	private WbcontentDao wbcontentDao;
	
	public void spiderNormalContent() {
		logger.info("开始抓取普通规则的内容");
		List<Groupurlrole> list=groupurlroleDao.getNormalGur();
		for (Groupurlrole gur : list) {
			List<Webbean> wblist=webbeanDao.getList("select * from webbean where gurid=? and status=0", Webbean.class, new Object[]{gur.getId()});
			for (Webbean wb : wblist) {
					if(gur.getIsPic()==1){
						boolean sb=saveContentPic(gur,wb);
						if(sb){
							int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
							logger.info("置状态1，wb——id："+wb.getId()+(i>0?"成功":"失败"));
						}
					}else{
						try {
							if(gur.getInnerWebs()!=null&&MyHtmlCleaner.isInnerPage(wb.getUrl().trim(),gur.getEncode(),gur.getInnerWebs())){
								//抓取内页起始页
								String c =getContent(gur,wb.getTitle(),wb.getUrl());
								if(c!=null&&!c.trim().equals("")){
									if(c.equals("404")){
										int k=webbeanDao.execute("update webbean set status=-4 where id=?", new Object[]{wb.getId()});
										logger.info("置起始内页状态-4："+wb.getId()+(k>0?"成功":"失败"));
										continue;
									}
									boolean sb=saveContent(gur,wb,c);
									logger.info("保存内页起始页，wb——id内容："+wb.getId()+(sb?"成功":"失败"));
									if(!sb)continue;
								}else{
									logger.info("抓取内页起始页wb——id内容："+wb.getId()+"为空！");
									int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
									logger.info("置状态-1，抓起wb——id内容为空或 异常："+wb.getId()+(i>0?"成功":"失败"));
									continue;
								}
								//获取所有内页链接
								List<String> lists=MyHtmlCleaner.getLinksByWebs(wb.getUrl().trim(),gur.getEncode(),gur.getInnerWebs() );
								saveInnerContent(gur,lists,wb);
							}else{
								String c =getContent(gur,wb.getTitle(),wb.getUrl());
								if(c!=null&&!c.trim().equals("")){
									if(c.equals("404")){
										int k=webbeanDao.execute("update webbean set status=-4 where id=?", new Object[]{wb.getId()});
										logger.info("置起始内页状态-4："+wb.getId()+(k>0?"成功":"失败"));
										continue;
									}
									boolean sb=saveContent(gur,wb,c);
									if(sb){
										int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
										logger.info("置状态1，wb——id："+wb.getId()+(i>0?"成功":"失败"));
									}
								}else{
									logger.info("抓取wb——id内容："+wb.getId()+"为空！");
									int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
									logger.info("置状态-1，抓起wb——id内容为空或 异常："+wb.getId()+(i>0?"成功":"失败"));
								}
							}
						} catch (Exception e) {
							logger.info("抓取wb——id内容："+wb.getId()+"异常！");
							int i=webbeanDao.execute("update webbean set status=-1 where id=?", new Object[]{wb.getId()});
							logger.info("置状态-1，抓起wb——id内容异常："+wb.getId()+(i>0?"成功":"失败"));
							continue;
						}
					}
			}
		}
	}
	/**
	 * 保存内页内容
	 * @param list
	 * @param title
	 */
	private boolean saveInnerContent(Groupurlrole gur,List<String> list, Webbean wb) {
		for (int i = 0; i <list.size(); i++) {
			String title=wb.getTitle()+"("+(i+1)+")";
			String url=list.get(i);
			int wbid=0;
			Webbean wb1= webbeanDao.get("select * from webbean where title=? and url=? ", Webbean.class, new Object[]{title,url});
			if(wb1!=null){
				if(wb1.getStatus()==1)continue;	//存在继续
				wbid=wb1.getId();
			}else{
				wbid=webbeanDao.addReturnId(new Webbean(gur.getId(),wb.getId(), title, url));
				if(wbid<1)return false;		//保存内页失败，退出。
			}
			String c=getContent(gur,title,url); 	//获取内页内容
			if(c==null){
				logger.info("保存wb——id_第"+(i+1)+"页_内页内容为空！");return false;	//获取内页内容失败，退出。
			}
			int j=c.indexOf(gur.getInnerEnd().replace("{0}",""+list.size()));
			if(j>0)c=c.substring(0,j);
			if(c.trim().equals(""))return false;	//获取内页内容为空，退出。
			Webbean w=new Webbean(/*i==0?wb1.getTitle():*/title,url);
			w.setId(wbid);
			boolean b=saveContent(gur,w,c);// 保存
			logger.info("保存wb——id_内页内容："+wb.getId()+(b?"成功":"失败"));
			if(b){
				int iw=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wbid});
				logger.info("置内页状态1，wb.getId()："+wbid+(iw>0?"成功":"失败"));
				if(i==list.size()-1){
					int k=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
					logger.info("置起始内页状态1："+wb.getId()+(k>0?"成功":"失败"));
					return true;
				}
//				if(iw<1)return false;				//更新内页webbean失败，退出。
			}else{
				return false;	//保存内页内容失败，退出。
			}
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
			if(content.equals("404")){
				return content;
			}
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
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存内容
	 * @param wb
	 * @param c
	 */
	private boolean saveContent(Groupurlrole gur,Webbean wb, String c) {
		try {
			if(c==null||c.trim().equals(""))return false;
			Wbcontent cc=new Wbcontent();
			cc.setContent(c);
			cc.setCreateTime();
			cc.setStatus(0);
			cc.setWbid(wb.getId());
			String s=wbcontentDao.add(cc);
			logger.info("添加content:《"+wb.getTitle()+(s.indexOf("成功")>=0?"》\t成功":"》\t失败"));
		} catch (DuplicateKeyException e) {
			logger.info("saveContent():\ttitle:<"+wb.getTitle()+">_已经存在!");
			int i=webbeanDao.execute("update webbean set status=1 where id=?", new Object[]{wb.getId()});
			logger.info("更改已经存在的wb——id内容："+wb.getId()+(i>0?"成功":"失败"));
			return false;
		}catch (Exception e) {
			logger.info("saveContent():\t"+e.getMessage());
			return false;

		}
		return true;
	}
}
