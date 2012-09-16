package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Course;

/**
 * Open163Course����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163CourseService {
	/** ��� Open163Course*/
	public List<Open163Course> browseOpen163Course();
	/** װ��ָ���� Open163Course*/
	public Open163Course loadOpen163Course(Integer id);
	/** ɾ��ָ���� Open163Course*/	
	public boolean deleteOpen163Course(Integer id);
	/** ���� Open163Course*/
	public boolean saveOpen163Course(Open163Course open163Course);
	/** �޸� Open163Course*/	
	public boolean updateOpen163Course(Open163Course open163Course);
	/** ����Open163Course:id��״̬ */
	public void updateStatus(int id,int status);
}