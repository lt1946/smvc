/**
 * 
 */
package com.iatb.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

/**
 * @author Administrator
 *
 */
public class MyJdom {
	
	private static MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();

	public static void main(String[] args) throws MalformedURLException, JDOMException, IOException {
		int c=getSizeFromXpath("http://gxtuan.com/api/baidu.php","/urlset/url/loc");
		System.out.println(c);
	}
	
	/**
	 * 获取根元素
	 * @param url
	 * @return
	 */
	public static Element getRoot(String url) {
			try {
				if(url.indexOf("www.didatuan.com")!=-1)return null ;	//TODO later java.lang.OutOfMemoryError: Java heap space  smvc
				SAXBuilder builder = new SAXBuilder();
				HttpClient client = new HttpClient(manager);
				GetMethod get = new GetMethod(url);
				get.setRequestHeader("User-Agent"," Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; InfoPath.2)");
				get.setFollowRedirects(true);
				get.setRequestHeader("Connection"," Keep-Alive");
				  int statusCode = client.executeMethod(get);
				   if (statusCode != HttpStatus.SC_OK) {
					   return null;
				   }
				InputStream is=get.getResponseBodyAsStream();
				Document doc = builder.build(is);
				is.close();
				return doc.getRootElement();
			} catch (Exception e) {
				return null;
			}
	}
	
	/**
	 * 获取根元素
	 * @param url
	 * @return
	 */
	public static Element getRoot(InputStream is) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(is);
			return doc.getRootElement();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取元素集合
	 * @param url
	 * @param xpath
	 * @return
	 */
	public static List<Element> getElementFromXpath(String url,String xpath) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new URL(url));
			Element root=doc.getRootElement();
			return XPath.selectNodes(root,xpath);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取元素个数
	 * @param url
	 * @param xpath
	 * @return
	 */
	public static int getSizeFromXpath(String url,String xpath){
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new URL(url));
				Element root=doc.getRootElement();
				return XPath.selectNodes(root,xpath).size();
			} catch (Exception e) {
				return -1;
			}
	}
	
}
