package com.iatb.util.porn;

import java.net.URL;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

public class xxxutil {

	public static void main(String[] args) {
		try {
			String url="http://www.eroxia.com/?ctr=filter&act=videos";
			//http://homesexdaily.com/
			HtmlCleaner cleaner = new HtmlCleaner();  
			String encode=com.iatb.util.http.MyHtmlCleaner.getEncode(url);
			TagNode node = cleaner.clean(new URL(url),encode);    
			Object[] ns =  node.evaluateXPath("//a"); 
			for (int i = 0; i < ns.length; i++) { 
				try {
					TagNode n = (TagNode) ns[i];
					System.out.println(n.getText()==null?"":n.getText().toString()+":::"+n.getAttributes().get("href")==null?"":n.getAttributes().get("href").toString());
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}
