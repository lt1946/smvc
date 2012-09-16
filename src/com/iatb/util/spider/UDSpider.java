package com.iatb.util.spider;

import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class UDSpider {
	/**
	 * 从115下载页面url，自动获取下载文件的真正电信下载地址
	 * @param url	下载页面
	 * @return
	 */
	public static String get115dlink(String url){
		try {
			HtmlCleaner cleaner = new HtmlCleaner();   
			TagNode node = cleaner.clean(new URL(url),"utf8"); 
//			Object o[]=node.evaluateXPath("//div[@class=\"file-info\"]");
//			System.out.println(o.length);
//			String time=((TagNode)(node.evaluateXPath("//div[@class='file-info']")[0]).getText().toString();
//			System.out.println(time);
			String t=node.getText().toString();
			int i=t.indexOf("url: \"");
			if(i>=0){
				String u="http://115.com/file"+t.substring(i+6,t.indexOf("\"", i+8));
				System.out.println(u);
				node = cleaner.clean(new URL(u),"utf8");
				t=node.getText().toString();
				String tj="{\"client\":1,\"url\":\"";
				int j=t.indexOf(tj)+tj.length();
				if(j>=0){
					u=t.substring(j,t.indexOf("\"", j));
					return (u.replace("\\", ""));
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
}
