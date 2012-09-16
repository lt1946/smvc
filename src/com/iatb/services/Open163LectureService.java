package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Open163Lecture;

/**
 * Open163Lecture����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-04-02 23:18:39
 */
public interface Open163LectureService {
	/** ��� Open163Lecture*/
	public List<Open163Lecture> browseOpen163Lecture();
	/** װ��ָ���� Open163Lecture*/
	public Open163Lecture loadOpen163Lecture(Integer id);
	/** ɾ��ָ���� Open163Lecture*/	
	public boolean deleteOpen163Lecture(Integer id);
	/** ���� Open163Lecture*/
	public boolean saveOpen163Lecture(Open163Lecture open163Lecture);
	/** �޸� Open163Lecture*/	
	public boolean updateOpen163Lecture(Open163Lecture open163Lecture);
	/** ����Open163Lecture:id��״̬ */
	public void updateStatus(int id,int status);
}