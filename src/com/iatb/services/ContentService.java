package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Content;

/**
 * Content����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2012-01-14 21:31:05
 */
public interface ContentService {
	/** ��� Content*/
	public List<Content> browseContent();
	/** װ��ָ���� Content*/
	public Content loadContent(Integer id);
	/** ɾ��ָ���� Content*/	
	public boolean deleteContent(Integer id);
	/** ���� Content*/
	public boolean saveContent(Content content);
	/** �޸� Content*/	
	public boolean updateContent(Content content);
}