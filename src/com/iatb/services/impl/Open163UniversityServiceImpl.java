package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163UniversityDao;
import com.iatb.pojo.Open163University;
import com.iatb.services.Open163UniversityService;
import org.apache.log4j.Logger;

/**
 * Open163University����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-04-02 23:12:11
 */
@Service("open163UniversityService")
public class Open163UniversityServiceImpl implements Open163UniversityService {

	private static final Logger log=Logger.getLogger(Open163UniversityServiceImpl.class);
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	Open163UniversityDao open163UniversityDao;
	/** ����Open163University */	
	public boolean saveOpen163University(Open163University columns){
			return open163UniversityDao.save(columns);
	}
	/** �޸�Open163University */	
	public boolean updateOpen163University(Open163University columns){
		return open163UniversityDao.update(columns);
	}

	/** ���Open163University */
	public List<Open163University> browseOpen163University(){
		return open163UniversityDao.listAll();
	}
	
	/** ɾ��ָ����Open163University */
	public boolean deleteOpen163University(Integer id){
		return open163UniversityDao.delete(id);
	}

	/** װ��ָ����Open163University */
	public Open163University loadOpen163University(Integer id){
		return (Open163University)open163UniversityDao.load(id);
	}

	/**
	 * ����Open163University:id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163UniversityDao.updateStatus(id,status);
	}
}
