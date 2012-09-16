/**
 * 
 */
package com.iatb.util.use.gb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.util.http.MyHttp2;

/**
 * @author Administrator
 *
 */
public class PostSite {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		signupgroupbuy();
//		loginGroupbuy();
//		inviteSignup2();
		lookrefer();
	}

	public static void loginGroupbuy() throws IOException {
		String login="http://www.wktuan.com/account/login.php";
		HttpClient client =new HttpClient();
		client.getHostConfiguration().setHost(login, 80);
		PostMethod post=new PostMethod(login);
		NameValuePair []nvp=new NameValuePair[] {
				new NameValuePair("email","lt1946@163.com"),
				new NameValuePair("password","ffffffgg")
//				new NameValuePair("auto-login","11"),
//				new NameValuePair("commit","%E7%199%BB%E5%BD%95")
		};
		Cookie []cookies=client.getState().getCookies(); 
		MyHttp2.processPost(client, post, login, nvp, cookies, true, false); 
		Header header=post.getResponseHeader("Location");
		try {
			String redirectUrl=header.getValue();
			System.out.println(redirectUrl);
			if(redirectUrl.equals("/index.php")) {
				System.out.println("µÇÈë³É¹¦£¡");
			}else {
				System.out.println("µÇÈëÊ§°Ü£¡");
			}
		} catch (RuntimeException e) {
			System.out.println("µÇÈëÒì³££¡");
		}
	}

	/**
	 * @since  2010-09-16
	 * @throws Exception
	 */
	public  static void signupgroupbuy()throws Exception{
		HttpClient client =new HttpClient();
		String invite="http://www.pintuan.com/r.php?r=146142305";
		String signup="http://www.wktuan.com/account/signup.php";
//		String signup="http://www.52apw.com/account/signup.php";
//		String signup="http://www.yiqituan.net/account/register/";
		client.getHostConfiguration().setHost(signup, 80);
		PostMethod post=new PostMethod(signup);
		NameValuePair []nvp=new NameValuePair[] {
				new NameValuePair("email","lt1946@163.com"),
				new NameValuePair("username","lt1946"),
				new NameValuePair("password","ffffffgg"),
				new NameValuePair("password2","ffffffgg"),
//				new NameValuePair("user_pwd_confirm","ffffffgg"),
				new NameValuePair("mobile","13426465170"),
				new NameValuePair("city_id","1"),
//				new NameValuePair("cityid","1"),
				new NameValuePair("subscribe","1"),
//				new NameValuePair("user_pwd_ziduan2","beijing"),
				new NameValuePair("commit","%E6%B3%A8%E5%86%8C")
		};
		Cookie []cookies=client.getState().getCookies(); 
		MyHttp2.processPost(client, post, signup, nvp, cookies, true, false); 
		Header header=post.getResponseHeader("Location");
		try {
			String redirectUrl=header.getValue();
			System.out.println(redirectUrl);
			if(redirectUrl.equals("/index.php")) {
				System.out.println("×¢²á³É¹¦£¡");
			}else {
				System.out.println("×¢²áÊ§°Ü£¡");
			}
		} catch (RuntimeException e) {
			System.out.println("×¢²áÒì³££¡");
		}
	}
	@SuppressWarnings("deprecation")
	public static void inviteSignup2() throws IOException {
//		String invite="http://www.kaolatuan.com/r.php?r=844";  ok
//		String invite="http://www.yiketuan.com/r.php?r=12367";
		Map<String,String> map=new HashMap<String, String>();
		map.put("email","ds23sf@163.com");
		map.put("username","dsf3s23");
		map.put("password","111111");
		map.put("password2","111111");
		map.put("mobile","13423216521");
		map.put("city_id","1");
		map.put("subscribe","1");
		map.put("commit","%E6%B3%A8%E5%86%8C");
		String signup="http://www.yiketuan.com/account/signup.php";
//		String redirectUrl=MyHttp.post2(invite,signup, map);
		String redirectUrl=MyHttp2.post(signup, map,"a60a_city=10; cnzz_a2255335=21; sin2255335=; rtime=0; ltime=1284713511497; cnzz_eid=96488021-1284713257-; PHPSESSID=559vl72lu98129eqddkugcrdi5; a60a__rid=12367");
		if(redirectUrl==null) {
			System.out.println("×¢²áÒì³££¡");
		}else if(redirectUrl.equals("/index.php")) {
			System.out.println("×¢²á³É¹¦£¡");
		}else {
			System.out.println("×¢²áÊ§°Ü£¡");
		}
	}
	public static void lookrefer() {
			String refer="http://www.dytuan.com/account/refer.php";
			String login="http://www.dytuan.com/account/login.php";
			Map<String,String> map=new HashMap<String, String>();
			map.put("email","lt1946@163.com");
			map.put("password","ffffffgg");
			boolean b=MyHttp2.loginCheckString(login, map, refer, "/index.php", "h8roer","","",false);
			System.out.println(b?"°üº¬":"²»°üº¬");
	}
}
