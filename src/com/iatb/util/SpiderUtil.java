package com.iatb.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.iatb.bean.Webs;
import com.iatb.util.http.MyHtmlCleaner;
import com.iatb.util.http.MyHttp2;

public class SpiderUtil {
	
	/**
	 * 获取纯文本内容
	 * @param url
	 * @param charset
	 * @param tag
	 * @param attribute
	 * @param value
	 * @return
	 */
	public static String getContentParser(String url,String charset,String webs,int b,boolean image) {
		String webs2[]=webs.split("[|]");
		for (int i = 0; i < webs2.length; i++) {
			String w[]=webs2[i].split(",");
			String content= "";
			try {
				HtmlCleaner cleaner = new HtmlCleaner();
				TagNode node=null;
				
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url.trim()); 
				HttpResponse response;
				String  r = null;
				response = client.execute(httpget);
				if(null != response){
					if(response.getStatusLine().toString().indexOf("404")>=0){
						return "404";
					}
					HttpEntity entity = response.getEntity();
					r  = EntityUtils.toString(entity ,charset);
					httpget.abort();
					client.getConnectionManager().shutdown(); 
				}
				if(b==1){
					 if(url.indexOf("51cto.com/")>=0){
						r=r.replaceAll("</PRE>", "</pre>").replaceAll("<PRE>", "<pre>").replaceAll("<LI", "<li").replaceAll("</LI>", "</li>").replaceAll("SPAN><li", "SPAN></li><li");
						r=c(r,-2,-2,3);
					}
				}
				//扩展
				if(url.startsWith("http://www.oschina.net/")){
					r=r.replaceAll("<br />", "<br />\n");		
	//					r=c(r,-2,-2,8);
				}else if(url.indexOf(".ifensi.com/")>=0){
					r=r.replaceAll("</p>", "</p>\n");	
				}
				if(image){
					r=r.replaceAll("<img", "###img");
//					int imgindex=-1;
//					String c="";
//					while((imgindex=r.indexOf("src",r.indexOf(w[2]))+4)>=4){
//						c=r.substring(imgindex,imgindex+1);
//						String imgurl=r.substring(imgindex+1,r.indexOf(c, imgindex+2));
//						String imgurlnew=MyString.getUrlWithHost(url, imgurl);
//						r.replace(imgurl, imgurlnew);
//					}
				}
				node= cleaner.clean(r.trim());  
				Object[] ns = node.evaluateXPath("//"+w[0]+"[@"+w[1]+"=\""+w[2]+"\"]");
				if(ns.length==0){
					if(i==webs.length()-1)
					return null;
					else continue;
				}
				content= MyString.decode(((TagNode)ns[0]).getText().toString().trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return content.trim();
		}
		return null;
	}
	/*public static List<String> getPics(String url,){
		List<String> list=new ArrayList<String>();
		
		return list;
	}*/
	//转换代码格式
	public static String c(String r,int i,int j,int siteid){
		i=i>=0?i:r.indexOf("<pre");  if(i<0)return r;
		j=j>=0?j:r.indexOf("</pre>",i);  if(j<0)return r;
		String code=r.substring(i,j+6);
		String c="";
		switch(siteid){
			case 3:c=code.replaceAll("</li>", "</li>\n");break;
			case 8:c=code.replaceAll("<br />", "<br />\n");break;
		}
		r=r.replace(code, c);
		if((i=r.indexOf("<pre",j+6))>=0){
			j=r.indexOf("</pre>",i);if(j<0)return r;
			return c(r,i,j,siteid);
		}
		return r;
	}
	public static void main(String[] args) {
	/*	String s="<li class=\"alt\"><span>";
		s=s.replaceAll("/<li(*)>/", "<li(*)>\n");
		System.out.println(s);*/
//		p();
		/*try {
			String r=FileUtils.readFileToString(new File("C:\\Documents and Settings\\Administrator\\桌面\\1"),"utf-8");
			r=r.replaceAll("</PRE>", "</pre>").replaceAll("<PRE>", "<pre>").replaceAll("<LI", "<li").replaceAll("SPAN><li", "SPAN></li><li");
			r=c(r,-2,-2);
			HtmlCleaner cleaner=new HtmlCleaner();
			TagNode tn=cleaner.clean(r);
			System.out.println(MyString.decode(tn.getText().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
//		get();
		testgetPage();
	}
	public static void get(){
		String url="http://www.docin.com/app/my/docin/myBook?folderId=0";
		String cookie="IBW_UserRandom=65; JSESSIONID=C1B44D435BF08DC6AAAB127F443841EE.tomcat2; docin_session_id=09a4723f-b83f-4aac-a4ba-89e1aad8346d; __utmc=140771623; cookie_id=C4A59C5541A00001D19B1F5B1B641572; time_id=201124223337; __utma=140771623.353948671.1296830018.1299129754.1299204513.13; __utmz=140771623.1296830018.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); login_email=lt1946; user_password=j9xheGZj7afc%2BH3OsE4MTg9N9oYhC%2FZtH_TuYVIulj9uOG5oxHH%2FfBF1v6PLLiEdYrHPps9HxtcCGbmdidmHSufoA%3D%3D; __utmb=140771623.10.10.1299204513; today_first_in=1";
		MyHttp2.get(url, cookie, true);
		
	}
	//test
	public static void p(){
		/*try {
			String url="http://developer.51cto.com/art/201012/237863.htm";
			String charset="gbk";
			Webs webs=new Webs("div","class","container");
			String r=IOUtil.readUrl(url, charset);
			String c=c(r,-2,-2);
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(c);    
			Object[] ns = node.evaluateXPath("//"+webs.getTag()+"[@"+webs.getAttr()+"=\""+webs.getValue()+"\"]");  
			System.out.println(MyString.decode( ((TagNode)ns[0]).getText().toString().trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void cleanHtml(String htmlurl, String xmlurl) { 
        try { 
            long start = System.currentTimeMillis(); 
            HtmlCleaner cleaner = new HtmlCleaner(); 
            CleanerProperties props = cleaner.getProperties(); 
            props.setUseCdataForScriptAndStyle(true); 
            props.setRecognizeUnicodeChars(true); 
            props.setUseEmptyElementTags(true); 
            props.setAdvancedXmlEscape(true); 
            props.setTranslateSpecialEntities(true); 
            props.setBooleanAttributeValues("empty"); 
            TagNode node = cleaner.clean(new File(htmlurl),"utf-8"); 
            System.out.println("vreme:" + (System.currentTimeMillis() - start)); 
            new PrettyXmlSerializer(props).writeXmlToFile(node, xmlurl,"utf-8"); 
            System.out.println("vreme:" + (System.currentTimeMillis() - start)); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 

	public static void parser(String[] args) {
		String url="http://developer.51cto.com/art/201006/206797_1.htm";
		String charset="gbk";
		Webs webs=new Webs("div","class","container");
		Webs webs2=new Webs("ol","class","dp-xml");
		StringBuffer sb=new StringBuffer();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);    
			Object[] ns = node.evaluateXPath("//"+webs.getTag()+"[@"+webs.getAttribute()+"=\""+webs.getValue()+"\"]");  
			if(webs2!=null){
				for (int i = 0; i < ns.length; i++) {
					TagNode tn=(TagNode)ns[i];
					TagNode[] tns=tn.getAllElements(false);
					for (int j = 0; j < tns.length; j++) {
						String s="";
						if(tns[j].getName().equals("span")&&tns[j].getParent().getName().equals("li")){
							s+=MyString.decode((tns[j].getText().toString().trim()))+"\n";		
						}else{
							if(s.equals(""))sb.append(s); sb.append(MyString.decode((tns[j].getText().toString().trim())));
						}
					}
				}
			}else{
				for (int i = 0; i < ns.length; i++) {
					sb.append(MyString.decode(((TagNode)ns[i]).getText().toString().trim()));
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
	}
	public static void testgetPage(){
		String url="http://book.51cto.com";
		String webs="div,class,subNav2";
		String urlLike="http://book.51cto.com/col/";
		String pageWebs="div,class,pages";
		Map<String, String> m = MyHtmlCleaner.getAllLink(url, "gbk",webs);
		for (Map.Entry<String, String> s : m.entrySet()) {
			System.out.println(s.getKey()+":"+s.getValue());
			if(s.getValue().indexOf(urlLike)>=0){
				Map<String, String> m2 = new HashMap<String, String>();
				int i=0;
				String u="";
				while(true){
					String uu="";
					if(m2.size()==0)m2 = MyHtmlCleaner.getAllLink(s.getValue(), "gbk",pageWebs);
					for (Map.Entry<String, String> s2 : m2.entrySet()) {
						if(s2.getValue().indexOf(urlLike)>=0){
							try {
								int j=Integer.parseInt(s2.getKey());
								if(j>i){
									i=j;uu=s2.getValue();
								}
							} catch (Exception e) {
								
							}
						}
					}
					if(u.equals(uu)||uu.equals("")){
						System.out.println(u);
						break;
					}
					else{
						m2 = MyHtmlCleaner.getAllLink(uu, "gbk",pageWebs);
						u=uu;
					}
				}
			}
		}
	}
	public static String getContentParser(String url, String encode,
			int hasCode, boolean b) {
			String content= "";
			try {
				DefaultHttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(url.trim()); 
				HttpResponse response;
				String  r = null;
				response = client.execute(httpget);
				if(null != response){
					HttpEntity entity = response.getEntity();
					r  = EntityUtils.toString(entity ,encode);
					httpget.abort();
					client.getConnectionManager().shutdown(); 
				}
				if(hasCode==1){
					 if(url.indexOf("51cto.com/")>=0){
						r=r.replaceAll("</PRE>", "</pre>").replaceAll("<PRE>", "<pre>").replaceAll("<LI", "<li").replaceAll("</LI>", "</li>").replaceAll("SPAN><li", "SPAN></li><li");
						r=c(r,-2,-2,3);
					}
				}
				//扩展
				if(url.startsWith("http://www.oschina.net/")){
					r=r.replaceAll("<br />", "<br />\n");		
	//					r=c(r,-2,-2,8);
				}else if(url.indexOf(".ifensi.com/")>=0){
					r=r.replaceAll("</p>", "</p>\n");	
				}
				if(b){
					r=r.replaceAll("<img", "###img");
				}
				content= MyString.decode(r.trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return content;
	}
}
