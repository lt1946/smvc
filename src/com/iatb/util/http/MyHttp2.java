/**
 *
 */
package com.iatb.util.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.Source;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.iatb.bean.Webs;
import com.iatb.util.MyFile;
import com.iatb.util.MyString;
import com.iatb.util.UTF8PostMethod;

/**
 * @author Administrator
 * 
 */
public class MyHttp2 {

	/**
	 * 获取文本
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String getCode(String url,String encode) {
		try {
			HttpClient client = new HttpClient( );
			GetMethod get = new GetMethod(url);
			get.setRequestHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
			get.setFollowRedirects(true);
			  int statusCode = client.executeMethod(get);
			   if (statusCode != HttpStatus.SC_OK) {
				   return null;
			   }
			HtmlCleaner cleaner = new HtmlCleaner();
			InputStream is=get.getResponseBodyAsStream();
			TagNode node = cleaner.clean(is,encode);   
			return node.getText().toString();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取纯文本
	 * @param url
	 * @return
	 */
	public static String getText(String url) {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(url);   
			return node.getText().toString();
	}
	/**
	 * getLocation	
	 * @since			2010-09-21
	 * @param url		链接url
	 * @param cookie	cookie
	 */
	public static String getLocation(String url,String cookie) {
		try {
			HttpClient client =new HttpClient();
			GetMethod get=new GetMethod(url);
			get.setRequestHeader("Cookie", cookie);
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			get.setFollowRedirects(false);
			client.executeMethod(get);
			Header header=get.getResponseHeader("Location");
			get.releaseConnection();
			return header.getValue();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * get	
	 * @since			2010-09-19
	 * @param url		链接url
	 * @param cookie	cookie
	 * @param b			是否输出信息
	 */
	public static void get(String url,String cookie,boolean b) {
		try {
			HttpClient client =new HttpClient();
			GetMethod get=new GetMethod(url);
			get.setRequestHeader("Cookie", cookie);
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			get.setFollowRedirects(true);
			client.executeMethod(get);
			if(b) {
				InputStream content=get.getResponseBodyAsStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(content,MyHtmlCleaner.getEncode(url)));
				StringBuffer sb=new StringBuffer();
				String s="";
				while((s=br.readLine())!=null){
					sb.append(s+"\n");
				}
				System.out.println(sb);
			}
			get.releaseConnection();
		} catch (Exception e) {
		}
	}
	public static List<String>  getWithWebs(String url,String cookie,List<Webs> webs,String encode){
		try {
			List<String> list=new ArrayList<String>();
			HttpClient client =new HttpClient();
			GetMethod get=new GetMethod(url);
			get.setRequestHeader("Cookie", cookie);
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			get.setFollowRedirects(true);
			client.executeMethod(get);
			InputStream content=get.getResponseBodyAsStream();
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(content,encode);   
			for (Webs w : webs) {
				Object[] ns = node.evaluateXPath("//"+w.getTag()+"[@"+w.getAttribute()+"=\""+w.getValue()+"\"]");  
				for (int i = 0; i < ns.length; i++) {
					 String in = ((TagNode)ns[i]).getText().toString();
					 list.add(in);
				}
			}
			get.releaseConnection();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	public static String postGetCookie(String url,Map<String,String> data,String cookie,String name) {
		try {
			HttpClient client =new HttpClient();
			PostMethod post=new PostMethod(url);
			NameValuePair []nvp=new NameValuePair[data.size()] ;
			Object key[]=data.keySet().toArray();
			for (int i = 0; i <data.size(); i++) {
				nvp[i]=new NameValuePair(key[i].toString().trim(),data.get(key[i].toString()).trim());
			}
			post.setRequestBody(nvp);
			post.setRequestHeader("Cookie",cookie);
			client.executeMethod(post);
		 	Header header=post.getResponseHeader("Set-Cookie");
			post.releaseConnection();
			return header.getValue();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * post提交数据
	 * @since			2010-09-17
	 * @param url		链接
	 * @param data		提交的数据
	 * @param cookie	cookie
	 * @return			跳转地址
	 */
	public static String post(String url,Map<String,String> data,String cookie) {
		try {
			HttpClient client =new HttpClient();
			PostMethod post=new PostMethod(url);
			NameValuePair []nvp=new NameValuePair[data.size()] ;
			Object key[]=data.keySet().toArray();
			for (int i = 0; i <data.size(); i++) {
				nvp[i]=new NameValuePair(key[i].toString(),data.get(key[i].toString()));
			}
			post.setRequestBody(nvp);
			post.setRequestHeader("Cookie",cookie);
			client.executeMethod(post);
			Header header=post.getResponseHeader("Location");
			post.releaseConnection();
			return header.getValue();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * post提交utf8数据
	 * @since			2010-09-17
	 * @param url		链接
	 * @param data		提交的数据
	 * @param cookie	cookie
	 * @return			跳转地址
	 */
	public static String postUtf8(String url,Map<String,String> data,String cookie) {
		try {
			HttpClient client =new HttpClient();
			PostMethod post=new UTF8PostMethod(url);
			NameValuePair []nvp=new NameValuePair[data.size()] ;
			Object key[]=data.keySet().toArray();
			for (int i = 0; i <data.size(); i++) {
				nvp[i]=new NameValuePair(key[i].toString(),data.get(key[i].toString()));
			}
			post.setRequestBody(nvp);
			post.setRequestHeader("Cookie",cookie);
			client.executeMethod(post);
//			post.setFollowRedirects(true);
			Header header=post.getResponseHeader("Location");
			post.releaseConnection();
			return header.getValue();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * post提交数据(获取cookieurl上的cookies，通过urlPost提交data数据)
	 * @since			2010-09-17
	 * @param url		链接
	 * @param data		提交的数据
	 * @param cookie	cookie
	 * @return			跳转地址
	 */
	public static String post2(String cookieurl,String url,Map<String,String> data) {
		try {
			HttpClient client =new HttpClient();
			GetMethod get=new GetMethod(cookieurl);
			client.executeMethod(get);
		    Cookie []cookies = client.getState().getCookies();   
	        client.getState().addCookies(cookies);  
	        get.releaseConnection();
			PostMethod post=new PostMethod(url);
			NameValuePair []nvp=new NameValuePair[data.size()] ;
			Object key[]=data.keySet().toArray();
			for (int i = 0; i <data.size(); i++) {
				nvp[i]=new NameValuePair(key[i].toString(),data.get(key[i].toString()));
			}
			post.setRequestBody(nvp);
			client.executeMethod(post);
			Header header=post.getResponseHeader("Location");
			post.releaseConnection();
			return header.getValue();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 登入判断是否包含字符check
	 * @since			2010-09-17
	 * @param loginurl	登入url
	 * @param data		提交数据
	 * @param checkurl	要检查的url
	 * @param rdurl		登入成功跳转的url
	 * @param check		要检查包含的字符串
	 * @param cookie 	登入url Post的cookie
	 * @param cookie2	要检查url提交的cookie
	 * @param b			是否输出信息
	 * @return
	 */
	public static boolean loginCheckString(String loginurl,Map<String,String> data,String checkurl,String rdurl,String check,String cookie,String cookie2,boolean b) {
		try {
			HttpClient client =new HttpClient();
			PostMethod post=new PostMethod(loginurl);
			NameValuePair []nvp=new NameValuePair[data.size()] ;
			Object key[]=data.keySet().toArray();
			for (int i = 0; i <data.size(); i++) {
				nvp[i]=new NameValuePair(key[i].toString(),data.get(key[i].toString()));
			}
			post.setRequestBody(nvp);
			post.setRequestHeader("Cookie",cookie);
			post.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			client.executeMethod(post);
			if(b) {
//				System.out.println(post.getResponseBodyAsString());
			}
			if(rdurl!=null&&rdurl.trim().length()!=0) {
				Header header=post.getResponseHeader("Location");
				post.releaseConnection();
				String redirectUrl=header.getValue();
				if(!redirectUrl.equals(rdurl)) {
					return false;
				}
			}
			GetMethod get=new GetMethod(checkurl);
			get.setRequestHeader("Host", "tg6a68.mail.163.com");
			get.setRequestHeader("Cookie", cookie);
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			client.executeMethod(get);
			String content=get.getResponseBodyAsString();
			if(b) {
				System.out.println(content);
			}
			get.releaseConnection();
			return content.indexOf(check)!=-1?true:false;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 登入获取相似链接
	 * @since	2010-09-17
	 * @param loginurl	登入地址
	 * @param postDate	提交数据
	 * @param reUrl		跳转地址
	 * @param url		获取地址
	 * @param encode	编码集
	 * @param like		相似链接字符串
	 * @return
	 */
	public static String LoginGetLink(String loginurl,Map<String,String> postDate,String reUrl,String url,String encode,String like) {
		try {
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(loginurl, 80);
			PostMethod post = new PostMethod(loginurl);
			Set<String> keys=postDate.keySet();
			Object[] d=keys.toArray();
			NameValuePair[] nvp = new NameValuePair[postDate.size()] ;
			for (int i = 0; i < nvp.length; i++) {
				nvp[i]=new NameValuePair((String)d[i], postDate.get(d[i]));
			}
			Cookie[] cookies = client.getState().getCookies();
			MyHttp2.processPost(client, post, loginurl, nvp, cookies, true, false);
			Header header = post.getResponseHeader("Location");
			String redirectUrl = header.getValue();
			if (redirectUrl.equals(reUrl)) {
				GetMethod get=new GetMethod(url);
				client.executeMethod(get);
				InputStream content=get.getResponseBodyAsStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(content,encode));
				String s="";
				Pattern p=Pattern.compile(like);
				while((s=br.readLine())!=null) {
					Matcher m= p.matcher(s);
					if(m.find()) {
						return m.group();
					}
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	/**
	 * 判断url是否存在,且不含跳转链接.
	 * @since 		2010-09-17
	 * @param url	链接地址
	 * @return
	 */
	public static boolean existsUrl(String url) {
		try {
//			int status=((HttpURLConnection)new URL(url).openConnection()).getResponseCode();
			HttpClient client =new HttpClient();
			HttpConnectionManagerParams managerParams = client
	          .getHttpConnectionManager().getParams();
		     // 设置连接的超时时间
		    managerParams.setConnectionTimeout(3 * 1000);
		    // 设置读取数据的超时时间
		    managerParams.setSoTimeout(5 * 1000);
			GetMethod get=new GetMethod(url);
			get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.10) Gecko/20100914 Firefox/3.6.10");
			int status =client.executeMethod(get);
			if(status==200){
				Header header=get.getResponseHeader("Location");
				get.releaseConnection();
				return header==null?true:header.getValue()!=null?false:true;
			}else{
				get.releaseConnection();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 注册url
	 * @since			2010-09-17
	 * @param signup	注册地址
	 * @param postDate	提交数据
	 * @param reUrl		跳转地址
	 * @return
	 */
	public static boolean signUp(String signup,Map<String,String> postDate,String reUrl) {
		try {
			HttpClient client = new HttpClient();
			client.getHostConfiguration().setHost(signup, 80);
			PostMethod post = new PostMethod(signup);
			Set<String> keys=postDate.keySet();
			Object[] d=keys.toArray();
			NameValuePair[] nvp = new NameValuePair[postDate.size()] ;
			for (int i = 0; i < nvp.length; i++) {
				nvp[i]=new NameValuePair((String)d[i], postDate.get(d[i]));
			}
			Cookie[] cookies = client.getState().getCookies();
			MyHttp2.processPost(client, post, signup, nvp, cookies, true, false);
			Header header = post.getResponseHeader("Location");
			String redirectUrl = header.getValue();
			if (redirectUrl.equals(reUrl)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 获取所有链接包括：标题和链接
	 * @param url
	 * @param charset
	 * @return
	 * @throws XPatherException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Map<String, String> getAllLink(String url, String charset)
			throws XPatherException, MalformedURLException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode node = cleaner.clean(new URL(url), charset);
		Object[] ns = new Object[] {};
		ns = node.evaluateXPath("//a");
		for (int i = 0; i < ns.length; i++) {
			try {
				map.put(((TagNode) ns[i]).getText().toString().trim(), ((TagNode) ns[i])
						.getAttributeByName("href").trim());
			} catch (Exception e) {
				continue;
			}
		}
		return map;
	}
	/**
	 * 返回相似字符串的链接集合。
	 * @since 2010-09-16
	 * @param url
	 *            获取的链接
	 * @param charset
	 *            字符集
	 * @param like
	 *            相似字符串
	 * @throws XPatherException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static List<String> getLikeLink(String url, String charset,
			String like) throws XPatherException, MalformedURLException,
			IOException {
		List<String> list = new ArrayList<String>();
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode node = cleaner.clean(new URL(url), charset);
		Object[] ns = new Object[] {};
		ns = node.evaluateXPath("//a");
		String u = "";
		for (int i = 0; i < ns.length; i++) {
			try {
				if ((u = ((TagNode) ns[i]).getAttributeByName("href")
						.toLowerCase()).indexOf(like) != -1) {
					if (!list.contains(u))
						list.add(u);
				} else {
					continue;
				}
			} catch (RuntimeException e) {
				continue;
			}
		}
		return list;
	}
	/**
	 * 返回不相似字符串的链接集合。
	 * @since 2010-09-16
	 * @param url
	 *            获取的链接
	 * @param charset
	 *            字符集
	 * @param like
	 *            相似字符串
	 * @throws XPatherException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static Map<String, String> getNotLikeLink(String url, String charset,
			String like) throws XPatherException, MalformedURLException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();
		HtmlCleaner cleaner = new HtmlCleaner();
		TagNode node = cleaner.clean(new URL(url), charset);
		Object[] ns = new Object[] {};
		ns = node.evaluateXPath("//a");
		String u = "";
		for (int i = 0; i < ns.length; i++) {
			boolean b=false;
			try {
				if(((TagNode) ns[i]).getText().toString().trim()==null||u==null)continue;
				u = ((TagNode) ns[i]).getAttributeByName("href")
				.toLowerCase();
				for (int j = 0; j < like.split(",").length; j++) {
					if (u.indexOf(like.split(",")[j]) != -1) {
						b=true;break;
					}
				}
				if (!b) {
						map.put(((TagNode) ns[i]).getText().toString().trim(), ((TagNode) ns[i])
								.getAttributeByName("href").trim());
				} else {
					continue;
				}
			} catch (RuntimeException e) {
				continue;
			}
		}
		return map;
	}
	/**
	 * 下载页面
	 * 
	 * @param url
	 *            下载链接
	 * @param filepath
	 *            保存路径
	 * @param fn
	 *            是否更改保存文件名
	 * @param dump
	 *            是否掉过文件
	 * @param msg
	 *            是否输出打印
	 * @since 2010-09-08
	 */
	public static void download(String url, String filepath, String fn,
			boolean dump, boolean msg) {
		try {
			String fileName = new File(url).getName();
			if (fn != null && !fn.trim().equals("")) {
				fileName = fn;
			}
			File f = new File(filepath + (filepath.endsWith("/")?"":"/") + fileName);
			if (!f.exists()) {
				MyFile.createFile(f, false);
			} else {
				if (dump) {
					if (msg) {
						System.out.println(f.getName() + (url.endsWith("/")?"文件夹":"文件")+"已经存在跳过!");
					}
					return;
				}
			}
			HttpClient hc = new HttpClient();
			GetMethod get = new GetMethod(url);
			int i=hc.executeMethod(get);
			if(i!=200)return;
			byte b[] = get.getResponseBody();
			get.releaseConnection();
			FileOutputStream out = new FileOutputStream(f);
			out.write(b);
			out.close();
			if (msg) {
				System.out.println(f.getName() + "下载保存成功!");
			}
		} catch (HttpException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * 获取源码
	 * 
	 * @param url
	 *            下载链接
	 * @param msg
	 *            是否输出打印
	 * @return
	 * @since 2010-09-08
	 */
	public static String getSource(String url, boolean msg) {
		try {
			HttpClient hc = new HttpClient();
			GetMethod get = new GetMethod(url);
			hc.executeMethod(get);
			String s = get.getResponseBodyAsString();
			get.releaseConnection();
			if (msg) {
				System.out.println(s);
			}
			return s;
		} catch (Exception e) {
			try {
				HttpClient hc = new HttpClient();
				GetMethod get = new GetMethod(url);
				hc.executeMethod(get);
				String s = get.getResponseBodyAsString();
				get.releaseConnection();
				if (msg) {
					System.out.println(s);
				}
				return s;
			} catch (Exception e2) {
				e2.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 获取差异路径
	 * url<=geturl
	 * @param url
	 * @param getUrl
	 * @return 目录字符串
	 */
	public static String getDeferentPath(String url,String getUrl) {
		url=url.trim();
		getUrl=getUrl.trim();
		int i=0;
		if((i=url.indexOf("/",10))==-1) {
			url=url+"/";
		}
		int j=0;
		if((j=getUrl.indexOf("/",10))==-1) {
			getUrl=getUrl+"/";
		}
		String urlroot=i==-1?url:url.substring(0,i+1);
		String geturlroot=j==-1?getUrl:getUrl.substring(0,j+1);
		if(!urlroot.equals(geturlroot)) {
			//如果是url该网站下的二级域名：
			return getUrl.substring(getUrl.indexOf("//")+2,getUrl.indexOf(".", 9))+"__2/"+(j==-1?"":getUrl.substring(j+1,getUrl.lastIndexOf("/")+1));
		}else { //是同个二级域名
			if((i=getUrl.indexOf(url))==-1) {//不是在同级url下的目录，返回主目录下，新建n个'_'+n?的目录。
				String returnurl="";
				String lu[]=url.split("/");
				String lgu[]=getUrl.split("/");
				int lenurl=lu.length-4;		// url共有几级目录
				int lengeturl=lgu.length-4;// geturl共有几级目录
				if(lengeturl==lenurl) {
					if(lenurl==0)return lgu[3]; 
					else { //都含有子目录
						for (int k = 3; k < lengeturl; k++) {
							if(lu[k].equals(lgu[k])) {
								continue;
							}else {
								if(k==lengeturl-1) {
									returnurl+=lgu[k];
								}else {
									returnurl+=lgu[k]+"_/";
								}
							}
						}
					}
				}else if(lengeturl>lenurl){ 
					for (int k = 3; k < lengeturl; k++) {
						if(lenurl==k+1) {
							returnurl+=lgu[k]+"+_/";break;
						}else {
							if(lu[k].equals(lgu[k])) {
								continue;
							}else {
								if(k==lengeturl-1) {
									returnurl+=lgu[k];
								}else {
									returnurl+=lgu[k]+"_/";
								}
							}
						}
					}
				}else {
					for (int k = 3; k < lenurl; k++) {
						if(lengeturl==k+1) {
							returnurl+=lgu[k]+"+_/";break;
						}else {
							if(lu[k].equals(lgu[k])) {
								continue;
							}else {
								if(k==lenurl-1) {
									returnurl+=lgu[k];
								}else {
									returnurl+=lgu[k]+"_/";
								}
							}
						}
					}
				}
				return returnurl;
			}else {
				return getUrl.substring(i,getUrl.lastIndexOf("/")+1);
			}
			
		}
	}
	/**
	 * 返回完整的url
	 * @param host     网站主页地址
	 * @param url      要返回地址
	 * @param msg      是否输出打印
	 * @return
	 */
	public static String getUrlWithHost(String host, String url, boolean msg) {
		String s = "";
		if (url == null || url.trim().equals("") || host == null
				|| host.trim().equals("")) {
			System.out.println("getUrlWithHost()：输入的url为空！");
		} else {
			if (!host.trim().startsWith("http")) {
				System.out.println("host网址不正确！");
			} else {
				if (url.startsWith("http")) {
					s = url;
				} else {
					if (!url.startsWith("/")) {
						s = host.substring(0, host.lastIndexOf("/") + 1) + url;
					} else {
						int i = host.indexOf("/", 10);
						String host1 = host;
						if (i > 0) {
							host1 = host.substring(0, i);
							if (url.trim().startsWith("/")) {
								s = host1 + url;
							} else {
								s = host.endsWith("/") ? host1 + url : host1
										+ "/" + url;
							}
						} else {
							if (url.trim().startsWith("/")) {
								s = host + url;
							} else {
								s = host + "/" + url;
							}
						}
					}
				}
			}
		}
		if (msg) {
			System.out.println(s);
		}
		return s;
	}
	/**
	 * 获取链接中的图片
	 * 
	 * @since 2010-09-08
	 * @param url      CSS链接
	 * @return 		           字符串集合
	 * @throws IOException
	 */
	public static List<String> GetImgs(String url, boolean msg)
			throws IOException {
		String s = MyHttp2.getSource(url, false);
		List<String> ls = new ArrayList<String>();
		// String r = "((images|img)/[\\w-]+\\.\\w+)(\"|\\){1})"; // Step #2
		// [\s|:|\w|/|\d|\.|\-]+\.(css|js|png|bmp|jpg|tiff|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw)

		String r = "([\\s|:|\\w|/|\\d|\\.|\\-]+\\.(css|js|png|bmp|jpg|tiff|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|htc))"; // Step
																																			// #2
		List<String> l = MyString.getLike(s, r, 1);
		for (String string : l) {
			if(string.startsWith("http"))continue;
//			s = MyHttp
//					.getUrlWithHost(url, string, false);
			if (msg) {
				System.out.println(s);
			}
			ls.add(string);
		}
		return ls;
	}
	/**
	 * 获取链接中的json,html,htm
	 * 
	 * @since 2010-09-08
	 * @param url     js链接
	 * @return 		       字符串集合
	 * @throws IOException
	 */
	public static List<String> GetJss(String url, boolean msg)
			throws IOException {
		String s = MyHttp2.getSource(url, false).toLowerCase();
		List<String> ls = new ArrayList<String>();
		// ('|")(.)+.(json|html|htm)('|")

		String r = ".+\\.(json|html|htm|js)"; // Step #2
		List<String> jsList = new ArrayList<String>();
		jsList.add(".json");
		jsList.add(".html");
		jsList.add(".htm");
		jsList.add(".js");
		for (String ss : jsList) {
			for (int i = 0; i < s.length(); i++) {
				i = s.indexOf(ss, i);
				if (i == -1)
					break;
				int j = i;
				int k = i;
				if ((j = s.indexOf("'", i)) != -1) {
					if ((ss.length() + i) == j
							|| s.substring(i + 1, j).trim().equals("")) {
						if ((k = s.lastIndexOf("'", i)) != -1) {
							String js = s.substring(k + 1, j);
							if (MyString.getLike(js, r)) {
								if (!ls.contains(js))
									ls.add(js);
							}
						}
					}
				} else {
					break;
				}
			}
			for (int i = 0; i < s.length(); i++) {
				i = s.indexOf(ss, i);
				if (i == -1)
					break;
				int j = i;
				int k = i;
				if ((j = s.indexOf("'", i)) != -1) {
					if ((ss.length() + i) == j
							|| s.substring(i + 1, j).trim().equals("")) {
						if ((k = s.lastIndexOf("'", i)) != -1) {
							String js = s.substring(k + 1, j);
							if (MyString.getLike(js, r)) {
								if (!ls.contains(js))
									ls.add(js);
							}
						}
					}
				} else {
					break;
				}
			}
		}
		return ls;
	}

	/**
	 * 从本地获取html相关下载链接
	 * @param file 本地网页文件
	 * @return 要下载的链接 
	 */
	public static List<String> GetUseLinkFromLocal(String file) {
		String s = MyFile.getText(file);
		// [\s|:|\w|/|\d|\.|\-]+\.(css|js|png|bmp|jpg|tiff|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw)
		return MyString
				.getLike(
						s,
						"[\\s|:|\\w|/|\\d|\\.|\\-]+\\.(css|js|png|bmp|jpg|tiff|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw)",
						0);
	}

	/**
	 * 替换静态页面里的链接
	 * 
	 * @param ls
	 * @param file
	 * @throws UnsupportedEncodingException
	 */
	public static void replaceHtml(String url, List<String> ls, String file)
			throws UnsupportedEncodingException {
		String encode = getEncode(file);
		String s = MyFile.getText(file, encode);
		for (String string : ls) {
			if (string.startsWith("/"))
				s = s.replaceAll(string, string.substring(1));
			else if (string.startsWith("..")) {
				s = s.replaceAll(string, string.substring(3));
				// int i=url.lastIndexOf("/");
				// if(i==-1)continue;
				// i=url.lastIndexOf("/", i-1);
				// if(i==-1)continue;
				// s=s.replaceAll(string,string.replace("..", url.substring(
				// url.substring(0,i).lastIndexOf("/")+1,i)));
			}
		}
		MyFile.writeToFile(s, encode, new File(file), false);
	}
	/**
	 * 获取url编码
	 * @param url
	 *            要获取编码的url
	 * @return String
	 * @throws Exception
	 * @author Longtao
	 * @date 2010.02.10
	 * @framework jericho
	 */
	public static String getEncode(String url) {
		try {
			if (url.length() == 0)
				return null;
			if (url.indexOf(':') == -1 || !url.startsWith("http"))
				url = "file:" + url;
			return new Source(new URL(url)).getEncoding();
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}

	}
	/**
	 * post方式请求
	 * @since 2010-09-16
	 * @param client
	 *            httpclient
	 * @param post
	 *            postmethond
	 * @param url
	 *            地址
	 * @param params
	 *            post参数
	 * @param cookies
	 *            请求时附加cookies
	 * @param needAppendCookies
	 *            是否在返回后附加cookies
	 * @param needResponse
	 *            是否需要返回内容
	 * @return
	 * @throws IOException
	 */
	public static String processPost(HttpClient client, PostMethod post,
			String url, NameValuePair[] params, Cookie[] cookies,
			boolean needAppendCookies, boolean needResponse) throws IOException {
		if (client == null || url == null || url == "")
			return "";
		if (post == null)
			post = new PostMethod(url);

		if (params != null && params.length > 0)
			post.setRequestBody(params);
		if (cookies != null)
			post.setRequestHeader("Cookie", cookies.toString());
		client.executeMethod(post);
		if (needAppendCookies) {
			cookies = client.getState().getCookies();
			client.getState().addCookies(cookies);
		}
		if (needResponse)
			return post.getResponseBodyAsString();
		post.releaseConnection();
		return "";
	}
	/**
	 * get方式请求
	 * @param client httpclient
	 * @param get getmethond
	 * @param url 地址
	 * @param cookies 请求时附加 cookies
	 * @param needAppendCookies 是否返回后附加 cookies
	 * @param needResponse 是否需要返回
	 * @return
	 * @throws IOException
	 */
	public static String processGet(HttpClient client,GetMethod get,String url,Cookie[] cookies,boolean needAppendCookies,boolean needResponse) throws IOException{
		if(client==null || url==null || url=="") return "";
		if(get==null)
			get=new GetMethod();
		get = new  GetMethod(url);  
		if(cookies!=null)
			get.setRequestHeader("Cookie" , cookies.toString());
        client.executeMethod(get);
        if(needAppendCookies){
	        cookies = client.getState().getCookies();   
	        client.getState().addCookies(cookies);   
        }
        if(needResponse)
        	return get.getResponseBodyAsString();   
		get.releaseConnection();
		return "";
	}
	
}
