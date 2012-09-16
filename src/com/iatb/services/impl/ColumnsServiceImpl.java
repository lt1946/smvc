package com.iatb.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iatb.dao.NewscolumnsDao;
import com.iatb.pojo.Newscolumns;
import com.iatb.services.NewscolumnsService;

/** ������Ŀ����ҵ���߼��ӿ�ʵ�� */
@Service("newscolumnsService")
public class ColumnsServiceImpl implements NewscolumnsService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	NewscolumnsDao newscolumnsDao;
	/** ����������Ŀ */	
	public boolean saveNewscolumns(Newscolumns columns){
			return newscolumnsDao.save(columns);
	}
	/** �޸�������Ŀ */	
	public boolean updateNewscolumns(Newscolumns columns){
		return newscolumnsDao.update(columns);
	}

	/** ���������Ŀ */
	public List<Newscolumns> browseNewscolumns(){
		return newscolumnsDao.listAll();
	}
	
	/** һ��������Ŀ�б� */
	/*public List<Newscolumns> listNewscolumns(){
//		return newscolumnsDao.listAll();
		return  newscolumnsDao.list("select * from newscolumns as a where a.newscolumns is null",new Object[]{});
	}*/
	
	/** �¼�������Ŀ�б� */
	/*@SuppressWarnings("unchecked")
	public List<Newscolumns> listChildColumns(Newscolumns columns){
		if (columns==null){
			return null;
		}else{
			return newscolumnsDao.query("from newscolumns as a where a.newscolumns.id="+columns.getId());
		}		
	}*/

	/** ɾ��ָ����������Ŀ */
	public boolean deleteNewscolumns(Integer id){
		return newscolumnsDao.delete(id);
	}

	/** װ��ָ����������Ŀ */
	public Newscolumns loadNewscolumns(Integer id){
		return (Newscolumns)newscolumnsDao.load(id);
	}

}
