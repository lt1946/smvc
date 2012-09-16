/**
 * 
 */
package com.iatb.util.use.gb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.htmlcleaner.XPatherException;
import org.jdom.Element;
import org.jdom.JDOMException;

import com.iatb.util.DateUtil;
import com.iatb.util.MyJdom;
import com.iatb.util.MyString;
import com.iatb.util.db.ConnTools;
import com.iatb.util.http.MyHtmlCleaner;
import com.iatb.util.http.MyHttp2;

/**
 * @author Administrator
 * 
 */
public class CopyOfGroupbuyClass {

	private static String url = "jdbc:mysql://127.0.0.1:3306/groupbuy?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=gbk&amp;mysqlEncoding=gbk&amp;zeroDateTimeBehavior=convertToNull";
	private static String user = "root";
	private static String password = "root";
	/**
	 * @param args
	 * @throws IOException
	 * @throws XPatherException
	 * @throws MalformedURLException
	 * @throws JDOMException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws MalformedURLException,
			XPatherException, IOException, JDOMException, SQLException {
		// saveProperties();
		// testgetSameUrl();
		// testgetSameloginUrl();
		// saveapi();
		// savecheck();
		// saveNoLoginUrl();
		// saveNoSignupUrl();
		// saveNoAPIurl();
//		saveCity();
		getAPI();
	}

	/**
	 * 保存api
	 */
	public static void getAPI() {
		int id=0;
		Connection con = ConnTools.makeConnection(url,user,password);
		List<Map<String, Object>> list = ConnTools.queryMap(con,
				"select id,apiUrl from site where  apiUrl is not null  and apiUrl <>' ' and isApi=0");
		for (Map<String, Object> map : list) {
			List<String> apilist=new ArrayList<String>();
			id = (Integer) map.get("id");
			String apiUrl = (String) map.get("apiUrl");
			if(apiUrl.indexOf("/baidu.php")==-1)continue;
			Element e=MyJdom.getRoot(apiUrl);
			if(e==null)continue;
			  List<Element> servlets;
			try {
				servlets = e.getChildren("url");
			  for (Element url : servlets) {
			      String loc=url.getChild("loc").getTextTrim();
			      Element display = url.getChild("data").getChild("display");
			      String website=display.getChild("website").getTextTrim();
			      String siteurl=display.getChild("siteurl").getTextTrim();
			      String city=display.getChild("city").getTextTrim();
			      String title=display.getChild("title").getTextTrim();
			      String image=display.getChild("image").getTextTrim();
			      String startTime=display.getChild("startTime").getTextTrim();
			      String endTime=display.getChild("endTime").getTextTrim();
			      String value=display.getChild("value").getTextTrim();
			      String price=display.getChild("price").getTextTrim();
			      String rebate=display.getChild("rebate").getTextTrim();
			      String bought=display.getChild("bought").getTextTrim();
			      String createTime=DateUtil.getCurrentDate();
			      GroupbuyClass gb=new GroupbuyClass();
			      gb.setBought(bought);
			      gb.setCity(city);
			      gb.setEndTime(endTime);
			      gb.setImage(image);
			      gb.setLoc(loc);
			      gb.setPrice(price);
			      gb.setRebate(rebate);
			      gb.setSiteurl(siteurl);
			      gb.setStartTime(startTime);
			      gb.setTitle(title);
			      gb.setValue(value);
			      gb.setWebsite(website);
			      gb.setCreateTime(createTime);
			      apilist.add(loc);
			      List<Map<String, Object>> listMap=  ConnTools.queryMap(con, "select count(1) c from goods where url='"+loc+"'" );
			      int i=0;
			      for (Map<String, Object> map2 : listMap) {
					 i=Integer.parseInt( map2.get("c").toString());
					 break;
			      }
			      if(i>0) {
//			    	  System.out.println("已经存在跳过！");
			    	  continue;
			      }
			      int b = ConnTools.update(con,
			    		  "insert into goods (siteid,url,title,simageurl,city,status,startdate,enddate,price,value,amount,percent,website,createTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
			    		  new Object[] {id,loc,title,image,city,0,startTime,endTime,price,value,bought,rebate,website,createTime});
			      System.out.println("插入"+(b>0?"成功"+b:apiUrl+"失败"+b));
			      if(b<0) {
			    	  b = ConnTools.update(con,
				    		  "update site set isApi=-1 where id=? ",
				    		  new Object[] {id});
				      System.out.println("更新插入错误api"+(b>0?"成功"+b:id+"失败"+b));
					  continue;
			      }
			  }
			  if(apilist!=null) {
				  String []urls=new String[apilist.size()];
				  apilist.toArray(urls);
				  String noturls="";
				  for (String s : urls) {
					noturls+="'"+s+"',";
				}
				  noturls=noturls.substring(0,noturls.length()-1);
				  //保存过期商品标记
				  int b = ConnTools.update(con,
			    		  "update  goods set isinvite=1 where isinvite=0 and siteid=? and url not in("+noturls+")",
			    		  new Object[] {id});
			      System.out.println("更新过期商品标记"+(b>0?"成功"+b:apiUrl+"失败"+b));
			  }
			} catch (RuntimeException e1) {
				 int b = ConnTools.update(con,
			    		  "update site set isApi=1 where id=? ",
			    		  new Object[] {id});
			      System.out.println("更新错误api"+(b>0?"成功"+b:id+"失败"+b));
				  continue;
			}
		}
		
		
	}
	/**
	 * 保存城市
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void saveCity() throws MalformedURLException,
			XPatherException, IOException {
		String url = "http://123.txtg.com/html/beijing/";
		Connection con = ConnTools.makeConnection();
		Map<String, String> com = MyHttp2.getNotLikeLink(url, "gbk",
				"?,txtg,void,#,javascript");
		Set<String> set = com.keySet();
		for (String title : set) {
			title = title.trim().endsWith("/") ? title.trim().substring(0,
					title.length() - 1) : title.trim();
			if (title.equals(""))
				continue;
			List<Map<String, Object>> list = ConnTools.queryMap(con,
					"select id,city from site where siteUrl like '%"
							+ com.get(title) + "%' or siteName ='" + title
							+ "'");
			if (list == null || list.size() == 0) {
				// 增加城市
				int b = ConnTools
						.update(
								con,
								"insert into site (siteName,siteUrl,city) values(?,?,?)",
								new Object[] { title, com.get(title), '5' });
				if (b == 1)
					System.out.println("增加成功");
				else
					System.out.println("增加失败");
			} else {
//				continue;
				// 更新城市
				for (Map<String, Object> map : list) {
					Integer id = (Integer) map.get("id");
					String city = (String) map.get("city");
					int b = ConnTools.update(con,
							"update site set city=? where id=" + id,
							new Object[] { city + ",2" });
					if (b == 1)
						System.out.println("更新成功");
					else
						System.out.println("更新失败");
				}
			}
		}

	}

	/**
	 * 保存要激活的网站
	 * 
	 * @since 2010-09-19
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 */
	public static void savecheck() throws MalformedURLException,
			XPatherException, IOException {
		Connection con = ConnTools.makeConnection();
		String sql = "update site set isCheck=? where siteUrl like ? and status=0";
		String url = "https://reg.163.com/logins.jsp?type=1&url=http://entry.mail.163.com/coremail/fcg/ntesdoor2?lightweight%3D1%26verifycookie%3D1%26language%3D-1%26style%3D-1";
		String name = "lt1946";
		String cookie = "P_INFO="
				+ name
				+ "@163.com|1284720763|0|mail163|11&21|bej&1284439878&mail163#bej&null#10|134170&0; MAIL163_SSN="
				+ name
				+ "; USERTRACK=219.143.144.133.1281943308198303; Province=010; City=010; _ntes_nnid=ee739e2b5659b7d745b1b4175a24c2a4; _ntes_nuid=ee739e2b5659b7d745b1b4175a24c2a4; ntes_ucc=; NTES_UFC=6000000000000000100000000000000000000000000000000000000000000000";
		Map<String, String> data = new HashMap<String, String>();
		data.put("product", "mail163");
		data.put("username", "lt1946");
		data.put("password", "asdfgdsa");
		data.put("selType", "-1");
		data.put("remUser", "on");
		data.put("secure", "secure");
		data.put("verifycookie", "1");
		data.put("style", "-1");
		data.put("savelogin", "1");
		data.put("url2", "http://mail.163.com/errorpage/err_163.htm");
		String b = MyHttp2.postGetCookie(url, data, cookie, "");
		String NTES_SESS = b.split(";")[0].split("=")[1];
		// System.out.println(NTES_SESS);
		String encode = "utf-8";
		String emailurl = "http://m.mail.163.com/xm/welcome.do?sid=MAfQFsIITAQYjgfKYHIIotkneNUkgAUl&cookie="
				+ NTES_SESS + "&go=index&keyfrom=index&page=1";
		String emailurl2 = MyHttp2.getLocation(emailurl, cookie);
		String sid = emailurl2.substring(emailurl2.indexOf("sid="), emailurl2
				.indexOf("&", emailurl2.indexOf("sid=")));
		for (int page = 1; page < 7; page++) {
			emailurl = "http://m.mail.163.com/xm/welcome.do?" + sid
					+ "&cookie=" + NTES_SESS + "&go=index&keyfrom=index&page="
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
					// System.out.println(t);
					String url1 = t.substring(0, t.indexOf("/", 10));
					int i = ConnTools
							.update(con, sql, new Object[] { 1, url1 });
					System.out.println(i);
				}
			}
		}
	}

	/**
	 * 保存团购api链接地址
	 */
	public static void saveapi() {
		PropertiesControl apip = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\api\\api.properties");
		Set<String> pcsignupkeys = apip.getKeys();
		String sql = "update site set apiUrl=? where siteName=? and status=0";
		Connection con = ConnTools.makeConnection();
		for (String string : pcsignupkeys) {
			int i = ConnTools.update(con, sql, new Object[] { apip.get(string),
					string });
			System.out.println(i);
		}
	}

	/**
	 * 保存没有apiurl
	 */
	public static void saveNoAPIurl() {
		List<String> api = new ArrayList<String>();
		Connection con = ConnTools.makeConnection();
		List<Map<String, Object>> list = ConnTools.queryMap(con,
				"select apiUrl from site where apiUrl<>''");
		for (Map<String, Object> map : list) {
			String apiUrl = (String) map.get("apiUrl");
			apiUrl = apiUrl.substring(apiUrl.indexOf("/", 10));
			if (!api.contains(apiUrl))
				api.add(apiUrl);
		}
		List<Map<String, Object>> noapilist = ConnTools.queryMap(con,
				"select siteUrl from site where apiUrl=''");
		for (Map<String, Object> map : noapilist) {
			String siteUrl = (String) map.get("siteUrl");
			for (String a : api) {
				boolean b = MyHttp2.existsUrl(siteUrl + a);
				if (b) {
					String sql = "update site set apiUrl=? where siteUrl=? and status=0";
					int i = ConnTools.update(con, sql, new Object[] {
							siteUrl + a, siteUrl });
					System.out.println(i);
					break;
				}
			}
		}

	}

	/**
	 * 保存没有注册url
	 */
	public static void saveNoSignupUrl() {
		String signup = "/account/signup.php";
		Connection con = ConnTools.makeConnection();
		List<Map<String, Object>> list = ConnTools.queryMap(con,
				"select siteUrl from site where signupUrl=''");
		for (Map<String, Object> map : list) {
			String siteUrl = (String) map.get("siteUrl");
			boolean b = MyHttp2.existsUrl(siteUrl + signup);
			if (b) {
				String sql = "update site set signupUrl=? where siteUrl=? and status=0";
				int i = ConnTools.update(con, sql, new Object[] { signup,
						siteUrl });
				System.out.println(i);
			}
		}
	}

	/**
	 * 保存团购登入链接地址
	 */
	public static void saveNoLoginUrl() {
		String login = "/account/login.php";
		Connection con = ConnTools.makeConnection();
		List<Map<String, Object>> list = ConnTools
				.queryMap(
						con,
						"select siteUrl from site where loginUrl is null or loginUrl='' or loginUrl='/account/signup.php'");
		for (Map<String, Object> map : list) {
			String siteUrl = (String) map.get("siteUrl");
			boolean b = MyHttp2.existsUrl(siteUrl + login);
			if (b) {
				String sql = "update site set loginUrl=? where siteUrl=? and status=0";
				int i = ConnTools.update(con, sql, new Object[] {
						siteUrl + login, siteUrl });
				System.out.println(i);
			}
		}
	}

	/**
	 * 保存团购登入链接地址
	 */
	public static void testgetSameloginUrl() {
		PropertiesControl groupbuy_loginOk = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_loginOk.properties");
		Set<String> pcsignupkeys = groupbuy_loginOk.getKeys();
		String sql = "update site set loginUrl=? where siteName=? and status=0";
		Connection con = ConnTools.makeConnection();
		for (String string : pcsignupkeys) {
			int i = ConnTools.update(con, sql, new Object[] {
					groupbuy_loginOk.get(string), string });
			System.out.println(i);
		}
	}

	/**
	 * 保存团购注册链接地址
	 */
	public static void testgetSameUrl() {
		// String signup = "/account/signup.php";
		PropertiesControl pcsignuphp = new PropertiesControl(
				"D:\\java\\workspace\\MyFrameWorker\\www\\groupbuy\\groupbuy_signup_php.properties");
		Set<String> pcsignupkeys = pcsignuphp.getKeys();
		String sql = "update site set signupUrl=? where siteName=? and status=0";
		Connection con = ConnTools.makeConnection();
		for (String string : pcsignupkeys) {
			int i = ConnTools.update(con, sql, new Object[] {
					pcsignuphp.get(string), string.split(",")[0] });
			System.out.println(i);
		}
	}

	/**
	 * 获取团购地址
	 * 
	 * @since 2010-09-21
	 * @throws MalformedURLException
	 * @throws XPatherException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void saveProperties() throws MalformedURLException,
			XPatherException, IOException, SQLException {
		String url = "http://www.goutuan.net/index.php?m=Index&a=site&sc=1";
		Map<String, String> m = MyHttp2.getAllLink(url, "utf-8");
		Map<String, String> m2 = new HashMap<String, String>();
		for (String s : m.keySet()) {
			String u = (m.get(s).indexOf("=http:") > 0 ? m.get(s).substring(
					m.get(s).indexOf("=http:") + 1) : m.get(s));
			if (u.indexOf("http") == -1 || s.trim().equals("")
					|| u.trim().equals("") /* ||m2.containsKey(s.trim()) */) {
				continue;
			}
			if (u.indexOf("/", 10) != -1)
				u = u.substring(0, u.indexOf("/", 10));
			m2.put(s.trim(), u.trim());
		}
		Set<String> set = m2.keySet();
		String sql = "insert into site (siteName,siteUrl) values(?,?)";
		// String sb[]=new String[m2.size()];
		// int i=0;
		Connection con = ConnTools.makeConnection();
		for (String string : set) {
			int i = ConnTools.update(con, sql, new Object[] { string,
					m2.get(string) });
			System.out.println(i);
			// sb[i]= "'"+ string +"','"+m2.get(string)+"'";
			// i++;
		}
		// ConnTools.batch(con, sql, sb);
	}
}
