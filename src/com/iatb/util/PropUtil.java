package com.iatb.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 属性文件实用类
 * @author Administrator
 * @since 2011.05.23
 */
public class PropUtil {
	
	private final static Logger log=Logger.getLogger(PropUtil.class);
	private static Properties p;
	private static String CLASS_PATH;
	private static String CONFIG_PATH;
	private static String PROJECT_PATH;
	private static String SYSTEM_PROP;
	private static String PROJECT_PROP;
	private static int MODE=1;
	
	public static synchronized Properties getP(){
		if(p==null)
			p=new Properties();
		return p;
	}
	public static synchronized String getClassPath() {
		if(CLASS_PATH==null)
		{
			if(MODE==1)
				CLASS_PATH=System.getProperty("java.class.path").split(";")[0].replace("\\WebRoot\\WEB-INF\\classes", "\\src");
			else{//线上模式
				try {
					CLASS_PATH=PropUtil.class.getClassLoader().getResource("").toURI().getPath();
					CLASS_PATH=CLASS_PATH.substring(0,CLASS_PATH.length()-1);
				} catch (URISyntaxException e) {
					e.printStackTrace();
					return null;
				}
				
			}
		}
		return CLASS_PATH;
	}
	public static synchronized String getConfigpath() {
		if(CONFIG_PATH==null)
			CONFIG_PATH=getClassPath()+"\\config\\";
		return CONFIG_PATH;
	}
	public static synchronized String getSysprop() {
		if(SYSTEM_PROP==null)
			SYSTEM_PROP=getConfigpath()+"system.properties";
		return SYSTEM_PROP;
	}
	public static synchronized String getProjectprop() {
		if(PROJECT_PROP==null)
			PROJECT_PROP=getConfigpath()+"project.properties";
		return PROJECT_PROP;
	}
	public static synchronized String getProjectpath() {
		if(PROJECT_PATH==null)
			PROJECT_PATH=System.getProperty("user.dir");
		return PROJECT_PATH;
	}
	/**
	 * 初始化（0:线上模式、1:线下模式）
	 * @param i
	 */
	public static void init(int i){
		MODE=i;
		File f=new File(getProjectprop());
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				log.error(f.getAbsolutePath()+"创建失败！");
			}
		}
		Map<String,String> map=new HashMap<String, String>();
		map.put("class_path", getClassPath());
		map.put("project_path", getProjectpath());
		map.put("config_path", getConfigpath());
		map.put("system_prop", getSysprop());
		map.put("project_prop", getProjectprop());
		addMap(getProjectprop(), map,"初始化系统路径");
		log.info("初始化"+getProjectprop()+"完成.");
	}
	/**
	 * 获取类路径的属性文件
	 * @param srcPath
	 * @return
	 */
	public static Properties loadSrcPath(String srcPath){
		try {
			InputStream is = ClassLoader.getSystemResourceAsStream(srcPath); 
			Properties p= new Properties();
			p.load(is);
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取绝对路径的属性文件
	 * @param absPath
	 * @return
	 */
	public static Properties loadAbsPath(String absPath){
		try {
			InputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(absPath));
			} catch (FileNotFoundException e) {
				new File(absPath).createNewFile();
			}
			Properties p= new Properties();
			p.load(is);
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 向绝对路径的属性文件添加map值.
	 * @param absPath
	 * @param map
	 */
	public static void addMap(String absPath, Map<String, String> map,String comments) {
		try {
			if(!new File(absPath).exists()){
				System.out.println("文件："+absPath+"不存在！");return;
			}
			Properties p=loadAbsPath(absPath);
			int count=0;
			for(Map.Entry<String, String> e:map.entrySet()){
				if(p.getProperty(e.getKey())==null){
					count++;
					p.setProperty(e.getKey(), e.getValue());
				}
			}
			if(count>0){
				FileOutputStream fos=new FileOutputStream(new File(absPath),true);
				p.store(fos,comments);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(absPath+"文件没有发现！");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(absPath+"文件保存异常！");
		}
	}
	/**
	 * 向绝对路径的属性文件添加map值.
	 * @param absPath
	 * @param map
	 */
	public static void addOrUpdateMap(String absPath, Map<String, String> map,String comments) {
		try {
			if(!new File(absPath).exists()){
				System.out.println("文件："+absPath+"不存在！");return;
			}
			Properties p=loadAbsPath(absPath);
			for(Map.Entry<String, String> e:map.entrySet()){
				p.setProperty(e.getKey(), e.getValue());
			}
			FileOutputStream fos=new FileOutputStream(new File(absPath));
			p.store(fos,comments);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(absPath+"文件保存异常！");
		}
	}
	/**
	 * 向绝对路径的属性文件添加值.
	 * @param absPath
	 * @param key
	 * @param value
	 * @param comments
	 */
	public static void add(String absPath, String key,String value,String comments) {
		try {
			if(!new File(absPath).exists()){
				System.out.println("文件："+absPath+"不存在！");return;
			}
			Properties p=loadAbsPath(absPath);
			int count=0;
			if(p.getProperty(key)==null){
				count++;
				p.setProperty(key,value);
			}
			if(count>0){
				FileOutputStream fos=new FileOutputStream(new File(absPath));
				p.store(fos,comments);
				fos.flush();
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error(absPath+"文件没有发现！");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(absPath+"文件保存异常！");
		}
	}
	/**
	 * 向绝对路径的属性文件添加值.
	 * @param absPath
	 * @param key
	 * @param value
	 * @param comments
	 */
	public static void addOrUpdate(String absPath, String key,String value,String comments) {
		try {
			if(!new File(absPath).exists()){
				System.out.println("文件："+absPath+"不存在！");return;
			}
			Properties p=loadAbsPath(absPath);
			if(p.getProperty(key)==null|| !p.getProperty(key).equals(value))
			{
				p.setProperty(key,value);
				FileOutputStream fos=new FileOutputStream(new File(absPath));
				p.store(fos,comments);
				fos.flush();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(absPath+"文件保存异常！");
		}
	}
	/**
	 * 获取config文件夹下name文件名的属性文件
	 * @param name
	 * @return
	 */
	public static Properties getConfProp(String name){
//		return loadSrcPath("config/"+name+".properties");
		return loadAbsPath(getConfigpath()+name+".properties");
	}
	/**
	 * 拷贝类目录下的属性文件到源文件相同目录下(@测试用@)
	 */
	public static void copyToSrc(){
		File f=new File(getClassPath());
		List<File> ps=FileUtil.getSubs(f, "properties");
		for (File file : ps) {
			File f2=new File(file.getAbsolutePath().replace("WebRoot\\WEB-INF\\classes", "src"));
			if(!f2.exists()){
				try {
					f2.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					log.error("文件："+f2.getAbsolutePath()+"创建异常！");
				}
				FileUtil.copyTo(file, f2);
			}
		}
	}
	public static void main(String[] args) {
//		init(1);
//		Properties p=getConfProp("project");
//		System.out.println(p.getProperty("project_path"));
//		addKey(p, "", "");
//		System.out.println(System.getProperty("java.class.path").split(";")[0].replace("\\WebRoot\\WEB-INF\\classes", "\\src"));
		System.out.println(getConfigpath()+"fntime.properties");
		String fntime=(String) loadSrcPath("config/fntime.properties").get(getConfigpath()+"fntime.properties");
		System.out.println(fntime);
	}
}
