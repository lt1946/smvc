package com.iatb.util.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.htmlparser.jericho.Source;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.iatb.bean.Webs;
import com.iatb.util.MyString;

/**
 * @author Administrator
 *
 */
public class MyHtmlCleaner1 {
	private final List<TagNode> TagNodes=new ArrayList<TagNode>();
	/**
	 * 获取所有的链接
	 * @param url
	 * @since @MyFrameworker@com.util.http.MyHtmlCleaner
	 * @haschange
	 */
	public static Map<String, String> getAllLink(String url,String charset) { 
		 	try {
		 		Map<String, String> map=new HashMap<String, String>();
				HtmlCleaner cleaner = new HtmlCleaner();    
				URL _url = new URL(url);
				TagNode node = cleaner.clean(_url,charset);    
				Object[] ns =  node.evaluateXPath("//a"); 
				for (int i = 0; i < ns.length; i++) { 
					TagNode n = (TagNode) ns[i];
					String u=n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString().trim();
					if(u.equals("")||n.getText().toString().trim().equals(""))continue;
					map.put(n.getText().toString().trim(), MyString.getUrlWithHost(url,u ));
				}
				return map;
			} catch (MalformedURLException e) { 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			} catch (XPatherException e) { 
				e.printStackTrace();
			}
		return null;
	}
	/**
	 * 获取所有的链接
	 * @param url
	 */
	public static Map<String, String> getAllLink(String url,String charset,Webs titlewebs) { 
		 	try {
		 		Map<String, String> map=new HashMap<String, String>();
				HtmlCleaner cleaner = new HtmlCleaner();    
				URL _url = new URL(url);
				TagNode node = cleaner.clean(_url,charset);    
				Object[] ns =  node.evaluateXPath("//a"); 
				for (int i = 0; i < ns.length; i++) { 
					TagNode n = (TagNode) ns[i];
					String u=n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString().trim();
					if(u.equals("")||n.getText().toString().trim().equals(""))continue;
					if(titlewebs==null)
						map.put(n.getText().toString().trim(), MyString.getUrlWithHost(url,u ));
					else{
						if(u.endsWith("html")){
							TagNode tn1 =n.findElementByAttValue(titlewebs.getAttribute(),titlewebs.getValue(),true,true);
							if(tn1==null)continue;
							map.put(tn1.getText().toString().trim(), MyString.getUrlWithHost(url,u ));
						}
					}
				}
				return map;
			} catch (MalformedURLException e) { 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			} catch (XPatherException e) { 
				e.printStackTrace();
			}
		return null;
	}
	public static String getEncode(String url) {
		try {
			if (url==null||url.trim().length() == 0)
				return null;
			if(!url.startsWith("http"))url="http://"+url;
			return new Source(new URL(url)).getEncoding();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return null;
	}
	public static String getTitle(String url,String charset) {
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			if (url==null||url.trim().length() == 0)
				return null;
			if(!url.startsWith("http"))url="http://"+url;
			TagNode node = cleaner.clean(new URL(url),charset);
			Object[] ns = node.getElementsByName("title", true);    //标题
			return ((TagNode)ns[0]).getText().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public   Map<String,String>  getAllLinkByRange(String url,String charset,Webs w,boolean isInner){
		try {
	 		Map<String, String> map=new HashMap<String, String>();
			HtmlCleaner cleaner = new HtmlCleaner();
			if (url==null||url.trim().length() == 0)
				return null;
			if(!url.startsWith("http"))url="http://"+url;
			URL _url = new URL(url);
			int fi=url.indexOf("://")+3;
			int li=url.indexOf("/",fi)<0?url.length():url.indexOf("/",fi);
			url=url.substring(fi,li );
			url=url.startsWith("www.")? url.substring(4):url.split("\\.").length>2? url.substring(url.indexOf(".")+1): url;
			TagNode node = cleaner.clean(_url,charset);
			Object[] ns =new Object[] {};
			if(w==null)ns=node.evaluateXPath("//a");
			else {
				if(w.getAttribute()==null||w.getAttribute().trim().equals(""))
				{
					ns = node.evaluateXPath("//"+w.getTag());
				}else {
					ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");
				}
			}
			if(isInner) {
				for (int i = 0; i < ns.length; i++) {
					TagNode n = (TagNode) ns[i];
					if(w!=null) {
						List<TagNode> tns=	new MyHtmlCleaner1().GetTagNodeList(n);
						for (TagNode tn : tns) {
							Object  o=tn.getAttributes().get("href");
							if(o==null||o.toString().trim().equals("")) {
								continue;
							}else {
								map.put(MyString.cleanSpans(tn.getText().toString()),MyString.cleanSpans(o.toString()));
							}
						}
					}else {
						Object  o=n.getAttributes().get("href");
						if(o==null||o.toString().trim().equals("")) {
							continue;
						}else {
							map.put(MyString.cleanSpans(n.getText().toString()),MyString.cleanSpans(o.toString()));
						}
					}
				}
			}else {
				for (int i = 0; i < ns.length; i++) {
					TagNode n = (TagNode) ns[i];
					if(w!=null) {
						List<TagNode> tns=	new MyHtmlCleaner1().GetTagNodeList(n);
						for (TagNode tn : tns) {
							Object  o=tn.getAttributes().get("href");
							if(o==null||o.toString().trim().equals("")||o.toString().trim().indexOf("?")>0||n.getText().toString().trim().equals("")||o.toString().trim().indexOf("www.miibeian")>0||o.toString().indexOf(url)>0||o.toString().indexOf(".")<0||!o.toString().startsWith("http")||o.toString().startsWith("/")||o.toString().indexOf(")")>0) {
								continue;
							}else {
								map.put(MyString.cleanSpans(tn.getText().toString()),MyString.cleanSpans(o.toString()));
							}
						}
					}else {
						Object  o=n.getAttributes().get("href");
						if(o==null||o.toString().trim().equals("")||o.toString().trim().indexOf("?")>0||n.getText().toString().trim().equals("")||o.toString().trim().indexOf("www.miibeian")>0||o.toString().indexOf(url)>0||o.toString().indexOf(".")<0||!o.toString().startsWith("http")||o.toString().startsWith("/")||o.toString().indexOf(")")>0) {
							continue;
						}else {
							map.put(MyString.cleanSpans(n.getText().toString()),MyString.cleanSpans(o.toString()));
						}
					}
				}
			}
			return map;
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (XPatherException e) {
		}
		return null;
	}
	public  List<TagNode> GetTagNodeList(TagNode tn) {
		TagNode[]	td=tn.getChildTags();
		if(td.length==0)return null;
		for (int j = 0; j < td.length; j++) {
			TagNodes.add(td[j]);
				GetTagNodeList(td[j]);
		}
		return TagNodes;
	}
	public static String getContent(String url,String charset,List<Webs> list,boolean isHtml) {
		StringBuffer sb=new StringBuffer();
		if(url==null||charset==null||url.trim().equals("")||charset.trim().equals(""))return null;
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);
			if(list==null) {
				if(isHtml)
					return node.getText().toString();
				else
					return cleaner.getInnerHtml(node);
			}
			for (Webs w : list) {
				Object[] ns =new Object[] {};
				if(w.getAttribute()==null||w.getAttribute().trim().equals(""))
				{
					ns = node.evaluateXPath("//"+w.getTag());
				}else {
					ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");
				}
				if(isHtml) {
					for (int i = 0; i < ns.length; i++) {
//						sb.append(cleaner.getInnerHtml((TagNode)ns[i]));
//						sb.append(((TagNode)ns[i]).getOriginalSource());
						sb.append(((TagNode)ns[i]).getAttributeByName("src"));
						
					}
				}else {
					for (int i = 0; i < ns.length; i++) {
						sb.append(((TagNode)ns[i]).getText().toString());
					}
				}
			}
		} catch (MalformedURLException e) {
			return null;
//			e.printStackTrace();
		} catch (IOException e) {
			return null;
//			e.printStackTrace();
		} catch (XPatherException e) {
			return null;
//			e.printStackTrace();
		}
		return sb.toString();
	}
	public static String getOutLink(String url,String charset,String split) {
		StringBuffer sb=new StringBuffer();
		if(url==null||charset==null||url.trim().equals("")||charset.trim().equals(""))return null;
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);
			Object[] ns =new Object[] {};
			ns = node.evaluateXPath("//a");
			for (int i = 0; i < ns.length; i++) {
				sb.append(((TagNode)ns[i]).getText().toString()).append("_").append(((TagNode)ns[i]).getAttributeByName("href")).append(split);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getContent2(String url,String charset,List<Webs> list,boolean isHtml,String split) {
		StringBuffer sb=new StringBuffer();
		if(url==null||charset==null||url.trim().equals("")||charset.trim().equals(""))return null;
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);
			if(list==null) {
				if(isHtml)
					return node.getText().toString();
				else
					return cleaner.getInnerHtml(node);
			}
			for (Webs w : list) {
				Object[] ns =new Object[] {};
				if(w.getAttribute()==null||w.getAttribute().trim().equals(""))
				{
					ns = node.evaluateXPath("//"+w.getTag());
				}else {
					ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");
				}
				if(isHtml) {
					for (int i = 0; i < ns.length; i++) {
						sb.append(cleaner.getInnerHtml((TagNode)ns[i])).append(split);
					}
				}else {
					for (int i = 0; i < ns.length; i++) {
						sb.append(((TagNode)ns[i]).getText().toString()).append(split);
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
//	public static String getContent(String url,String charset,List<Webs> list,boolean isHtml,String attribute) {
//		StringBuffer sb=new StringBuffer();
//		if(url==null||charset==null||url.trim().equals("")||charset.trim().equals(""))return null;
//		try {
//			HtmlCleaner cleaner = new HtmlCleaner();
//			TagNode node = cleaner.clean(new URL(url),charset);
//			if(list==null) {
//				if(isHtml)
//					return node.getText().toString();
//				else
//					return cleaner.getInnerHtml(node);
//			}
//			for (Webs w : list) {
//				Object[] ns =new Object[] {};
//				if(w.getAttribute()==null||w.getAttribute().trim().equals(""))
//				{
//					ns = node.evaluateXPath("//"+w.getTag());
//				}else {
//					ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");
//				}
//				if(isHtml) {
//					for (int i = 0; i < ns.length; i++) {
////						sb.append(cleaner.getInnerHtml((TagNode)ns[i]));
//						if(attribute==null||attribute.trim().equals("")) {
//							sb.append(((TagNode)ns[i]).getOriginalSource());
//						}else {
//							sb.append(((TagNode)ns[i]).getAttributeByName(attribute));
//						}
//					}
//				}else {
//					for (int i = 0; i < ns.length; i++) {
//						if(attribute==null||attribute.trim().equals("")) {
//							sb.append(((TagNode)ns[i]).getText().toString());
//						}else {
//							sb.append(((TagNode)ns[i]).getAttributeByName(attribute));
//						}
//					}
//				}
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (XPatherException e) {
//			e.printStackTrace();
//		}
//		return sb.toString();
//	}
	public static void main(String[] args) {
		try {
	 		Webs w=new Webs("strong","class","vtitle");
			String s="<a class='hotspot' href='http://www.realgfporn.com/4102/The_worlds_hottest_usersubmitted_girlfriend_porn.html'><img alt='' src='http://www.realgfporn.com/thumbs/37c7l7k.jpg' /><span class='det'><em>views 83984</em><strong class='rating r4'>rating 4</strong></span><strong class='vtitle'>The world&#039;s hottest user-submitted girlfriend porn! 2 2 2</strong></a>";
			HtmlCleaner hc=new HtmlCleaner();
			TagNode tn=hc.clean(s);
//			Object o[]=tn.evaluateXPath("//strong");
//			System.out.println(o.length);
			TagNode ns =	tn.findElementByAttValue(w.getAttribute(),w.getValue(),true,true);
			System.out.println(ns.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取源码中的字符串
	 * @param url
	 * @param encode
	 * @param start
	 * @param end
	 * @return
	 */
	public static String getStringFromHtml(String url,String encode,String start,String end) {
		if(!url.trim().startsWith("http")){
			url = "http://"+url;
		}
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url.trim()); 
		HttpResponse response;
		String  r = null;
		try {
			response = client.execute(httpget);
			if(null != response){
				HttpEntity entity = response.getEntity();
				r  = EntityUtils.toString(entity ,encode);
				httpget.abort();
				client.getConnectionManager().shutdown(); 
				int si = r.indexOf(start) + start.length();
				if (si > start.length()) {
					return r.substring(si,r.indexOf(end, si));
				}
			}
		} catch (Exception e) { 
			return null;
		}
        return r;
	}
}
