package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Newscolumns;

/**
 * Newscolumns����ҵ���߼��ӿ�
 * @author Administrator
 * @since  2011-12-22 16:26:19
 */
public interface NewscolumnsService {
	/** ��� Newscolumns*/
	public List<Newscolumns> browseNewscolumns();
	/** װ��ָ���� Newscolumns*/
	public Newscolumns loadNewscolumns(Integer id);
	/** ɾ��ָ���� Newscolumns*/	
	public boolean deleteNewscolumns(Integer id);
	/** ���� Newscolumns*/
	public boolean saveNewscolumns(Newscolumns newscolumns);
	/** �޸� Newscolumns*/	
	public boolean updateNewscolumns(Newscolumns newscolumns);
	
}