package com.iatb.util.porn;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iatb.util.DateUtil;
import com.iatb.util.DownloadNet;
import com.iatb.util.MyFile;
import com.iatb.util.SystemUtil;
import com.iatb.util.db.ConnTools;
import com.iatb.util.http.MyHtmlCleaner;

public class XDaoImpl{
	
//	private int count=0;

	public XDaoImpl() {
	}

	private static Connection conn = ConnTools.makeConnection("springmvc");
	private  final static boolean  isWin=SystemUtil.isWindows();
	
	// 添加网站
	public void saveSite(Object o[]) {
		try {
			String sql = "insert into crawl_site (hosturl,siteName,`type`,createTime,status) values(?,?,?,'"
					+ DateUtil.getPlusTimeDate() + "',0)";
			int i = ConnTools.update(conn, sql, o);
			System.out.println("添加网站" + (i == 1 ? "成功" : "失败"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSite() {

	}
	public static void setStatus(){
		List<File> list=new ArrayList<File>();
		if(isWin){
			list=MyFile.getFiles(new File("g:\\Media\\A\\homesexdaily\\"), null);
		}else{
			list=MyFile.getFiles(new File("/home/media/homesexdaily/"), null);
		}
		List<String> names = new ArrayList<String>();
		for (File f : list) {
			names.add(f.getName().substring(0,f.getName().lastIndexOf(".")));
		}
		for (int i = 0; i < Integer.MAX_VALUE; i+=10) {
			List<Map<String ,Object>> l=query(10, i);
			for (Map<String, Object> map : l) {
				if(names.contains(map.get("title").toString()))continue;
				else updateStatus(map.get("title").toString(), 0);
			}
			if(l==null||l.size()==0)return;
		}
		delete();
	}
	/**
	 * 分页查询
	 * @param num
	 * @param pre
	 * @return
	 */
	public static List<Map<String ,Object>> query(int num,int pre){
		return ConnTools.queryMap(conn, "select title from movieurl order by id asc limit "+(pre<0?0:pre)+","+num);
	}
	/**
	 * 判断sql是否存在
	 * @param sql
	 * @return
	 */
	public static boolean exists(String sql){
		return ConnTools.count(sql,null)>0?true:false;
	}
	// 删除crawl_site
	public static void deleteSite(int id) {
		String sql = "delete from crawl_site where id=?";
		int i = ConnTools.update(conn, sql, new Object[] { id });
		System.out.println("删除网站" + (i >= 1 ? "成功" : "失败"));
	}
	// 删除movieurl
	public static void deletemovieurl(String id) {
		String sql = "delete from movieurl where id=?";
		int i = ConnTools.update(conn, sql, new Object[] { id });
		System.out.println("删除movieurl" + (i >= 1 ? "成功" : "失败"));
	}
	// 真删除
	public static void delete() {
		String sql = "delete from movieurl where status=0";
		int i = ConnTools.update2(conn, sql);
		System.out.println("删除movieurl" + (i >= 1 ? "成功" :i==0?"无": "失败"));
	}
	// 获取链接
	public Map<String, String> getUrl(String url) {
		Map<String, String> m = MyHtmlCleaner.getAllLink(url, getencode(url));
		if(m==null)return null;
		Map<String, String> m2 = new HashMap<String, String>();
		for (Map.Entry<String, String> s : m.entrySet()) {
			if (s.getValue().trim().startsWith(
					"http://www.homesexdaily.com/video/")) {
				m2.put(s.getKey().trim(), s.getValue().trim());
			}
		}
		return m2;
	}

	//添加movieurl
	public void saveMovieUrl(int siteid, String title, String url,
			boolean isFinish) {
		Long i = (Long) ConnTools.queryMap(
				conn,
				"select count(1) b from movieurl where title='"
						+ title.replace("'", "‘") + "'").get(0).get("b");
		if (i > 0)
			return;
		String s = MyHtmlCleaner.getCode(url, getencode(url));
		int start = s.indexOf("url: '") + 6;
		String movieurl = "";
		if (start > 5) {
			movieurl = s.substring(start, s.indexOf("'", start));
		}
		String sql = "insert into movieurl (siteid,title,url,movieurl,createTime,status)  values(?,?,?,?,'"
				+ DateUtil.getPlusTimeDate() + "',?)";
		Object[] o = new Object[] { siteid, title, url, movieurl,
				isFinish ? 1 : 0 };
		int ii = ConnTools.update(conn, sql, o);
		System.out.println("添加movieurl：" + movieurl + (ii == 1 ? "成功" : "失败"));
	}
	//更新过期的movieurl
	public void updateMovieUrl(){
		List<Map<String, Object>> list=ConnTools.queryMap(
				conn,
				"select url from movieurl where  status=0 ");
		for (Map<String, Object> map : list) {
			String url=(String) map.get("url");
			String s = MyHtmlCleaner.getCode(url, getencode(url));
			int start = s.indexOf("url: '") + 6;
			String movieurl = "";
			if (start > 5) {
				movieurl = s.substring(start, s.indexOf("'", start));
			}
			int i=ConnTools.update(conn, "update movieurl set movieurl=? where url=?",
				new Object[]{movieurl,url});
			System.out.println("更新url："+(i>0?"成功":"失败"));
		}
	}

	// 获取编码
	public String getencode(String url) {
		return MyHtmlCleaner.getEncode(url);
	}

	// 下载
	public void downloadFromDB() {
		String path = isWin?"g://Media//A//homesexdaily//"+ DateUtil.getCurrentDate() + "//":"/home/media/"+ DateUtil.getCurrentDate() + "/";
		if (!new File(path).exists())
			new File(path).mkdirs();
		List<Map<String, Object>> list = ConnTools.queryMap(conn,
				"select * from movieurl where status=0");
		if(list==null)return;
		for (Map<String, Object> map : list) {
			String title = (String) map.get("title");
			String movieurl = (String) map.get("movieurl");
			Long s = System.currentTimeMillis();
			
			DownloadNet d = new DownloadNet(movieurl, path, title + ".mp4", 10,
					true);
			System.out.println(title + ".mp4:"
					+ (System.currentTimeMillis() - s));
			if (d.isFinish()){
				updateStatus(title, 1); // 更新状态
			}else{
				deletemovieurl(map.get("id").toString());
			}
		}
	}

	// 更新状态
	public static void updateStatus(String title, int finish) {
		int ii = ConnTools.update(conn,
				"update movieurl set status=? where title=?", new Object[] {
						finish, title });
		System.out.println("更新title：：" + title + (ii >= 1 ? "成功" : "失败"));
	}

	// 1获取分页
	public void getGroupBuy(String url, int pre, int end, int next) {
		String u = "";
		for (int i = pre; i <= end; i += next) {
			u = url.replace("(*)", String.valueOf(i));
			System.out.println("开始抓取分页：" + u);
			Map<String, String> m = getUrl(u); // 获取url
			if(m==null)return;
			for (Map.Entry<String, String> s : m.entrySet()) {
				if (s.getValue().startsWith(
						"http://www.homesexdaily.com/video/")) {
					// 保存
					saveMovieUrl(1, s.getKey().length() > 100 ? s.getKey()
							.substring(0, 100) : s.getKey(), s.getValue(),
							false);
				}
			}
		}
	}
	
	public static void run(){
	  System.out.println("开始porn");
	  XDaoImpl xd=new XDaoImpl();
	  xd.downloadFromDB(); 
//	  if(xd.count==2)return;
//	  delete();
	  for (int i =1; i <60; i++) { 
		  String  url="http://www.homesexdaily.com/videos/all-recent-(*).html";
	      xd.getGroupBuy(url,i, i, 1); 
	      xd.downloadFromDB(); 
//	      if(xd.count==2)return;
	   }
//	  delete();
	  System.out.println("结束porn");
	}
	public static void main(String[] args) {
		run();
	}
}

