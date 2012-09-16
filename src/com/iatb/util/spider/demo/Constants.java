package com.iatb.util.spider.demo;

import com.iatb.util.service.PropertyManager;

//import org.junit.Test;


/**
 * 广告投放系统的常量类
 * Class Constants
 *
 * @author <a href="mailto:yangzhenguo@ufstone.com">Yang Zhenguo</a>
 * @version $Revision:1.0.0, $Date:2009-3-19 上午10:31:15 $
 */
public class Constants
{
	static PropertyManager pManager = new PropertyManager();
	
    public static final String PROPERTIE_NAME_FOR_UPLOAD = "upload.properties";
    public static final String PROPERTIE_SWT_PATH = "swtpath.properties";
    public static final String PROPERTIE_ERROR_URL = "errorurl.properties";
    // 上传文件访问跟url
    public static final String UPLOAD_DOMAIN = pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_domain") + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_path");
    // 上传跟路径
    public static final String UPLOAD_ROOT_PATH = pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_root_path");
    // 上传的路径
    public static final String UPLOAD_PATH = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_path");
    public static final String DOCUPLOAD_PATH = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "docupload_path");
    public static final String DOCUPLOAD_PATHONE = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "doconeupload_path");
    //商务通路径
    public static final String SWT_PATH = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_path");
    //商务通Url.Net
    public static final String SWT_URL = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_url");
    //商务通oa
    public static final String SWT_OA=pManager.getProValue(PROPERTIE_SWT_PATH, "swt_oa");
    /**
     * 商务通数据库名字后缀
     */
    public static final String SWT_DB_SUFFIX = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_db_suffix");
    /**
     *商务通数据库名字前缀 
     */
    public static final String SWT_DB_PREFIX = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_db_prefix");
    /** 正则之间的分隔符 "||" */
	public static final String TAG_REGEX_SPLIT = "||";
    
//    @Test
//    public void test(){
//    	System.out.println(Constants.UPLOAD_DOMAIN 
//    			+ "\n" + Constants.UPLOAD_PATH);
//    }
    
    // Springcontext的属性文件名
    public static final String PROPERTIE_NAME_FOR_SPRING_CONTEXT = "spring_context_oa.properties";
    // Stringcontext工厂属性文件中键
    public static final String PROPERTIE_KEY_FOR_SPRING_CONTEXT = "spring.context.config";

    //网页错误显示代码
    public static final String URL_ERROR_403=pManager.getProValue(PROPERTIE_ERROR_URL,"403");
    
}
