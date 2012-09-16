package com.iatb.util.service;





/**
 * ���Ͷ��ϵͳ�ĳ�����
 * Class Constants
 *
 * @author <a href="mailto:yangzhenguo@ufstone.com">Yang Zhenguo</a>
 * @version $Revision:1.0.0, $Date:2009-3-19 ����10:31:15 $
 */
public class Constants
{
	static PropertyManager pManager = new PropertyManager();
	
    public static final String PROPERTIE_NAME_FOR_UPLOAD = "upload.properties";
    
    public static final String PROPERTIE_SWT_PATH = "swtpath.properties";
    // �ϴ��ļ����ʸ�url
    public static final String UPLOAD_DOMAIN = pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_domain") + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_path");
    // �ϴ���·��
    public static final String UPLOAD_ROOT_PATH = pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_root_path");
    // �ϴ���·��
    public static final String UPLOAD_PATH = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "upload_path");
    public static final String DOCUPLOAD_PATH = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "docupload_path");
    public static final String DOCUPLOAD_PATHONE = UPLOAD_ROOT_PATH + pManager.getProValue(PROPERTIE_NAME_FOR_UPLOAD, "doconeupload_path");
    //����ͨ·��
    public static final String SWT_PATH = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_path");
    //����ͨUrl.Net
    public static final String SWT_URL = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_url");
    /**
     * ����ͨ���ݿ����ֺ�׺
     */
    public static final String SWT_DB_SUFFIX = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_db_suffix");
    /**
     *����ͨ���ݿ�����ǰ׺ 
     */
    public static final String SWT_DB_PREFIX = pManager.getProValue(PROPERTIE_SWT_PATH, "swt_db_prefix");
    /** ����֮��ķָ��� "||" */
	public static final String TAG_REGEX_SPLIT = "||";
    // Springcontext�������ļ���
    public static final String PROPERTIE_NAME_FOR_SPRING_CONTEXT = "config/spring.properties";
    // Stringcontext���������ļ��м�
    public static final String PROPERTIE_KEY_FOR_SPRING_CONTEXT = "spring.context.config";

    
    
}
