package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Type;

/**
 * Open163Type����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163TypeService {
	/** ��� Open163Type*/
	public List<Open163Type> browseOpen163Type();
	/** װ��ָ���� Open163Type*/
	public Open163Type loadOpen163Type(Integer id);
	/** ɾ��ָ���� Open163Type*/	
	public boolean deleteOpen163Type(Integer id);
	/** ���� Open163Type*/
	public boolean saveOpen163Type(Open163Type open163Type);
	/** �޸� Open163Type*/	
	public boolean updateOpen163Type(Open163Type open163Type);
	/** ����Open163Type:id��״̬ */
	public void updateStatus(int id,int status);
}