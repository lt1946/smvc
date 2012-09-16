/**
 * 
 */
package com.iatb.util.use.gb;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.XPatherException;

import com.javaBean.Buy;
import com.javaBean.site;
import com.util.db.ConnTools;
import com.util.http.MyHtmlCleaner;
import com.util.http.MyHttp2;
import com.util.string.MyString;

/**
 * @author Administrator
 *
 */
public class OneGB {

	public static void main(String[] args) throws MalformedURLException, XPatherException, IOException {
//		String signuphp="http://www.onezona.com/account/signup.php";
//		regedit(signuphp);
//		checkEmail("h8roer", null,1);
		String siteUrl="http://www.onezona.com";
		buy(siteUrl, null,  61);
	}
	

	public static void buy(String siteUrl,Buy buy,int id) throws UnsupportedEncodingException {
		String name="";
		String pass="";
		String  phone="";
		String  address="";
		if(buy==null||buy.getName().equals("lt1946")) {
			 name="lt1946";
			 pass="asdfgdsa";
			  phone="13426465170";
			  address="北京市朝阳区东四环中路78号大成国际中心二号楼B2座0617";
		} else {
			 name=buy.getName();
			 pass=buy.getPass();
			  phone=buy.getPhone();
			  address=buy.getAddress();
		}
		Connection congroupbuy = ConnTools.makeConnection("groupbuy");
		List<site> list=ConnTools.query(congroupbuy, "select * from site where siteUrl='"+siteUrl+"'",site.class );
		site s=list.get(0);
		String login=s.getLoginUrl();
		Map<String, String> map3 = new HashMap<String, String>();
		map3.put("email", name);
		map3.put("password", pass);
//		map3.put("auto-login", "1");
		map3.put("commit", "登录");
		String b="5f81_city=14; kpJ_cookietime=2592000; uchome_loginuser="+name+"; PHPSESSID=ec745e0644f8201e1af2ed7eea821cfb; 5f81_ru=MzcyOEAxZDAzYTM0Y2Q3ZWZhYjcyOGMzN2EyMzE0ZGI0M2UxYQ%3D%3D; kpJ_loginuser="+name+"; kpJ_activationauth=2a28n7hTmXAOHKSdqmqmbdnBkuO6jDxmSziwKwwOWsuTsR4; __utma=194220274.1171838941.1286435992.1286435992.1286435992.1; __utmb=194220274.47.10.1286435992; __utmz=194220274.1286435992.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmc=194220274"+name+"; __utma=194220274.1171838941.1286435992.1286435992.1286435992.1; __utmb=194220274.10.10.1286435992; __utmc=194220274; __utmz=194220274.1286435992.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";
		String b2 = MyHttp2.postGetCookie(login, map3, "", "");
		System.out.println(b2);
		 map3 = new HashMap<String, String>();
		 map3.put("mobile", phone);
		 map3.put("service", "credit");
//		map3.put("address", address);
//		map3.put("buy", "确认无误，购买");	
//		map3.put("mobile", phone);
		map3.put("quantity", "1");	
//		map3.put("realname",name);
//		map3.put("zipcode", "100086");
//		String location =MyHttp.postUtf8("http://localhost/team/buy.php?id="+id, map3, b2);
		String location =MyHttp2.postUtf8(siteUrl+"/team/buy.php?id="+id, map3, b);
		System.out.println(location);
		map3 = new HashMap<String, String>();
		map3.put("order_id", location.substring(location.indexOf("=")+1));
		map3.put("service", "credit");		
//		String location2 =MyHttp.post("http://localhost/order/pay.php?id="+map3.get("order_id"), map3, b2);
//		String location2 =MyHttp.post(siteUrl+"/order/pay.php?id="+map3.get("order_id"), map3, b2);
		String location2 =MyHttp2.post(siteUrl+"/order/check.php?act=confirmOrder"+map3.get("order_id"), map3, b2);
		System.out.println(location2);
		
	}	

	/**
	 * 从163获取验证url，并激活
	 * @since 2010-09-19
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void checkEmail(String name,String password,int pageid) throws MalformedURLException,
			XPatherException, IOException {
		if(name==null||name.equals("lt1946")) {
			name="lt1946";
			password="asdfgdsa";
		}else if(name.equals("h8roer")) {
			password="ffffffgg";
		}
		String url = "https://reg.163.com/logins.jsp?type=1&url=http://entry.mail.163.com/coremail/fcg/ntesdoor2?lightweight%3D1%26verifycookie%3D1%26language%3D-1%26style%3D-1";
		String cookie = "P_INFO="
				+ name
				+ "@163.com|1284720763|0|mail163|11&21|bej&1284439878&mail163#bej&null#10|134170&0; MAIL163_SSN="
				+ name
				+ "; USERTRACK=219.143.144.133.1281943308198303; Province=010; City=010; _ntes_nnid=ee739e2b5659b7d745b1b4175a24c2a4,0; _ntes_nuid=ee739e2b5659b7d745b1b4175a24c2a4; ntes_ucc=; NTES_UFC=6000000000000000100000000000000000000000000000000000000000000000";
		Map<String, String> data = new HashMap<String, String>();
		 data.put("username", name);
		 data.put("password", password);
		data.put("selType", "-1");
		data.put("remUser", "on");
		data.put("secure", "secure");
		data.put("verifycookie", "1");
		data.put("style", "-1");
		data.put("savelogin", "1");
		data.put("url2", "http://mail.163.com/errorpage/err_163.htm");
		String b = MyHttp2.postGetCookie(url, data, cookie, "");
		String NTES_SESS = b.split(";")[0].split("=")[1];
		String encode = "utf-8";
		for (int page = 1; page <=pageid; page++) {
			String emailurl = "http://m.mail.163.com/xm/welcome.do?sid=eAXfDPYYJCzOWLmRgsYYTbvoFYxEmecQ&cookie="
					+ NTES_SESS
					+ "&go=index&keyfrom=index&page="
					+ String.valueOf(page);
			Map<String, String> d = MyHttp2.getAllLink(emailurl, encode);
			Set<String> key = d.keySet();
			for (String k : key) {
				if (k.indexOf("验证") != -1) {
					String url2 = "http://m.mail.163.com/xm/"
							+ MyString.decode((d.get(k))).trim();
					String t = MyHtmlCleaner.getCode(url2, "utf-8", "Email：",
							" ").trim();
					if (t.indexOf("--") != -1)
						t = t.substring(0, t.indexOf("--"));
					System.out.println(t);
					MyHttp2.get(t, "", false);
				}
			}
		}
	}
	public static void regedit(String signuphp) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "h8roer@163.com");
		map.put("username", "h8roer");
		map.put("password", "111111");
		map.put("password2", "111111");
		map.put("mobile", "13421265170");
//		map.put("email", "lt1946@163.com");
//		map.put("username", "lt1946");
//		map.put("password", "ffffffgg");
//		map.put("password2", "ffffffgg");
//		map.put("mobile", "13426465170");
		map.put("city_id", "1");
		map.put("subscribe", "1");
		map.put("commit", "%E6%B3%A8%E5%86%8C");
			boolean b = MyHttp2.signUp(signuphp, map, "/index.php");
			Connection  con=ConnTools.makeConnection("groupbuy");
			System.out.println("注册"+(b?"成功":"失败"));
			int ib = ConnTools.update(con,
		    		  "update site set isReg=? where siteUrl like ? ",
		    		  new Object[] {b?0:1,"onezona"});
		      System.out.println("更新插入错误api"+(ib>0?"成功"+ib:"失败"+ib));

	}
}
