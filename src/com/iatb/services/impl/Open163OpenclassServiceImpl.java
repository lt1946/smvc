package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.Open163OpenclassDao;
import com.iatb.pojo.Open163Openclass;
import com.iatb.services.Open163OpenclassService;
import org.apache.log4j.Logger;

/**
 * Open163Openclass����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-04-02 23:12:10
 */
@Service("open163OpenclassService")
public class Open163OpenclassServiceImpl implements Open163OpenclassService {

	private static final Logger log=Logger.getLogger(Open163OpenclassServiceImpl.class);
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	Open163OpenclassDao open163OpenclassDao;
	/** ����Open163Openclass */	
	public boolean saveOpen163Openclass(Open163Openclass columns){
			return open163OpenclassDao.save(columns);
	}
	/** �޸�Open163Openclass */	
	public boolean updateOpen163Openclass(Open163Openclass columns){
		return open163OpenclassDao.update(columns);
	}

	/** ���Open163Openclass */
	public List<Open163Openclass> browseOpen163Openclass(){
		return open163OpenclassDao.listAll();
	}
	
	/** ɾ��ָ����Open163Openclass */
	public boolean deleteOpen163Openclass(Integer id){
		return open163OpenclassDao.delete(id);
	}

	/** װ��ָ����Open163Openclass */
	public Open163Openclass loadOpen163Openclass(Integer id){
		return (Open163Openclass)open163OpenclassDao.load(id);
	}

	/**
	 * ����Open163Openclass:id��״̬
	 * @param id
	 * @param status
	 */
	public void updateStatus(int id,int status){
		open163OpenclassDao.updateStatus(id,status);
	}
}
