package com.iatb.util.porn;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iatb.bean.Webs;
import com.iatb.util.DateUtil;
import com.iatb.util.DownloadNet;
import com.iatb.util.db.ConnTools;
import com.iatb.util.http.MyHtmlCleaner1;

/**
 * @author Administrator
 *
 */
public class PornUtil {
	
	private static Connection conn = ConnTools.makeConnection("springmvc");

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args)  {
		
		downloadFromDB(); 
		String url="http://www.realgfporn.com/videos/page_(*).html";
		for (int i =1; i <10; i++) { 
			  getGroupBuy(url, i, i,1);
		      downloadFromDB(); 
		}
	}
	/**
	 * 获取分页
	 * @param url
	 * @param pre
	 * @param end
	 * @param next
	 */
	public static void getGroupBuy(String url, int pre, int end, int next) {
		String u = "";
		for (int i = pre; i <= end; i += next) {
			u = url.replace("(*)", String.valueOf(i));
			System.out.println("开始抓取分页：" + u);
			Map<String, String> m = getUrl(u); // 获取url
			if(m==null)return;
			for (Map.Entry<String, String> s : m.entrySet()) {
				saveMovieUrl(12, s.getKey().length() > 100 ? s.getKey()
						.substring(0, 100) : s.getKey(), s.getValue(),
						false);				// 保存
			}
		}
	}
	/**
	 * 过滤链接
	 * @param url
	 * @return
	 */
	public static Map<String, String> getUrl(String url) {
		Map<String, String> m = MyHtmlCleaner1.getAllLink(url, getencode(url),new Webs("strong","class","vtitle"));
		if(m==null)return null;
		Map<String, String> m2 = new HashMap<String, String>();
		for (Map.Entry<String, String> s : m.entrySet()) {
			if (s.getValue().trim().startsWith(
					"http://www.realgfporn.com/")&&s.getValue().trim().endsWith(".html")&&s.getValue().indexOf("page_")<0) {
				m2.put(s.getKey().trim(), s.getValue().trim());
			}
		}
		return m2;
	}
	/**AWX
	 * 获取编码
	 * @param url
	 * @return
	 */
	public static String getencode(String url) {
		return MyHtmlCleaner1.getEncode(url);
	}

	/**
	 *  添加movieurl
	 * @param siteid
	 * @param title
	 * @param url
	 * @param isFinish
	 */
	public static void saveMovieUrl(int siteid, String title, String url,
			boolean isFinish) {
		Long i = (Long) ConnTools.queryMap(
				conn,
				"select count(1) b from movieurl where title='"
						+ title.replace("'", "‘") + "'").get(0).get("b");
		if (i > 0)
			return;
		//-----getMovieUrl
		String movieurl=MyHtmlCleaner1.getStringFromHtml(url, getencode(url),";so.addVariable('file','", "'");
		if(movieurl==null||movieurl.length()>255){
			System.out.println(url+"\t未能发现视频链接!");return;
		}
		//----------------
		String sql = "insert into movieurl (siteid,title,url,movieurl,createTime,status)  values(?,?,?,?,'"
				+ DateUtil.getPlusTimeDate() + "',?)";
		Object[] o = new Object[] { siteid, title, url, movieurl,
				isFinish ? 1 : 0 };
		int ii = ConnTools.update(conn, sql, o);
		System.out.println("添加movieurl：" + movieurl + (ii == 1 ? "成功" : "失败"));
	}
	/**
	 * 下载
	 */
	public static void downloadFromDB() {
		String path = "g://Media//A//realgfporn//"
				+ DateUtil.getCurrentDate() + "//";
		if (!new File(path).exists())
			new File(path).mkdirs();
		List<Map<String, Object>> list = ConnTools.queryMap(conn,
				"select * from movieurl where status=0 and siteid=12");
		for (Map<String, Object> map : list) {
			String title = (String) map.get("title");
			String movieurl = (String) map.get("movieurl");
			//----下载视频格式
			Long s = System.currentTimeMillis();
			String title1=title+movieurl.substring(movieurl.lastIndexOf("."),movieurl.length());
			DownloadNet d = new DownloadNet(movieurl, path, title1, 10,
					true);
			System.out.println(title
					+ (System.currentTimeMillis() - s));
			//-----------
			if (d.isFinish())
				updateStatus(title, 1); // 更新状态
				System.gc();
		}
	}

	// 更新状态
	public static void updateStatus(String title, int finish) {
		int ii = ConnTools.update(conn,
				"update movieurl set status=? where title=?", new Object[] {
						finish, title });
		System.out.println("更新title：：" + title + (ii >= 1 ? "成功" : "失败"));
	}

}
