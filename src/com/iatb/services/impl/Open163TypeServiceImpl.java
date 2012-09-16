package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163TypeDao;
import com.iatb.pojo.Open163Type;
import com.iatb.services.Open163TypeService;
import org.apache.log4j.Logger;

/**
 * Open163Type����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-04-02 23:12:11
 */
@Service("open163TypeService")
public class Open163TypeServiceImpl implements Open163TypeService {

	private static final Logger log=Logger.getLogger(Open163TypeServiceImpl.class);
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	Open163TypeDao open163TypeDao;
	/** ����Open163Type */	
	public boolean saveOpen163Type(Open163Type columns){
			return open163TypeDao.save(columns);
	}
	/** �޸�Open163Type */	
	public boolean updateOpen163Type(Open163Type columns){
		return open163TypeDao.update(columns);
	}

	/** ���Open163Type */
	public List<Open163Type> browseOpen163Type(){
		return open163TypeDao.listAll();
	}
	
	/** ɾ��ָ����Open163Type */
	public boolean deleteOpen163Type(Integer id){
		return open163TypeDao.delete(id);
	}

	/** װ��ָ����Open163Type */
	public Open163Type loadOpen163Type(Integer id){
		return (Open163Type)open163TypeDao.load(id);
	}

	/**
	 * ����Open163Type:id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163TypeDao.updateStatus(id,status);
	}
}
