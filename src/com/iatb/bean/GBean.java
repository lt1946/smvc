package com.iatb.bean;

public class GBean {
	
	/**����**/public final static Integer STATUS_STOP=1;								
	/**����**/public final static Integer STATUS_NORMAL=0;						
	/**�Ѿ�ץȡ**/public final static Integer STATUS_ALREALDYSIPDER=10;			
	/**û�����api**/public final static Integer STATUS_NOADDAPI=11;					

	/**�Ѿ���֤**/public final static Integer CHECK_NEED=1;							
	/**δ��֤**/public final static Integer CHECK_DONTNEED=0;						
	/**��֤ʧ��**/public final static Integer CHECK_FAIL=2;								

	/**��������**/public final static Integer INVITE_OK=1;									
	/**����������**/public final static Integer INVITE_NO=0;									

	/**������**/public final static Integer DOWNLINE_YES=1;							
	/**û������**/public final static Integer DOWNLINE_NO=0;							
	/**����ʧ��**/public final static Integer DOWNLINE_FAIL=2;							
	
	/**�Ѿ�ע��**/public final static Integer REG_ALREALDY=1;							
	/**δע��**/	public final static Integer REG_NO=0;										
	/**ע��ʧ��**/public final static Integer REG_FAIL=2;									

	/**��ͼ���վ��**/public final static Integer LEVEL_LOWEST=-10;						
	
	/**API����**/public final static Integer API_HEALTH_NORMAL=0;				
	/**API���ܴ򿪴���-1**/public final static Integer API_HEALTH_NOPEN=-1;			
	
//	/**APIû����Ʒ����-1**/public final static Integer API_GOODS_NORMAL=0;			
	/**APIû����Ʒ����-1**/public final static Integer API_GOODS_NO=-1;			
	/**API����Ʒ����1**/public final static Integer API_GOODS_OK=1;		
	
	/**apiץȡ����δ���� **/public final static Integer API_STATUS_NODEVELOPER=11;
	/**api�����Ѿ�ץȡ **/public static final int API_STATUS_ALREADLYSPIDER = 10;								
	
}
