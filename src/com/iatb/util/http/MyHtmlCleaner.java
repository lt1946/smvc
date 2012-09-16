package com.iatb.util.http;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import net.htmlparser.jericho.Source;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.w3c.dom.Document;

import com.iatb.bean.Webs;
import com.iatb.util.MyString;

import cpdetector.io.ASCIIDetector;
import cpdetector.io.CodepageDetectorProxy;
import cpdetector.io.JChardetFacade;
import cpdetector.io.ParsingDetector;
import cpdetector.io.UnicodeDetector;

/**
 * ��ҳ������
 * @author TaoLong
 * @time 2010-04-10
 */
public class MyHtmlCleaner {
	private static final Logger  log=Logger.getLogger(MyHtmlCleaner.class);
	private static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();
	/**
	 * �ڵ��б�
	 */
	private List<String> Nodetagsset=new ArrayList<String>();
	/**
	 * 0.�Զ�̽��ҳ����룬������������ĳ���
	 * @param url
	 * @return
	 */
	public static String autoDetectCharset(String url) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/**
		 * ParsingDetector�����ڼ��HTML��XML���ļ����ַ����ı��� ���췽���еĲ�������ָʾ�Ƿ���ʾ̽����̵���ϸ��Ϣ
		 * Ϊfalse����ʾ
		 */
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		try {
			charset = detector.detectCodepage(new URL(url));
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		if (charset == null)
			charset = Charset.defaultCharset();
		return charset.name();
	}
	/**
	 * 1.��ȡ��ҳ�б�
	 * @param url	��ҳ��ʽ��
	 * @param pre	�׸����֡�
	 * @param sub	ĩβ���֡�
	 * @param next	�������֡�
	 * @return
	 */
	public static List<String> getPageList(String url,int pre,int sub,int next){
		List<String> list=new ArrayList<String>(); 
		for (int j = pre; j <=sub; j+=next) {
			list.add(url.replace("(*)",String.valueOf(j))); 
		}
		return list; 
	}
	/**
	 * 1.2.��ȡxml
	 * @param url
	 * @param outFile
	 */
	@SuppressWarnings("deprecation")
	public static String getXml(String url,String encode) {
		 try {
			 	HtmlCleaner cleaner = new HtmlCleaner();  
			    CleanerProperties props = cleaner.getProperties();  
			    props.setUseCdataForScriptAndStyle(true);  
			    props.setRecognizeUnicodeChars(true);  
			    props.setUseEmptyElementTags(true);  
			    props.setAdvancedXmlEscape(true);  
			    props.setTranslateSpecialEntities(true);  
			    props.setBooleanAttributeValues("empty");  
			    String s=getHtml(url, encode);
			    TagNode node = cleaner.clean(s);  
			   return new PrettyXmlSerializer(props).getXmlAsString(node, encode);
		} catch (IOException e) {
			log.info("htmlд��xml writeXml:"+e.getMessage());
			return null;
		}  
	}
	/**
	 * 2.htmlд��xml
	 * @param url
	 * @param outFile
	 */
	@SuppressWarnings("deprecation")
	public static void writeXml(String url,String outFile) {
		 try {
			 	HtmlCleaner cleaner = new HtmlCleaner();  
			    CleanerProperties props = cleaner.getProperties();  
			    props.setUseCdataForScriptAndStyle(true);  
			    props.setRecognizeUnicodeChars(true);  
			    props.setUseEmptyElementTags(true);  
			    props.setAdvancedXmlEscape(true);  
			    props.setTranslateSpecialEntities(true);  
			    props.setBooleanAttributeValues("empty");  
			    TagNode node = cleaner.clean(url);  
			    new PrettyXmlSerializer(props).writeXmlToFile(node, outFile);
		} catch (IOException e) {
			log.info("htmlд��xml writeXml:"+e.getMessage());
		}  
	}
	/**
	 * 3.Stringд��xml
	 * @param url
	 * @param outFile
	 */
	@SuppressWarnings("deprecation")
	public static void writeXml2(String s,String outFile) {
		 try {
			 	HtmlCleaner cleaner = new HtmlCleaner();  
			    CleanerProperties props = cleaner.getProperties();  
			    props.setUseCdataForScriptAndStyle(true);  
			    props.setRecognizeUnicodeChars(true);  
			    props.setUseEmptyElementTags(true);  
			    props.setAdvancedXmlEscape(true);  
			    props.setTranslateSpecialEntities(true);  
			    props.setBooleanAttributeValues("empty");  
			    TagNode node = cleaner.clean(s);  
			    new PrettyXmlSerializer(props).writeXmlToFile(node, outFile);
		} catch (IOException e) {
			log.info("Stringд��xml writeXml2:"+e.getMessage());
		}  
	}
	/**
	 * ��ȡԴ���е��ַ���
	 * @param url
	 * @param encode
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
		} catch (ClientProtocolException e) { 
			log.info(" getHtml :"+e.getMessage());
			return null;
		} catch (IOException e) { 
			log.info("  getHtml:"+e.getMessage());
			return null;
		}
        return r;
	}
	/**
	 * ��ȡԴ��
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String getHtml(String url,String encode) {
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
			}
		} catch (ClientProtocolException e) { 
			log.info(" getHtml :"+e.getMessage());
			return null;
		} catch (IOException e) { 
			log.info("  getHtml:"+e.getMessage());
			return null;
		}
        return r;
	}
	/**
	 * ��ȡ�ı�
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String getCode(String url,String encode) {
		try {
			HttpClient client = new HttpClient(manager);
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
			get.setFollowRedirects(true);
//			get.setRequestHeader("Connection"," Keep-Alive");
			  int statusCode = client.executeMethod(get);
			   if (statusCode != HttpStatus.SC_OK) {
				   return null;
			   }
			HtmlCleaner cleaner = new HtmlCleaner();
			InputStream is=get.getResponseBodyAsStream();
			TagNode node = cleaner.clean(is,encode);   
			return node.getText().toString();
		} catch (MalformedURLException e) {
			log.info(" getCode :"+e.getMessage());
			return null;
		} catch (IOException e) {
			log.info(" getCode :"+e.getMessage());
			return null;
		}
	}
	/**
	 * 4.��ȡ�ı�
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String getCode(String url,String encode,String pre,String end) {
		try {
			HttpClient client = new HttpClient(manager);
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("User-Agent"," Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
			get.setFollowRedirects(true);
			get.setRequestHeader("Connection"," Keep-Alive");
			  int statusCode = client.executeMethod(get);
			   if (statusCode != HttpStatus.SC_OK) {
				   return null;
			   }
			HtmlCleaner cleaner = new HtmlCleaner();
			InputStream is=get.getResponseBodyAsStream();
			TagNode node = cleaner.clean(is,encode);   
			String sb=node.getText().toString();
			int i=sb.indexOf(pre);
			int j=i>=0?sb.indexOf(end,i+pre.length()+1):sb.length();
			return sb.substring(i>=0?i+pre.length():0,j>=0?j:sb.length());
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * ��ȡWebs���ϵĴ��ı����ϡ�
	 * @param url
	 * @param tag
	 * @param attribute
	 * @param value
	 */
	public static List<String> getContentByGroups(String url,String charset,List<Webs> list) {
		List<String> ls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);   
			for (Webs w : list) {
				String like=w.getLike();
				String notlike=w.getNoLike();
				Object[] ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");  
				for (int i = 0; i < ns.length; i++) {
					 String in =MyString.cleanSpans2(((TagNode)ns[i]).getText().toString());
					 if(like!=null) {
						 if(in.indexOf(like)<0)continue;
					 }
					 if(notlike!=null) {
						 if(in.indexOf(like)>=0)continue;
					 }
					 ls.add(in);
				}
			}
		} catch (Exception e) {
			log.info("��ȡWebs���ϵĴ��ı����ϡ� getContentByGroups :"+e.getMessage());
			return null;
		} 
		return ls;
	}
	/**
	 * ��ȡWebs���ϵĴ��ı����ϡ�
	 * @param url
	 * @param tag
	 * @param attribute
	 * @param value
	 */
	public static List<String> getContentByGroups(String s,List<Webs> list) {
		List<String> ls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(s);    
			for (Webs w : list) {
				Object[] ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");  
				for (int i = 0; i < ns.length; i++) {
					 String in = ((TagNode)ns[i]).getText().toString();
					 ls.add(in);
				}
			}
		} catch (Exception e) {
			log.info("��ȡWebs���ϵĴ��ı����ϡ� getContentByGroups :"+e.getMessage());
			return null;
		} 
		return ls;
	}
	/**
	 * ��ȡWebs���ϰ������µ�webs���������Ĵ��ı����ϡ�(�н�ֹ����ʱʹ��)
	 * @param url	��ץȡ����ַ
	 * @param content	��ҳԴ��
	 * @param charset	���뼯String
	 * @param list	��ץ����ҳԪ��List
	 * @param map	��Ԫ��ѡ��Map<Webs,String> String[0]:attname,String[1]:type,String[2]:number
	 * @param split	����ָ��ַ�String
	 * @return	�ַ���List
	 */
	public static List<String> getContentAndTypeByGroups0(String url,String encode,List<Webs> list, Map<Webs,String> map,String split) {
		List<String> ls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			String s=getHtml(url,encode);
			if(s==null)return null;
			TagNode node = cleaner.clean(s);
			for (Webs w : list) {
				Object[] ns;
				if (w.getAttribute()==null||w.getAttribute().trim().equals("")) {
					 ns = node.evaluateXPath("//"+w.getTag());  
				}else if(w.getValue()==null||w.getValue().trim().equals("")) {
					  ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"]");  
				}else {
					  ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");  
				}
				for (int i = 0; i < ns.length; i++) {		//���ڶ��TagNode�����todo������ָ���ڼ�������webs��Ӹ�����ʲô�ġ�
					TagNode td=((TagNode)ns[i]);			
					String in ="";
					if(map!=null) {
//					if(map.get(w)!=null) {
						Set<Webs> keys=map.keySet();
						for (Webs key : keys) {
							Object[] ns2 =null;
							String values[]=map.get(key).split(":");		//�ָ�Ҫ��ȡ������
							int len=0;	//ץȡ��λ��
							int lenii=0;
							if(key.getAttribute()==null||key.getAttribute().trim().equals(""))
							{
								  ns2 = td.evaluateXPath("//"+key.getTag());  
								  if(ns2==null||ns2.length==0)continue;
								  len=key.getNum();
								  lenii=len-1;
							}else if(key.getValue()==null||key.getValue().trim().equals("")){
								  ns2 = td.evaluateXPath("//"+key.getTag()+"[@"+key.getAttribute()+"]");
								  if(ns2==null||ns2.length==0)continue;
								  len=values.length<3?ns2.length:values[2]==null||values[2].equals("")?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  if(ns2.length==3)len=len-1; //���javaeye����webbean
								  if(ns2.length==1)continue;
								  lenii=len-1;
							}else{
								  ns2 = td.evaluateXPath("//"+key.getTag()+"[@"+key.getAttribute()+"=\""+key.getValue()+"\"]");  
								  if(ns2==null||ns2.length==0)continue;
								  
//								  len=values.length<3?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  len=values.length<3?ns2.length:values[2]==null||values[2].equals("")?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  lenii=len-1;
							}
//							log.info(ns2.length);
							if(values[1].equalsIgnoreCase("text")) {			//��ȡԪ��֮����ı���
								for (int ii = lenii; ii < len; ii++) {
									in+=((TagNode)ns2[ii]).getText()+values[3]+split;
								}
							}else if(values[1].equalsIgnoreCase("value")) {		//��ȡԪ��֮���ֵ��
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");		//��href�����������ж�
										h=MyString.getUrlWithHost(url, h);
										in+=h+values[3]+split;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+values[3]+split;
									}
								}
							}else if(values[1].equalsIgnoreCase("all")) {		//��ȡԪ��֮���ֵ���ı���	
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");
										h=MyString.getUrlWithHost(url, h);
										in+=h+"@url"+split;
										in+=((TagNode)ns2[ii]).getText()+values[3]+split;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+values[3]+split;
										in+=((TagNode)ns2[ii]).getText()+values[3]+split;
									}
								}
							}
						}
					}else {			//û��Ҫ��ȡ����Webs�µ�Ԫ��
						in = td.getText().toString();
					}
					ls.add(in);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ls;
	}
	/**
	 * ��ȡWebs���ϰ������µ�webs���������Ĵ��ı����ϡ�
	 * @param url	��ץȡ����ַString
	 * @param charset	���뼯String
	 * @param list	��ץ����ҳԪ��List
	 * @param map	��Ԫ��ѡ��Map<Webs,String> String[0]:attname,String[1]:type,String[2]:number
	 * @param split	����ָ��ַ�String
	 * @return	�ַ���List
	 */
	public static List<String> getContentAndTypeByGroups(String url,String charset,List<Webs> list, Map<Webs,String> map,String split) {
		List<String> ls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);
			for (Webs w : list) {
				Object[] ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");  
				for (int i = 0; i < ns.length; i++) {		//���ڶ��TagNode�����todo������ָ���ڼ�������webs��Ӹ�����ʲô�ġ�
					TagNode td=((TagNode)ns[i]);			
					String in ="";
					if(map!=null) {
//					if(map.get(w)!=null) {
						Set<Webs> keys=map.keySet();
						for (Webs key : keys) {
							Object[] ns2 =null;
							String values[]=map.get(key).split(":");		//�ָ�Ҫ��ȡ������
							int len=0;	//ץȡ��λ��
							int lenii=0;
							if(key.getAttribute()==null||key.getAttribute().trim().equals(""))
							{
								  ns2 = td.evaluateXPath("//"+key.getTag());  
								  len=key.getNum();
								  lenii=len-1;
							}else {
								  ns2 = td.evaluateXPath("//"+key.getTag()+"[@"+key.getAttribute()+"=\""+key.getValue()+"\"]");  
								  if(ns2==null)continue;
//								  len=values.length<3?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  len=values.length<3?ns2.length:values[2]==null||values[2].equals("")?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  lenii=len-1;
							}
//							log.info(ns2.length);
							if(values[1].equalsIgnoreCase("text")) {			//��ȡԪ��֮����ı���
								for (int ii = lenii; ii < len; ii++) {
									in+=((TagNode)ns2[ii]).getText()+values[3]+split;
								}
							}else if(values[1].equalsIgnoreCase("value")) {		//��ȡԪ��֮���ֵ��
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");		//��href�����������ж�
										h=MyString.getUrlWithHost(url, h);
										in+=h+values[3]+split;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+values[3]+split;
									}
								}
							}else if(values[1].equalsIgnoreCase("all")) {		//��ȡԪ��֮���ֵ���ı���	
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");
										h=MyString.getUrlWithHost(url, h);
										in+=h+"@url"+split;
										in+=((TagNode)ns2[ii]).getText()+values[3]+split;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+values[3]+split;
										in+=((TagNode)ns2[ii]).getText()+values[3]+split;
									}
								}
							}
						}
					}else {			//û��Ҫ��ȡ����Webs�µ�Ԫ��
						in = td.getText().toString();
					}
					ls.add(in);
				}
			}
		} catch (MalformedURLException e) {
			log.info("��������ȷ��url");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} 
		return ls;
	}
	/**
	 * ��ȡWebs���ϰ������µ�webs���������Ĵ��ı����ϡ�
	 * @param url	��ץȡ����ַ
	 * @param charset	���뼯
	 * @param list	��ץ����ҳԪ��
	 * @param map	��Ԫ��ѡ��Map<Webs,String> String[0]:attname,String[1]:type,String[2]:number
	 * @param split	����ָ��ַ�String
	 * @return	�ַ���List
	 */
	public static List<String> getContentAndTypeByGroups2(String url,String charset,List<Webs> list, Map<Webs,String> map,String split) {
		String sp=split==null?"":split;
		List<String> ls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);
			for (Webs key : list) {
				Object[] ns = node.evaluateXPath("//"+key.getTag()+"[@"+key.getAttribute()+"=\""+key.getValue()+"\"]");  
//				if(ns.length==0)continue;
				for (int i = 0; i < ns.length; i++) {		//���ڶ��TagNode�����todo�����ns.length>1����ָ���ڼ�������webs��Ӹ�����ʲô�ġ�
					TagNode td=((TagNode)ns[i]);			
					String in ="";
//					if(map!=null) {
					if(map.get(key)!=null) {
//						Set<Webs> keys=map.keySet();
//						for (Webs key : keys) {
							Object[] ns2 =null;
							String values[]=map.get(key).split(":");		//�ָ�Ҫ��ȡ������
							int len=0;	//ץȡ��λ��
							int lenii=0;
//							if(key.getAttribute()==null||key.getAttribute().trim().equals(""))
//							{
//								  ns2 = td.evaluateXPath("//"+key.getTag());  
//								  len=key.getNum();
//								  lenii=len-1;
//							}else {
									ns2=td.getChildTags();
//								  ns2 = td.evaluateXPath("//"+key.getTag()+"[@"+key.getAttribute()+"=\""+key.getValue()+"\"]");  
//								  if(ns2==null||ns2.length==0)continue;
//								  len=values.length<3?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  len=values.length<3?ns2.length:values[2]==null||values[2].equals("")?ns2.length:Integer.parseInt(values[2]);	//ץȡ�ĸ���
								  lenii=len-1;
								  String out=values.length<4?"":values[3];
//							}
							log.info(ns2.length);
							if(values[1].equalsIgnoreCase("text")) {			//��ȡԪ��֮����ı���
								for (int ii = lenii; ii < len; ii++) {
									in+=((TagNode)ns2[ii]).getText()+out+sp;
								}
							}else if(values[1].equalsIgnoreCase("value")) {		//��ȡԪ��֮���ֵ��
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");		//��href�����������ж�
										h=MyString.getUrlWithHost(url, h);
										in+=h+out+sp;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+out+sp;
									}
								}
							}else if(values[1].equalsIgnoreCase("all")) {		//��ȡԪ��֮���ֵ���ı���	
								for (int ii = lenii; ii < len; ii++) {
									if(values[0].equalsIgnoreCase("href")) {
										String h=((TagNode)ns2[ii]).getAttributeByName("href");
										h=MyString.getUrlWithHost(url, h);
										in+=h+"@url"+sp;
										in+=((TagNode)ns2[ii]).getText()+out+sp;
									}else {
										in +=((TagNode)ns2[ii]).getAttributeByName(values[0])+out+sp;
										in+=((TagNode)ns2[ii]).getText()+out+sp;
									}
								}
							}
//						}
					}else {			//û��Ҫ��ȡ����Webs�µ�Ԫ��
						in = td.getText().toString();
					}
					ls.add(in);
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} 
		return ls;
	}
	/**
	 * ��ȡ���ı�����
	 * @param url
	 * @param charset
	 * @param tag
	 * @param attribute
	 * @param value
	 * @return
	 */
	public static StringBuffer getContentParser(String url,String charset,String tag,String attribute,String value) {
		StringBuffer sb=new StringBuffer();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),charset);    
			Object[] ns = node.evaluateXPath("//"+tag+"[@"+attribute+"=\""+value+"\"]");  
			for (int i = 0; i < ns.length; i++) {
				sb.append(MyString.decode(((TagNode)ns[i]).getText().toString().trim()));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return sb;
	}
 
	/**
	 * ��ȡû�д��������,ͨ��XPath
	 * @param url
	 * @param XPath
	 * @return
	 */
	public static StringBuffer getContentNoParserByXPath(String url,String charset,String XPath) {
		StringBuffer sb=new StringBuffer();
		try { 
			HtmlCleaner cleaner = new HtmlCleaner();   
			TagNode node = cleaner.clean(new URL(url),charset);    
			Object[] ns = node.evaluateXPath(XPath);  
			log.info(ns.length);
			for (int i = 0; i < ns.length; i++) {
				 String in = cleaner.getInnerHtml((TagNode)ns[i]);
				 sb.append(in);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} 
		return sb;
	}
	
	/**
	 * ���ѭ���ݹ�TagNode�ַ���
	 * @param tn  TagNode�ڵ�
	 * @param out	��ʽ������ַ�
	 */
	public static void PrintTagNodeList(TagNode tn,String out) {
		TagNode[]	td=tn.getChildTags();
	    out="	"+out+".";
		if(td.length==0)return;
		for (int j = 0; j < td.length; j++) {
			if(td[j].getChildTags().length==0)out=out.replace("+", "-");
			log.info(out+(j+1)+". "+td[j].toString());
			PrintTagNodeList(td[j],(out+(j+1)));
		}
	}

	/**
	 * ����ڵ��б�
	 * @��ʽ��+1. div  
	 * 			+1.1. div  
	 * 				-1.1. d 
	 * 				-1.2. d 
	 * 				-1.3. d
	 * 		 +2. div
	 * 			+2.1. ul
	 * 				-2.1.1. li 
	 * 				-2.1.2. li	
	 * 			+2.2. ul
	 * 				-2.2.1. li 
	 * 				-2.2.2. li	
	 * 			-2.3. ul
	 * @param url
	 * @param XPath
	 */
	public static void PrintNodeList(String url,String XPath) {
		try {
			HtmlCleaner cleaner = new HtmlCleaner();   
			TagNode node = cleaner.clean(new URL(url));    
			Object[] ns = node.evaluateXPath(XPath);  
			for (int i = 0; i < ns.length; i++) {
				log.info("+"+(i+1)+""+ns[i].toString());
				PrintTagNodeList((TagNode)ns[i],"+"+String.valueOf(i+1));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * ����ѭ���ݹ�TagNode�ַ���
	 * @param tn  TagNode�ڵ�
	 * @param out	��ʽ������ַ�
	 * @param isHtml	�Ƿ���ʾ����
	 */
	public   void GetTagNodeList(TagNode tn,String out,boolean isHtml) {
		TagNode[]	td=tn.getChildTags();
	    out=out+".";
		if(td.length==0)return;
		for (int j = 0; j < td.length; j++) {
			if(isHtml) {
				Nodetagsset.add(out+(j+1)+". "+td[j].toString()+"	"+(td[j]).getText() );
			}else {
				Nodetagsset.add(out+(j+1)+". "+td[j].toString());
			}
			GetTagNodeList(td[j],(out+(j+1)),isHtml);
		}
	}

	/**
	 * ���ؽڵ��б�
	 * @��ʽ��+1. div  
	 * 			+1.1. div  
	 * 				-1.1. d 
	 * 				-1.2. d 
	 * 				-1.3. d
	 * 		 +2. div
	 * 			+2.1. ul
	 * 				-2.1.1. li 
	 * 				-2.1.2. li	
	 * 			+2.2. ul
	 * 				-2.2.1. li 
	 * 				-2.2.2. li	
	 * 			-2.3. ul
	 * @param url		����
	 * @param charset	���뼯
	 * @param XPath		XPath���ʽ
	 * @param isHtml	�Ƿ���ʾ����
	 */
	@SuppressWarnings("finally")
	public   List<String>  GetNodeList(String url,String charset,String XPath,boolean isHtml) {
		try {
			HtmlCleaner cleaner = new HtmlCleaner();   
			TagNode node = cleaner.clean(new URL(url),charset);    
			Object[] ns = node.evaluateXPath(XPath);  
			for (int i = 0; i < ns.length; i++) {
				if(isHtml) {
					Nodetagsset.add((i+1)+"."+ns[i].toString()+"	"+((TagNode)ns[i]).getText() );
				}else {
					Nodetagsset.add((i+1)+"."+ns[i].toString() );
				}
				GetTagNodeList((TagNode)ns[i],String.valueOf(i+1),isHtml);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}finally {
			return Nodetagsset;
		}
	}
	
	/**
	 * ��ȡurl����
	 * @param url Ҫ��ȡ�����url
	 * @return	String
	 * @throws Exception
	 * @author Longtao
	 * @date 2010.02.10
	 * @framework jericho
	 */ 
	public static String getEncode(String url) {    
			try {
				if (url.length() == 0)
					return "";
				if (url.indexOf(':') == -1)
					url = "file:" + url; 
				return new Source(new URL(url)).getEncoding();
			} catch (MalformedURLException e) {
//				e.printStackTrace();
				return null;
			} catch (IOException e) {
//				e.printStackTrace();
				return null;
			}
	}
	
	
	public static String getEncode2(String url) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		/**
		 * ParsingDetector�����ڼ��HTML��XML���ļ����ַ����ı��� ���췽���еĲ�������ָʾ�Ƿ���ʾ̽����̵���ϸ��Ϣ
		 * Ϊfalse����ʾ
		 */
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		try {
			charset = detector.detectCodepage(new URL(url));
		} catch (MalformedURLException mue) {
			return null;
//			mue.printStackTrace();
		} catch (IOException ie) {
			return null;
//			ie.printStackTrace();
		}
		if (charset == null)
			charset = Charset.defaultCharset();
		return charset.name();
	}
	
	/**
	 * ��ȡ����
	 * @param url  ����
	 * @return String
	 */
	public static String getTitle(String url,String encode) {
		try {
			HttpClient client = new HttpClient(manager);
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("User-Agent"," Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
			get.setFollowRedirects(true);
			get.setRequestHeader("Connection"," Keep-Alive");
			  int statusCode = client.executeMethod(get);
			   if (statusCode != HttpStatus.SC_OK) {
				   return null;
			   }
			HtmlCleaner cleaner = new HtmlCleaner();
			InputStream is=get.getResponseBodyAsStream();
			TagNode node = cleaner.clean(is,encode);   
			Object[] ns = node.getElementsByName("title", true);    //����  
			/*if(ns.length > 0) {   
			    log.info("title="+((TagNode)ns[0]).getText());   
			}   */
			return ((TagNode)ns[0]).getText().toString();
		} catch (MalformedURLException e) {
//			e.printStackTrace();
			return null;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}
	
	 /**
	  * 0707��htmlת����xml
	 * @param htmlurl   ����html�ļ�
	 * @param xmlurl	����xml�ļ�
	 */
	@SuppressWarnings("deprecation")
	public static void htmlToXml(String htmlurl,String charset, String xmlurl) {  
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
            TagNode node = cleaner.clean(new File(htmlurl),charset);  
            log.info("vreme:" + (System.currentTimeMillis() - start));  
            new PrettyXmlSerializer(props).writeXmlToFile(node, xmlurl);  
            log.info("vreme:" + (System.currentTimeMillis() - start));  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	
	
	/**
	 * NO��url��ҳת����JDom���Խ�����Domcument��
	 * @param url
	 * @return
	 */
	public static Document convertHtmlToXML(String url,String charset){ 
 
	       HtmlCleaner cleaner = new HtmlCleaner();  
	       CleanerProperties props = cleaner.getProperties();  
	       TagNode tagNode=null;  
	       Document document=null;  
	       try {
	           tagNode = cleaner.clean(new URL(url),charset);  
	           document= new DomSerializer(props, true).createDOM(tagNode);  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	       } catch (ParserConfigurationException e) {   
	           e.printStackTrace();  
	       }  
	       return document; 

	} 
	
	/**
	 * ��ȡָ�����Եĸ���
	 * @return
	 */
	public static int getAttributeNum(String url,String charset,String XPath) {
		try { 
			HtmlCleaner cleaner = new HtmlCleaner();    
			URL _url = new URL(url);
			TagNode node = cleaner.clean(_url,charset);    
			Object[] ns =  node.evaluateXPath(XPath); 
			return ns.length;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * ��ȡָ�㷶Χ��Link
	 * @param url
	 * @param charset
	 * @param XPath
	 * @return
	 */
	public static Map<String,String> getAllLinkByRange(String url,String charset,String XPath){
		try {
	 		Map<String, String> map=new HashMap<String, String>();
			HtmlCleaner cleaner = new HtmlCleaner();    
			URL _url = new URL(url);
			TagNode node = cleaner.clean(_url,charset);    
			Object[] ns =  node.evaluateXPath(XPath); 
			for (int i = 0; i < ns.length; i++) { 
				TagNode n = (TagNode) ns[i];
				map.put(n.getText().toString(), n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString());
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
	 * ��ȡ���е�����
	 * @param url
	 */
	public static Map<String, String> getAllLink(String url,String charset) { 
	 	try {
	 		Map<String, String> map=new LinkedHashMap<String, String>();
			HtmlCleaner cleaner = new HtmlCleaner();    
			URL _url = new URL(url);
			TagNode node = cleaner.clean(_url,charset);    
			Object[] ns =  node.evaluateXPath("//a"); 
			for (int i = 0; i < ns.length; i++) { 
				TagNode n = (TagNode) ns[i];
				String u=n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString().trim();
				if(u.equals(""))continue;
				map.put(n.getText().toString().trim(), MyString.getUrlWithHost(url,u ));
			}
			return map;
		} catch (Exception e) { 
			System.out.println(e.getMessage());
			return null;
		}
	}
	/**
	 * ��ȡ���е�����
	 * @param url
	 */
	public static Map<String, String> getAllNewsLink(String url,String charset,String w) { 
	 	try {
	 		HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node=null;
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url.trim()); 
			HttpResponse response;
			String  r = null;
			response = client.execute(httpget);
			if(null != response){
				HttpEntity entity = response.getEntity();
				r  = EntityUtils.toString(entity ,charset);
				httpget.abort();
				client.getConnectionManager().shutdown(); 
			}
	 		Map<String, String> map=new HashMap<String, String>();
			node = cleaner.clean(r);    
				String web[]=w.split(",");
				int tnlen=-1;
				if(web.length==4){
					tnlen=Integer.parseInt( web[3]);
				}
				Object[] ns = node.evaluateXPath("//"+web[0]+"[@"+web[1]+"=\""+web[2]+"\"]"); 
				for (int i = 0; i < ns.length; i++) {
					if(tnlen!=-1&&i!=tnlen)continue;
					Object[] ns2=((TagNode) ns[i]).evaluateXPath("//a");
					for (int ii = 0; ii < ns2.length; ii++) { 
						TagNode n = (TagNode) ns2[ii];
						if(n.getText().toString().trim().equals(""))continue;
						String u=n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString().trim();
						u=MyString.getUrlWithHost(url,u );
						if(u==null||u.equals(""))continue;
						String title="";
						if(u.startsWith("http://www.javaeye.com/news/")){
							try {
								title=n.getAttributeByName("title").trim();
								if(!map.containsKey(title))
									map.put(title,u );
							} catch (Exception e) {
								continue;
							}
						}else{
							title=n.getText().toString().trim();
							if(!map.containsKey(title))
								map.put(title,u );
						}
					}
				}
			return map;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * ��ȡ���е�����
	 * @param url
	 */
	public static Map<String, String> getAllLink(String url,String charset,String w) { 
	 	try {
	 		HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node=null;
			DefaultHttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url.trim()); 
			HttpResponse response;
			String  r = null;
			response = client.execute(httpget);
			if(null != response){
				HttpEntity entity = response.getEntity();
				r  = EntityUtils.toString(entity ,charset);
				httpget.abort();
				client.getConnectionManager().shutdown(); 
			}
	 		Map<String, String> map=new LinkedHashMap<String, String>();
			node = cleaner.clean(r);    
			String web[]=w.split(",");
			int tnlen=-1;
			if(web.length==4){
				tnlen=Integer.parseInt( web[3]);
			}
			Object[] ns = node.evaluateXPath("//"+web[0]+"[@"+web[1]+"=\""+web[2]+"\"]"); 
			for (int i = 0; i < ns.length; i++) {
				if(tnlen!=-1&&i!=tnlen)continue;
				Object[] ns2=((TagNode) ns[i]).evaluateXPath("//a");
				for (int ii = 0; ii < ns2.length; ii++) { 
					TagNode n = (TagNode) ns2[ii];
					if(n.getText().toString().trim().equals(""))continue;
					String u=n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString().trim();
					u=MyString.getUrlWithHost(url,u );
					if(u==null||u.equals(""))continue;
					String title="";
					if(u.startsWith("http://www.javaeye.com/news/")){
						try {
							title=n.getAttributeByName("title").trim();
							if(!map.containsKey(title))
								map.put(title,u );
						} catch (Exception e) {
							continue;
						}
					}else{
						title=n.getText().toString().trim();
						if(!map.containsKey(title))
							map.put(title,u );
					}
				}
			}
			
			return map;
		} catch (Exception e) { 
			System.out.println(e.getMessage());
			return null;
		}
	}
	public void g2() throws Exception{
        HtmlCleaner cleaner = new HtmlCleaner();   
        String url = "http://finance.sina.com.cn/nmetal/hjfx.html";
        URL _url = new URL(url);
        TagNode node = cleaner.clean(_url);   
        
        //��tagȡ.   
        Object[] ns = node.getElementsByName("title", true);    //����   
        
        if(ns.length > 0) { 
            log.info("title="+((TagNode)ns[0]).getText());   
        }  
        
        
        ns = node.evaluateXPath("//*[@class='Frame-Row3-01-C']/table[2]/tbody/tr/td/a"); //ѡȡclassΪָ��blkContainerSblkCon��div���������p
        for (int i = 0; i < ns.length; i++) {
        	
        	//ȡ�����ı�
//        	 String in = cleaner.getInnerHtml((TagNode)ns[i]);
//           log.info(in);
        	
        	//��ȡ���ӵ�
        	TagNode n = (TagNode) ns[i];
//        	log.info(n.getAttributeByName("href"));
        	log.info(new URL(_url,n.getAttributeByName("href")).toString());
		}
//        String in = cleaner.getInnerHtml((TagNode)ns[0]);
//        log.info(in);

//        log.info(((TagNode)ns[0]).getText());
        
//        log.info("ul/li:");   
//        //��xpathȡ   
//        ns = node.evaluateXPath("//div[@class='d_1']//li");   
//        for(Object on : ns) {   
//            TagNode n = (TagNode) on;   
//            log.info("\ttext="+n.getText());   
//        }   
//        log.info("a:");   
//        //������ֵȡ   
//        ns = node.getElementsByAttValue("name", "my_href", true, true);   
//        for(Object on : ns) {   
//            TagNode n = (TagNode) on;   
//            log.info("\thref="+n.getAttributeByName("href")+", text="+n.getText());   
//        }   
    

	}

	public   void g() {
        try {
			HtmlCleaner cleaner = new HtmlCleaner();   
			TagNode node = cleaner.clean(new URL("http://finance.sina.com.cn/money/nmetal/20091209/10157077895.shtml"));   
			//��tagȡ.   
			Object[] ns = node.getElementsByName("title", true);    //����  
			if(ns.length > 0) {   
			    log.info("title="+((TagNode)ns[0]).getText());   
			}   
			// /html/body/div[2]/div[4]/div/div/div/div[2]/p
			ns = node.evaluateXPath("//div[@class=\"blkContainerSblkCon\"]/p"); //ѡȡclassΪָ��blkContainerSblkCon��div���������p��ǩ
			for (int i = 0; i < ns.length; i++) {
				 String in = cleaner.getInnerHtml((TagNode)ns[i]);
			     log.info("<p>"+in + "</p>");
			}
			String in = cleaner.getInnerHtml((TagNode)ns[0]);
			log.info(in);

			log.info(((TagNode)ns[0]).getText());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPatherException e) {
			e.printStackTrace();
		} 


	}

	public List<String> getNodetagsset() {
		return Nodetagsset;
	}

	public void setNodetagsset(List<String> nodetagsset) {
		Nodetagsset = nodetagsset;
	}
	//��ȡwebs�µ�����
	public static List<String> getLinksByWebs(String url, String encode,
			String w) {
		List<String> urls=new ArrayList<String>();
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),encode);   
				String web[]=w.split(",");
				Object[] ns = node.evaluateXPath("//"+web[0]+"[@"+web[1]+"=\""+web[2]+"\"]");  
				Object [] tnurls=	((TagNode)ns[0]).evaluateXPath("//a");
				for (int i = 0; i < tnurls.length; i++) {
					//TODO ����URL(ok?)
					String u=MyString.getUrlWithHost(url,((TagNode)tnurls[i]).getAttributeByName("href").toString().trim() ); 
					if(!urls.contains(u))urls.add(u);
				}
		} catch (Exception e) {
			log.info("��ȡWebs���ϵĴ��ı����ϡ� getContentByGroups :"+e.getMessage());
			return null;
		} 
		return urls;
	}
	/**
	 * �ж��Ƿ������ҳ
	 * @param url
	 * @param encode
	 * @param w
	 * @return
	 */
	public static boolean isInnerPage(String url, String encode,
			String w) {
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url),encode);   
			if(w==null||w.trim().equals(""))return false;
			String web[]=w.split(",");
			Object[] ns = node.evaluateXPath("//"+web[0]+"[@"+web[1]+"=\""+web[2]+"\"]");
			return ns.length>0?true:false;
		} catch ( Exception e) {
			e.printStackTrace();
			return false;
		}  

	}

}
