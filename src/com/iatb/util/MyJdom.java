/**
 * 
 */
package com.iatb.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder.build(new URL(url));
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
