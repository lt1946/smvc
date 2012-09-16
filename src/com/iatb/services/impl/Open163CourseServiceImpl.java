package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163CourseDao;
import com.iatb.pojo.Open163Course;
import com.iatb.services.Open163CourseService;
import org.apache.log4j.Logger;

/**
 * Open163Course����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163CourseService")
public class Open163CourseServiceImpl implements Open163CourseService {

	private static final Logger log=Logger.getLogger(Open163CourseServiceImpl.class);
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	Open163CourseDao open163CourseDao;
	/** ����Open163Course */	
	public boolean saveOpen163Course(Open163Course columns){
			return open163CourseDao.save(columns);
	}
	/** �޸�Open163Course */	
	public boolean updateOpen163Course(Open163Course columns){
		return open163CourseDao.update(columns);
	}

	/** ���Open163Course */
	public List<Open163Course> browseOpen163Course(){
		return open163CourseDao.listAll();
	}
	
	/** ɾ��ָ����Open163Course */
	public boolean deleteOpen163Course(Integer id){
		return open163CourseDao.delete(id);
	}

	/** װ��ָ����Open163Course */
	public Open163Course loadOpen163Course(Integer id){
		return (Open163Course)open163CourseDao.load(id);
	}

	/**
	 * ����Open163Course:id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163CourseDao.updateStatus(id,status);
	}
}
