package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Openclasstype;

/**
 * Open163Openclasstype����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163OpenclasstypeService {
	/** ��� Open163Openclasstype*/
	public List<Open163Openclasstype> browseOpen163Openclasstype();
	/** װ��ָ���� Open163Openclasstype*/
	public Open163Openclasstype loadOpen163Openclasstype(Integer id);
	/** ɾ��ָ���� Open163Openclasstype*/	
	public boolean deleteOpen163Openclasstype(Integer id);
	/** ���� Open163Openclasstype*/
	public boolean saveOpen163Openclasstype(Open163Openclasstype open163Openclasstype);
	/** �޸� Open163Openclasstype*/	
	public boolean updateOpen163Openclasstype(Open163Openclasstype open163Openclasstype);
	/** ����Open163Openclasstype:id��״̬ */
	public void updateStatus(int id,int status);
}