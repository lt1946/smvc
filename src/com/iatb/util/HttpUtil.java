package com.iatb.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class HttpUtil {
	private static final Logger log=Logger.getLogger(HttpUtil.class);

	public static boolean existUrl(String url){
		try {
			int status=((HttpURLConnection)new URL(url).openConnection()).getResponseCode();
			return status==200?true:false;
		} catch (MalformedURLException e) {
			log.error("¼ì²éurl×´Ì¬Òì³££¡"+url+e.getMessage());
//			e.printStackTrace();
			return false;
		} catch (IOException e) {
			log.error("¼ì²éurl×´Ì¬Òì³££¡"+url+e.getMessage());
//			e.printStackTrace();
			return false;
		}
	}
}
