package com.iatb.util;

import java.io.UnsupportedEncodingException;


public class StringUtil {
	
	/**
	 * 判断字符串like是否都在content里
	 * @param content
	 * @param like
	 * @return
	 */
	public static boolean like(String content,String like){
		String l[]=like.split("[|]");
		for (String s : l) {
			if(content.indexOf(s)==-1)return false;
		}
		return true;
	}
	/**
	 * 用空格填充name总长度为total
	 * @param total
	 * @param name
	 * @return
	 */
	public static String space(int total,String name){
		StringBuilder s=new StringBuilder(name);
		String a=" ";
		for (int i = name.length()-1; i < total; i++) {
			s.append(a);
		}
		return s.toString();
	}
	/**
	 * 按len长度分割字符串s
	 * @param s
	 * @param len
	 * @return
	 */
	public static StringBuffer formateContent(String s,int len){
		String ss[]=s.split("\n");
		StringBuffer sb=new StringBuffer("");
		String pre="  ";
		for (int i = 0; i < ss.length; i++) {
			String l=ss[i].trim();
			if(l.equals(""))continue;
			if(!l.startsWith("  "))l=pre+l;
			if(l.length()>len){
				int lens=l.length()/len>0?(l.length()%len==0?l.length()/len:l.length()/len+1):1;
				for (int j = 0; j < lens; j++) {
					if(j==lens-1){
						sb.append(l.substring(((lens-1)*len))).append("\n");
					}else{
						sb.append(l.substring(j*len+1, (j+1)*len+1)).append("\n");
					}
				}
			}else{
				sb.append(l).append("\n");
			}
		}
		return sb;
	}
	//
	public static String formateCode(){
		return null;
	}
	//装配get()字段方法
	public static String getString(String[] s,String bean){
		StringBuffer sb=new StringBuffer("");
		String a=bean+".get";String b="(),";
		for (int i = 0; i <s.length; i++) {
			sb.append(a+s[i].substring(0,1).toUpperCase()+s[i].substring(1)+b);
		}
		a=sb.toString();
		a=sb.substring(0,a.length()-1);
		return a;
	}
	public static void testDBString(){
		String s="id,siteName,siteUrl,loginUrl,signupUrl,inviteURl,apiUrl,isReg,isApi,isDownLine,isInvite,isCheck,createTime,status";
		String get[]=s.split(",");
		for (int i = 0; i < get.length; i++) {
//			case 1:name="`name`";break;
			System.out.println("case "+(i+1)+":name=\"`"+get[i]+"`\";break;");
		}
		/*System.out.println(s.split(",").length);
		StringBuffer sb=new StringBuffer("");
		String a="?,";
		for (int i = 0; i <19; i++) {
			sb.append(a);
		}
		System.out.println(sb.toString());
		 */
//		map.get("urlUnLike");
//		String get[]=s.split(",");
		
	/*	for (int i = 0; i < 19; i++) {
			System.out.println("System.out.println(\""+get[i].trim()+":\"+map.get(\""+get[i].trim()+"\"));");
		}*/
//	"Commend:\t1.name\t2.desc(like)\t3.remark(like)\t4.type(like)\t5.#up\t6.#exit");
	/*	for (int i = 0; i <19; i++) {
			System.out.print("\\t"+(i+1)+"."+get[i]);
			if((1+i)%8==0)System.out.println();
		}*/
//	case 4:len=20;name="type";break;
//		System.out.println("[id:"+map.get("id")+"]");
		/*for (int i = 0; i < 19; i++) {
			System.out.println("	System.out.println(\"["+get[i].trim()+":\"+map.get(\""+get[i].trim()+"\")+\"]\");");
		}*/
	}
	public static void main(String[] args) {
		testDBString();
	/*	Content c=ContentDaoImpl.queryById(1575).get(0);
		StringBuffer sb=rightContent(c.getContent(), 85);
		System.out.println(sb.toString());*/
		System.out.println(getFileName("?*或+正则表达式使用详解.txt文"));
	}
	//过滤文件名
	public static String getFileName(String name){
		while(true){
			if(name.indexOf(":")>=0){
				name=name.replace(":", "：");
			}else if(name.indexOf("*")>=0){
				name=name.replace("*", "×");
			}else if(name.indexOf("\"")>=0){
				name=name.replace("\"", "'");
			}else if(name.indexOf("|")>=0){
				name=name.replace("|", "`");
			}else if(name.indexOf("\\")>=0){
				name=name.replace("\\", " ");
			}else if(name.indexOf("/")>=0){
				name=name.replace("/", " ");
			}else if(name.indexOf("?")>=0){
				name=name.replace("?", "？");
			}else if(name.indexOf("<")>=0){
				name=name.replace("<", "《");
			}else if(name.indexOf(">")>=0){
				name=name.replace(">", "》");
			}else break;
		}
		return name;
	}
	/**
	 * 去除末尾的字符串
	 * @param zipname
	 * @param string
	 * @return
	 */
	public static String removeEnd(String zipname, String string) {
		return zipname.endsWith(string)?zipname.substring(0,zipname.lastIndexOf(string)):zipname;
	}
	/**
	 * iso-8859-1 转 gbk
	 * @param t
	 * @return
	 */
	public static String iso2gbk(String t){
		try {
			return new String(t.getBytes("iso-8859-1"),"gbk");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
