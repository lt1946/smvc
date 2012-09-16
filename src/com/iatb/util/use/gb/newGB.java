package com.iatb.util.use.gb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class newGB {
	
	public static final String url="http://www.tuan800.com/sites";
	//获取城市
	public static List<String> getCitys(){
		try{
			HtmlCleaner hc=new HtmlCleaner();
			TagNode tn= hc.clean(new URL(url),"utf-8");
			List<String> ls=new ArrayList<String>();
			Object o[]=tn.evaluateXPath("//div[@class]");
			for (int i = 0; i < o.length; i++) {
				String cl=((TagNode)o[i]).getAttributeByName("class");
				if(cl!=null&&!cl.trim().equals("category_sites")&&cl.trim().startsWith("category_sites"))
					{
						String english=cl.trim();
						ls.add(english);
//						String name=((TagNode)((TagNode)o[i]).evaluateXPath("//h3[@class='title']")[0]).getText().toString().replace("地区团购网站导航", "");
						//todo 保存数据库
					}
			}
			return ls;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	//获取网址,网站名。
	public static void getSites(List<String> citys){
		try {
			Map<String, String> map = new HashMap<String, String>();
			for(String c:citys){
				HtmlCleaner cleaner=new HtmlCleaner();
				TagNode node=cleaner.clean(new URL(url),"utf-8");
				Object[] ns = new Object[] {};
				ns = node.evaluateXPath("//div[@class='"+c+"']");
				node = cleaner.clean(cleaner.getInnerHtml((TagNode)ns[0]));
				ns=node.evaluateXPath("//a");
				for (int i = 0; i < ns.length; i+=2) {
					if((((TagNode)ns[i]).getAttributeByName("href")!=null)&&((TagNode)ns[i]).getAttributeByName("href").startsWith("http://www.tuan800.com/out/jump-to/")){
						try {
							map.put(((TagNode) ns[i]).getText().toString().trim(), ((TagNode) ns[i])
									.getAttributeByName("href").trim()+"::"+((TagNode) ns[i+1])
									.getAttributeByName("href").trim());
							String url=((TagNode) ns[i])
							.getAttributeByName("href").trim();
							System.out.println( url);
							HttpClient hc=new HttpClient();
							GetMethod get=new GetMethod(url);
							int code=hc.executeMethod(get);
							if(code==200){
								Header h=get.getResponseHeader("Location");
								String redirectUrl=h.getValue();
								System.out.println(redirectUrl);
							}else{
								System.out.println("获取网址有误");
							}
							get.releaseConnection();
							System.exit(1);
							//TODO 获取网站相关信息：getSiteMsg();(6)
							//TODO 保存到数据库。(6)
						} catch (Exception e) {
							continue;
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取网站相关信息
	public static void getSiteMsg( ){
		String SiteMsgurl="http://www.tuan800.com/tuangou/jigocity";
//		SiteMsg sm=new SiteMsg();   //TODO SiteMsg sm
		HtmlCleaner hc=new HtmlCleaner();
		try {
			TagNode tn=hc.clean(new URL(SiteMsgurl),"utf-8");
			try {
				Object[] o=tn.evaluateXPath("//div[@class='site_info_content']");
				for (int i = 0; i < o.length; i++) {
					String s=((TagNode)o[i]).getText().toString().trim();
					if(s.equals(""))continue;
					else{
						if(s.startsWith("上线时间")){
							int type=s.indexOf("购买类型");
							System.out.println(s.substring(5,type));
						}else if(s.startsWith("&nbsp;")){
							int m=s.indexOf("查看更多集购城新闻");
							System.out.println(s.substring(24,m));
						}else{
							int qq=s.indexOf("QQ");
							int tel=s.indexOf("电话");
							int address=s.indexOf("地址");
							System.out.println(s.substring(6,qq)+"\n"+s.substring(qq+3,tel)+"\n"+s.substring(tel+3,address)+"\n"+s.substring(address+3,s.length()));
						}
					}
//					System.out.println(((TagNode)o[i]).getText());
					//TODO 过滤特殊字符。（gb10）
				}
				//TODO 返回SiteMsg类。（gb10）
			} catch (XPatherException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
//		List<String> cl=getCitys();
//		getSites(cl);
//		getSiteMsg();
		try {
			HttpClient hc=new HttpClient();
			hc.getHostConfiguration().setHost("http://donatino.skygate.cn/post/21/10897", 80);
			GetMethod get = new GetMethod("http://donatino.skygate.cn/post/21/10897");
			Cookie[] cookies = hc.getState().getCookies();
			get.setRequestHeader("Cookie",cookies.toString());
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			int code = hc.executeMethod(get);
			if(code==200){
				if(get.getURI().toString().trim().equals("http://www.tuan800.com/out/jump-to/1")){
					try {
						HtmlCleaner cleaner=new HtmlCleaner();
						TagNode tn=cleaner.clean(get.getResponseBodyAsStream());
						Object []o=tn.evaluateXPath("//a");
						System.out.println(((TagNode)o[0]).getAttributeByName("href"));
						
					} catch (XPatherException e) {
						e.printStackTrace();
					}
					
				}else{
					System.out.println(get.getURI());
				}
			}else{
				System.out.println("获取网址有误");
			}
			get.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
