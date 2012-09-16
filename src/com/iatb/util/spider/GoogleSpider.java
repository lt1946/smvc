package com.iatb.util.spider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import com.iatb.util.http.MyHtmlCleaner;

public class GoogleSpider {

	private static final String search_url="http://www.google.com.hk/search?hl=zh-CN&q=";
	private static final String search_num="&num=";
	private static final String search_strart="&start=";
	
	/**
	 * 通过google(关键词，开始个数，每页个数)搜索获取title,url
	 * @param kw		关键词
	 * @param start		开始个数
	 * @param num		每页个数
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Map<String, String> getUrl(String kw,int start,int num){
		try {
			MyHtmlCleaner mc=new MyHtmlCleaner();
			kw = URLEncoder.encode(kw, "utf8");
			String url = search_url+kw+search_strart+start+search_num+num;
			return mc.getAllLink(url, "utf8", "h3,class,r");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		
	
	}
}
