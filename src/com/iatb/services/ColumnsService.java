package com.iatb.services;

import java.util.List;
import com.iatb.pojo.Newscolumns;

/** ������Ŀ����ҵ���߼��ӿ� */
public interface ColumnsService {
	/** ���������Ŀ */
	public List<Newscolumns> browseColumns();
	/** һ��������Ŀ�б� */
	public List<Newscolumns> listColumns();	
	/** �¼�������Ŀ�б� */
	public List<Newscolumns> listChildColumns(Newscolumns columns);		
	/** װ��ָ����������Ŀ */	
	public Newscolumns loadColumns(Integer id);	
	/** ɾ��ָ����������Ŀ */	
	public boolean delColumns(Integer id);	
	/** ����������Ŀ */	
	public boolean saveColumns(Newscolumns columns);
	/** �޸�������Ŀ */	
	public boolean updateColumns(Newscolumns columns);
}
