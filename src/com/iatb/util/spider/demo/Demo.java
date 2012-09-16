package com.iatb.util.spider.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Demo {

	public static void main(String[] args) {
		Demo demo = new Demo();
//		demo.Test();
		System.out.println("baidu������"+demo.getBaiduRank("ǰ����", "www.nk91.com"));
		System.out.println("google������"+demo.getGoogleRank("ǰ����", "www.nk91.com"));
	}
	@SuppressWarnings("unchecked")
	public int getBaiduRank(String keyWords,String address) {
		int rank = 0;
		int ahead = 0;
		try {
			keyWords = URLEncoder.encode(keyWords, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpClientUtil hc = new HttpClientUtil();
		String firstUrl = "http://www.baidu.com/s?wd="+keyWords;//��һҳ��url
		String startStr = "<html>";//��ʼ�ַ���
    	String endStr = "</html>";//�����ַ���
		String firstresult = hc.getGetResponseWithHttpClient(firstUrl, "gb2312");
		RegexUtil util = new RegexUtil();
		String firstString = util.getBetween(firstresult,startStr,endStr);//��ȡ��ҳ������Ϣ
		String pageString = util.getBetween(firstString, "<div class=\"p\">", "</div>");//��ȡ��ҳ�ַ���
		List pageList = util.getAllMatched(pageString, "href=(.*?)>\\[", 0);//����ÿҳ��url���ַ���
		List allPages = new ArrayList();
		allPages.add(firstUrl);
		allPages.addAll(pageList);//1��10ҳ��url������2-10ҳ��Ҫ����
		//��ȡÿҳ��Ϣ
		for(int i=0; i<allPages.size(); i++) {
			String url = (String) allPages.get(i);
			Pattern p = Pattern.compile("(href=)(.*?)(>\\[)");
			Matcher m = p.matcher(url);
			while(m.find()) {
				url = m.group(2);
				url = "http://www.baidu.com/" + url;
			}
			System.out.println("baiduurl:"+url);
			String resultStr = hc.getGetResponseWithHttpClient(url, "gb2312");
        	String infoStr = util.getBetween(resultStr,startStr,endStr);//��ȡ��ҳ������Ϣ
        	String reg = "cellpadding=\"0\" cellspacing=\"0\" id=\"(.*?)\">(.*?)</table>";//�ٶ�������ÿ����Ϣ������
	    	List resultList = util.getAllMatched(infoStr, reg, 0);//ÿ��������Ϣ
	    	for(int j=0; j<resultList.size(); j++) {//ѭ���ж��Ƿ������Ӧ����ַ
	    		String str = (String) resultList.get(j);
	    		String strReg = "href=\"(.*?)\" target=\"_blank\"";
	    		String urlResult = util.getMatchedStr(str, strReg, 1);//������ַ���ַ���
	    		urlResult = str.replace("<b>", "");
	    		urlResult = str.replace("</b>", "");
	    		if(null != urlResult && urlResult.contains(address)) {
	    			rank = ahead + j + 1;
	    			return rank;
	    		}
	    	}
	    	if(rank == 0) {
	    		ahead += resultList.size();
	    	}
		}
		return rank;
	}
	
	public int getGoogleRank(String keyWords,String address) {
		int rank = 0;
		int ahead = 0;
		try {
			keyWords = URLEncoder.encode(keyWords, "gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		HttpClientUtil hc = new HttpClientUtil();
		String firstUrl = "http://www.google.cn/search?hl=zh-CN&q="+keyWords;//��һҳ��url
		String firstresult = hc.getGetResponseWithHttpClient(firstUrl, "gb2312");
		RegexUtil util = new RegexUtil();
		String pageString = util.getBetween(firstresult, "<table id=nav align=center", "</table>");//��ȡ��ҳ�ַ���
		List pageList = util.getAllMatched(pageString, "<td><a href=\"(.*?)><", 0);//����ÿҳ��url���ַ���
		List allPages = new ArrayList();
		allPages.add(firstUrl);
		allPages.addAll(pageList);//1��10ҳ��url������2-10ҳ��Ҫ����
		//��ȡÿҳ��Ϣ
		for(int i=0; i<allPages.size(); i++) {
			String url = (String) allPages.get(i);
			Pattern p = Pattern.compile("(href=\")(.*?)(><)");
			Matcher m = p.matcher(url);
			while(m.find()) {
				url = m.group(2);
				url = url.replace("amp;", "");
				url = "http://www.google.cn" + url;
			}
			System.out.println("googleurl:"+url);
			String resultStr = hc.getGetResponseWithHttpClient(url, "gb2312");
			String sstr = "<div id=res class=med";
			String estr = "<p id=mfr";
        	String infoStr = util.getBetween(resultStr,sstr,estr);//��ȡ��ҳ������Ϣ
        	String[] resultArrays = infoStr.split("<li class=");
	    	for(int j=1; j<resultArrays.length; j++) {//ѭ���ж��Ƿ������Ӧ����ַ
	    		String str = resultArrays[j];
	    		str = str.replaceAll("<.*?>", "");
	    		if(str.contains(address)) {
	    			rank = ahead + j;
	    			return rank;
	    		}
	    	}
	    	if(rank == 0) {
	    		ahead += resultArrays.length - 1;
	    	}
		}
		return rank;
	}
	
	public void Test() {
		HttpClientUtil hc = new HttpClientUtil();
		String url = "http://www.google.cn/search?hl=zh-CN&newwindow=1&q=%E7%94%B7%E7%A7%91&btnG=Google+%E6%90%9C%E7%B4%A2";
		RegexUtil util = new RegexUtil();
		String firstresult = hc.getGetResponseWithHttpClient(url, "gb2312");
		String firstString = util.getBetween(firstresult,"<div id=res class=med>","id=mfr");//��ȡ��ҳ������Ϣ
		System.out.println(firstString);
	}
}
