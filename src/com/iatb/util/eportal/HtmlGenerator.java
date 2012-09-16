package com.iatb.util.eportal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import com.iatb.bean.BaseLog;


/** 静态页面引擎 */
public class HtmlGenerator   {
	public static Logger logger = Logger.getLogger(BaseLog.class);
	HttpClient httpClient = null; //HttpClient实例
	GetMethod getMethod =null; //GetMethod实例
	BufferedWriter fw = null;
	String page = null;
	String webappname = null;
	BufferedReader br = null;
	InputStream in = null;
	StringBuffer sb = null;
	String line = null;

	//构造方法
	public HtmlGenerator(String webappname){
		this.webappname = webappname;

	}

	/** 根据模版及参数产生静态页面 */
	public boolean createHtmlPage(String url,String htmlFileName){
		boolean status = false;
		int statusCode = 0;
		try{
			httpClient = new HttpClient();
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			//设置Get方法提交参数时使用的字符集,以支持中文参数的正常传递
			getMethod.addRequestHeader("Content-Type","text/html;charset=gbk");
			//执行Get方法并取得返回状态码，200表示正常，其它代码为异常
			statusCode = httpClient.executeMethod(getMethod);
			if (statusCode!=200) {
				logger.fatal("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错!");
			}else{
				//读取解析结果
				sb = new StringBuffer();
				in = getMethod.getResponseBodyAsStream();
				br = new BufferedReader(new InputStreamReader(in));
				while((line=br.readLine())!=null){
					sb.append(line+"\n");
				}
				if(br!=null)br.close();
				page = sb.toString();
				//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
				page = formatPage(page);
				//将解析结果写入指定的静态HTML文件中，实现静态HTML生成
				writeHtml(htmlFileName,page);
				status = true;
			}
		}catch(Exception ex){
			logger.fatal("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错:"+ex.getMessage());
        }finally{
        	//释放http连接
        	getMethod.releaseConnection();
        }
		return status;
	}

	//将解析结果写入指定的静态HTML文件中
	private synchronized void writeHtml(String htmlFileName,String content) throws Exception{
		fw = new BufferedWriter(new FileWriter(htmlFileName));
		fw.write(content);
		if(fw!=null)fw.close();
	}

	//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
	private String formatPage(String page){
		page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./", webappname+"/");
		return page;
	}

	//测试方法
	public static void main(String[] args){
		HtmlGenerator h = new HtmlGenerator("");
		h.createHtmlPage("http://www.qq.com","c:/a.html");
	}

}
