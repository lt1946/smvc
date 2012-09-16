package com.iatb.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

/**
 * @desc 字符串类
 * @author Longtao
 */
public class MyString {
	private final static Logger log=Logger.getLogger(MyString.class);

	public static boolean indexOf(String []s,String check){
		for (int i = 0; i < s.length; i++) {
			if(check.indexOf(s[i])>=0)return true;
		}
		return false;
	}
	public static String utf8ToGbk(String s){
		try {
			return IOUtils.toString(IOUtils.toInputStream(s,"GBK")) ;
		} catch (IOException e) {
			return null;
		}
//		StringBuffer sb=new StringBuffer();
//		for(var i:int;i<byte.length;i++){
//			   result += escape(String.fromCharCode(byte[i]));
//			}
//		return result;
	}
	public static String UTF8ToGB(String src) {
        String result = null;
        
        if (src != null && src.trim().length() > 0) {
            StringBuffer sb = new StringBuffer();
            String[] srcArray = src.split(";");
            int position = 0;
            for (int i = 0; i < srcArray.length; i++) {
                position = srcArray[i].indexOf("&#x");
                if (position >= 0) {
                    sb.append(srcArray[i].substring(0, position));
                    
                    String tmp = srcArray[i].substring(position + 3);
                    if (tmp.startsWith("00")) {
                        tmp = tmp.substring(2);
                    }
                    int l = Integer.valueOf(tmp, 16).intValue();
                    sb.append((char) l);
                } else {
                    sb.append(srcArray[i]);
                }
            }
            result = sb.toString();
        }
        
        return result;
    }
	/**
	 * HTML转义字符
	 * @since		2010-09-19
	 * @param text
	 * @return
	 */
	public static String encode(String text) {
		return HtmlUtils.htmlEscape(text);
    }
	/**
	 * 解析HTML转义字符成实体 
	 * @since		2011.02.25
	 * @param text	字符串
	 * @return
	 */
	public static String decode(String text) {
		text=text.replaceAll("&nbsp;", " ").replaceAll("&bull;", "•").replaceAll("\n\n", "\n")
		.replaceAll("&apos;", "\'").replace("&#46;", ".");
		text=HtmlUtils.htmlUnescape(text);
		return text;
	}
	/**
	 * 对encode的逆 
	 * @since		2010-09-19
	 * @param text	字符串
	 * @return
	 */
	public static String decode2(String text) {
		char c, c1, c2, c3, c4, c5;
		StringBuffer n = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			if (c == '&') {
				c1 = text.charAt(i + 1);
				c2 = text.charAt(i + 2);
				c3 = text.charAt(i + 3);
				c4 = text.charAt(i + 4);
				c5 = text.charAt(i + 5);

				if (c1 == 'a' && c2 == 'm' && c3 == 'p' && c4 == ';') {
					n.append("&");
					i += 5;
				} else if (c1 == 'l' && c2 == 't' && c3 == ';') {
					n.append("<");
					i += 4;
				} else if (c1 == 'g' && c2 == 't' && c3 == ';') {
					n.append(">");
					i += 4;
				} else if (c1 == 'q' && c2 == 'u' && c3 == 'o' && c4 == 't'
						&& c5 == ';') {
					n.append("\"");
					i += 6;
				} else if (c1 == 'a' && c2 == 'p' && c3 == 'o' && c4 == 's'
						&& c5 == ';') {
					n.append("'");
					i += 6;
				} else
					n.append("&");
			} else
				n.append(c);
		}
		return new String(n);
	}

	/**
	 * 返回匹配正则字符串
	 * 
	 * @date 2010-09-07
	 * @param content
	 * @param like
	 * @return
	 */
	public static List<String> getLike(String content, String like, int index) {
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile(like).matcher(content);
		while (m.find()) {
			if (!list.contains(m.group(index)))
				list.add(m.group(index));
		}
		return list;
	}

	/**
	 * 返回是否匹配正则字符串
	 * 
	 * @date 2010-09-07
	 * @param content
	 * @param like
	 * @return
	 */
	public static boolean getLike(String content, String like) {
		Matcher m = Pattern.compile(like).matcher(content);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public void testsdf() {
		String s = ".sortlist_box_dp .sort-list li {background:url(../img/dot.gif) repeat-x left bottom;padding:1px 30px 5px 16px;}";
		String r = "\\(\\w+img/\\w+\\)";
		List<String> l = MyString.getString(s, r);
		for (String string : l) {
			log.info(string);
		}
	}

	/**
	 * 返回匹配正则字符串
	 * 
	 * @param content
	 * @param like
	 * @return
	 */
	public static List<String> getString(String content, String like) {
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile(like).matcher(content);
		if (m.find()) {
			for (int i = 0; i < m.groupCount(); i++) {
				list.add(m.group(i));
			}
			return list;
		} else {
			return null;
		}
	}

	/**
	 * 获取正确的字符串
	 * 
	 * @param data
	 * @param src
	 * @param dest
	 * @return
	 */
	public static String turnEncode(String data, String src, String dest) {
		try {
			return new String(data.getBytes(src), dest);
		} catch (UnsupportedEncodingException e) {
			return data;
		}
	}

	/**
	 * iso-encode
	 * 
	 * @param data
	 * @param src
	 * @param dest
	 * @return
	 */
	public static String iso2(String data,String encode) {
		try {
			return new String(data.getBytes("iso-8859-1"), encode);
		} catch (UnsupportedEncodingException e) {
			return data;
		}
	}
	public static String iso2gbk(String data) {
		try {
			return new String(data.getBytes("iso-8859-1"), "gbk");
		} catch (UnsupportedEncodingException e) {
			return data;
		}
	}

	/**
	 * 返回完整的url
	 * 
	 * @param host
	 * @param url
	 * @return
	 */
	public static String getUrlWithHost(String host, String url) {
		if (url == null || url.trim().equals("") || host == null
				|| host.trim().equals("")) {
			log.info("getUrlWithHost():输入的url为空！");
			return null;
		} else {
			if (!host.trim().startsWith("http")) {
				log.info("host网址不正确！");
				return null;
			} else {
				if (url.startsWith("http"))
					return url;
				int i = host.indexOf("/", 10);
				String host1 = host;
				if (i > 0) {
					host1 = host.substring(0,host.lastIndexOf("/"));
					if (url.trim().startsWith("/")) {
						return host1.substring(0,i) + url;
					} else {
						return host1+"/"+url;
					}
				} else {
					if (url.trim().startsWith("/")) {
						return host + url;
					} else {
						return host + "/" + url;
					}
				}
			}
		}
	}

	/**
	 * 清除所有多余空格
	 * 
	 * @param sb
	 * @return
	 */
	public static String cleanSpans(String sb) {
		return sb.replaceAll("\\s*|\t|\r|\n", "");// 去除字符串中的空格,回车,换行符,制表符
	}
	/**
	 * 清除所有多余空格和html代码
	 * @param sb
	 * @return
	 */
	public static String cleanSpans2(String sb) {
		return sb.replaceAll("\\s*|\t|\r|\n", "").replaceAll("&mdash;", "").replaceAll("&nbsp;", "").replaceAll("&ldquo;", "").replaceAll("&rdquo;", "").trim();// 去除字符串中的空格,回车,换行符,制表符
	}

	// 获取内容s里匹配正则表达式patternString的个数。
	public static int getPatternNum(String s, String patternString) {
		Pattern p = Pattern.compile(patternString);
		Matcher matcher = p.matcher(s);
		return matcher.groupCount();
	}

	// 获取内容s里匹配正则表达式patternString的个数。
	public static int getStringNum(String s, String patternString) {
		return s.split(patternString).length;
	}

	// 截取内容中间的部分。
	public static String getMiddleContext(String s, String split[]) {
		String f = split[0].trim();
		String l = split[1].trim();
		if (s.equals(null) || s.trim().equals(""))
			return "内容为空！";
		else {
			int first = 0, last = s.length();
			if (!f.equals(null) && !f.trim().equals(""))
				first = s.indexOf(f) != -1 ? (s.indexOf(f) + f.length()) : 0;
			if (!l.equals(null) && !l.trim().equals(""))
				last = s.lastIndexOf(l) != -1 ? s.lastIndexOf(l) : s.length();
			return s.substring(first, last);
		}
	}

	/**
	 * 截取内容中间的部分2
	 * 
	 * @param s
	 * @param split
	 * @return
	 */
	public static String getMiddleContext2(String s,
			Map<String, List<String>> split) {
		if (s.equals(null) || s.trim().equals(""))
			return "内容为空！";
		else {
			if (split == null) {
				return s;
			}
			List<String> f = split.get("first");
			List<String> l = split.get("last");
			int fl = f.size();
			int ll = l.size();
			int first[] = new int[fl];
			int last[] = new int[ll];
			int lasts = s.length();
			if (f != null && f.size() != 0) {
				for (int i = 0; i < first.length; i++) {
					first[i] = s.indexOf(f.get(i)) != -1 ? (s.indexOf(f.get(i)) + f
							.get(i).length())
							: 0;
				}
				Arrays.sort(first);
			}
			if (l != null && l.size() != 0) {
				for (int i = 0; i < last.length; i++) {
					last[i] = s.lastIndexOf(l.get(i)) != -1 ? (s.lastIndexOf(l
							.get(i))) : 0;
				}
				Arrays.sort(last);
				for (int i = 0; i < last.length; i++) {
					if (last[i] == 0)
						continue;
					else {
						lasts = last[i];
						break;
					}
				}
			}
			// log.info( Arrays.toString(first));
			// log.info(Arrays.toString(last));
			return s.substring(first[fl - 1], lasts);
		}
	}

	public static String clearString(String s, String c[]) { // 清除多余字符串数组。
		if (s.trim().equals("") || s == null)
			return "";
		int i = c.length;
		for (int j = 0; j < i; j++) {
			s = s.replaceAll(c[j].trim(), "");
		}
		return s;
	}

	public static String clearother(String s, String c[]) {

		if (s.indexOf("\"") != -1)
			s = s.replaceAll("\"", "\\\"");
		int i = c.length;
		log.info(i);
		for (int j = 0; j < i; j++) {
			int k = 0;
			if ((k = s.indexOf(c[j])) != -1) {

			} else {
				log.info("not found:" + c[j]);
				continue;
			}
			// log.info(k);
			int first = (k == 0) ? 0 : s.lastIndexOf(" ", k);
			// log.info(first);
			int last = s.indexOf(" ", first + 1) != -1 ? s.indexOf(" ", first)
					: s.length();
			// log.info(last);
			s = s.substring(0, first) + s.substring(last);
			/*
			 * String other=s.substring(first,last); s=s.replace(other, "");
			 */

			/*
			 * if (s.indexOf(c[j]) != -1) clearother(s, c);
			 */
		}
		return s;
	}

	public static String turnFileName(String filename) {
		filename = filename.replaceAll("[*]", "").replaceAll("[?]", "");
		filename = filename.replaceAll(">", "").replaceAll("<", "");
		filename = filename.replaceAll("/", "").replaceAll(":", "");
		filename = filename.replaceAll("\"", "").replaceAll("|", "");
		filename = filename.replaceAll("\\\\", "");
		return filename;
	}

	public void testturnFileName() {
		String t = turnFileName("sdf*.txt");
		log.info(t);
	}

	/**
	 * @desc 获取s左边的的字符串从indexs字符串开始
	 * @param s
	 * @param indexs
	 * @return String
	 * @date 2010.2.14
	 */
	public static String leftString(String s, String indexs) {
		if (s.trim().equals("") || s == null) {
			return null;
		}
		if (indexs == null) {
			return s;
		}
		int i = -1;
		if ((i = s.indexOf(indexs)) != -1 && i != 0) {
			s = s.substring(0, i);
		}
		// log.info(i);
		return s;
	}

	/**
	 * 将HTML格式的字符串转换成常规显示的字符串
	 */
	public static String toText(String str) {
		String text = str;
		if (str == null || str.length() == 0) {
			return "";
		} else {
			// text=Pattern.compile("&lt;").matcher(str).replaceAll("<");
			// Long l=System.currentTimeMillis();
			text = text.replaceAll("&amp;", "&").replaceAll("&lt;", "<")
					.replaceAll("&gt;", ">").replaceAll("<br>\n", "\n");
			text = text.replaceAll("<br>", "\n").replaceAll("&quot;", "\"")
					.replaceAll("&nbsp;", " ");
			text = text.replaceAll("&#39;", "'");
			// log.info(System.currentTimeMillis()-l);
			return text;
		}
	}

	public static void main(String[] args) {
		// String s="2} *&AZ";
		// String s="<%@page import=\"java.net.*,java.io.*\"%> *MNWb=QC&";
		// s=s.replaceAll("\"", "\\\"");
		// String s="java能够干什么? - 中文JAVA技术网 ";
		// log.info(s.indexOf(""));
		// String s="醋溜尖椒土豆丝[图] 家常菜谱 -
		// 美食菜谱_家常食谱_123美食网_HaoChi123.com(好吃123).txt";
		/*String s = "《迅雷英雄》官方网站—2009年度最火爆游戏交友社区";
		String c[] = { "—2009年度最火爆游戏交友社区  " };
		SplitString ss = new SplitString();*/
		// String out=ss.clearother(s, new String[]{"",""});
		// String c[]={" -
		// 美食菜谱_家常食谱_123美食网_HaoChi123.com","[?\\\\|<>*:/\"()]","好吃123"};
	/*	String out = SplitString.clearString(s, c);
		log.info(out);*/
		String s=decode("&amp;#46;");
		log.info(s);
		/*
		 * String srt="abcabc? - 中文JAVA技术网";
		 * 
		 * srt=srt.replaceAll("[?|*技术网]", "#");
		 * 
		 * log.info(srt);
		 */
		// log.info(s.indexOf(""));
	}

	// fomate Date getNowTime like '1900-01-01 01:01:01'
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(new Date());
	}
}
