package com.iatb.util.spider.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import com.iatb.bean.Webs;

/**
 * 
 * @author Pablo
 * 
 */
public class HttpClientUtil {

	private static int connectionTimeOut = 20000;
	// 标志初始化是否完成的flag
	private static boolean initialed = false;
	public static final Logger log = Logger.getLogger(HttpClientUtil.class);

	// 获得ConnectionManager，设置相关参数
	private static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();

	private static int maxConnectionPerHost = 5;

	private static int maxTotalConnections = 40;

	private static int socketTimeOut = 20000;

	private static String ConverterStringCode(String source, String srcEncode,
			String destEncode) {
		if (source != null) {
			try {
				return new String(source.getBytes(srcEncode), destEncode);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} else {
			return "";

		}
	}

	public static String getContent2(String url, String charset,
			List<Webs> list, boolean isHtml, String split) {
		StringBuffer sb = new StringBuffer();
		if (url == null || charset == null || url.trim().equals("")
				|| charset.trim().equals("")) {
			return null;
		}
		try {
			HtmlCleaner cleaner = new HtmlCleaner();
			TagNode node = cleaner.clean(new URL(url), charset);
			if (list == null) {
				if (isHtml) {
					return node.getText().toString();
				} else {
					return cleaner.getInnerHtml(node);
				}
			}
			for (Webs w : list) {
				String clear = w.getClearAttr();
				boolean b = clear == null ? false
						: clear.trim().equals("") ? false : true;
				Object[] ns = new Object[] {};
				if (w.getAttribute() == null
						|| w.getAttribute().trim().equals("")) {
					ns = node.evaluateXPath("//" + w.getTag());
				} else {
					ns = node.evaluateXPath("//" + w.getTag() + "[@"
							+ w.getAttribute() + "=\"" + w.getValue() + "\"]");
				}
				if (isHtml) {
					if (!b) {
						for (int i = 0; i < ns.length; i++) {
							sb.append(cleaner.getInnerHtml((TagNode) ns[i]))
									.append(split);
						}
					} else {
						for (int i = 0; i < ns.length; i++) {
							if (((TagNode) ns[i]).getAttributeByName(clear) != null) {
								continue;
							}
							sb.append(cleaner.getInnerHtml((TagNode) ns[i]))
									.append(split);
						}
					}
				} else {
					if (!b) {
						for (int i = 0; i < ns.length; i++) {
							sb.append(((TagNode) ns[i]).getText().toString())
									.append(split);
						}
					} else {
						for (int i = 0; i < ns.length; i++) {
							if (((TagNode) ns[i]).getAttributeByName(clear) != null) {
								continue;
							}
							sb.append(((TagNode) ns[i]).getText().toString())
									.append(split);
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			log.info("httpclientutil:getContent2()" + e.getMessage());
			return null;
		} catch (IOException e) {
			log.info("httpclientutil:getContent2()" + e.getMessage());
			return null;
		} catch (XPatherException e) {
			log.info("httpclientutil:getContent2()" + e.getMessage());
			return null;
		}
		return sb.toString();
	}

	/**
	 * 通过get方法获取网页内容
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getGetHTMLByUrl(String url) {
		if (!StringUtil.checkNotNull(url)) {
			return null;
		}
		if (!url.trim().startsWith("http")) {
			url = "http://" + url;
		}
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url.trim());
		HttpResponse response;
		String r = null;
		try {
			response = client.execute(httpget);
			if (null != response) {
				HttpEntity entity = response.getEntity();
				r = EntityUtils.toString(entity, "GBK");
				httpget.abort();
				client.getConnectionManager().shutdown();
			}
		} catch (ClientProtocolException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			// e.printStackTrace();
		} finally {
			// httpget.abort();
			// client.getConnectionManager().shutdown();
		}
		return r;
	}
	/**
	 * 判断网页是否错误
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static boolean isError(String url) {
		if (!StringUtil.checkNotNull(url)) {
			return true;
		}
		if (!url.trim().startsWith("http")) {
			url = "http://" + url;
		}
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url.trim());
		HttpResponse response;
		String r = null;
		try {
			response = client.execute(httpget);
			if (null != response) {
				HttpEntity entity = response.getEntity();
				r = EntityUtils.toString(entity, "GBK");
				httpget.abort();
				client.getConnectionManager().shutdown();
			}
			String errors[]=Constants.URL_ERROR_403.split("[|]");
			for (int i = 0; i < errors.length; i++) {
				if(r.trim().indexOf(errors[i])>=0)return true;
			}
		} catch (ClientProtocolException e) {
			return true;
		} catch (IOException e) {
			return true;
		} 
		return false;
	}

	public static String getGetResponseByType(String url, String type) {
		HttpClient client = new HttpClient(manager);
		if (initialed) {
			HttpClientUtil.SetPara();
		}
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		String encode = "iso-8859-1";
		if (type.equals("yahoo")) {
			get.setRequestHeader("Host", "www.google.com.hk");
			get.setRequestHeader("Host", "one.cn.yahoo.com");
			encode = "utf-8";
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=one.cn.yahoo.com");
		} else if (type.equals("google")) {
			encode = "gb2312";
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=.google.com.hk");
		} else if (type.equals("baidu")) {
			get.setRequestHeader("Host", "www.baidu.com");
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=.baidu.com");
			encode = "gb2312";
		}
		try {
			client.executeMethod(get);
			BufferedReader br = new BufferedReader(new InputStreamReader(get
					.getResponseBodyAsStream(), encode));
			StringBuffer sb = new StringBuffer();
			int len = 10240;
			char c[] = new char[len];
			while (br.read(c) != -1) {
				sb.append(c);
			}
			c = null;
			br.close();
			String strGetResponseBody = sb.toString();
			if (type.equals("google")) {
				int slen = 0;
				int elen = 0;
				if (((slen = strGetResponseBody.indexOf("获得约")) > 0 || (slen = strGetResponseBody
						.indexOf("获得约")) > 0)
						&& ((elen = strGetResponseBody.indexOf("条结果", slen))) > 0) {
					String s = strGetResponseBody.substring(slen + 3, elen)
							.trim().replaceAll(",", "");
					strGetResponseBody = null;
					return s;
				} else if (strGetResponseBody.indexOf("找不到和您的查") > 0) {
					return "0";
				}
			} else if (type.equals("yahoo")) {
				int slen = 0;
				int elen = 0;
				if (strGetResponseBody.indexOf("没有找到与") > 0) {
					return "0";
				}
				if ((slen = strGetResponseBody.indexOf("网页约")) > 0
						&& ((elen = strGetResponseBody.indexOf("条", slen))) > 0) {
					String s = strGetResponseBody.substring(slen + 3, elen)
							.trim().replaceAll(",", "");
					strGetResponseBody = null;
					return s;
				} else {
					return null;
				}
			} else if (type.equals("baidu")) {
				int slen = 0;
				int elen = 0;
				slen = strGetResponseBody.indexOf("网页约") > 0 ? strGetResponseBody
						.indexOf("网页约") + 3
						: strGetResponseBody.indexOf("找到相关网页") > 0 ? strGetResponseBody
								.indexOf("找到相关网页") + 6
								: -1;
				if (slen == 0) {
					return "0";
				} else if (slen == -1) {
					return null;
				}
				if (slen > 0
						&& ((elen = strGetResponseBody.indexOf("篇", slen))) > 0) {
					String s = strGetResponseBody.substring(slen, elen).trim()
							.replaceAll(",", "");
					strGetResponseBody = null;
					return s == null ? "0" : s;
				} else {
					return null;
				}
			}
		} catch (Exception e) {
			log
					.info("Method getGetResponseByType in HttpClientUtil Exception is:"
							+ e.getMessage());
		} finally {
			get.releaseConnection();
		}
		return null;
	}

	public static String getGetResponseWithHttpClient(String url, String encode) {
		HttpClient client = new HttpClient(manager);
		if (initialed) {
			HttpClientUtil.SetPara();
		}
		GetMethod get = new GetMethod(url);
		if (url.startsWith("http://www.google")) {
			get.setRequestHeader("Host", "www.google.com.hk");
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=.google.com.hk");
		} else if (url.startsWith("http://one.cn.yahoo.com")) {
			get.setRequestHeader("Host", "one.cn.yahoo.com");
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=one.cn.yahoo.com");
		} else if (url.startsWith("http://www.baidu.com")) {
			get.setRequestHeader("Host", "www.baidu.com");
			get
					.setRequestHeader("Cookie",
							"$Version=0; EEEE=25l6ppd5upq67&b=3&s=1e; $Path=/; $Domain=.baidu.com");
		}
		get.setFollowRedirects(true);
		String result = null;
		try {
			client.executeMethod(get);
			// 在目标页面情况未知的条件下，不推荐使用getResponseBodyAsString()方法
			BufferedReader br = new BufferedReader(new InputStreamReader(get
					.getResponseBodyAsStream(), encode));
			StringBuffer sb = new StringBuffer();
			int len = 10240;
			char c[] = new char[len];
			while (br.read(c) != -1) {
				sb.append(c);
			}
			// iso-8859-1 is the default reading encode
			result = HttpClientUtil.ConverterStringCode(sb.toString(), get
					.getResponseCharSet(), encode);
		} catch (Exception e) {
			log
					.info("Method getGetResponseWithHttpClient in HttpClientUtil Exception is:"
							+ e.getMessage());
			result = "";
		} finally {
			get.releaseConnection();
		}
		return result;
	}

	/**
	 * 通过get方法获取网页内容
	 */
	public static String getGetResponseWithHttpClient(String url,
			String encode, String host, String cookie) {
		HttpClient client = new HttpClient(manager);
		if (initialed) {
			HttpClientUtil.SetPara();
		}
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		get.setRequestHeader("Accept-Language", "zh-cn");
		get.setRequestHeader("Accept-Encoding", " gzip, deflate");
		get.setRequestHeader("If-Modified-Since",
				"Thu, 29 Jul 2004 02:24:49 GMT");
		get.setRequestHeader("If-None-Match", "'3014d-1d31-41085ff1'");
		get
				.setRequestHeader("User-Agent",
						" Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
		get.setRequestHeader("Host", host);
		get.setRequestHeader("Connection", " Keep-Alive");
		get.setRequestHeader("Cookie", cookie);
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			/*
			 * client.getHostConfiguration().setProxy("localhost", 808);
			 * UsernamePasswordCredentials creds = new
			 * UsernamePasswordCredentials("chenlb", "123456");
			 * client.getState().setProxyCredentials(AuthScope.ANY, creds);
			 */
			client.executeMethod(get);
			// 在目标页面情况未知的条件下，不推荐使用getResponseBodyAsString()方法
			// String strGetResponseBody = post.getResponseBodyAsString();
			BufferedReader in = new BufferedReader(new InputStreamReader(get
					.getResponseBodyAsStream(), get.getResponseCharSet()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);
			}
			in.close();
			result = resultBuffer.toString();
			result = HttpClientUtil.ConverterStringCode(
					resultBuffer.toString(), get.getResponseCharSet(), encode);
		} catch (Exception e) {
			log
					.info("Method getGetResponseWithHttpClient in HttpClientUtil happen Exception:\n"
							+ e.getMessage());
			result = "";
		} finally {
			get.releaseConnection();
			return result;
		}
	}

	public static String getPostResponseWithHttpClient(InputStream inputStream,
			String responseCharSet, String encode) {
		String result = null;
		StringBuffer resultBuffer = new StringBuffer();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					inputStream, responseCharSet));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {

				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}

			in.close();

			// iso-8859-1 is the default reading encode
			result = HttpClientUtil.ConverterStringCode(
					resultBuffer.toString(), responseCharSet, encode);
		} catch (Exception e) {
			e.printStackTrace();

			result = "";
		} finally {

			return result;
		}
	}

	public static String getPostResponseWithHttpClient(String url, String encode) {
		HttpClient client = new HttpClient(manager);

		if (initialed) {
			HttpClientUtil.SetPara();
		}

		PostMethod post = new PostMethod(url);
		post.setFollowRedirects(false);

		StringBuffer resultBuffer = new StringBuffer();

		String result = null;

		try {
			client.executeMethod(post);

			BufferedReader in = new BufferedReader(

			new InputStreamReader(post.getResponseBodyAsStream(), post
					.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {
				resultBuffer.append(inputLine);

				resultBuffer.append("\n");
			}

			in.close();

			// iso-8859-1 is the default reading encode
			result = HttpClientUtil.ConverterStringCode(
					resultBuffer.toString(), post.getResponseCharSet(), encode);
		} catch (Exception e) {
			e.printStackTrace();
			result = "";
		} finally {
			post.releaseConnection();
			return result;
		}
	}

	public static String getPostResponseWithHttpClient(String url,
			String encode, NameValuePair[] nameValuePair) {
		HttpClient client = new HttpClient(manager);

		if (initialed) {
			HttpClientUtil.SetPara();
		}

		PostMethod post = new PostMethod(url);
		post.setRequestBody(nameValuePair);
		post.setFollowRedirects(false);

		String result = null;
		StringBuffer resultBuffer = new StringBuffer();

		try {
			client.executeMethod(post);
			BufferedReader in = new BufferedReader(new InputStreamReader(post
					.getResponseBodyAsStream(), post.getResponseCharSet()));
			String inputLine = null;

			while ((inputLine = in.readLine()) != null) {

				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}

			in.close();

			// iso-8859-1 is the default reading encode
			result = HttpClientUtil.ConverterStringCode(
					resultBuffer.toString(), post.getResponseCharSet(), encode);
		} catch (Exception e) {
			e.printStackTrace();

			result = "";
		} finally {
			post.releaseConnection();

			return result;
		}
	}

	/**
	 * 从商务通数据库查询
	 * @param sql
	 * @return
	 */
	public static String getSwtXml(String sql, String ecid) {
		try {
			String reqURL = Constants.SWT_URL
					+ "?sql="
					+ URLEncoder.encode(sql, "UTF-8")
					+ "&datapath="
					+ URLEncoder.encode(Constants.SWT_PATH + ecid
							+ java.io.File.separator + Constants.SWT_DB_PREFIX
							+ Constants.SWT_DB_SUFFIX, "UTF-8");
			URL url = new URL(reqURL);
			URLConnection connection = url.openConnection();
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream(), "utf-8");
			StringBuffer sb = new StringBuffer();
			while (in.hasNext()) {
				sb.append(in.nextLine());
			}
			return sb.toString();
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return null;
		}
	}

	public static String getUrlContent(String u, String encode) {
		try {
			URL url = new URL(u);
			URLConnection connection = url.openConnection();
			connection.connect();
			Scanner in = new Scanner(connection.getInputStream(), encode);
			StringBuffer sb = new StringBuffer();
			while (in.hasNext()) {
				sb.append(in.nextLine());
			}
			return sb.toString();
		} catch (IOException e) {
			log.info(e.getMessage(), e);
			return null;
		}
	}

	public static String getWithCookie(String url, List<Webs> list, String cookie) {
		HttpClient client = new HttpClient(manager);
		if (initialed) {
			HttpClientUtil.SetPara();
		}
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(true);
		get.setRequestHeader("Cookie", cookie);
		StringBuffer sb = new StringBuffer();
		try {
			int statusCode = client.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HtmlCleaner cleaner = new HtmlCleaner();
			InputStream is = get.getResponseBodyAsStream();
			TagNode node = cleaner.clean(is);
			is.close();
			for (Webs w : list) {
				Object[] ns = new Object[] {};
					ns = node.evaluateXPath("//" + w.getTag() + "[@"
							+ w.getAttribute() + "=\"" + w.getValue() + "\"]");
					for (int i = 0; i < ns.length; i++) {
						sb.append(((TagNode) ns[i]).getText().toString())
								.append("|");
					}
			}
		} catch (Exception e) {
			log.info("httpClientUtil1:getWithCookie()" + e.getMessage());
			return null;
		} finally {
			get.releaseConnection();
			return sb.toString();
		}
	}

	/**
	 * 从商务通数据库查询
	 * 
	 * @param sql
	 * @return
	 */
	public static String queryAsXML(String sql, String ecid) {
		try {
			String reqURL = Constants.SWT_URL
					+ "?sql="
					+ URLEncoder.encode(sql, "UTF-8")
					+ "&datapath="
					+ URLEncoder.encode(Constants.SWT_PATH + ecid
							+ java.io.File.separator + Constants.SWT_DB_PREFIX
							+ Constants.SWT_DB_SUFFIX, "UTF-8");
			System.out.println(reqURL);
			URL url = new URL(reqURL);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(300000);
			connection.setReadTimeout(300000);
			connection.connect();
			InputStream in = connection.getInputStream();

			StringBuffer xmlBuff = new StringBuffer();
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					in, "UTF-8"));
			while ((line = reader.readLine()) != null) {
				xmlBuff.append(line);
			}
			reader.close();
			in.close();
			return xmlBuff.toString();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
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
	// 初始化ConnectionManger的方法
	public static void SetPara() {
		manager.getParams().setConnectionTimeout(connectionTimeOut);
		manager.getParams().setSoTimeout(socketTimeOut);
		manager.getParams().setDefaultMaxConnectionsPerHost(
				maxConnectionPerHost);
		manager.getParams().setMaxTotalConnections(maxTotalConnections);
		initialed = true;
	}
}
