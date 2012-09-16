/**
 * 
 */
package com.iatb.util.use.gb;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.XPatherException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;

import com.util.file.PropertiesControl;
import com.util.http.MyHtmlCleaner;
import com.util.http.MyHttp2;
import com.util.string.MyString;

/**
 * @author Administrator
 * 
 */
public class GroupbuyClass {

	/**
	 * @param args
	 * @throws IOException
	 * @throws XPatherException
	 * @throws MalformedURLException
	 * @throws JDOMException
	 */
	public static void main(String[] args) throws MalformedURLException,
			XPatherException, IOException, JDOMException {
		// saveProperties();
		// getSignUp();
		// testSaveUnSignupUrl();
		// testgetSameUrl();
		// batchReg();
		// batchLogin();
//		 testApi();
		// getinvite();
		// batchReginvite();
		// batchcheckrefer();
//		checkEmail();
//		getApi();
//		getXml();
		testLoginurl();
	}
	
	public static void saveXml()  {
		PropertiesControl apip = new PropertiesControl(
		"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\api.properties");
		Set<String> pcsignupkeys = apip.getKeys();
		for (String key : pcsignupkeys) {
			String url =  apip.get(key);
			try {
				SAXBuilder sb = new SAXBuilder(false);
				Document doc = sb.build(url);
				Iterator<Element> itr = doc.getDescendants(new ElementFilter());
				while (itr.hasNext()) {
					Element c = itr.next();
					if (c.getName().equals("url") || c.getName().equals("data")
							|| c.getName().equals("display")
							|| c.getName().equals("urlset"))
						continue;
					System.out.println(c.getName() + ":" + c.getTextTrim().trim());
				}
			} catch (Exception e) {
				continue;
			}
		}
	}
	/**
	 * 获取apixml地址
	 * @throws JDOMException
	 * @throws IOException
	 * @throws XPatherException 
	 */
	public static void getXml()   {
		PropertiesControl apiOk = new PropertiesControl(
		"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\apiOk.properties");
		PropertiesControl apip = new PropertiesControl(
		"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\api0223.properties");
		Set<String> pcsignupkeys = apiOk.getKeys();
		for (String key : pcsignupkeys) {
			if(apip.get(key)!=null)continue;
			String url =  apiOk.get(key);
			System.out.println(url);
			String api[]=url.split("[|]");
			String apiurl=MyString.getUrlWithHost(api[0],api[1].split(",")[0]);
			try {
				List<String> b = MyHttp2.getLikeLink(apiurl, "utf-8", "api");
				for (String s : b) {
					if(s.equals("/help/api.php"))continue;
					apip.add(key, MyString.getUrlWithHost(url, s), false, "");
					break;
				}
			} catch (Exception e) {
				continue;
			}
		}
		
	}

	/**
	 * 批量获取api
	 * @since	2010-09-19
	 * @throws IOException 
	 * @throws XPatherException 
	 * @throws MalformedURLException 
	 */
	public static void getApi() throws MalformedURLException, XPatherException, IOException {
		PropertiesControl inviteOk = new PropertiesControl(
		"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\inviteOk.properties");
	/*	PropertiesControl inviteOk = new PropertiesControl(
		"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\site.properties");*/
		Set<String> pcsignupkeys = inviteOk.getKeys();
		PropertiesControl apiOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\apiOk0223.properties");
		PropertiesControl apiError = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\apiError.properties");

		for (String key : pcsignupkeys) {
			String url =  inviteOk.get(key);
			List<String> b = MyHttp2.getLikeLink(url, "utf-8", "api");
			if (b!=null&&b.size()>0) {
				apiOk.add(key,url+"|"+b.toString().substring(1, b.toString().length()-1), false, "");
			} else {
				apiError
						.add(key,url,false, "");
			}
		}
	}

	/**
	 * 从163获取验证url，并激活
	 * @since 2010-09-19
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void checkEmail() throws MalformedURLException,
			XPatherException, IOException {
		String url = "https://reg.163.com/logins.jsp?type=1&url=http://entry.mail.163.com/coremail/fcg/ntesdoor2?lightweight%3D1%26verifycookie%3D1%26language%3D-1%26style%3D-1";
//		String name = "h8roer";
		String name = "lt1946";
		String cookie = "P_INFO="
				+ name
				+ "@163.com|1284720763|0|mail163|11&21|bej&1284439878&mail163#bej&null#10|134170&0; MAIL163_SSN="
				+ name
				+ "; USERTRACK=219.143.144.133.1281943308198303; Province=010; City=010; _ntes_nnid=ee739e2b5659b7d745b1b4175a24c2a4,0; _ntes_nuid=ee739e2b5659b7d745b1b4175a24c2a4; ntes_ucc=; NTES_UFC=6000000000000000100000000000000000000000000000000000000000000000";
		Map<String, String> data = new HashMap<String, String>();
//		data.put("product", "mail163");
		 data.put("username", "lt1946");
//		data.put("username", "h8roer");
		 data.put("password", "asdfgdsa");
//		data.put("password", "ffffffgg");
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
		for (int page = 1; page < 7; page++) {
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

	/**
	 * 批量检查注册邀请链接后是否成功
	 */
	public static void batchcheckrefer() {
		String login = "/account/login.php";
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "lt1946@163.com");
		map.put("password", "ffffffgg");
		PropertiesControl inviteOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\inviteOk.properties");
		Set<String> pcsignupkeys = inviteOk.getKeys();

		PropertiesControl referOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\referOk.properties");
		PropertiesControl referError = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\referError.properties");

		for (String key : pcsignupkeys) {
			String refer = inviteOk.get(key);
			login = refer.substring(0, refer.lastIndexOf("/"))
					+ "/account/login.php";
			String checkurl = refer.substring(0, refer.lastIndexOf("/"))
					+ "/account/refer.php";
			boolean b = MyHttp2.loginCheckString(login, map, checkurl,
					"/index.php", "h8roer", "", "", false);
			System.out.println(b ? "包含" : "不包含");
			if (b) {
				referOk.add(key, refer, false, "");
			} else {
				referError.add(key, refer, false, "");
			}

		}
	}

	/**
	 * 批量注册邀请链接
	 */
	public static void batchReginvite() {
		PropertiesControl groupbuy_inviteOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_inviteOk.properties");
		Set<String> pcsignupkeys = groupbuy_inviteOk.getKeys();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "h8roer@163.com");
		map.put("username", "h8roer");
		map.put("password", "111111");
		map.put("password2", "111111");
		map.put("mobile", "13532165423");
		map.put("city_id", "1");
		map.put("subscribe", "1");
		map.put("commit", "%E6%B3%A8%E5%86%8C");
		PropertiesControl inviteOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\inviteOk.properties");
		PropertiesControl inviteError = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\invite\\inviteError.properties");

		for (String key : pcsignupkeys) {
			String url = groupbuy_inviteOk.get(key);
			String signup = url.substring(0, url.lastIndexOf("/"))
					+ "/account/signup.php";
			String redirectUrl = MyHttp2.post2(url, signup, map);
			if (redirectUrl == null) {
				inviteError.add(key, url, false, "");
				System.out.println("注册异常！");
			} else if (redirectUrl.equals("/index.php")) {
				inviteOk.add(key, url, false, "");
				System.out.println("注册成功！");
			} else {
				inviteError.add(key, url, false, "");
				System.out.println("注册失败！");
			}
		}
	}

	/**
	 * 批量获取邀请链接
	 */
	public static void getinvite() {
		PropertiesControl inviteOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_inviteOk.properties");
		PropertiesControl inviteErrot = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_inviteErrot.properties");

		PropertiesControl encodeok = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_encode_ok.properties");
		PropertiesControl encodeerror = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_encode_error.properties");

		// http://www.tianetuan.com/team.php?id=64
		String invite = "/account/invite.php";
		String login = "/account/login.php";
		String like = "http:.+php\\?r=\\d+";
		PropertiesControl signupOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signupOk.properties");
		Set<String> pcsignupkeys = signupOk.getKeys();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "lt1946@163.com");
		map.put("password", "ffffffgg");
		for (String key : pcsignupkeys) {
			String loginurl = "http://" + signupOk.get(key) + login;
			String encode = "";
			if ((encode = encodeok.get(key)) != null) {

			} else {
				encode = MyHttp2.getEncode(loginurl);
				if (encode == null) {
					encodeerror.add(signupOk.get(key), "gbk", false, "");
				} else {
					encodeok.add(signupOk.get(key), encode, false, "");
				}
			}

			String url = "http://" + signupOk.get(key) + invite;
			String bb = MyHttp2.LoginGetLink(loginurl, map, "/index.php", url,
					encode, like);
			if (bb != null) {
				inviteOk.add(key, bb, false, "");
			} else {
				inviteErrot.add(key, url, false, "");
			}
		}

	}


	/**
	 * 是否存在api
	 */
	public static void testApi() {
		PropertiesControl apiOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_apiOk0223.properties");
		PropertiesControl apiErrot = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_apiErrot0223.properties");
		String api[] = new String[]{/*"/api/api.php",*/"/open.php?page=api"};
		PropertiesControl signupOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signupOk.properties");
		Set<String> pcsignupkeys = signupOk.getKeys();
		for (String key : pcsignupkeys) {
			for (int i = 0; i < api.length; i++) {
				String url = "http://" + signupOk.get(key) + api[i];
				boolean b = MyHttp2.existsUrl(url);
				if (b) {
					apiOk.add(key, url, false, "");
				} else {
					apiErrot.add(key, url, false, "");
				}
			}
		}
	}
	
	/**
	 * 是否存在loginurl
	 * 2011.02.23
	 */
	public static void testLoginurl() {
		PropertiesControl loginurl0223 = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_loginurl0223.properties");
		PropertiesControl unloginurl0223 = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_unloginurl0223.properties");
		String api[] = new String[]{ "/account/login.php"};
		PropertiesControl site = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy.properties");
		Set<String> pcsignupkeys = site.getKeys();
		for (String key : pcsignupkeys) {
			for (int i = 0; i < api.length; i++) {
				String url = site.get(key) + api[i];
				boolean b = MyHttp2.existsUrl(url);
				if (b) {
					loginurl0223.add(key, url, false, "");
				} else {
					unloginurl0223.add(key, url, false, "");
				}
			}
		}
	}
	/**
	 * 批量登入
	 */
	public static void batchLogin() {
		String login = "/account/login.php";
		PropertiesControl signupOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signupOk.properties");

		PropertiesControl loginOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_loginOk.properties");
		PropertiesControl loginErrot = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_loginErrot.properties");

		Set<String> pcsignupkeys = signupOk.getKeys();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "lt1946@163.com");
		map.put("password", "ffffffgg");
		for (String key : pcsignupkeys) {
			String url = "http://" + signupOk.get(key) + login;
			boolean b = MyHttp2.signUp(url, map, "/index.php");
			if (b) {
				loginOk.add(key, url, false, "");
			} else {
				loginErrot.add(key, url, false, "");
			}
		}
	}

	/**
	 * 批量注册
	 */
	public static void batchReg() {
		PropertiesControl pcsignuphp = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup_php.properties");
		Set<String> pcsignupkeys = pcsignuphp.getKeys();
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "lt1946@163.com");
		map.put("username", "lt1946");
		map.put("password", "ffffffgg");
		map.put("password2", "ffffffgg");
		map.put("mobile", "13426465170");
		map.put("city_id", "1");
		map.put("subscribe", "1");
		map.put("commit", "%E6%B3%A8%E5%86%8C");
		PropertiesControl signupOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signupOk.properties");
		PropertiesControl signupError = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signupError.properties");

		for (String key : pcsignupkeys) {
			String url = "http://" + key.split(",")[1] + pcsignuphp.get(key);
			boolean b = MyHttp2.signUp(url, map, "/index.php");
			if (b) {
				signupOk.add(key.split(",")[0], key.split(",")[1], false, "");
			} else {
				signupError
						.add(key.split(",")[0], key.split(",")[1], false, "");
			}
		}
	}

	/**
	 * 保存相同的团购注册链接地址
	 */
	public static void testgetSameUrl() {
		String signup = "/account/signup.php";
		PropertiesControl pcsignup = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup.properties");
		Set<String> pcsignupkeys = pcsignup.getKeys();
		Map<String, String> m = new HashMap<String, String>();
		Map<String, String> m2 = new HashMap<String, String>();
		for (String pckey : pcsignupkeys) {
			if (pcsignup.get(pckey).indexOf(signup) != -1) {
				m.put(pckey, pcsignup.get(pckey));
			} else {
				m2.put(pckey, pcsignup.get(pckey));
			}
		}
		PropertiesControl pcsignuphp = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup_php.properties");
		pcsignuphp.addMap(m, false, "");
		PropertiesControl pcsignunophp = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup_no.properties");
		pcsignunophp.addMap(m2, false, "");
	}

	/**
	 * 保存没有获取到的团购链接地址
	 */
	public static void testSaveUnSignupUrl() {
		PropertiesControl pc = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy2.properties");
		PropertiesControl pcsignup = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup.properties");
		Set<String> keys = pc.getKeys();
		Set<String> pcsignupkeys = pcsignup.getKeys();
		Map<String, String> m = new HashMap<String, String>();
		// for (String key : keys) {
		Object[] key = keys.toArray();
		for (int i = key.length - 1; i >= 0; i--) {
			boolean b = false;
			String url = pc.get((String) key[i]);
			url = url.substring(url.indexOf("//") + 2).trim();
			for (String pckey : pcsignupkeys) {
				if (url.equals(pckey.split(",")[1])) {
					b = true;
					break;
				}
			}
			if (!b) {
				m.put((String) key[i], pc.get((String) key[i]));
			}
		}
		File f = new File(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_unsignup.properties");
		if (f.exists())
			f.delete();
		PropertiesControl unsignup = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_unsignup.properties");
		unsignup.addMap(m, false, "");
	}

	/**
	 * 获取注册链接
	 * 
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void getSignUp() throws MalformedURLException,
			XPatherException, IOException {
		String signup = "signup";
		// PropertiesControl pc=new
		// PropertiesControl("D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy2.properties");
		PropertiesControl pc = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_unsignup.properties");
		PropertiesControl pcsignup = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup.properties");
		Set<String> keys = pc.getKeys();
		// Map<String,String> m=new HashMap<String, String>();
		for (String key : keys) {
			String url = pc.get(key);
			List<String> list = new ArrayList<String>();
			try {
				String charset = MyHttp2.getEncode(url);
				System.out.println(charset);
				list = MyHttp2.getLikeLink(url, charset, signup);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println(url);
			if (list == null || list.size() == 0)
				continue;
			if (url.indexOf("//") != -1)
				url = url.substring(url.indexOf("//") + 2);
			pcsignup.add(key + "," + url, list.toString().substring(1,
					list.toString().length() - 1), false, "");
		}
	}

	/**
	 * 获取团购地址
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void saveProperties() throws MalformedURLException,
			XPatherException, IOException {
		String url = "http://www.goutuan.net/index.php?m=Index&a=site&sc=1";
		Map<String, String> m = MyHttp2.getAllLink(url, "utf-8");
		Map<String, String> m2 = new HashMap<String, String>();
		for (String s : m.keySet()) {
			String u = (m.get(s).indexOf("=http:") > 0 ? m.get(s).substring(
					m.get(s).indexOf("=http:") + 1) : m.get(s));
			if (u.indexOf("http") == -1 ||m2.containsKey(s.trim()) ) {
				continue;
			}
			if (u.indexOf("/", 10) != -1)
				u = u.substring(0, u.indexOf("/", 10));
			m2.put(s.trim(), u.trim());
		}
		PropertiesControl pc = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy3.properties");
		pc.addMap(m2, false, "");
	}

}
