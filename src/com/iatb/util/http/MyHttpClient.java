package com.iatb.util.http;

import java.io.IOException;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;

public class MyHttpClient {

	private HttpClient client;
	private HttpMethodBase method;
	private String stringData;

	public MyHttpClient() {
	   this.client = new HttpClient();
	   this.method = new GetMethod();
	}

	public void setParams(String url) throws URIException, NullPointerException {
	   this.client.getParams().setParameter("http.socket.timeout", 10000); //   为HttpClient设置参数
	   this.client.getHttpConnectionManager().getParams().setParameter("http.socket.timeout", 1); //   为HttpConnetionManager设置参数
	   this.method.getParams().setParameter("http.socket.timeout", 10000); //   为HttpMethod设置参数
	   HostConfiguration hostConf = new HostConfiguration();
	   hostConf.setHost(new URI(url, true));
	   this.client.setHostConfiguration(hostConf);
	} 

	public void execute() throws HttpException, IOException {
	   int status = this.client.executeMethod(this.method);
	   if(status == HttpStatus.SC_OK) {
		   this.stringData = new String(this.method.getResponseBody(), "gb2312");
	   }
	}

	public void print() {
	   System.out.println(this.stringData);
	}

	public static void main(String[] args) {
	   MyHttpClient client = new MyHttpClient();
	   try {
	    client.setParams("http://www.csdn.net");
	    client.execute();
	    client.print();
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	}
}
