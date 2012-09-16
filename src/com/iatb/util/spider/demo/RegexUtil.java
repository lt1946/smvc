/**
 * 
 */
package com.iatb.util.spider.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcherInput;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 正则表达式工具类
 * 
 * @author Pablo
 * 
 */
public class RegexUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RegexUtil.class);

	private String url;

	public RegexUtil(String url) {
		this.url = url;
	}

	public RegexUtil() {

	}

	/**
	 * 设置模式
	 * 
	 * @param findStr
	 * @return
	 * @throws Exception
	 */
	private Pattern getPattern(String findStr) throws Exception {
		Pattern pattern = null;
		PatternCompiler compiler = new Perl5Compiler();
		pattern = compiler.compile(findStr, Perl5Compiler.CASE_INSENSITIVE_MASK
				| Perl5Compiler.SINGLELINE_MASK);
		return pattern;
	}

	/**
	 * 返回匹配的字符串
	 * 
	 * @param source
	 *            字符传
	 * @param patten
	 *            正则表达式
	 * @param index
	 *            匹配的索引
	 * @return
	 */
	public String getMatchedStr(String source, String patten, int index) {
		MatchResult result = getMatchResult(source, patten);
		if (result != null)
			return result.group(index);
		return null;
	}

	/**
	 * 返回匹配的字符串
	 * 
	 * @param source
	 *            字符传
	 * @param patten
	 *            正则表达式
	 * @param replaceRegex
	 *            替换正则表达式
	 * @return
	 */
	public String getMatchedStr(String source, String patten,
			String replaceRegex) {
		MatchResult match = getMatchResult(source, patten);
		if (match == null)
			return null;
		String result = match.group(1);
		// 替换replaceRegex中的^1、^2、^3...等参数
		if (match != null && replaceRegex != null
				&& !replaceRegex.trim().equals("")) {
			result = replaceRegex;
			int count = match.groups();
			for (int i = 1; i < count; i++) {
				result = result.replaceAll("\\^" + i, match.group(i));
			}
		}
		return result;
	}

	/**
	 * 返回是否匹配
	 * 
	 * @return
	 */
	public boolean isMatch(String source, String patten) {
		if (patten == null || source == null)
			return false;
		boolean flag = true;
		if (patten.startsWith("!!")) {
			patten = patten.substring(2);
			flag = false;
		}
		MatchResult result = getMatchResult(source, patten);
		return flag ? result != null : result == null;
	}

	/**
	 * @param source
	 * @param patten
	 * @return
	 */
	public MatchResult getMatchResult(String source, String patten) {
		if (source == null || patten == null) {
			return null;
		}
		try {
			String pattens[] = StringUtil.splitByStr(patten,
					Constants.TAG_REGEX_SPLIT);
			for (int i = 0; i < pattens.length; i++) {
				Perl5Matcher matcher = new Perl5Matcher();
				Pattern pattern = getPattern(pattens[i]);
				if (matcher.contains(source, pattern)) {
					return matcher.getMatch();
				}
			}
		} catch (Exception e) {
//			logger.warn(new ContentError(webSite.getId(), url, e,
//					"getMatchResult(String, String) - exception ignored"));
		}
		return null;
	}

	/**
	 * 返回所有的匹配
	 * 
	 * @param source
	 * @param patten
	 * @param index
	 * @return
	 */
	public List<String> getAllMatched(String source, String patten, int index) {
		List<String> list = new ArrayList<String>();
		if (source != null && patten != null) {
			try {
				Perl5Matcher matcher = new Perl5Matcher();
				Pattern pattern = getPattern(patten);
				PatternMatcherInput input = new PatternMatcherInput(source);
				while (matcher.contains(input, pattern)) {
					list.add(matcher.getMatch().group(index));
				}
			} catch (Exception e) {
//				logger.warn(new ContentError(webSite.getId(), url, e,
//						"getAllMatched(String, String) - exception ignored"));
			}
		}
		return list;
	}
	/**
	 * @desc: OA中文章抓取
	 * @author：Dingding Zhang
	 * @return: List
	 * @date: Jan 28, 2010
	 */
	public List getAllMatched(String source, String patten) {
		List list = new ArrayList();
		if (source != null && patten != null) {
			try {
				Perl5Matcher matcher = new Perl5Matcher();
				Pattern pattern = getPattern(patten);
				PatternMatcherInput input = new PatternMatcherInput(source);
				while (matcher.contains(input, pattern)) {
					list.add(matcher.getMatch());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 获取匹配的中间字符串
	 * 
	 * @param source
	 * @param beginPatten
	 * @param endPatten
	 * @return
	 */
	public String getBetween(String source, String beginPatten, String endPatten) {
		if (source == null)
			return null;
		int beginPos = 0;
		int endPos = -1;
		MatchResult resultBegin = null;
		// 查找开始位置
		if (beginPatten != null && !beginPatten.equals("")) {
			resultBegin = getMatchResult(source, beginPatten);
			if (resultBegin == null) {
//				logger.warn(new ContentError(webSite.getId(), url, null,
//						"beginPatten not found," + beginPatten));
				return null;
			}
			String begin = resultBegin.group(0);
			beginPos = resultBegin.beginOffset(0) + begin.length();
		}

		// 查找结束位置
		if (endPatten != null && !endPatten.equals("")) {
			String tail = source;
			if (resultBegin != null)
				tail = source.substring(beginPos);
			MatchResult resultEnd = getMatchResult(tail, endPatten);
			if (resultEnd == null) {
//				logger.warn(new ContentError(webSite.getId(), url, null,
//						"endPatten not found," + endPatten));
				return null;
			}
			endPos = beginPos + resultEnd.beginOffset(0);

		}

		if (beginPos >= 0 && endPos >= 0) {
			return source.substring(beginPos, endPos);
		} else if (beginPos > 0)
			return source.substring(beginPos);
		else if (beginPos == 0)
			return source;
		else {
			return null;
		}
	}

	/**
	 * 过滤字符串中所有html代码,但是不包括<br>
	 * <p>
	 * 
	 * @param s
	 * @return
	 */
	public static synchronized String filterHTML(String s) {

		if (s != null) {

			s = s.replaceAll("<br>", "/r/n/br");
			s = s.replaceAll("</p>", "/r/n/p/r");
			s = s.replaceAll("<p>", "/r/n/p/l");

			s = s.replaceAll("&nbsp;", " ");
			s = s.replaceAll("<.*?>", "");
			s = s.replaceAll("·", ",");

			// 处理全角空格
			s = s.replaceAll("　+", " ");

			s = s.replaceAll("/r/n/br", "<br>");
			s = s.replaceAll("/r/n/p/r", "</p>");
			s = s.replaceAll("/r/n/p/l", "<p>");
			// 去多个空合格

			s = s.replaceAll("\\s+", " ");
			// 区首尾空格
			s = s.trim();
		}
		return s;
	}

	/**
	 * 过滤字符串中所有html代码
	 * 
	 * @param s
	 * @return
	 */
	public static synchronized String filterAllHTML(String s) {

		if (s != null) {
			s = s.replaceAll("&nbsp;", " ");
			s = s.replaceAll("<.*?>", "");
			// 处理全角空格
			s = s.replaceAll("　+", " ");
			// 去多个空合格
			s = s.replaceAll("\\s+", " ");
			s = s.replaceAll("·", ",");
			// 区首尾空格
			s = s.trim();
		}
		return s;
	}

	/**
	 * 获取网站域名
	 * 
	 * @param url
	 * @return
	 */
	public static synchronized String getDomain(String url) {
		if (url == null)
			return null;
		String first = "";

		// 协议头
		int pos = url.indexOf("://");
		if (pos >= 0) {
			first = url.substring(0, pos + 3);
			url = url.substring(pos + 3);
		}

		// 第一个路径
		pos = url.indexOf("/");
		if (pos >= 0)
			url = url.substring(0, pos);
		return first + url;
	}

	/**
	 * 获取连接
	 * 
	 * @param url
	 * @param relativeUrl
	 * @return
	 */
	public static synchronized String getPath(String url, String relativeUrl) {
		if (relativeUrl == null)
			return null;
		if (url == null)
			return relativeUrl;
		if (relativeUrl.startsWith("/")) {
			return getDomain(url) + relativeUrl;
		} else if (relativeUrl.toLowerCase().startsWith("http:")) {
			return relativeUrl;
		} else {
			// 协议头
			String first = "";
			int pos = url.indexOf("://");
			if (pos >= 0) {
				first = url.substring(0, pos + 3);
				url = url.substring(pos + 3);
			}
			int idx = url.lastIndexOf('/');
			if (idx > 0)
				url = url.substring(0, idx);

			int flag = 3;
			// 处理../或./
			while (flag != 0) {
				if (relativeUrl.startsWith("../"))
					flag = 3;
				else if (relativeUrl.startsWith("./"))
					flag = 2;
				else
					flag = 0;
				if (flag == 3) {
					pos = url.lastIndexOf('/');
					if (pos >= 1)
						url = url.substring(0, pos);
				}
				if (flag > 0)
					relativeUrl = relativeUrl.substring(flag);
			}
			// 相对路径
			return first + url + "/" + relativeUrl;
		}

	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
