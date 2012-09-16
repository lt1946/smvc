package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163LectureDao;
import com.iatb.pojo.Open163Lecture;
import com.iatb.services.Open163LectureService;
import org.apache.log4j.Logger;

/**
 * Open163Lecture����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163LectureService")
public class Open163LectureServiceImpl implements Open163LectureService {

	private static final Logger log=Logger.getLogger(Open163LectureServiceImpl.class);
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	Open163LectureDao open163LectureDao;
	/** ����Open163Lecture */	
	public boolean saveOpen163Lecture(Open163Lecture columns){
			return open163LectureDao.save(columns);
	}
	/** �޸�Open163Lecture */	
	public boolean updateOpen163Lecture(Open163Lecture columns){
		return open163LectureDao.update(columns);
	}

	/** ���Open163Lecture */
	public List<Open163Lecture> browseOpen163Lecture(){
		return open163LectureDao.listAll();
	}
	
	/** ɾ��ָ����Open163Lecture */
	public boolean deleteOpen163Lecture(Integer id){
		return open163LectureDao.delete(id);
	}

	/** װ��ָ����Open163Lecture */
	public Open163Lecture loadOpen163Lecture(Integer id){
		return (Open163Lecture)open163LectureDao.load(id);
	}

	/**
	 * ����Open163Lecture:id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163LectureDao.updateStatus(id,status);
	}
}
