package com.iatb.util.spider;


import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.iatb.bean.Webbean;
import com.iatb.pojo.Groupurlrole;
import com.iatb.util.DateUtil;
import com.iatb.util.MyString;
import com.iatb.util.SpiderUtil;
import com.iatb.util.db.ConnTools;
import com.iatb.util.http.MyHtmlCleaner;

public class SpiderLimitDaoImpl {
	public SpiderLimitDaoImpl() {
		super();
	}
	private static final Logger log=Logger.getLogger(SpiderLimitDaoImpl.class);
	private Connection conn = ConnTools.makeConnection("spider2011");
	private Groupurlrole gur;
	private boolean b=true;
	private int count=10;
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}
	public static SpiderLimitDaoImpl instance(){
		return new SpiderLimitDaoImpl();
	}
	public static void run5(){
		instance().getGroupurlRole5();
	}
	/**
	 * 获取限制分页规则
	 */
	@SuppressWarnings("unchecked")
	public void getGroupurlRole5(){
		System.out.println("开始获取抓取规则");
		List<Groupurlrole> list=ConnTools.query(conn, "select * from groupurlrole where status=5 and isNew=1",Groupurlrole.class);
		for (Groupurlrole gur : list) {
			if(count<=0)return;
			this.gur=gur;
			String url[]=((String) gur.getUrl()).split(",");
			getGroupUrl(url[0], Integer.parseInt(url[1]),  Integer.parseInt(url[2]),  Integer.parseInt(url[3]));
		}
		System.out.println("结束获取抓取规则");
	}
	/**
	 * 获取分页
	 * @param url
	 * @param pre
	 * @param end
	 * @param next
	 */
	public void getGroupUrl(String url, int pre, int end, int next) {
		String u = "";
		for (int i = pre; i <= end; i += next) {
			u = url.replace("(*)", String.valueOf(i));
			log.info("开始抓取分页：" + u);
			getUrl(u); 		// 获取url
			if(count<=0)return;	//达到一次限制抓取个数
//			System.gc();
		}
	}
	/**
	 * 获取分页里的链接
	 * @param url
	 */
	public  void getUrl(String url) {
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
					for (Map.Entry<String, String> s2 : m2.entrySet()) {
						Webbean wb=checkUrl(s.getKey()+"_"+ s2.getKey(), s2.getValue());  	//过滤webbean
						if(wb!=null)
							if(!checkWebbean(wb)){	//检查webben是否重复		(优化返回值)
								if(gur.getInnerWebs()!=null&&MyHtmlCleaner.isInnerPage(s2.getValue().trim(),gur.getEncode(),gur.getInnerWebs())){
									//获取所有内页链接
									List<String> list=MyHtmlCleaner.getLinksByWebs(s2.getValue().trim(),gur.getEncode(),gur.getInnerWebs() );
									saveContent(list,wb.getTitle());
								}else if(gur.getIsPic()==1){
									String c =getContent(wb.getTitle().trim(),wb.getUrl().trim());
									if(c!=null){
										count--;
										saveContent(wb,c);// 保存
									}
								}else{
									try {
										String c =getContent(s2.getKey().trim(), s2.getValue().trim());
										if(c!=null){
											count--;
											saveContent(wb,c);// 保存
										}									} catch (Exception e) {
										continue;
									}
								}
							}
					}
				}
			}else 
			for (Map.Entry<String, String> s : m.entrySet()) {
				Webbean wb=checkUrl(s.getKey(), s.getValue());  	//过滤webbean
				if(wb!=null)
					if(!checkWebbean(wb)){	//检查webben是否重复		//优化返回值
						if(gur.getInnerWebs()!=null&&MyHtmlCleaner.isInnerPage(s.getValue().trim(),gur.getEncode(),gur.getInnerWebs())){
							//获取所有内页链接
							List<String> list=MyHtmlCleaner.getLinksByWebs(s.getValue().trim(),gur.getEncode(),gur.getInnerWebs() );
							saveContent(list,wb.getTitle());
						}else if(gur.getIsPic()==1){
							String c =getContent(wb.getTitle().trim(),wb.getUrl().trim());
							if(c!=null){
								count--;
								saveContent(wb,c);
							}
						}else{
							try {
								String c =getContent(s.getKey().trim(), s.getValue().trim());
								if(c!=null){
									count--;
									saveContent(wb,c);
								}
							} catch (Exception e) {
								continue;
							}
						}
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 检查webben是否重复
	 * @param wb
	 * @return
	 */
	private boolean checkWebbean(Webbean wb) {
		Long i=(Long) ConnTools.queryMap(conn, "select count(1) b from content where title='"+wb.getTitle().replaceAll("'", "‘").replaceAll("\"","‘")+"'").get(0).get("b");
		return i>0?true:false;
	}
	/**
	 * 保存内页内容
	 * @param list
	 * @param title
	 */
	private void saveContent(List<String> list, String title) {
		for (int i = 0; i <list.size(); i++) {
			String c =getContent(title+"("+i+1+")",list.get(i)); //获取内容
			if(c==null){
				log.info("saveContent():	content is :null");return;
			}
			int j=c.indexOf(gur.getInnerEnd().replace("{0}",""+list.size()));
			if(j>0)c=c.substring(0,j);
			if(c.trim().equals(""))return;
			saveContent(new Webbean(i==0?title:title+"("+(i+1)+")",list.get(i)),c);// 保存
		}
	}
	/**
	 * 保存内容
	 * @param wb
	 * @param c
	 */
	private void saveContent(Webbean wb, String c) {
		try {
			if(c==null||c.trim().equals(""))return;
			String sql="insert into content (title,url,content,siteid,groupurlid,status,createTime" +
			" ) values(?,?,?,?,?,?,?)";
			Object o[]=new Object[]{wb.getTitle(),wb.getUrl(),c,gur.getSiteid(),gur.getId(),0,DateUtil.getPlusTimeDate()};
			int i=ConnTools.update(conn, sql, o);
			log.info("添加content:《"+wb.getTitle()+(i>0?"》\t成功":"》\t失败"));
		} catch (Exception e) {
//			e.printStackTrace();
			log.info("saveContent():\t"+e.getMessage());
		}
	}
	/**
	 * 获取内容
	 * @param title
	 * @param url
	 * @return
	 */
	private String getContent(String title, String url) {
		try {
			String encode=gur.getEncode();
			String webs=gur.getWebs();
			String contentUnLike=gur.getContentUnLike();
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
//			System.exit(1);
			return null;
		}
	}
	/**
	 * 过滤webbean
	 * @param title
	 * @param link
	 * @return
	 */
	public Webbean checkUrl(String title,String link){
		if(title==null||link==null||title.equals(""))return null;
		String titleLike=gur.getTitleLike();
		String titleUnLike=gur.getTitleUnLike();
		String urlLike=gur.getUrlLike();
		String urlUnLike=gur.getUrlUnLike();
		if(titleLike!=null&&!titleLike.trim().equals("")){
			String tl[]=titleLike.split("[|]");
			boolean btl=true;
			for (int i = 0; i < tl.length; i++) {
				if(title.indexOf(tl[i])>=0){btl=false;break;}
			}
			if(btl)return null;
		}
		if(urlLike!=null&&!urlLike.trim().equals("")){
			if(gur.getUrlWebs()!=null&&gur.getUrlWebs().trim().length()!=0){
				if(gur.getUrlWebs().split("[|]").length==2){
					if(!link.startsWith(urlLike.split("[|]")[1])){return null;}
				}else{
					String tl[]=urlLike.split("[|]");
					boolean btl=true;
					for (int i = 0; i < tl.length; i++) {
						if(link.indexOf(tl[i])>=0){btl=false;break;}
					}
					if(btl)return null;
				}
			}else{
				String tl[]=urlLike.split("[|]");
				boolean btl=true;
				for (int i = 0; i < tl.length; i++) {
					if(link.indexOf(tl[i])>=0){btl=false;break;}
				}
				if(btl)return null;
			}
		}
		if(urlUnLike!=null&&!urlUnLike.trim().equals("")){
			String tl[]=urlUnLike.split("[|]");
			
			boolean btl=false;
			for (int i = 0; i < tl.length; i++) {
				if(link.indexOf(tl[i])>=0){btl=true;break;}
			}
			if(btl)return null;
		}
		if(titleUnLike!=null&&!titleUnLike.trim().equals("")){
			String tl[]=titleUnLike.split("[|]");
			boolean btl=false;
			for (int i = 0; i < tl.length; i++) {
				if(title.indexOf(tl[i])>=0){btl=true;break;}
			}
			if(btl)return null;
		} 
		title=MyString.decode(title);
		return new Webbean(title,link);
	}
	/**
	 * 添加GroupurlRole
	 * @param gur
	 */
	public void addGroupurlRole(Groupurlrole gur){
		String sql="insert into groupurlrole (name,url,encode,titleLike,titleUnLike," +
				"urlLike,urlUnLike,webs,contentPre,contentEnd,contentUnLike, isNew,siteId,createTime,status" +
				" ) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object o[]=new Object[]{gur.getName(),gur.getUrl(),gur.getEncode(), gur.getTitleLike(),gur.getTitleUnLike(),gur.getUrlLike(),
				gur.getUrlUnLike(), gur.getWebs(),gur.getContentPre(),gur.getContentEnd(),gur.getContentUnLike(),
				gur.getIsNew(),gur.getSiteid(),gur.getCreateTime(),gur.getStatus()};
		int i=ConnTools.update(conn, sql, o);
		log.info("添加groupurlRole:"+(i>0?"\t成功":"\t失败"));
	}
	/**
	 * 获取编码
	 * @param url
	 * @return
	 */
	public String getencode(String url) {
		return	gur.getEncode();
	}
	public static void main(String[] args) {
		run5();
	}
}
