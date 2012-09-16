package com.iatb.util.spider.demo;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

import sun.io.ByteToCharConverter;
import sun.io.CharToByteConverter;


/**
 * <p>����������</p>
 * <p>�ṩ�ַ��������ʵ�÷�����</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: APP</p>
 * @author Pablo
 * @version 1.0
 *
 */

public class StringUtil
{
	public StringUtil()
	{
	}
	
	public static String getUrlWithHost(String host,String url) {
		if(url==null||url.trim().equals("")||host==null||host.trim().equals("")) {
//			System.out.println("�����urlΪ�գ�");
			return null;
		}else {
			if(!host.trim().startsWith("http")) {
//				System.out.println("host��ַ����ȷ��");
				return null;
			}else {
					int i=host.indexOf("/",10);
					String host1=host;
					if(i>0) {
						host1=host.substring(0,i);
						if(url.trim().startsWith("/")) {
							return host1+url;
						}else {
							return host.endsWith("/")?host+url:host+"/"+url;
						}
					}else {
						if(url.trim().startsWith("/")) {
							return host+url;
						}else {
							return host+"/"+url;
						}
					}
			}
		}
	}
	public static final String escapeForIntro(String string)
	{
		//			 String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");

	}
	/**
	 * �õ��ǿյ��ַ��������ַ�������Ϊnull���򷵻�""��
	 * @param objValue Object��ת����ԭ�ַ�������
	 * @return String ת������ַ���
	 * */
	public static String getNotNullStr(Object objValue)
	{
		return (objValue == null ? "" : objValue.toString());
	}
	/**
	 * �õ��ǿյ��ַ��������ַ���Ϊnull���򷵻�""��
	 * @param strValue String��ת����ԭ�ַ���
	 * @return String ת������ַ���
	 * */
	public static String getNotNullStr(String strValue)
	{
		return (strValue == null ? "" : strValue.trim());
	}
	/**
	 * ������ת����AscII���Ա�������ݿ�
	 * @param s  �����ַ���
	 * @return 16�����ַ���
	 */
	public static String ChineseStringToAscii(String s)
	{
		try
		{
			CharToByteConverter toByte = CharToByteConverter.getConverter("GBK");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}
	/**
	 * ��UTF-8ת����AscII���Ա�������ݿ�
	 * @param s �����ַ���
	 * @return 16�����ַ���
	 */
	public static String ChineseStringToUTF(String s)
	{
		try
		{
			CharToByteConverter toByte = CharToByteConverter.getConverter("UTF-8");
			byte[] orig = toByte.convertAll(s.toCharArray());
			char[] dest = new char[orig.length];
			for (int i = 0; i < orig.length; i++)
				dest[i] = (char) (orig[i] & 0xFF);
			return new String(dest);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}

	/**
	 * ��AscII�ַ�ת���ɺ���
	 * @param s -  ASCII�ַ���
	 * @return ����
	 */
	public static String AsciiToChineseString(String s)
	{
		char[] orig = s.toCharArray();
		byte[] dest = new byte[orig.length];
		for (int i = 0; i < orig.length; i++)
			dest[i] = (byte) (orig[i] & 0xFF);
		try
		{
			ByteToCharConverter toChar = ByteToCharConverter.getConverter("GBK");
			return new String(toChar.convertAll(dest));
		}
		catch (Exception e)
		{
			System.out.println(e);
			return s;
		}
	}

	/**
	 * ʹ��������ʽ�����ַ��������滻
	 * @param regularExpression ������ʽ
	 * @param sub �滻���ַ���
	 * @param input Ҫ�滻���ַ���
	 * @return String �滻����ַ���
	 */
	/*public static synchronized String regexReplace(String regularExpression, String sub, String input)
	{
		Pattern pattern = PatternFactory.getPattern(regularExpression);
		Matcher matcher = pattern.matcher(input);
		StringBuffer sb = new StringBuffer();
		while (matcher.find())
		{
			matcher.appendReplacement(sb, sub);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}*/

	/**
	 * �ж�һ���ַ������Ƿ��������������ʽ�����ƥ���������Ӵ�
	 * @param regularExpression - ������ʽ
	 * @param input - ������ַ���
	 * @return - �������ַ����а�������������ʽ�����ƥ���������Ӵ����򷵻�true�����򷵻�false
	 */
	//������ʽƥ���ж�
	/*public static synchronized boolean exist(String regularExpression, String input)
	{
		Pattern pattern = PatternFactory.getPattern(regularExpression);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}*/

	/**
	 * ��"0"����һ���ַ�����ָ������
	 * @param str -  Դ�ַ���
	 * @param size - �����Ӧ�ﵽ�ĳ���
	 * @return - �����Ľ��
	 */
	public static String fillZero(String str, int size)
	{
		String result;
		if (str.length() < size)
		{
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++)
			{
				s[i] = '0';
			}
			result = new String(s) + str;
		}
		else
		{
			result = str;
		}

		return result;
	}

	/**
	 * �����ַ������ļ����ͻ��߶����������ͣ���ȡ�ַ�������
	 * @param str1 String �����ַ���
	 * @return String[] ���ؽ��
	 */
	public static String[] getStrArryByString(String str1)
	{
		if (str1.indexOf("\t") > 0)
		{
			for (int i = 0; i < str1.length(); i++)
			{
				if (str1.substring(i, i + 1).equals("\t"))
				{
					str1 = str1.substring(0, i) + " " + str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens())
		{
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ NUll ��Ϊ��
	 * @param inStr inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr)
	{
		if (inStr == null)
		{
			return false;
		}
		else if (inStr.trim().equals(""))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ NUll ��Ϊ��
	 * @param inStr inStr
	 * @return boolean
	 */	
	public static boolean checkNotNull(String str){
		boolean flag = false;
		
		if(str != null && str.trim().length() != 0)	
			flag = true;
		return flag;
	}
	/**
	 * ���ָ�����ȵĿո�
	 * @param spaceNum spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum)
	{
		return getStrWithSameElement(spaceNum, " ");
	}
	/**
	 * ���ָ�����ȵ��ַ���
	 * @param num int
	 * @param element String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element)
	{
		if (num <= 0)
		{
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++)
		{
			sb.append(element);
		}
		return sb.toString();
	}
	/**
	 * ���һ���ӿո�
	 * @param strIn String
	 * @param totalLength int
	 * @param isRightAlign boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength, boolean isRightAlign)
	{
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;

		if (strIn == null)
		{
			strIn = "";
		}

		spaceLength = totalLength - strIn.length();

		if (spaceLength < 0)
		{
			spaceLength = 0;
		}
		spaces = StringUtil.getStrSpace(spaceLength);

		if (isRightAlign)
		{
			strOut = spaces + strIn;
		}
		else
		{
			strOut = strIn + spaces;

		}
		return strOut;
	}
	/**
	 * ��String���ͷ��ش����׳��Ķ�ջ��Ϣ
	 * @param t Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		t.printStackTrace(pw);
		return sw.toString();
	}
	/**
	 * ת���ַ�����һ���ַ�Ϊ��д
	 * @param str String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * ת���ַ�����һ���ַ�ΪСд
	 * @param str String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}

	}
	/**
	 * ͨ���ַ���ת������Ӧ�����ͣ������ء�
	 * @param strValue String ��ת�����ַ���
	 * @return int ת����ɵ�����
	 * */
	public static int getStrToInt(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = new java.lang.Integer(strValue.trim()).intValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * ͨ���ַ���ת������Ӧ��DOUBLE�������ء�
	 * @param strValue String ��ת�����ַ���
	 * @return double ת����ɵ�DOUBLE
	 * */
	public static double getStrToDouble(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		double dValue = 0;
		try
		{
			dValue = Double.parseDouble(strValue.trim());
		}
		catch (Exception ex)
		{
			dValue = 0;
		}
		return dValue;
	}

	/**
	 * ͨ���ַ���ת������Ӧ�Ķ����ͣ������ء�
	 * @param strValue String ��ת�����ַ���
	 * @return short ת����ɵĶ�����
	 * */
	public static short getStrToShort(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		short iValue = 0;
		try
		{
			iValue = new java.lang.Short(strValue.trim()).shortValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}

	/**
	 * ͨ���ַ���ת������Ӧ�ĳ����ͣ������ء�
	 * @param strValue String ��ת�����ַ���
	 * @return long ת����ɵĳ�����
	 * */
	public static long getStrToLong(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		long lValue = 0;
		try
		{
			lValue = new java.lang.Long(strValue.trim()).longValue();
		}
		catch (Exception ex)
		{
			lValue = 0;
		}
		return lValue;
	}

	public static String toLengthForEn(String str, int length)
	{
		if (null != str)
		{
			if (str.length() <= length)
			{
				return str;
			}
			else
			{
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		}
		else
		{
			return "";
		}
	}

	/**
	  * ���ַ���ת���ɸ������ȵ��ַ������糬���Ļ��ضϣ�����������������β
	  * @param str String
	  * @param length int
	  * @return String
	  */
	public static String toLengthForIntroduce(String str, int length)
	{
		str = delTag(str);

		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2))
		{
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++)
			{
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2))
				{
					break;
				}
				buff.append(charArray[i]);

			}
			buff.append("..");
			str = buff.toString();
		}

		//		str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "��", ",");
		return str;

	}

	public static String delTag(String str)
	{
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;

		while (str.length() > 0)
		{
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0)
			{
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length())
			{
				str = str.substring(end + 1, str.length());
			}
			else
			{
				str = "";
			}

		}
		String result = buff.toString();

		while (result.startsWith(" "))
		{

			result = result.substring(result.indexOf(" ") + 1, result.length());

		}
		return result;

	}

	public static final String replace(String line, String oldString, String newString)
	{
		if (line == null)
		{
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;

	}
	//	Replace
	public static String Replace(String source, String oldString, String newString)
	{
		if (source == null)
		{
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0)
		{
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource)
		{
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	//�˺���ǰ̨ʹ���У���������޸ģ���Ȼ�������ʾ����(��ǰ�޸İ汾���·�ע����)
	public static String toHtml(String s)
	{
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");
		//s = Replace(s, " ", "&nbsp;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "��");
		//	s = Replace(s, "&", "&amp;");
		return s;
	}
	//	��
	public static String unHtml(String s)
	{

		//s = Replace(s, "&lt;", "<");
		//s = Replace(s, "&gt;", ">");
		//		s = Replace(s, "    ", "\t");
		//		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		//		s = Replace(s, "&nbsp;", " ");
		//		s = Replace(s, "&amp;", "&");
		//		s = Replace(s, "&#39;", "'");
		//		s = Replace(s, "&#92;", "\\");
		//		s = Replace(s, "��", "%");
		return s;
	}

	//	�˺�����̨ʹ���У���������޸ģ���Ȼ�������ʾ����(��ǰ�޸İ汾���·�ע����)
	public static String toHtmlBack(String s)
	{
		//��ʾ
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		//		s = Replace(s, "\n", "<br>");
//		s = Replace(s, " ", "&nbsp;");
		//		s = Replace(s, "'", "&#39;");
		//		s = Replace(s, "%", "%");

		return s;
	}
	//	��
	public static String unHtmlBack(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "��", "%");
		return s;
	}
	/*
	public static String toHtml(String s)
	{
		//��ʾ
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
	//		s = Replace(s, "\n", "<br>");
		s = Replace(s, " ", "&nbsp;");
	//		s = Replace(s, "'", "&#39;");
	//		s = Replace(s, "%", "%");
		
		return s;
	}
	
	public static String unHtml(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "��", "%");
		return s;
	}
	*/
	//�ж��Ƿ������ģ�����������ķ���ture
	public static boolean containsChinese(String str) throws UnsupportedEncodingException
	{

		if (str.length() < (str.getBytes()).length)
			return true;

		return false;

		//	  for (int i = 0; i < username.length(); i++) {
		//		String unit=Character.toString(username.charAt(i));
		//		byte[] unitByte=unit.getBytes("GBK");
		////  		((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		//	   if (unitByte.length == 2)
		//		{
		//		  return true;
		//		}
		//	  }
		//	  return false;

	}

	public static boolean isEmpty(String str)
	{
		if (str == null)
			return true;
		return "".equals(str.trim());
	}

	public static String[] split(String str1, String str2)
	{
		return org.apache.commons.lang.StringUtils.split(str1, str2);
	}

	public static String left(String str, int length)
	{
		return org.apache.commons.lang.StringUtils.left(str, length);
	}
	
	/** 
	 * �����ַ�������.   
	 * һ�����ֻ��պ��ĳ���Ϊ2,Ӣ���ַ�����Ϊ1
	 * ����������ַ���Ϊnull,����0.   
	 * @author:Pablo3518
	 * @date:2009-4-29
	 * @param str �����㳤�ȵ��ַ���
	 * @return    �ַ�������
	 */
	public static int strlen(String str) {
		if (str == null || str.trim().length() <= 0) {
			return 0;
		}
		
		int len = 0;

		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {//��ĸ, ����   
				len++;
			} else {
				if (Character.isLetter(c)) { //����   
					len += 2;
				} else { //���Ż�����ַ�   
					len++;
				}
			}
		}

		return len;
	}

	/**
	 * ��ȡһ���ַ��ĳ���,��������Ӣ��,������ֲ����ã�����ȡһ���ַ�λ����β��ƴ��ʡ�Ժŵ����֡�
	 * @author:Pablo3518
	 * @date:2009-4-29
	 * @param origin ԭʼ�ַ���
	 * @param len ��ȡ����(һ�����ֳ��Ȱ�2���)
	 * @param more ���ȹ�����ʡ���ַ�
	 * @return
	 */
	public static String substrings(String origin, int len, String more) {
		if (null == origin || origin.trim().length() < 1 || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > StringUtil.strlen(origin)) {
			return origin;
		}
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			--len;
		}
		int n = count / 2;
		String retS = "";
		if(null == more && more.trim().length() < 1){
			more = "";
		}
		retS = origin.substring(0, len - n) + more;

		return retS;
	}
	
	/**
	 * ��ȡһ���ַ��ĳ���,��������Ӣ��,������ֲ����ã�����ȡһ���ַ�λ��
	 * @author:Pablo3518
	 * @date:2009-4-29
	 * @param origin ԭʼ�ַ���
	 * @param len ��ȡ����(һ�����ֳ��Ȱ�2���)
	 * @return
	 */
	public static String substrings(String origin, int len) {
		if (null == origin || origin.trim().length() < 1 || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > StringUtil.strlen(origin)) {
			return origin;
		}
		System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			--len;
		}
		int n = count / 2;
		String retS = "";
		retS = origin.substring(0, len - n);

		return retS;
	}
	
	/**
	 * ��������ĳ��Ƚ�ȡ�ַ������������ʳ������볤�ȵ�ǿ�Ƽ�<BR>����
	 * @param str ������ַ���
	 * @param len ����ĳ���
	 * @return ���������ַ���
	 */
	public static String truncateStr(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str))
		{

			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0)
			{
				for (int i = 0; i < strs.length; i++)
				{
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len)
					{
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}

			}
			return buff.toString();
		}
		else
		{
			return "";
		}
	}
	
	public static String truncateStr2(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str) && len!=0)
		{
			String strs[] = str.split(" ");
			
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++)
			{
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len)
				{
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ","&nbsp; ");
					tempstr = tempstr.replaceAll("<","&lt; ");
					tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);
					
					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ","&nbsp; ");
				tempstr = tempstr.replaceAll("<","&lt; ");
				tempstr = tempstr.replaceAll("\n","<br> ").replaceAll("\"","&quot; ").replaceAll("'","&#39; ");
								
				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}
			
			if(buff.length() > 0)
				return buff.toString().substring(0,buff.length()-1);
			else
				return str;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * ����ת������unicodeת��ΪGBK
	 * @param str �����ַ���
	 * @return str����ת������ַ���
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}
	/**
	 * ����ת������GBKת��Ϊunicode
	 * @param str �����ַ���
	 * @return str ����ת������ַ���
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source) throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}

	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 * 
	 * <p>It escapes both <tt>'</tt> and <tt>"</tt>.
	 * In additional it escapes <tt>></tt> as <tt>\></tt> (to avoid
	 * <tt>&lt;/script></tt>). Furthermore, all characters under UCS code point
	 * 0x20, that has no dedicated escape sequence in JavaScript language, will
	 * be replaced with hexadecimal escape (<tt>\x<i>XX</i></tt>). 
	 */ 
	public static String javaScriptStringEnc(String s) {
		int ln = s.length();
		for (int i = 0; i < ln; i++) {
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20) {
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true) {
					if (c == '"') {
						b.append("\\\"");
					} else if (c == '\'') {
						b.append("\\'");
					} else if (c == '\\') {
						b.append("\\\\");
					} else if (c == '>') {
						b.append("\\>");
					} else if (c < 0x20) {
						if (c == '\n') {
							b.append("\\n");
						} else if (c == '\r') {
							b.append("\\r");
						} else if (c == '\f') {
							b.append("\\f");
						} else if (c == '\b') {
							b.append("\\b");
						} else if (c == '\t') {
							b.append("\\t");
						} else {
							b.append("\\x");
							int x = c / 0x10;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
							x = c & 0xF;
							b.append((char)
									(x < 0xA ? x + '0' : x - 0xA + 'A'));
						}
					} else {
						b.append(c);
					}
					i++;
					if (i >= ln) {
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}
	
	
	private static StringUtil instance = null;
	
	public static synchronized StringUtil getInstance()
	{
		if (instance == null)
		{
			instance = new StringUtil();
		}
		return instance;
	}
	/**
	 * ����������ո��滻Ϊһ��,"a  b"-->"a b"
	 * @param src
	 * @return
	 * @author WilliamLau
	 * @desc
	 */
	public static String trimContinuousSpace(String src){
		return src.replaceAll("\\s+", " ");
	}
	public static String replace(String src, String target, String rWith, int maxCount)
	{
		return org.apache.commons.lang.StringUtils.replace(src, target, rWith, maxCount);
	}

	public static boolean equals(String str1, String str2)
	{
		return org.apache.commons.lang.StringUtils.equals(str1, str2);
	}

	public static boolean isAlphanumeric(String str)
	{
		return org.apache.commons.lang.StringUtils.isAlphanumeric(str);
	}

	public static boolean isNumeric(String str)
	{
		return org.apache.commons.lang.StringUtils.isNumeric(str);
	}

	public static String[] stripAll(String[] strs)
	{
		return org.apache.commons.lang.StringUtils.stripAll(strs);
	}
	public static void main(String[] args)
	{
		//		String testStr = "<input > &    \\r\\n    \\n", newStr;
		//		newStr = toHtml(testStr);
		//		System.out.println(testStr);
		//		System.out.println(newStr);
		//		System.out.println(unHtml(newStr));
		//		String aaa = "������������bcdefghijk";

		//        		String bbb = toLengthForIntroduce(aaa,5);
		//		System.out.println(bbb);
		//		Object aa = null;
		//		String bb = "  aaaa  ";

		try
		{
			//			System.out.println(getNotNullStr(aa));
			//			System.out.println(getNotNullStr(bb));
			//			System.out.println(containsChinese(aaa));
			//			System.out.println(containsChinese(aaa));
			//			System.out.println(containsChinese(bb));
			//String abc = null;
			//System.out.println(toLengthForEn(abc, 3));
			String num = "05";
			if(num.indexOf(".")==-1){
				num = num + ".00";
			}
			//System.out.println(num);
			//System.out.println(toFloatNumber("5.2"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
//
	public static String toFloatNumber(String num) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}
	
	/**
	 * ���ַ������� str[] ת��Ϊ�Զ��Ÿ����� �ַ��� str
	 * @author:Shen yanyan
	 * @date:2009-3-19
	 * @param s
	 * @return
	 */
	public static String getString(String[] s){
		StringBuffer sb = new StringBuffer() ;
		for (int i = 0; i < s.length; i++) {
			sb.append(s[i]) ;
			if(i!=s.length-1){
				sb.append(",") ;
			}
		}
		return sb.toString() ;
	}
	
	/**
	 * ��֤��ʾ��ַ
	 * @author:Shen yanyan
	 * @date:2009-3-23
	 * @param s
	 * @return
	 */
	public static boolean isDisplayUrl(String s){
		boolean flag = false ;
		int index = s.indexOf(".") ;
		int lastIndex = s.lastIndexOf(".") ;
		int len = s.length() ;
		if(index>0 && lastIndex<len-2){
			flag = true ;
		}
		return flag ;
	}
	
	/**
	 * ��֤Ŀ����ַ
	 * @author:Shen yanyan
	 * @date:2009-3-23
	 * @param s
	 * @return
	 */
	public static boolean isDestinationUrl(String s){
		boolean flag = false ;
		int index = s.indexOf(".") ;
		int lastIndex = s.lastIndexOf(".") ;
		int len = s.length() ;
		if(index>7 && lastIndex<len-2 && s.startsWith("http://") && (s.indexOf("http://")==s.lastIndexOf("http://"))){
			flag = true ;
		}
		return flag ;
	}
	
	
	/**
	 * �Ƚϲ���֤������������
	 * @author:Wangzheng
	 * @date:2009-5-12
	 * @param Long
	 * @return �����������죨1�죬2�죩
	 */
	public static Long GapDate(String beginDate,String endDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(!isValid(beginDate) && !isValid(endDate)) return new Long("0");
		
		try {
			return (sf.parse(sf.format(sf.parse(beginDate))).getTime()-sf.parse(sf.format(sf.parse(endDate))).getTime())/(24*60*60*1000);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Long("0");
		}
	}
	
	/**
	 * ��֤һ�����Ƿ���С����������λС��
	 * @author:Wangzheng
	 * @date:2009-5-12
	 * @param str
	 * @return ��С��:true ,����С��:false
	 */
	public static boolean isZDecimal(String str){
		if(!isValid(str)) return false;
		if(str.trim().matches("^\\d+$")){
			return true;
		}else if(str.trim().matches("^\\d+(\\.\\d{1,2})?$")){
			return true;
		}
		return false;
	}
	
	/**
	 * ��֤һ�����Ƿ�����������
	 * @author:Wangzheng
	 * @date:2009-5-6
	 * @param str
	 * @return �Ǹ�����:true ,���Ǹ�����:false
	 */
	public static boolean isZFloat(String str){
		if(!isValid(str)) return false;
		
		if(str.trim().matches("^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$")){
			return true;
		}
		return false;
	}
	/**
	 * ȥ����β�ո񣬶�������ո�ֻ����һ��
	 * @author:Shen yanyan
	 * @date:2009-3-23
	 * @param src
	 * @return
	 */
	public static String optimizeTrimSpace(String src){
		return src.trim().replaceAll("\\s+", " ");
	}
	
	/**
	 * ȥ����β�ո񣬶�������ո�ֻ����һ��
	 * @author:Shen yanyan
	 * @date:2009-3-23
	 * @param src
	 * @return
	 */
	public static boolean afterOptimizeIsNull(String src){
		String s = optimizeTrimSpace(src) ;
		return isValid(s) ;
	}
	
	/**
	 * ȥ����β�ո񣬶�������ո�ֻ����һ�����ٽ�ȡ
	 * @author:Shen yanyan
	 * @date:2009-3-23
	 * @param src
	 * @param index
	 * @return
	 */
	public static String afterOptimizeSub(String src, int index){
		return optimizeTrimSpace(src).substring(0,index) ;
	}
	
	public static boolean isNumber(String number){
		int index = number.indexOf(".");
		if(index<0){
			return StringUtils.isNumeric(number);
		}else{
			String num1 = number.substring(0,index);
			String num2 = number.substring(index+1);			
			return StringUtils.isNumeric(num1) && StringUtils.isNumeric(num2);
		}
	}	

	/**
	 * @param s_value
	 * @param delim
	 * @return
	 */
	public static synchronized String[] splitByStr(String s_value, String delim) {
		int pos = 0;
		String s_list[];

		if (s_value != null && delim != null) {

			ArrayList<String> list = new ArrayList<String>();

			pos = s_value.indexOf(delim);
			int len = delim.length();

			while (pos >= 0) {
				if (pos > 0)
					list.add(s_value.substring(0, pos));
				if ((pos + len) < s_value.length())
					s_value = s_value.substring(len + pos);
				else
					s_value = null;
				if (s_value != null)
					pos = s_value.indexOf(delim);
				else
					pos = -1;
			}
			if (s_value != null)
				list.add(s_value);
			s_list = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				s_list[i] = (String) list.get(i);
			}
		} else {
			s_list = new String[0];
		}
		return s_list;
	}
	
	public static List<String> stringSort(List<String> l) {
		 Collections.sort(l, com);
		 return l;
	}
	static Comparator<String> com=new Comparator<String>() {
		public int compare(String o1, String o2) {
			String []number= {"һ","��","��","��","��","��","��","��","��","ʮ"}; 
			   if (o1 == o2) {
                    return 0;
                }
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                o1=o1.replaceAll("Ӫ��","").replaceAll("��", "");
                o2=o2.replaceAll("Ӫ��","").replaceAll("��", "");
                if(o1.length()>o2.length()) {
                	return 1;
                }
                if(o1.length()<o2.length())return -1;
                if(o1.length()==o2.length()) {
                	for (int i = 0; i < o1.length(); i++) {
                		int numa=0;
                		int numb=0;
                		boolean a=false;
                		boolean b=false;
                		for (int j = 0; j < number.length; j++) {
							if(!a&& number[j].equals(String.valueOf(o1.charAt(i)))) {
								numa=j;a=true;
							}
							if(!b&&number[j].equals(String.valueOf(o2.charAt(i)))) {
								numb=j;b=true;
							}
							if(a&&b)break;
						}
                		if(numa==numb) {
                			if(i==o1.length()-1)return 0;
                			continue;
                		}else if(numa>numb){
                			return 1;
                		}else {
                			return -1;
                		}
					}
                }
                return 0;
		}
	};
}
