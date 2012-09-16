package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.NewscolumnsDao;
import com.iatb.pojo.Newscolumns;
import com.iatb.services.NewscolumnsService;

/**
 * Newscolumns����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2011-12-22 17:17:13
 */
@Service("newscolumnsService")
public class NewscolumnsServiceImpl implements NewscolumnsService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	NewscolumnsDao newscolumnsDao;
	/** ����Newscolumns */	
	public boolean saveNewscolumns(Newscolumns columns){
			return newscolumnsDao.save(columns);
	}
	/** �޸�Newscolumns */	
	public boolean updateNewscolumns(Newscolumns columns){ 
		return newscolumnsDao.update(columns);
	}

	/** ���Newscolumns */
	public List<Newscolumns> browseNewscolumns(){
		return newscolumnsDao.listAll();
	}
	/** һ��������Ŀ�б� */
	public List<Newscolumns> listNewscolumns(){
		return  newscolumnsDao.list("select * from newscolumns as a where a.newscolumns is null",new Object[]{});
	}
	
	/** �¼�������Ŀ�б� */
	@SuppressWarnings("unchecked")
	public List<Newscolumns> listChildColumns(Newscolumns columns){
		if (columns==null){
			return null;
		}else{
			return newscolumnsDao.list("select * from newscolumns as a where a.newscolumns.id=?",new Object[]{columns.getID()});
		}		
	}
	
	/** ɾ��ָ����Newscolumns */
	public boolean deleteNewscolumns(Integer id){
		return newscolumnsDao.delete(id);
	}

	/** װ��ָ����Newscolumns */
	public Newscolumns loadNewscolumns(Integer id){
		return (Newscolumns)newscolumnsDao.load(id);
	}

}
