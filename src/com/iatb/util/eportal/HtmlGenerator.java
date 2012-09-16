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


/** ��̬ҳ������ */
public class HtmlGenerator   {
	public static Logger logger = Logger.getLogger(BaseLog.class);
	HttpClient httpClient = null; //HttpClientʵ��
	GetMethod getMethod =null; //GetMethodʵ��
	BufferedWriter fw = null;
	String page = null;
	String webappname = null;
	BufferedReader br = null;
	InputStream in = null;
	StringBuffer sb = null;
	String line = null;

	//���췽��
	public HtmlGenerator(String webappname){
		this.webappname = webappname;

	}

	/** ����ģ�漰����������̬ҳ�� */
	public boolean createHtmlPage(String url,String htmlFileName){
		boolean status = false;
		int statusCode = 0;
		try{
			httpClient = new HttpClient();
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"gbk");
			getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			//����Get�����ύ����ʱʹ�õ��ַ���,��֧�����Ĳ�������������
			getMethod.addRequestHeader("Content-Type","text/html;charset=gbk");
			//ִ��Get������ȡ�÷���״̬�룬200��ʾ��������������Ϊ�쳣
			statusCode = httpClient.executeMethod(getMethod);
			if (statusCode!=200) {
				logger.fatal("��̬ҳ�������ڽ���"+url+"������̬ҳ��"+htmlFileName+"ʱ����!");
			}else{
				//��ȡ�������
				sb = new StringBuffer();
				in = getMethod.getResponseBodyAsStream();
				br = new BufferedReader(new InputStreamReader(in));
				while((line=br.readLine())!=null){
					sb.append(line+"\n");
				}
				if(br!=null)br.close();
				page = sb.toString();
				//��ҳ���е����·���滻�ɾ���·������ȷ��ҳ����Դ��������
				page = formatPage(page);
				//���������д��ָ���ľ�̬HTML�ļ��У�ʵ�־�̬HTML����
				writeHtml(htmlFileName,page);
				status = true;
			}
		}catch(Exception ex){
			logger.fatal("��̬ҳ�������ڽ���"+url+"������̬ҳ��"+htmlFileName+"ʱ����:"+ex.getMessage());
        }finally{
        	//�ͷ�http����
        	getMethod.releaseConnection();
        }
		return status;
	}

	//���������д��ָ���ľ�̬HTML�ļ���
	private synchronized void writeHtml(String htmlFileName,String content) throws Exception{
		fw = new BufferedWriter(new FileWriter(htmlFileName));
		fw.write(content);
		if(fw!=null)fw.close();
	}

	//��ҳ���е����·���滻�ɾ���·������ȷ��ҳ����Դ��������
	private String formatPage(String page){
		page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./", webappname+"/");
		return page;
	}

	//���Է���
	public static void main(String[] args){
		HtmlGenerator h = new HtmlGenerator("");
		h.createHtmlPage("http://www.qq.com","c:/a.html");
	}

}
