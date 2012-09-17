package com.iatb.bean;

public class GBean {
	
	/**禁用**/public final static Integer STATUS_STOP=1;								
	/**启用**/public final static Integer STATUS_NORMAL=0;						
	/**已经抓取**/public final static Integer STATUS_ALREALDYSIPDER=10;			
	/**没有添加api**/public final static Integer STATUS_NOADDAPI=11;					

	/**已经验证**/public final static Integer CHECK_NEED=1;							
	/**未验证**/public final static Integer CHECK_DONTNEED=0;						
	/**验证失败**/public final static Integer CHECK_FAIL=2;								

	/**可以邀请**/public final static Integer INVITE_OK=1;									
	/**不可以邀请**/public final static Integer INVITE_NO=0;									

	/**有下线**/public final static Integer DOWNLINE_YES=1;							
	/**没有下线**/public final static Integer DOWNLINE_NO=0;							
	/**下线失败**/public final static Integer DOWNLINE_FAIL=2;							
	
	/**已经注册**/public final static Integer REG_ALREALDY=1;							
	/**未注册**/	public final static Integer REG_NO=0;										
	/**注册失败**/public final static Integer REG_FAIL=2;									

	/**最低级的站点**/public final static Integer LEVEL_LOWEST=-10;						
	
	/**API正常**/public final static Integer API_HEALTH_NORMAL=0;				
	/**API不能打开次数-1**/public final static Integer API_HEALTH_NOPEN=-1;			
	
//	/**API没有商品次数-1**/public final static Integer API_GOODS_NORMAL=0;			
	/**API没有商品次数-1**/public final static Integer API_GOODS_NO=-1;			
	/**API有商品次数1**/public final static Integer API_GOODS_OK=1;		
	
	/**api抓取规则未开发 **/public final static Integer API_STATUS_NODEVELOPER=11;
	/**api今天已经抓取 **/public static final int API_STATUS_ALREADLYSPIDER = 10;								
	
}
