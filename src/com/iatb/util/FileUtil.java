package com.iatb.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.FileCopyUtils;

public class FileUtil {
	public final static Logger log=Logger.getLogger(FileUtil.class);

	/**
	 * ��ȡ�ļ���f�µ�����Ŀ¼�ļ�����
	 * @param lf
	 * @param f
	 * @return
	 */
	public static List<File> getDirectorys(List<File> lf,File f){
		List<File> fs=new ArrayList<File>();
		File fs2[]=f.listFiles(new FilenameFilter(){
			public boolean accept(File dir, String name) {
				if(dir.isDirectory())return true;
				return false;
			}
		});
		if(lf!=null){
			if(fs2!=null&&fs2.length!=0)lf.addAll(Arrays.asList(fs2));
			fs=lf;
		}else {
			if(fs2!=null&&fs2.length!=0)fs.addAll(Arrays.asList(fs2));
		}
		if(fs2!=null&&fs2.length!=0)
		for (File ff : fs2) {
			getDirectorys(fs, ff);
		}
		return fs;
	}

	/**
	 * ��ȡ�ļ���f�º�׺subs�������ļ�����
	 * @param f
	 * @param subs
	 * @return
	 */
	public static List<File> getSubs(File f,final String subs){
		List<File> ps=new ArrayList<File>();
		List<File> fs=getDirectorys(null, f);
		for (File ff : fs) {
			File fss[]=ff.listFiles(new FilenameFilter(){
				public boolean accept(File dir, String name) {
					if(name.endsWith(subs))return true;
					return false;
				}
			});
			if(fss!=null)
			ps.addAll(Arrays.asList(fss));
		}
		return ps;
	}
	/**
	 * �ļ��Կ�
	 * @param infile
	 * @param outfile
	 */
	public static void copyTo(File infile,File outfile){
		try {
			FileCopyUtils.copy(infile, outfile);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("��ԭ�ļ���"+infile.getAbsolutePath()+"��Ŀ���ļ���"+outfile.getAbsolutePath()+"�����쳣��");
		}
	}
	/**
	 * ��ȡ�ļ����޸�ʱ��
	 * @param f	�ļ�File
	 * @return
	 */
	public static Date getModifTime(File f){
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(f.lastModified());
		return c.getTime();
	}
	/**
	 * ��ȡ�ļ����޸�ʱ��
	 * @param f	�ļ�File
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getModifString(File f){
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(f.lastModified());
		return c.getTime().toLocaleString();
	}
	public static boolean isExists(String absfile){
		return new File(absfile).exists();
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String t=getModifTime(new File("D:\\java\\tool\\Genuitec\\workspace\\SpringMVC\\src\\config\\fntime.properties")).toLocaleString();
		System.out.println(t);
	}
}
