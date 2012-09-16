package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163University;

/**
 * Open163University����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163UniversityService {
	/** ��� Open163University*/
	public List<Open163University> browseOpen163University();
	/** װ��ָ���� Open163University*/
	public Open163University loadOpen163University(Integer id);
	/** ɾ��ָ���� Open163University*/	
	public boolean deleteOpen163University(Integer id);
	/** ���� Open163University*/
	public boolean saveOpen163University(Open163University open163University);
	/** �޸� Open163University*/	
	public boolean updateOpen163University(Open163University open163University);
	/** ����Open163University:id��״̬ */
	public void updateStatus(int id,int status);
}