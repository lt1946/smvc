package com.iatb.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.iatb.dao.ContentDao;
import com.iatb.pojo.Content;
import com.iatb.services.ContentService;

/**
 * Content����ҵ���߼��ӿ�ʵ��
 * @author Administrator
 * @since  2012-01-14 21:31:05
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
	/** ͨ������ע��DAO���ʵ�� */
	@Autowired
	ContentDao contentDao;
	/** ����Content */	
	public boolean saveContent(Content columns){
			return contentDao.save(columns);
	}
	/** �޸�Content */	
	public boolean updateContent(Content columns){
		return contentDao.update(columns);
	}

	/** ���Content */
	public List<Content> browseContent(){
		return contentDao.listAll();
	}
	
	/** ɾ��ָ����Content */
	public boolean deleteContent(Integer id){
		return contentDao.delete(id);
	}

	/** װ��ָ����Content */
	public Content loadContent(Integer id){
		return (Content)contentDao.load(id);
	}

}
