package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Openclass;

/**
 * Open163Openclass����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163OpenclassService {
	/** ��� Open163Openclass*/
	public List<Open163Openclass> browseOpen163Openclass();
	/** װ��ָ���� Open163Openclass*/
	public Open163Openclass loadOpen163Openclass(Integer id);
	/** ɾ��ָ���� Open163Openclass*/	
	public boolean deleteOpen163Openclass(Integer id);
	/** ���� Open163Openclass*/
	public boolean saveOpen163Openclass(Open163Openclass open163Openclass);
	/** �޸� Open163Openclass*/	
	public boolean updateOpen163Openclass(Open163Openclass open163Openclass);
	/** ����Open163Openclass:id��״̬ */
	public void updateStatus(int id,int status);
}