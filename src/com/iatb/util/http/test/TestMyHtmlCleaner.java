package com.iatb.util.http.test;

import java.util.Map;
import org.junit.Test;
import com.iatb.util.http.MyHtmlCleaner;
import com.iatb.util.http.MyHttp2;

public class TestMyHtmlCleaner {
	//TODO  download rss
	public final String propertiesEditorUrl="http://propedit.sourceforge.jp/eclipse/updates";
	public final String propertiesEditorfilePath="D:\\java\\MyEclipse\\MyEclipse 10\\plugins\\propertiesEditor\\";

	public final String vjetUrl="https://www.ebayopensource.org/p2/vjet/eclipse/";
	public final String vjetUrlfilePath="D:\\java\\MyEclipse\\MyEclipse 10\\plugins\\propertiesEditor\\";
	
//	public final String mobilePhonegapTollUrl="http://svn.codespot.com/a/eclipselabs.org/mobile-web-development-with-phonegap/tags/r1.2.9/download/";
//	public final String mobilePhonegapTollfilePath="D:\\java\\MyEclipse\\MyEclipse 10\\plugins\\mobile-web-development-with-phonegap\\";
	
	@Test
	public void downDirectory(){
		 Map<String, String> m=MyHtmlCleaner.getAllLink(vjetUrl, "iso-8859-1");
		 for(Map.Entry<String, String> mm:m.entrySet()){
			 if(mm.getValue().indexOf("?")>0||mm.getKey().equals("Parent Directory"))continue;
			 System.out.println(mm.getKey());
			 System.out.println(mm.getValue());
			 if(mm.getValue().endsWith("/")){
				 String dirname[]=mm.getValue().split("/");
				 downDirectory(mm.getValue(),dirname[dirname.length-1],vjetUrlfilePath);
			 }else 
				MyHttp2.download(mm.getValue(), vjetUrlfilePath, "",true, true);
		 }
	}
	/**下载url子目录文件
	 * @param path				url子目录
	 * @param directoryName		保存本地文件相对文件目录
	 * @param filePath		保存本地文件起始目录
	 */
	private void downDirectory(String path,String directoryName,String filePath){
		 Map<String, String> m=MyHtmlCleaner.getAllLink(path, "iso-8859-1");
		 for(Map.Entry<String, String> mm:m.entrySet()){
			 if(mm.getValue().indexOf("?")>0||mm.getKey().equals("Parent Directory"))continue;
			 System.out.println(mm.getKey());
			 System.out.println(mm.getValue());
			 if(mm.getValue().endsWith("/")){
				 String dirname[]=mm.getValue().split("/");
				 downDirectory(mm.getValue(),directoryName+"\\"+dirname[dirname.length-1],filePath);				 
			 }else 
				MyHttp2.download(mm.getValue(), filePath+directoryName, "",true, true);
		 }
	}
}
